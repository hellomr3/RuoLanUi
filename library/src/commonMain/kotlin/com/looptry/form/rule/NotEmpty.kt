package com.looptry.form.rule

data class NotEmpty(override val errorMsg: String) : IRule {
    override fun verify(value: Any?): Boolean {
        if (value == null) return false
        return when (value) {
            is String -> value.isNotEmpty()
            is List<*> -> value.isNotEmpty()
            else -> false
        }
    }
}