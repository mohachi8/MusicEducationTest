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
    private val _selectedBlockId = MutableStateFlow<String?>(null) // 選択されたブロックのIDを保持する変数
    val selectedBlockId: StateFlow<String?> = _selectedBlockId

    // ブロックを選択するメソッド
    fun selectBlock(blockId: String) {
        _selectedBlockId.value = blockId
    }

    // ブロックの音楽を再生するメソッド
    fun playBlockMusic(musicResId: Int) {
        currentlyPlayingMusic = if (currentlyPlayingMusic == musicResId) {
            // 同じブロックが再度クリックされた場合、再生を停止
            mediaPlayerHelper.togglePlayPause(isPlaying = true)
            null // 現在のブロックをリセット（currentlyPlayingMusic）
        } else {
            // 異なるブロックがクリックされた場合、新しい曲の再生を始める
            mediaPlayerHelper.releaseMediaPlayer() // 既存のMediaPlayerをリリース
            mediaPlayerHelper.initializeMediaPlayer(musicResId) // 新しい音楽リソースでMediaPlayerを初期化
            mediaPlayerHelper.togglePlayPause(isPlaying = false) // 再生開始
            musicResId // 現在再生中のブロックに更新（currentlyPlayingMusic）
        }
    }

    // フローチャートをリセットするメソッド
    fun resetFlowChart() {
        mediaPlayerHelper.releaseMediaPlayer() // MediaPlayerをリリース
        _selectedBlockId.value = null  // 選択したブロックをリセット
    }
}