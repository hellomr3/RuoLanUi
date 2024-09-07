package com.looptry.form.rule

data class StringNotBlank(override val errorMsg: String) : IRule<String> {
    override fun verify(value: Any?): Boolean {
        if (value !is String) return false
        return value.isNotBlank()
    }
}