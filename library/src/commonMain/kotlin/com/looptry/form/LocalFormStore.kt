package com.looptry.form

import androidx.compose.runtime.staticCompositionLocalOf

val LocalFormStore = staticCompositionLocalOf<FormStore> {
    throw RuntimeException("No Value")
}