package com.example.musiceducationtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.musiceducationtest.helper.SoundPoolHelper
import androidx.lifecycle.ViewModel
import com.example.musiceducationtest.helper.MediaPlayerHelper
import com.example.musiceducationtest.repository.LessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SongCompositionViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {
    private val mediaPlayerHelper = MediaPlayerHelper(application)
    // 選択されたブロックのIDを保持する変数
    private val _selectedBlockId = MutableStateFlow<String?>(null)
    val selectedBlockId: StateFlow<String?> = _selectedBlockId

    // ブロックを選択するメソッド
    fun selectBlock(blockId: String) {
        _selectedBlockId.value = blockId
    }

    fun playBlockMusic(musicResId: Int) {
        mediaPlayerHelper.releaseMediaPlayer() // 既存のMediaPlayerをリリース
        mediaPlayerHelper.initializeMediaPlayer(musicResId)
        mediaPlayerHelper.togglePlayPause(isPlaying = false) // 再生開始
    }
}