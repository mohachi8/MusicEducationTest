package com.example.musiceducationtest.helper

import android.app.Application
import android.media.MediaPlayer

class MediaPlayerHelper(private val application: Application) {
    private var mediaPlayer: MediaPlayer? = null

    // getter
    fun getMediaPlayer(): MediaPlayer? {
        return mediaPlayer
    }

    // MediaPlayerの初期化
    fun initializeMediaPlayer(musicResId: Int) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(application, musicResId)
        }
    }

    // 再生が終了したときの処理
    fun setOnCompletionListener(onCompletion: () -> Unit) {
        mediaPlayer?.setOnCompletionListener {
            onCompletion()
            mediaPlayer?.seekTo(0) // 再生位置をリセット
        }
    }

    // 再生と一時停止を入れ替える
    fun togglePlayPause(isPlaying: Boolean) {
        if (isPlaying) {
            mediaPlayer?.pause()
        } else {
            mediaPlayer?.start()
        }
    }

    // MediaPlayerの解放
    fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}