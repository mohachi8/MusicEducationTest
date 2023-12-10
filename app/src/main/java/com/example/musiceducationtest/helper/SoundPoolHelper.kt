package com.example.musiceducationtest.helper

import android.app.Application
import android.media.AudioAttributes
import android.media.SoundPool
import javax.inject.Inject

class SoundPoolHelper @Inject constructor(
    private val application: Application
) {
    private val soundPool: SoundPool = SoundPool.Builder()
        .setMaxStreams(1)
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        .build()

    private val soundMap = mutableMapOf<Int, Int>()

    fun loadSound(soundId: Int) {
        soundMap[soundId] = soundPool.load(application, soundId, 1)
    }

    fun playSound(soundId: Int) {
        soundMap[soundId]?.let { soundPool.play(it, 1f, 1f, 0, 0, 1f) }
    }
}