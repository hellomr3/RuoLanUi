package com.ui.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.looptry.form.FormValidException
import com.looptry.form.LocalFormStore
import com.looptry.form.rememberFormStore
import com.looptry.form.rule.NotEmpty
import com.looptry.toast.rememberToastState

/**
 * 不需要viewModel参与的Form表单
 */
@Composable
fun FormNoViewModelScreen() {
    val formStore = rememberFormStore()
    val toast = rememberToastState()
    CompositionLocalProvider(LocalFormStore provides formStore) {
        Scaffold(modifier = Modifier.statusBarsPadding()) {
            Column(Modifier.padding(it)) {
                TextFieldFormItem(key = "username", rules = listOf(NotEmpty("请输入用户名")))
                TextFieldFormItem(key = "password", rules = listOf(NotEmpty("请输入密码")))
                Button(onClick = {
                    val result = formStore.verify()
                    if (result.isFailure) {
                        toast.show("${result.exceptionOrNull()?.message}_${(result.exceptionOrNull() as? FormValidException)?.formValue?.index}")
                    }
                }) {
                    Text("确认")
                }
            }
        }
    }
}

/**
 * 配合ViewModel使用的表单
 * @param viewModel FormViewModel
 */
@Composable
fun FormScreen(
    viewModel: FormViewModel = viewModel(),
) {
    val toast = rememberToastState()
    CompositionLocalProvider(LocalFormStore provides viewModel.formStore) {
        Scaffold(modifier = Modifier.statusBarsPadding()) {
            Column(Modifier.padding(it)) {
                TextFieldFormItem(key = "username", rules = listOf(NotEmpty("请输入用户名")))
                TextFieldFormItem(key = "password", rules = listOf(NotEmpty("请输入密码")))
                Button(onClick = {
                    val result = viewModel.formStore.verify()
                    if (result.isFailure) {
                        toast.show("${result.exceptionOrNull()?.message}_${(result.exceptionOrNull() as? FormValidException)?.formValue?.index}")
                    }
                }) {
                    Text("确认")
                }
            }
        }
    }
}