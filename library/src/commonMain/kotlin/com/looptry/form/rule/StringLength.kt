package com.looptry.form.rule

data class StringLength(val min: Int, override val errorMsg: String, val max: Int = min) : IRule<String> {
    override fun verify(value: Any?): Boolean {
        if (value !is String) return false
        require(min <= max)
        return if (min == max) {
            value.length == min
        } else {
            value.toString().length in min..max
        }
    }
}