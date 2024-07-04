package com.looptry

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Float.colorValue: Float
    get() {
        return this
    }

// 白色
val White100 = Color(0xFFFFFFFF)
val White95 = White100.copy(alpha = 0.95f)
val White90 = White100.copy(alpha = 0.90f)
val White85 = White100.copy(alpha = 0.85f)
val White80 = White100.copy(alpha = 0.80f)
val White75 = White100.copy(alpha = 0.75f)
val White70 = White100.copy(alpha = 0.70f)
val White65 = White100.copy(alpha = 0.65f)
val White60 = White100.copy(alpha = 0.60f)
val White55 = White100.copy(alpha = 0.55f)
val White50 = White100.copy(alpha = 0.50f)
val White45 = White100.copy(alpha = 0.45f)
val White40 = White100.copy(alpha = 0.40f)
val White35 = White100.copy(alpha = 0.35f)
val White30 = White100.copy(alpha = 0.30f)
val White25 = White100.copy(alpha = 0.25f)
val White20 = White100.copy(alpha = 0.20f)
val White15 = White100.copy(alpha = 0.15f)
val White10 = White100.copy(alpha = 0.10f)
val White5 = White100.copy(alpha = 0.5f)

// 背景色
val Background100 = Color(0xFFF5F6FA)
val Background95 = Background100.copy(alpha = 0.95f)
val Background90 = Background100.copy(alpha = 0.90f)
val Background85 = Background100.copy(alpha = 0.85f)
val Background80 = Background100.copy(alpha = 0.80f)
val Background75 = Background100.copy(alpha = 0.75f)
val Background70 = Background100.copy(alpha = 0.70f)
val Background65 = Background100.copy(alpha = 0.65f)
val Background60 = Background100.copy(alpha = 0.60f)
val Background55 = Background100.copy(alpha = 0.55f)
val Background50 = Background100.copy(alpha = 0.50f)
val Background45 = Background100.copy(alpha = 0.45f)
val Background40 = Background100.copy(alpha = 0.40f)
val Background35 = Background100.copy(alpha = 0.35f)
val Background30 = Background100.copy(alpha = 0.30f)
val Background25 = Background100.copy(alpha = 0.25f)
val Background20 = Background100.copy(alpha = 0.20f)
val Background15 = Background100.copy(alpha = 0.15f)
val Background10 = Background100.copy(alpha = 0.10f)
val Background5 = Background100.copy(alpha = 0.5f)

// 黑色
val Black100 = Color(0xFF000000)
val Black95 = Black100.copy(alpha = 0.95f)
val Black90 = Black100.copy(alpha = 0.90f)
val Black85 = Black100.copy(alpha = 0.85f)
val Black80 = Black100.copy(alpha = 0.80f)
val Black75 = Black100.copy(alpha = 0.75f)
val Black70 = Black100.copy(alpha = 0.70f)
val Black65 = Black100.copy(alpha = 0.65f)
val Black60 = Black100.copy(alpha = 0.60f)
val Black55 = Black100.copy(alpha = 0.55f)
val Black50 = Black100.copy(alpha = 0.50f)
val Black45 = Black100.copy(alpha = 0.45f)
val Black40 = Black100.copy(alpha = 0.40f)
val Black35 = Black100.copy(alpha = 0.35f)
val Black30 = Black100.copy(alpha = 0.30f)
val Black25 = Black100.copy(alpha = 0.25f)
val Black20 = Black100.copy(alpha = 0.20f)
val Black15 = Black100.copy(alpha = 0.15f)
val Black12 = Black100.copy(alpha = 0.12f)
val Black10 = Black100.copy(alpha = 0.10f)
val Black5 = Black100.copy(alpha = 0.5f)

val TitleTextColor
    @Composable
    get() = if (isSystemInDarkTheme()) White85 else Black85
