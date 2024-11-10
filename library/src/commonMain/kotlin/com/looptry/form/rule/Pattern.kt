package com.looptry.form.rule

/**
 * @author guoqingshan
 * @date 2024/5/9/009
 * @description
 */
data class Pattern(
    private val errorMsg: String,
    val regex: Regex,
) : IRule {
    override fun verify(value: Any?): Result<Unit> {
        if (value !is String || !regex.matches(value)) return Result.failure(
            Throwable(
                errorMsg
            )
        )
        return Result.success(Unit)
    }
}
