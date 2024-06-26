interface PlatformVibrate {
    fun short()

    fun long()

    fun custom(ms: Long)
}

expect fun platformVibrate(): PlatformVibrate