package com.example.musiceducationtest.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.musiceducationtest.helper.MediaPlayerHelper
import com.example.musiceducationtest.repository.LessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SongCompositionViewModel @Inject constructor(
    private val lessonRepository: LessonRepository,
    application: Application
) : AndroidViewModel(application) {
    private val mediaPlayerHelper = MediaPlayerHelper(application)
    private var currentlyPlayingMusic: Int? = null // 現在再生中の曲
    private val _selectedBlockId = MutableStateFlow<String?>(null)
    private val _isPlaying = MutableStateFlow(false)

    val selectedBlockId: StateFlow<String?> = _selectedBlockId // 選択されたブロックのIDを保持する変数
    val isPlaying: StateFlow<Boolean> = _isPlaying // 再生しているかどうかを保持


    private val _flowChartBlocks = MutableStateFlow<List<String>>(emptyList())
    val flowChartBlocks: StateFlow<List<String>> = _flowChartBlocks

    fun addToFlowChart() {
        selectedBlockId.value?.let { selectedBlock ->
            _flowChartBlocks.value = _flowChartBlocks.value + selectedBlock
        }
    }

    fun removeFromFlowChart() {
        if (_flowChartBlocks.value.isNotEmpty()) {
            _flowChartBlocks.value = _flowChartBlocks.value.dropLast(1)
        }
    }

    fun clearFlowChart() {
        _flowChartBlocks.value = emptyList()
    }


    fun startFlowChartMusic() {
        val blocks = _flowChartBlocks.value
        if (blocks.isNotEmpty()) {
            playMusicSequence(blocks, 0)
        }
    }

    private fun playMusicSequence(blocks: List<String>, index: Int) {
        if (index < blocks.size) {
            val blockId = blocks[index]
            val musicResId = getMusicResIdFromBlockId(blockId)
            mediaPlayerHelper.initializeMediaPlayer(musicResId)
            mediaPlayerHelper.setOnCompletionListener {
                playMusicSequence(blocks, index + 1)
            }
            mediaPlayerHelper.togglePlayPause(isPlaying = false)
        }
    }
    private fun getMusicResIdFromBlockId(blockId: String): Int {
        lessonRepository.getAllLessons().forEach { lesson ->
            lesson.flowChartBlocks.forEach { block ->
                if (block.id == blockId) {
                    Log.d("SongCompositionVM", "Block ID: $blockId, Music Res ID: ${block.musicResId}")
                    return block.musicResId
                }
            }
        }
        return -1 // ブロックIDが見つからない場合
    }

    // ブロックを選択するメソッド
    fun selectBlock(blockId: String) {
        _selectedBlockId.value = blockId
    }

    // ブロックの音楽を再生するメソッド
    fun playBlockMusic(musicResId: Int) {
        if (currentlyPlayingMusic == musicResId && mediaPlayerHelper.isPlaying()) {
            // 再生中かつ、同じブロックが再度クリックされた場合、再生を停止
            mediaPlayerHelper.togglePlayPause(isPlaying = true)
            currentlyPlayingMusic = null // 現在のブロックをリセット
            _isPlaying.value = false // 停止状態にする
        } else {
            // 異なるブロックがクリックされた場合、新しい曲の再生を始める
            mediaPlayerHelper.releaseMediaPlayer() // 既存のMediaPlayerをリリース
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
        _selectedBlockId.value = null  // 選択したブロックをリセット
    }
}