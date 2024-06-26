package com.init

import android.content.Context
import androidx.startup.Initializer
import contextCache

class LibInitializer : Initializer<Unit> {
    override fun create(context: Context): Unit {
        contextCache.value = context
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}