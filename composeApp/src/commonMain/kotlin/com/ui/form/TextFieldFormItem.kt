package com.ui.form

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.looptry.form.FormValue
import com.looptry.form.LocalFormStore
import com.looptry.form.get
import com.looptry.form.rule.IRule

/**
 * @author guoqingshan
 * @date 2024/8/26/周一
 * @description
 */
@Composable
fun TextFieldFormItem(
    key: String,
    rules: List<IRule<String>>,
    index: Int = 0,
) {
    val formStore = LocalFormStore.current
    // 提取 formStore.get(key, "") 结果，避免重复操作
    val value = formStore.get(key, "")
    // 使用 remember 来避免不必要的重建
    val initialValue = remember(rules) { value }
    // 更新 FormValue 的逻辑放在 LaunchedEffect 中
    LaunchedEffect(rules, initialValue, index) {
        formStore.setFormValue(
            key,
            FormValue(rules = rules, value = initialValue, index = index)
        )
    }
    TextField(value = value, onValueChange = {
        formStore.set(key, it)
    })
}