package com.looptry.form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.looptry.form.rule.IRule

interface FormStore {
    val store: StateFlow<Map<String, FormValue<Any>>>

    fun verify(): Result<Unit>

    fun set(key: String, value: Any)

    fun setFormValue(key: String, value: FormValue<Any>)

    fun setRules(key: String, rules: List<IRule>)
}

/**
 * 获取表单单个数据
 * @receiver FormStore
 * @param key String
 * @param default T
 * @return T
 */
@Composable
inline fun <reified T> FormStore.get(key: String, default: T): T {
    val formState by this.store.collectAsState()
    val formValue = formState[key]?.value
    return if (formValue != null && formValue is T) {
        formValue
    } else {
        default
    }
}

@Composable
fun rememberFormStore(): FormStore {
    val store = remember { mutableStateMapOf<String, FormValue<Any>>() }
    val state = remember(store) { FormStoreImpl() }
    return state
}

class FormStoreImpl : FormStore {
    private val _store = MutableStateFlow<Map<String, FormValue<Any>>>(emptyMap())
    override val store: StateFlow<Map<String, FormValue<Any>>> = _store

    override fun set(key: String, value: Any) {
        val updateStore = _store.value.toMutableMap()
        updateStore[key] = updateStore[key]?.copy(value = value) ?: FormValue(value)
        _store.value = updateStore
    }

    override fun setFormValue(key: String, value: FormValue<Any>) {
        val updateStore = _store.value.toMutableMap()
        updateStore[key] = updateStore[key]?.copy(index = value.index, rules = value.rules) ?: value
        _store.value = updateStore
    }

    override fun setRules(key: String, rules: List<IRule>) {
        val updateStore = _store.value.toMutableMap()
        updateStore[key] = updateStore[key]?.copy(rules = rules) ?: FormValue(null, rules = rules)
        _store.value = updateStore
    }

    override fun verify(): Result<Unit> {
        val items = store.value.values.sortedBy { it.index }
        items.forEach { item ->
            item.rules.forEach { rule ->
                val result = rule.verify(item.value)
                if (result.isFailure) {
                    return Result.failure(
                        FormValidException(
                            formValue = item,
                            rule = rule,
                            errorMsg = result.exceptionOrNull()?.message ?: "校验未通过"
                        )
                    )
                }
            }
        }
        return Result.success(Unit)
    }
}