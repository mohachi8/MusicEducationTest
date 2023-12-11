package com.example.musiceducationtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.musiceducationtest.helper.MediaPlayerHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SongCompositionViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {
    private val mediaPlayerHelper = MediaPlayerHelper(application)
    private var currentlyPlayingMusic: Int? = null // 現在再生中の曲
    private val _selectedBlockId = MutableStateFlow<String?>(null)
    private val _isPlaying = MutableStateFlow(false)

    val selectedBlockId: StateFlow<String?> = _selectedBlockId // 選択されたブロックのIDを保持する変数
    val isPlaying: StateFlow<Boolean> = _isPlaying // 再生しているかどうかを保持

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