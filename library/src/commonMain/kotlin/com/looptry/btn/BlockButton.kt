package com.looptry.btn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.looptry.Black65
import com.looptry.Black85
import com.looptry.Dp12
import com.looptry.Dp56
import com.looptry.Text12
import com.looptry.Text14
import com.looptry.divider.AppDivider
import com.looptry.modifier.debounceClick
import com.looptry.spacer.AppSpacer
import com.looptry.text.AppText
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun BlockButton(
    modifier: Modifier = Modifier,
    leadTitle: String,
    tailTitle: String = "",
    tailAction: @Composable RowScope.() -> Unit = {},
    leadIconTintColor: Color = Color.Unspecified,
    leadIcon: DrawableResource? = null,
    tailIcon: DrawableResource? =null,
    showDivider: Boolean = false,
    dividerPadding: PaddingValues = PaddingValues(),
    onItemClick: () -> Unit
) {
    BlockButton(
        modifier = modifier.height(Dp56),
        leadIcon = {
            leadIcon?.let {
                AppSpacer(horizontal = Dp12)
                AppIconButton(painter = painterResource(it), tint = leadIconTintColor)
            }
        },
        leadAction = {
            AppSpacer(horizontal = Dp12)
            AppText(
                text = leadTitle,
                style = MaterialTheme.typography.titleMedium,
                fontSize = Text14,
                color = Black85
            )
        },
        tailIcon = {
            tailIcon?.let {
                AppIconButton(
                    painter = painterResource(tailIcon),
                    tint = Black65
                )
                AppSpacer(horizontal = Dp12)
            }
        },
        tailAction = {
            tailAction()
            AppText(
                text = tailTitle,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = Text12
            )
            AppSpacer(horizontal = Dp12)
        },
        showDivider = showDivider,
        dividerPadding = dividerPadding,
        onItemClick = onItemClick
    )
}

@Composable
fun BlockButton(
    modifier: Modifier = Modifier,
    leadIcon: @Composable () -> Unit = {},
    leadAction: @Composable () -> Unit = {},
    tailIcon: @Composable () -> Unit = {},
    tailAction: @Composable RowScope.() -> Unit = {},
    showDivider: Boolean = false,
    dividerPadding: PaddingValues = PaddingValues(),
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .debounceClick(onClick = onItemClick)
            .then(modifier),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            leadIcon()
            leadAction()
            AppSpacer(modifier = Modifier.weight(1f))
            tailAction()
            tailIcon()
        }
        if (showDivider) {
            AppDivider(
                modifier = Modifier
                    .padding(dividerPadding)
                    .align(Alignment.BottomStart)
            )
        }
    }
}
