import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

class AndroidVibrate() : PlatformVibrate {
    override fun short() {
        vibrate(15)
    }

    override fun long() {
        vibrate(300)
    }

    override fun custom(ms: Long) {
        vibrate(ms)
    }

    private fun vibrate(ms: Long) {
        val context = contextCache.value ?: return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibrator =
                context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibrator.defaultVibrator.vibrate(
                VibrationEffect.createOneShot(
                    ms,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            @Suppress("DEPRECATION")
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            @Suppress("DEPRECATION")
            vibrator.vibrate(ms)
        }
    }
}

actual fun platformVibrate(): PlatformVibrate {
    return AndroidVibrate()
}