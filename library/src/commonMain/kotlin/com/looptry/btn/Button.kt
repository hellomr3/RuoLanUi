package com.looptry.btn

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.looptry.Dp1
import com.looptry.Dp24
import com.looptry.Dp40
import com.looptry.Dp6
import com.looptry.text.AppText

@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    painter: Painter?,
    size: Dp = Dp24,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified,
    onClick: (() -> Unit)? = null,
) {
    if (painter != null) {
        if (onClick != null) {
            IconButton(
                modifier = modifier
                    .size(size),
                onClick = onClick
            ) {
                Icon(
                    painter = painter,
                    contentDescription = contentDescription,
                    tint = tint
                )
            }
        } else {
            Icon(
                modifier = modifier
                    .size(size),
                painter = painter,
                contentDescription = contentDescription,
                tint = tint
            )
        }
    }
}

@Composable
fun AppSolidButton(
    modifier: Modifier = Modifier,
    radius: Dp = Dp6,
    text: String,
    onClick: (() -> Unit),
) {
    Button(
        modifier = modifier
            .height(Dp40)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(),
        shape = RoundedCornerShape(radius),
        onClick = onClick
    ) {
        AppText(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = LocalContentColor.current
        )
    }
}

@Composable
fun AppOutlineButton(
    modifier: Modifier = Modifier,
    radius: Dp = Dp6,
    text: String,
    onClick: (() -> Unit),
) {
    OutlinedButton(
        modifier = modifier
            .height(Dp40)
            .fillMaxWidth(),
        border = BorderStroke(Dp1, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(radius),
        onClick = onClick
    ) {
        AppText(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}


@Composable
fun AppTextButton(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    onClick: (() -> Unit),
) {
    TextButton(
        modifier = modifier,
        interactionSource =remember {
            MutableInteractionSource()
        },
        onClick = onClick
    ) {
        AppText(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically),
            style = style
        )
    }
}
