package com.poke.core.extensions

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

val Long.asPokeNumber: String
    get() = String.format("%03d", this)

val Context.isLandscape
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

val Context.createHorizontalLinearLayoutManager
    get() = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

val Context.createGridLayoutManager
    get() = GridLayoutManager(this, if (isLandscape) 4 else 3, LinearLayoutManager.VERTICAL, false)

fun Context.resIdByName(resIdName: String?, resType: String): Int {
    resIdName?.let {
        return resources.getIdentifier(it, resType, packageName)
    }
    return 0
}