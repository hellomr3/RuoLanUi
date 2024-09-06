package com.ui.form

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.looptry.form.FormStore
import com.looptry.form.FormStoreImpl
import com.looptry.form.FormValue

/**
 * @author guoqingshan
 * @date 2024/9/6/周五
 * @description
 */
class FormViewModel : ViewModel() {
    // 使用 mutableStateMapOf 创建 store
    private val store = mutableStateMapOf<String, FormValue<Any>>()

    // 初始化 FormStoreImpl 并提供 store
    private val _formStore = FormStoreImpl(store)

    // 提供外部访问的 FormStore
    val formStore: FormStore get() = _formStore
}