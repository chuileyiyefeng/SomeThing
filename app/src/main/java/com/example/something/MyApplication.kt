package com.example.something

import android.app.Application
import android.content.Context

/**
 *  create by pan yi on 2020/10/27
 *  desc : MyApplication
 */
class MyApplication : Application() {
    companion object{
         lateinit var context: Context
    }
    override fun getApplicationContext(): Context {
        context = super.getApplicationContext()
        return context
    }
}