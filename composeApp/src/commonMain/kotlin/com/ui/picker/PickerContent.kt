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
import com.looptry.picker.rememberTimePickerState

@Composable
fun PickerScreen() {
    val pickerState = rememberSingleColumnPickerState()
    val timePickerState = rememberTimePickerState()
    var value1 by remember { mutableStateOf(0) }
    Scaffold {
        Column {
            BlockButton(leadTitle = "单项选择器") {
                pickerState.show(
                    title = "请选择性别",
                    range = listOf("男", "女", "不限制"),
                    value = value1,
                    onChange = { value1 = it }
                )
            }
            BlockButton(leadTitle = "日期选择器") {
                pickerState.show(
                    title = "请选择性别",
                    range = listOf("男", "女", "不限制"),
                    value = value1,
                    onChange = { value1 = it }
                )
            }
            BlockButton(leadTitle = "时间选择器") {
                timePickerState.show(onChange = {})
            }
        }
    }
}