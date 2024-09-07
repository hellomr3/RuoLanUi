package com.ui.form

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    initialValue: String = "",
) {
    val formStore = LocalFormStore.current
    // 更新 FormValue 的逻辑放在 LaunchedEffect 中
    LaunchedEffect(rules, initialValue, index) {
        formStore.setFormValue(
            key,
            FormValue(rules = rules, value = initialValue, index = index)
        )
    }
    TextField(value = formStore.get(key, initialValue), onValueChange = {
        formStore.set(key, it)
    })
}