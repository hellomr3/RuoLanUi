import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.UIKit.UIScreen

class IOSPlatformDimen : PlatformDimen {
    @OptIn(ExperimentalForeignApi::class)
    override fun screenScale(designWidth: Float): Float {
        val mainScreen = UIScreen.mainScreen
        val screenWidth = mainScreen.bounds().useContents {
            this.size.width
        }
        return screenWidth.toFloat() / designWidth
    }

}

actual fun platformDimen(): PlatformDimen {
    return IOSPlatformDimen()
}