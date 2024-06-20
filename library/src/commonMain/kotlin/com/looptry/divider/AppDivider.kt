package com.looptry.divider

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.looptry.Dp0
import com.looptry.Dp1

@Composable
fun AppDivider(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFFEFF1F6),
    thickness: Dp = Dp1,
    startIndent: Dp = Dp0
) {
    HorizontalDivider(
        modifier = modifier.padding(start = startIndent),
        color = color,
        thickness = thickness,
    )
}