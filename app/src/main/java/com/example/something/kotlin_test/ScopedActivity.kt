package com.example.something.kotlin_test

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 *  create by pan yi on 2020/10/29
 *  desc : 封装协程的Activity
 */
open class ScopedActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onDestroy() {
        super.onDestroy()
        cancel()
        lifecycleScope.launch {

        }
    }
}