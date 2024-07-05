package com.ui.picker

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.looptry.btn.BlockButton
import com.looptry.picker.rememberDatePickerState
import com.looptry.picker.rememberSingleColumnPickerState
import com.looptry.picker.rememberTimePickerState
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun PickerScreen() {
    val pickerState = rememberSingleColumnPickerState()
    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()
    var value1 by remember { mutableStateOf(0) }
    var date by remember {
        mutableStateOf(
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        )
    }
    var time by remember {
        mutableStateOf(
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time
        )
    }
    Scaffold {
        Column {
            BlockButton(leadTitle = "单项选择器   ${value1}") {
                pickerState.show(
                    title = "请选择性别",
                    range = listOf("男", "女", "不限制"),
                    value = value1,
                    onChange = { value1 = it }
                )
            }
            BlockButton(leadTitle = "日期选择器   ${date.year}/${date.monthNumber}/${date.dayOfMonth}") {
                datePickerState.show(value = date) {
                    date = it
                }
            }
            BlockButton(leadTitle = "时间选择器   ${time.hour}时${time.minute}分${time.second}秒") {
                timePickerState.show(value = time, onChange = {
                    time = it
                })
            }
        }
    }
}