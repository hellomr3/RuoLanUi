package com.looptry.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun AppSpacer(
    modifier: Modifier = Modifier,
    vertical: Dp? = null,
    horizontal: Dp? = null
) {
    if (vertical != null) {
        Spacer(modifier = Modifier.height(vertical))
    } else if (horizontal != null) {
        Spacer(modifier = Modifier.width(horizontal))
    } else {
        Spacer(modifier = modifier)
    }
}