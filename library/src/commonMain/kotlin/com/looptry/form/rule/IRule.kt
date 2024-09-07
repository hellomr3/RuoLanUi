package com.looptry.form.rule

interface IRule<out T> {
    val errorMsg: String

    fun verify(value: Any?): Boolean
}