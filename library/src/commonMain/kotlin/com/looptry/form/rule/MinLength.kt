package com.looptry.form.rule

data class Length(val min: Int, override val errorMsg: String, val max: Int = min) : IRule {
    override fun verify(value: Any?): Boolean {
        if (value == null) return false
        require(min <= max)
        return if (min == max) {
            if (value is List<*>) {
                value.size == min
            } else {
                value.toString().length == min
            }
        } else {
            if (value is List<*>) {
                value.size in min..max
            } else {
                value.toString().length in min..max
            }
        }
    }
}