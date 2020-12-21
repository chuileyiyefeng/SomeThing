package com.example.something.utils

import android.content.Context
import android.content.SharedPreferences
import android.view.View

/**
 *  create by pan yi on 2020/10/23
 *  desc :
 */

fun SharedPreferences.edit(
    action: SharedPreferences.Editor.() -> Unit
) {
    val editor = edit()
    action(editor)
    editor.apply()
}

fun String.log(action: () -> Unit) {

}

fun Float.dp(context: Context): Float {
    return context.resources.displayMetrics.density * this + 0.5f
}

fun View.dpToPx(float: Float): Float {
    return context.resources.displayMetrics.density * float + 0.5f
}