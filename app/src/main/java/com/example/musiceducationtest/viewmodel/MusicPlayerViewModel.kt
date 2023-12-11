package com.example.musiceducationtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.musiceducationtest.helper.MediaPlayerHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

// 音楽プレイヤーに関する処理
@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {
    private val mediaPlayerHelper = MediaPlayerHelper(application)
    private val _isPlaying = MutableStateFlow(false)
    private val _playbackPosition = MutableStateFlow(0f)
    private val _isSliderBeingTouched = MutableStateFlow(false)

    val isPlaying: StateFlow<Boolean> = _isPlaying // 再生しているかどうかを保持
    val playbackPosition: StateFlow<Float> = _playbackPosition // 再生位置

    // スライダーの位置を更新するメソッド
    private fun startPlaybackPositionUpdater() {
        viewModelScope.launch {
            while (isActive) {
                if (!_isSliderBeingTouched.value) {
                    mediaPlayerHelper.getMediaPlayer()?.let { player ->
                        val currentPosition = player.currentPosition.toFloat()
                        val totalDuration = player.duration.toFloat()
                        _playbackPosition.value = currentPosition / totalDuration
                    }
                }
                delay(100)
            }
        }
    }

    // スライダーの値が変化した時に呼び出されるメソッド
    // スライダーに触れている時はスライドバーの見た目だけ変更
    fun onSliderValueChanged(value: Float) {
        _isSliderBeingTouched.value = true
        _playbackPosition.value = value
    }

    // スライダーの操作が終了した時に呼び出されるメソッド
    // スライダーから指を離した時に実際の音楽の再生位置を変更
    fun onSliderValueChangeFinished() {
        _isSliderBeingTouched.value = false
        mediaPlayerHelper.getMediaPlayer()?.let {
            val newPosition = (_playbackPosition.value * it.duration).toInt()
            it.seekTo(newPosition)
        }
    }

    // MediaPlayerの初期化
    // レッスンが選択されたら呼び出される
    fun initializeMediaPlayer(musicResId: Int) {
        mediaPlayerHelper.initializeMediaPlayer(musicResId)
        mediaPlayerHelper.setOnCompletionListener { _isPlaying.value = false } // 再生が終了したときの処理
        startPlaybackPositionUpdater()
    }

    // 再生ボタンが押された時のメソッド
    // 再生と一時停止の変数、isPlayingが入れ替わる
    fun togglePlayPause() {
        val isPlayingNow = mediaPlayerHelper.getMediaPlayer()?.isPlaying ?: false
        _isPlaying.value = !isPlayingNow
        mediaPlayerHelper.togglePlayPause(isPlayingNow)
    }

    // 音楽を停止し、MediaPlayerのリソースを解放するメソッド
    // レッスンを終了してレッスン選択画面に戻る際に呼び出される
    fun releaseMediaPlayer() {
        _isPlaying.value = false
        mediaPlayerHelper.releaseMediaPlayer()
    }

    // ViewModelが破棄される際に呼び出されるメソッド
    override fun onCleared() {
        _isPlaying.value = false
        mediaPlayerHelper.releaseMediaPlayer()
        super.onCleared()
    }
}