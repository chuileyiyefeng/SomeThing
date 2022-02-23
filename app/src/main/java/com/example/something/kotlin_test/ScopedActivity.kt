package com.example.something.kotlin_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

/**
 *  create by pan yi on 2020/10/29
 *  desc : 封装协程的Activity
 */
open class ScopedActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch {

        }
        val s: Deferred<String> = async {
            delay(1000)
            "100"
        }

        val deferredList = listOf(async {
            "test"
        }, async {
            "haha"
        })

        lifecycleScope.launch {
            deferredList.awaitAll()
            val result = withContext(Dispatchers.Default) {
                "test"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}