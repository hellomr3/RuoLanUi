package com.ui.form

import androidx.lifecycle.ViewModel
import com.looptry.form.FormStore
import com.looptry.form.FormStoreImpl

/**
 * @author guoqingshan
 * @date 2024/9/6/周五
 * @description
 */
class FormViewModel : ViewModel() {

    // 初始化 FormStoreImpl 并提供 store
    private val _formStore = FormStoreImpl()

    // 提供外部访问的 FormStore
    val formStore: FormStore get() = _formStore
}