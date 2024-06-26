package com.ui.picker

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.looptry.btn.BlockButton
import com.looptry.picker.rememberSingleColumnPickerState

@Composable
fun PickerScreen() {
    val pickerState = rememberSingleColumnPickerState()
    var value1 by remember { mutableStateOf(0) }
    Scaffold {
        Column {
            BlockButton(leadTitle = "普通弹窗") {
                pickerState.show(
                    title = "测试picer",
                    range = listOf("1", "2", "3"),
                    value = value1,
                    onChange = { value1 = it }
                )
            }
        }
    }
}