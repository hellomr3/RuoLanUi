interface PlatformDimen {
    /**
     * 定义方法用于提供基于设计图的屏幕缩放系数
     */
    fun screenScale(designWidth: Float): Float
}


expect fun platformDimen(): PlatformDimen