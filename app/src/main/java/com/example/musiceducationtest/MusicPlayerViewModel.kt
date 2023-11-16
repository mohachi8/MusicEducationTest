package com.example.musiceducationtest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Thread.State

class MusicPlayerViewModel : ViewModel() {
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    private val _playbackPosition = MutableStateFlow(0f) // 0f~1fの範囲
    val playbackPosition: StateFlow<Float> = _playbackPosition

    fun togglePlayPause() {
        _isPlaying.value = !_isPlaying.value
    }

    fun onSliderValueChanged(value: Float){
        _playbackPosition.value = value
    }
}