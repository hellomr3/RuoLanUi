package com.looptry.form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember

interface FormStore {
    val values: Map<String, Any>

    fun verify(): Result<Unit>

    fun set(key: String, value: Any)

    fun setFormValue(key: String, value: FormValue<Any>)
}

/**
 * 获取表单单个数据
 * @receiver FormStore
 * @param key String
 * @param default T
 * @return T
 */
inline fun <reified T> FormStore.get(key: String, default: T): T {
    val formValue = this.values[key]
    return if (formValue != null && formValue is T) {
        formValue
    } else {
        default
    }
}

@Composable
fun rememberFormStore(): FormStore {
    val store = remember { mutableStateMapOf<String, FormValue<Any>>() }
    val state = remember(store) { FormStoreImpl(store) }
    return state
}

class FormStoreImpl(private val store: MutableMap<String, FormValue<Any>>) : FormStore {
    override val values: Map<String, Any>
        get() = store.filter { it.value.value != null }.mapValues { it.value.value!! }

    override fun set(key: String, value: Any) {
        store[key] = store[key]?.copy(value = value) ?: FormValue(value)
    }

    override fun setFormValue(key: String, value: FormValue<Any>) {
        store[key] = store[key]?.copy(index = value.index, rules = value.rules) ?: value
    }

    override fun verify(): Result<Unit> {
        store.values.sortedBy { it.index }.onEach { item ->
            item.rules.onEach { rule ->
                if (!rule.verify(item.value)) {
                    return Result.failure(FormValidException(rule, formValue = item))
                }
            }
        }
        return Result.success(Unit)
    }
}