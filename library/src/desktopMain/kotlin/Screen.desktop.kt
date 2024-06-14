class DesktopPlatformDimen() : PlatformDimen {
    override fun screenScale(designWidth: Float): Float {
        return 1f
    }
}

actual fun platformDimen(): PlatformDimen {
    return DesktopPlatformDimen()
}