class DeskTopVibrate : PlatformVibrate {
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

    }
}

actual fun platformVibrate(): PlatformVibrate {
    return DeskTopVibrate()
}