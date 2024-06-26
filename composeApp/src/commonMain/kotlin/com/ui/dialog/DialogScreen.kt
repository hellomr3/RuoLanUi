package com.ui.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.looptry.btn.BlockButton
import com.looptry.dialogs.AppConfirmDialog
import com.looptry.dialogs.rememberDialogState

@Composable
fun DialogScreen() {
    val dialogState = rememberDialogState(false)
    val dialogState2 = rememberDialogState(false)
    Scaffold {
        Column {
            BlockButton(leadTitle = "普通弹窗") {
                dialogState.show()
            }
            BlockButton(leadTitle = "普通弹窗带关闭") {
                dialogState2.show()
            }
        }
    }
    AppConfirmDialog(dialogState, title = "温馨提示", content = "这是一个弹窗的提示内容")
    AppConfirmDialog(
        dialogState2,
        title = "温馨提示",
        content = "这是一个弹窗的提示内容",
        showCloseBtn = true
    )
}