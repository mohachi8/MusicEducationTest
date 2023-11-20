package com.example.musiceducationtest

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

// 音楽プレイヤーのViewModel = 音楽プレイヤーに関する処理
class MusicPlayerViewModel(private val context: Context) :
    ViewModel() {
    /* -------------------------- 変数の定義 -------------------------- */

    // mediaPlayer(音楽の再生に使用)
    private var mediaPlayer: MediaPlayer? = null

    // 再生フラグ（初期状態はfalse(再生停止の状態)）
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    // スライドバー位置の変数
    private val _playbackPosition = MutableStateFlow(0f) // 0f~1fの範囲
    val playbackPosition: StateFlow<Float> = _playbackPosition

    // スライダー操作中フラグ
    private val _isSliderBeingTouched = MutableStateFlow(false)

    /* -------------------------- 初期化 -------------------------- */

    // MediaPlayerオブジェクトの初期化
    init {
        // 再生する曲
        mediaPlayer = MediaPlayer.create(context, R.raw.music)

        // 音楽の再生が終了したら再生停止状態にする
        mediaPlayer?.setOnCompletionListener {
            _isPlaying.value = false
        }

        // スライダーポジションのアップデーター
        startPlaybackPositionUpdater()
    }

    /* -------------------------- 処理 -------------------------- */

    // 再生ボタンがタップされたら呼ばれる関数
    fun togglePlayPause() {
        // 再生中のとき、曲を一時停止しフラグをfalseにする。
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
            _isPlaying.value = false
        }
        // 一時停止中のとき、曲を再生しフラグをtrueにする。
        else {
            mediaPlayer?.start()
            _isPlaying.value = true
        }
    }

    // スライダーポジションのアップデーター
    private fun startPlaybackPositionUpdater() {
        viewModelScope.launch {
            while (isActive) {
                if (!_isSliderBeingTouched.value) { // スライダーが操作中でない場合のみ更新
                    val currentPosition = mediaPlayer?.currentPosition?.toFloat() ?: 0f
                    val totalDuration = mediaPlayer?.duration?.toFloat() ?: 1f
                    _playbackPosition.value = currentPosition / totalDuration
                }
                delay(100)
            }
        }
    }

    // スライダーの値が変更されたときに呼び出される関数
    fun onSliderValueChanged(value: Float) {
        _isSliderBeingTouched.value = true  // スライダー操作中に設定
        _playbackPosition.value = value
    }

    // スライダーの操作が完了した時に呼び出される関数
    fun onSliderValueChangeFinished(value: Float) {
        _isSliderBeingTouched.value = false // スライダー操作完了に設定
        mediaPlayer?.let {
            val newPosition = (value * it.duration).toInt()
            if (newPosition >= 0 && newPosition <= it.duration) {
                it.seekTo(newPosition)
            }
        }
    }

    override fun onCleared() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onCleared()
    }
}