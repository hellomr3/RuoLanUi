package com.looptry.form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember

interface FormStore {
    val values: Map<String, Any>

    fun verify(onError: (String) -> Unit): Boolean

    fun <T> get(key: String, default: T): T

    fun set(key: String, value: Any)

    fun setFormValue(key: String, value: FormValue<Any>)
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

    override fun <T> get(key: String, default: T): T {
        return runCatching { (store[key] as? FormValue<T>)?.value }.getOrNull() ?: default
    }

    override fun set(key: String, value: Any) {
        store[key] = store[key]?.copy(value = value) ?: FormValue(value)
    }

    override fun setFormValue(key: String, value: FormValue<Any>) {
        store[key] = store[key]?.copy(index = value.index, rules = value.rules) ?: value
    }

    override fun verify(onError: (String) -> Unit): Boolean {
        store.values.sortedBy { it.index }.onEach { item ->
            item.rules.onEach { rule ->
                if (!rule.verify(item.value)) {
                    onError(rule.errorMsg)
                    return false
                }
            }
        }
        return true
    }
}