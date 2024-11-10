package com.looptry.form.rule

interface IRule {
    fun verify(value: Any?): Result<Unit>
}