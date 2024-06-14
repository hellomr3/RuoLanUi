import android.content.res.Resources

class AndroidPlatformDimen : PlatformDimen {
    override fun screenScale(designWidth: Float): Float {
        val metrics = Resources.getSystem().displayMetrics
        return metrics.widthPixels / metrics.density / designWidth
    }
}

actual fun platformDimen(): PlatformDimen {
    return AndroidPlatformDimen()
}