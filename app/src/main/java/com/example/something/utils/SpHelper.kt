package com.example.something.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.something.MyApplication

/**
 *  create by pan yi on 2020/10/23
 *  desc :  SharedPreferences工具类
 */
class SpHelper {
    private val sharedName = "name"
    private val preferences by lazy {
        val context = MyApplication.context
        context.getSharedPreferences(sharedName, Context.MODE_PRIVATE)
    }

    // 经典用法
    fun classic(context: Context) {
        val sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("SP_KEY_RESPONSE", "response")
        editor.apply()
    }

    fun putString(key: String, value: String) {
        preferences.edit {
            putString(key, value)
        }
    }

}