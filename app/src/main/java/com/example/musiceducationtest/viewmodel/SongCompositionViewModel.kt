package com.example.musiceducationtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.musiceducationtest.helper.MediaPlayerHelper
import com.example.musiceducationtest.model.BlockDataModel
import com.example.musiceducationtest.model.BlockType
import com.example.musiceducationtest.repository.LessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SongCompositionViewModel @Inject constructor(
    private val lessonRepository: LessonRepository,
    application: Application
) : AndroidViewModel(application) {
    private val mediaPlayerHelper = MediaPlayerHelper(application)
    private var currentlyPlayingMusic: Int? = null // 現在再生中の曲
    private val _firstFlowChartBlock = MutableStateFlow<BlockDataModel?>(null)
    private val _selectedBlock = MutableStateFlow<BlockDataModel?>(null)
    private val _isPlaying = MutableStateFlow(false)
    private val _isPlayingFlowChart = MutableStateFlow(false)
    private val _flowChartBlocks = MutableStateFlow<List<BlockDataModel>>(emptyList())
    private val _showDialog = MutableStateFlow(false)
    private val _isCorrectOrder = MutableStateFlow<Boolean?>(null)
    private val _currentlyPlayingBlock = MutableStateFlow<BlockDataModel?>(null)
    private val _previousBlock = MutableStateFlow<BlockDataModel?>(null)

    val selectedBlock: StateFlow<BlockDataModel?> = _selectedBlock
    val isPlaying: StateFlow<Boolean> = _isPlaying // 再生しているかどうかを保持
    val isPlayingFlowChart: StateFlow<Boolean> = _isPlayingFlowChart // 再生しているかどうかを保持
    val flowChartBlocks: StateFlow<List<BlockDataModel>> = _flowChartBlocks
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow() // ダイアログ表示の状態を管理
    val isCorrectOrder: StateFlow<Boolean?> = _isCorrectOrder
    val currentlyPlayingBlock: StateFlow<BlockDataModel?> = _currentlyPlayingBlock.asStateFlow()
    val previousBlock: StateFlow<BlockDataModel?> = _previousBlock.asStateFlow()

    // 答えあわせ
    fun checkAnswer(lessonId: String) {
        val lesson = lessonRepository.getLessonById(lessonId)
        val correctMusicResIds = lesson?.answers?.map { it.musicResId }
        val currentMusicResIds = _flowChartBlocks.value.map { it.musicResId }

        _isCorrectOrder.value = correctMusicResIds == currentMusicResIds
    }

    // 選択されたレッスンに基づいてフローチャートを初期化
    fun initializeFlowChart(lessonId: String) {
        val lesson = lessonRepository.getLessonById(lessonId)
        _flowChartBlocks.value = emptyList()
        lesson?.let {
            _firstFlowChartBlock.value = lesson.firstFlowChartBlock
            val initialBlocks = listOf(lesson.firstFlowChartBlock)// + lesson.flowChartBlocks
            _flowChartBlocks.value = initialBlocks
        }
        mediaPlayerHelper.togglePlayPause(isPlaying = true)
        _isPlaying.value = false
        _isPlayingFlowChart.value = false
        _selectedBlock.value = null
        _currentlyPlayingBlock.value = null
    }


    // 選択したブロックをフローチャートに追加
    fun addToFlowChart() {
        selectedBlock.value?.let { block ->
            // ブロックがフローチャートに既に存在しないことを確認
            val isNotAlreadyInChart = !_flowChartBlocks.value.contains(block)

            // フローチャートの最初のブロックではないことを確認
            val isNotFirstBlock = block != _firstFlowChartBlock.value

            // REPEAT_END を追加する場合、REPEAT_START が既にリストに存在するか確認
            val isRepeatEndValid = block.type != BlockType.REPEAT_END ||
                    _flowChartBlocks.value.any { it.type == BlockType.REPEAT_START }

            if (isNotAlreadyInChart && isNotFirstBlock && isRepeatEndValid) {
                _flowChartBlocks.value = _flowChartBlocks.value + block

                // REPEAT_END ブロックが追加された場合、その一つ前のブロックを保持
                if (block.type == BlockType.REPEAT_END && _flowChartBlocks.value.size > 1) {
                    val previousBlock = _flowChartBlocks.value[_flowChartBlocks.value.size - 2]
                    _previousBlock.value = previousBlock
                }
            }
        }
        mediaPlayerHelper.togglePlayPause(isPlaying = true)
        _isPlaying.value = false
        _isPlayingFlowChart.value = false
        _selectedBlock.value = null
        _currentlyPlayingBlock.value = null
    }


    // 選択肢ブロックを選択
    fun selectBlock(block: BlockDataModel) {
        _selectedBlock.value = block
    }

    // フローチャートの要素を一つ削除
    fun removeFromFlowChart() {
        if (_flowChartBlocks.value.size > 1) {
            // リストに2つ以上の要素がある場合のみ、最後の要素を削除
            _flowChartBlocks.value = _flowChartBlocks.value.dropLast(1)
        }
        mediaPlayerHelper.togglePlayPause(isPlaying = true)
        _isPlaying.value = false
        _isPlayingFlowChart.value = false
        _selectedBlock.value = null
        _currentlyPlayingBlock.value = null
    }

    // フローチャートの再生ボタンが押された時の処理
    fun startFlowChartMusic() {
        if (_isPlayingFlowChart.value) {
            // 既に再生中の場合は再生を停止
            mediaPlayerHelper.releaseMediaPlayer() // MediaPlayerをリリース
            _isPlayingFlowChart.value = false
            _currentlyPlayingBlock.value = null
        } else {
            // まだ再生していない場合は、フローチャートの音楽を再生開始
            val blocks = _flowChartBlocks.value
            if (blocks.isNotEmpty()) {
                playMusicSequence(blocks, 0)
                _isPlayingFlowChart.value = true
                _isPlaying.value = false
                _selectedBlock.value = null
            }
        }
    }

    // フローチャートの曲を再生
    private fun playMusicSequence(blocks: List<BlockDataModel>, index: Int) {
        if (index < blocks.size) {
            val block = blocks[index]
            when (block.type) {
                BlockType.REPEAT_START -> {
                    val endIndex = findRepeatEndIndex(blocks, index)
                    val repeatBlocks = blocks.subList(index + 1, endIndex)
                    playRepeatSequence(repeatBlocks, 0, 2) {
                        playMusicSequence(blocks, endIndex + 1)
                    }
                }

                else -> {
                    _currentlyPlayingBlock.value = block
                    playBlock(block) {
                        playMusicSequence(blocks, index + 1)
                    }
                }
            }
        } else {
            _isPlayingFlowChart.value = false
            _currentlyPlayingBlock.value = null
        }
    }

    private fun playBlock(block: BlockDataModel, onCompletion: () -> Unit) {
        block.musicResId?.let { musicResId ->
            mediaPlayerHelper.initializeMediaPlayer(musicResId)
        }
        mediaPlayerHelper.setOnCompletionListener {
            onCompletion()
        }
        mediaPlayerHelper.togglePlayPause(isPlaying = false)
    }

    private fun playRepeatSequence(
        repeatBlocks: List<BlockDataModel>,
        index: Int,
        repeatCount: Int,
        onCompletion: () -> Unit
    ) {
        if (index < repeatBlocks.size * repeatCount) {
            val actualIndex = index % repeatBlocks.size
            val block = repeatBlocks[actualIndex]
            _currentlyPlayingBlock.value = block
            playBlock(block) {
                playRepeatSequence(repeatBlocks, index + 1, repeatCount, onCompletion)
            }
        } else {
            onCompletion()
        }
    }

    private fun findRepeatEndIndex(blocks: List<BlockDataModel>, startIndex: Int): Int {
        return blocks.subList(startIndex + 1, blocks.size)
            .indexOfFirst { it.type == BlockType.REPEAT_END } + startIndex + 1
    }


    // 選択肢ブロックの音楽を再生するメソッド
    fun playBlockMusic(musicResId: Int) {
        if (currentlyPlayingMusic == musicResId && mediaPlayerHelper.isPlaying()) {
            // 再生中かつ、同じブロックが再度クリックされた場合、再生を停止
            mediaPlayerHelper.togglePlayPause(isPlaying = true)
            currentlyPlayingMusic = null // 現在のブロックをリセット
            _isPlaying.value = false // 停止状態にする
        } else {
            // 異なるブロックがクリックされた場合、新しい曲の再生を始める
            mediaPlayerHelper.initializeMediaPlayer(musicResId) // 新しい音楽リソースでMediaPlayerを初期化
            mediaPlayerHelper.togglePlayPause(isPlaying = false) // 再生開始
            currentlyPlayingMusic = musicResId // 現在再生中のブロックに更新
            _isPlaying.value = true // 再生状態にする
            _isPlayingFlowChart.value = false
            _currentlyPlayingBlock.value = null
            mediaPlayerHelper.setOnCompletionListener { _isPlaying.value = false } // 再生が終了したら停止状態
        }
    }

    // フローチャートをリセットするメソッド
    fun resetFlowChart() {
        mediaPlayerHelper.releaseMediaPlayer() // MediaPlayerをリリース
        _selectedBlock.value = null  // 選択したブロックをリセット
        _isCorrectOrder.value = null // 正解、不正解をリセット
    }

    // やめるボタンを押した時に呼び出されるメソッド
    fun toggleDialog(show: Boolean) {
        _showDialog.value = show
    }
}