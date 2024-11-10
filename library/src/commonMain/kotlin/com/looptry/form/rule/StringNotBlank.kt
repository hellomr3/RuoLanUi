package com.looptry.form.rule

data class StringNotBlank(private val errorMsg: String) : IRule {
    override fun verify(value: Any?): Result<Unit> {
        if (value !is String || value.isBlank()) {
            return Result.failure(Throwable(errorMsg))
        }
        return Result.success(Unit)
    }
}