package com.looptry.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import kotlinx.datetime.Clock

/**
 *
 * @receiver Modifier
 * @param onClick Function0<Unit>?      待处理的点击事件
 * @param debounce Long                 防连点时间
 * @return Modifier
 */
fun Modifier.debounceClick(debounce: Long = 300L, onClick: (() -> Unit)? = null): Modifier =
    composed {
        if (onClick == null) return@composed Modifier
        // 无点击效果的点击事件
        // 防连点
        var lastClick by remember {
            mutableStateOf(0L)
        }
        return@composed this.clickable(
            interactionSource = remember {
                MutableInteractionSource()
            },
            indication = null,
            onClick = {
                if (getCurrentTimestamp() - lastClick < debounce) {
                    return@clickable
                }
                onClick.invoke()
                lastClick = getCurrentTimestamp()
            }
        )
    }

fun getCurrentTimestamp(): Long {
    val currentMoment = Clock.System.now()
    return currentMoment.epochSeconds * 1000
}