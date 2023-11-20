package com.example.musiceducationtest

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

// 音楽プレイヤーに関する処理
class MusicPlayerViewModel(application: Application) : AndroidViewModel(application) {

    /* -------------------------- 変数の定義 -------------------------- */
    private var mediaPlayer: MediaPlayer? = MediaPlayer.create(application, R.raw.music)
    private val _isPlaying = MutableStateFlow(false) // 再生状態のフラグ（このクラス内のみで使用）
    private val _playbackPosition = MutableStateFlow(0f) // スライダーの位置（このクラス内のみで使用）
    private val _isSliderBeingTouched = MutableStateFlow(false) // スライダーが操作されている時のフラグ

    val isPlaying: StateFlow<Boolean> = _isPlaying // 再生状態のフラグ
    val playbackPosition: StateFlow<Float> = _playbackPosition // スライダーの位置


    /* -------------------------- 初期化 -------------------------- */
    init {
        // 再生が完了したら再生停止状態に変更する
        mediaPlayer?.setOnCompletionListener {
            _isPlaying.value = false
        }
        startPlaybackPositionUpdater()
    }


    /* -------------------------- 処理 -------------------------- */
    // 再生ボタンが押された時に再生と一時停止を入れ替えるメソッド
    fun togglePlayPause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                _isPlaying.value = false
            } else {
                it.start()
                _isPlaying.value = true
            }
        }
    }

    // スライダーの位置を更新するメソッド
    private fun startPlaybackPositionUpdater() {
        viewModelScope.launch {
            while (isActive) {
                if (!_isSliderBeingTouched.value) {
                    mediaPlayer?.let { player ->
                        val currentPosition = player.currentPosition.toFloat()
                        val totalDuration = player.duration.toFloat()
                        _playbackPosition.value = currentPosition / totalDuration
                    }
                }
                delay(500) // 更新間隔を 0.5 秒に設定
            }
        }
    }

    // スライダーの値が変化した時に呼び出されるメソッド
    fun onSliderValueChanged(value: Float) {
        _isSliderBeingTouched.value = true
        _playbackPosition.value = value
    }

    // スライダーの操作が完了した時に呼び出されるメソッド
    fun onSliderValueChangeFinished() {
        _isSliderBeingTouched.value = false
        mediaPlayer?.let {
            val newPosition = (_playbackPosition.value * it.duration).toInt()
            it.seekTo(newPosition)
        }
    }

    // ViewModelが破棄される際に呼び出されるメソッド
    override fun onCleared() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onCleared()
    }
}