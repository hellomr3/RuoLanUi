package com.looptry.form.rule

/**
 * @author guoqingshan
 * @date 2024/5/9/009
 * @description
 */
data class Pattern(
    override val errorMsg: String,
    val regex: String,
) : IRule<String> {
    override fun verify(value: Any?): Boolean {
        if (value !is String) return false
        return regex.toRegex().matches(value)
    }
}
