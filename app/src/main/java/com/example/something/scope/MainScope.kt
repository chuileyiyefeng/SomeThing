package com.example.something.scope

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.weishe.baselibrary.utils.log
import java.lang.Exception
import kotlin.system.measureTimeMillis

/**
 *  create by pan yi on 2021/1/7
 *  desc :
 */
fun main() {
    log("start")
    GlobalScope.launch {
        launch {
            delay(400)
            log("delay a")
        }
        launch {
            delay(300)
            log("delay b")
        }
    }

    Thread.sleep(500)
    log("the end")

}


fun createFlow(): Flow<Int> = flow {
    for (i in 1..10) {
        emit(i)
    }
}


suspend fun getResult1(): Int {
    delay(3000)
    return 1
}

suspend fun getResult2(): Int {
    delay(4000)
    return 2
}
