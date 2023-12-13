package com.example.musiceducationtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.musiceducationtest.helper.MediaPlayerHelper
import com.example.musiceducationtest.model.BlockDataModel
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
    private val _flowChartBlocks = MutableStateFlow<List<BlockDataModel>>(emptyList())
    private val _isPlaying = MutableStateFlow(false)
    private val _isPlayingFlowChart = MutableStateFlow(false)

    val selectedBlock: StateFlow<BlockDataModel?> = _selectedBlock
    val isPlaying: StateFlow<Boolean> = _isPlaying // 再生しているかどうかを保持
    val isPlayingFlowChart: StateFlow<Boolean> = _isPlayingFlowChart // 再生しているかどうかを保持
    val flowChartBlocks: StateFlow<List<BlockDataModel>> = _flowChartBlocks

    private val _isCorrectOrder = MutableStateFlow<Boolean?>(null)
    val isCorrectOrder: StateFlow<Boolean?> = _isCorrectOrder

    fun checkAnswer(lessonId: String) {
        val lesson = lessonRepository.getLessonById(lessonId)
        val correctOrder = lesson?.answers
        val currentOrder = _flowChartBlocks.value

        _isCorrectOrder.value = correctOrder?.equals(currentOrder) ?: false
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
    }


    // 選択したブロックをフローチャートに追加
    fun addToFlowChart() {
        selectedBlock.value?.let { block ->
            // ブロックがフローチャートに既に存在しないか確認、フローチャートの最初のブロックではないかを確認
            if (!_flowChartBlocks.value.contains(block) && block != _firstFlowChartBlock.value) {
                _flowChartBlocks.value = _flowChartBlocks.value + block
            }
        }
        mediaPlayerHelper.togglePlayPause(isPlaying = true)
        _isPlaying.value = false
        _isPlayingFlowChart.value = false
        _selectedBlock.value = null
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
    }

    // フローチャートの再生ボタンが押された時の処理
    fun startFlowChartMusic() {
        if (_isPlayingFlowChart.value) {
            // 既に再生中の場合は再生を停止
            mediaPlayerHelper.releaseMediaPlayer() // MediaPlayerをリリース
            _isPlaying.value = false
            _isPlayingFlowChart.value = false
        } else {
            // まだ再生していない場合は、フローチャートの音楽を再生開始
            val blocks = _flowChartBlocks.value
            if (blocks.isNotEmpty()) {
                playMusicSequence(blocks, 0)
                _isPlayingFlowChart.value = true
            }
        }
    }

    // フローチャートの音楽を組み立てるメソッド
    private fun playMusicSequence(blocks: List<BlockDataModel>, index: Int) {
        if (index < blocks.size) {
            val block = blocks[index]
            mediaPlayerHelper.initializeMediaPlayer(block.musicResId)
            mediaPlayerHelper.setOnCompletionListener { // 再生が終了した後の処理
                if (index + 1 < blocks.size) {
                    playMusicSequence(blocks, index + 1) // 次の曲を再生
                } else {
                    _isPlayingFlowChart.value = false
                }
            }
            mediaPlayerHelper.togglePlayPause(isPlaying = false)
        } else {
            _isPlayingFlowChart.value = false
        }
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
            mediaPlayerHelper.setOnCompletionListener { _isPlaying.value = false } // 再生が終了したら停止状態
        }
    }

    // フローチャートをリセットするメソッド
    fun resetFlowChart() {
        mediaPlayerHelper.releaseMediaPlayer() // MediaPlayerをリリース
        _selectedBlock.value = null  // 選択したブロックをリセット
        _isCorrectOrder.value = null // 正解、不正解をリセット
    }


    private val _showDialog = MutableStateFlow(false)

    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow() // ダイアログ表示の状態を管理

    // やめるボタンを押した時に呼び出されるメソッド
    fun toggleDialog(show: Boolean) {
        _showDialog.value = show
    }
}