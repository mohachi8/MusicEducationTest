import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

class SoundPoolHelper(private val context: Context, private val maxStreams: Int) {
    private val soundPool: SoundPool = SoundPool.Builder()
        .setMaxStreams(maxStreams)
        .setAudioAttributes(AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build())
        .build()

    private val soundMap = mutableMapOf<Int, Int>()

    fun loadSound(soundId: Int) {
        soundMap[soundId] = soundPool.load(context, soundId, 1)
    }

    fun playSound(soundId: Int) {
        soundMap[soundId]?.let { soundPool.play(it, 1f, 1f, 0, 0, 1f) }
    }
}
