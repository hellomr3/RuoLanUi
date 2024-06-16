package com.looptry.form.rule

interface IRule {
    val errorMsg: String

    fun verify(value: Any?): Boolean
}