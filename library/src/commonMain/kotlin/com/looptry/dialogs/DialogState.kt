package com.looptry.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue


@Composable
fun rememberDialogState(initState: Boolean): DialogState {
    return rememberSaveable(saver = DialogState.Saver()) {
        DialogState(initState)
    }
}


class DialogState(private val initState: Boolean = false) {

    var visible by mutableStateOf(initState)
    fun show() {
        visible = true
    }

    fun hide() {
        visible = false
    }

    companion object {
        fun Saver(): Saver<DialogState, *> = Saver(save = {
            it.visible
        }, restore = {
            DialogState(it)
        })
    }
}