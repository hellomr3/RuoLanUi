package com.ui.toast

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.looptry.btn.AppIconButton
import com.looptry.btn.BlockButton
import com.looptry.toast.ToastIcon
import com.looptry.toast.rememberToastState
import org.jetbrains.compose.resources.painterResource
import ruolanui.composeapp.generated.resources.Res
import ruolanui.composeapp.generated.resources.compose_multiplatform
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun ToastScreen() {
    Scaffold {
        val toastState = rememberToastState()
        Column {
            BlockButton(
                leadTitle = "loading提示",
                leadIcon = Res.drawable.compose_multiplatform,
                showDivider = true
            ) {
                toastState.show("测试toast", icon = ToastIcon.LOADING, mask = true)
            }
            BlockButton(
                leadTitle = "成功提示",
                leadIcon = Res.drawable.compose_multiplatform,
                showDivider = true
            ) {
                toastState.show("加载成功", icon = ToastIcon.SUCCESS, mask = false)
            }
            BlockButton(
                leadTitle = "失败提示",
                leadIcon = Res.drawable.compose_multiplatform,
                showDivider = true
            ) {
                toastState.show("加载失败", icon = ToastIcon.FAIL, mask = false)
            }
            BlockButton(
                leadTitle = "提示文案",
                leadIcon = Res.drawable.compose_multiplatform,
                showDivider = true
            ) {
                toastState.show(
                    "这是一段提示文案，用于测试提示",
                    icon = ToastIcon.NONE,
                    mask = false
                )
            }
            BlockButton(
                leadTitle = "自定义时长提示文案",
                leadIcon = Res.drawable.compose_multiplatform,
                showDivider = true
            ) {
                toastState.show(
                    "这是一段提示文案，用于测试自定义时长提示文案",
                    icon = ToastIcon.NONE,
                    mask = false,
                    duration = 3000.milliseconds
                )
            }
        }
    }
}