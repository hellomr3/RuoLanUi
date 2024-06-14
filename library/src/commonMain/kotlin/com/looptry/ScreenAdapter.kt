package com.looptry

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import platformDimen

/**
 * 定义设计图宽
 */
private const val designWidth = 375f

/**
 * 默认使用宽度适配，根据需求切换
 * 定义根据宽适配的缩放比例
 * 1、屏幕宽度和设计图宽比例，获得density的缩放值
 * 2、根据这个值去获取对应dp
 */
private val scale: Float
    get() {
        return platformDimen().screenScale(designWidth)
    }

val Int.sdp get() = (this * scale).dp

val Double.sdp get() = (this * scale).toInt().dp

val Int.ssp get() = (this * scale).sp

val Double.ssp get() = (this * scale).toInt().sp
