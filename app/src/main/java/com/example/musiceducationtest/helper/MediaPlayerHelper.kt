package com.example.musiceducationtest.helper

import android.content.Context
import android.media.MediaPlayer

class MediaPlayerHelper(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null

    // MediaPlayerの初期化
    fun initializeMediaPlayer(musicResId: Int) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, musicResId)
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

    fun getMediaPlayer(): MediaPlayer? {
        return mediaPlayer
    }
}