package com.looptry.form

import com.looptry.form.rule.IRule

/**
 * @description 表单校验异常
 */
data class FormValidException(val rule: IRule<*>, val formValue: FormValue<Any>) :
    Throwable(message = rule.errorMsg)