package com.looptry.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.looptry.Black65
import com.looptry.Dp12
import com.looptry.Dp16
import com.looptry.Dp24
import com.looptry.Dp32
import com.looptry.Dp8
import com.looptry.White100
import com.looptry.btn.AppIconButton
import com.looptry.btn.AppOutlineButton
import com.looptry.btn.AppSolidButton
import com.looptry.spacer.AppSpacer
import com.looptry.text.AppText
import org.jetbrains.compose.resources.painterResource
import ruolanui.library.generated.resources.Res
import ruolanui.library.generated.resources.popup_ic_close

@Composable
fun AppConfirmDialog(
    state: DialogState,
    onDismissRequest: () -> Unit = { state.visible = false },
    title: String = "",
    content: String = "",
    mainText: String = "确认",
    subText: String = "取消",
    showCloseBtn: Boolean = false,
    onMainClick: () -> Unit = {},
    onSubClick: () -> Unit = {},
    onCloseClick: () -> Unit = {}
) {
    val showContent = content.isNotBlank()
    val showCancelBtn = subText.isNotBlank()
    if (state.visible)
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = Dp24)
                    .background(White100, RoundedCornerShape(Dp8))
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Column(
                    modifier = Modifier.padding(all = Dp16),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (title.isNotBlank()) {
                        AppSpacer(vertical = Dp16)
                        AppText(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    if (showContent) {
                        AppSpacer(vertical = Dp12)
                        AppText(
                            text = content,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Black65
                        )
                    }
                    AppSpacer(vertical = Dp32)
                    Row {
                        if (showCancelBtn) {
                            AppOutlineButton(
                                text = subText,
                                onClick = {
                                    onSubClick()
                                    onDismissRequest()
                                },
                                modifier = Modifier.weight(1f)
                            )
                            AppSpacer(horizontal = Dp12)
                        }
                        AppSolidButton(
                            text = mainText,
                            onClick = {
                                onMainClick()
                                onDismissRequest()
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                // 关闭按钮
                if (showCloseBtn) {
                    AppIconButton(
                        modifier = Modifier.align(Alignment.TopEnd).padding(top = Dp8, end = Dp8),
                        painter = painterResource(Res.drawable.popup_ic_close),
                        onClick = {
                            onCloseClick()
                            onDismissRequest()
                        }
                    )
                }
            }

        }
}