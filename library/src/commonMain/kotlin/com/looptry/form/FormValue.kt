package com.looptry.form

import com.looptry.form.rule.IRule

data class FormValue<T>(
    var value: T? = null,
    val index: Int? = null,
    val rules: List<IRule> = emptyList(),
)