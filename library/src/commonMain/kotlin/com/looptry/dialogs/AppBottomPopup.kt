package com.looptry.dialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.looptry.modifier.debounceClick

@Composable
fun AppBottomPopup(
    modifier: Modifier = Modifier,
    showPopup: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable (onDismissRequest: () -> Unit) -> Unit
) {
    AnimatedVisibility(visible = showPopup,
        enter = slideInVertically {
            it
        } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut()
    ) {
        Box(
            modifier = Modifier.navigationBarsPadding()
                .fillMaxSize()
                .debounceClick {
                    //消费点击事件，防止点击穿透
                }) {
            Popup(
                alignment = Alignment.BottomStart,
                onDismissRequest = onDismissRequest,
                properties = PopupProperties(dismissOnClickOutside = false)
            ) {
                Box(
                    modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    content(onDismissRequest)
                }
            }
        }
    }
}