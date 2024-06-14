package com.looptry.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.looptry.Dp16
import org.jetbrains.compose.resources.painterResource
import ruolanui.library.generated.resources.Res
import ruolanui.library.generated.resources.ic_loading


/**
 * 加载中动画图标
 *
 * @param size 大小
 * @param color 颜色
 * @param isRotating 是否旋转
 */
@Composable
fun AppLoading(
    size: Dp = Dp16,
    color: Color = MaterialTheme.colorScheme.primary,
    isRotating: Boolean = true
) {
    val angle by if (isRotating) {
        val transition = rememberInfiniteTransition(label = "")
        transition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = 1000, easing = LinearEasing),
                RepeatMode.Restart
            ),
            label = "LoadingAnimation"
        )
    } else {
        remember { mutableFloatStateOf(0f) }
    }

    Icon(
        painter = painterResource(Res.drawable.ic_loading),
        contentDescription = "loading",
        modifier = Modifier
            .size(size)
            .rotate(angle),
        tint = color
    )
}
