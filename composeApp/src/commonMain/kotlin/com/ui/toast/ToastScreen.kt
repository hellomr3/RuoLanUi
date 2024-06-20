package com.ui.toast

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.looptry.btn.BlockButton
import com.looptry.toast.ToastIcon
import com.looptry.toast.rememberToastState

@Composable
fun ToastScreen() {
    Scaffold {
        val toastState = rememberToastState()
        BlockButton {
            toastState.show("测试toast", icon = ToastIcon.LOADING, mask = true)
        }
    }
}