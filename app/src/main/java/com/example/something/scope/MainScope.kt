package com.example.something.scope

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.Exception
import kotlin.system.measureTimeMillis

/**
 *  create by pan yi on 2021/1/7
 *  desc :
 */
fun main(args: Array<String>) {
    GlobalScope.launch {
        delay(200)
        println("global")
    }
    runBlocking {
        delay(1000)
        println("runBlocking")
    }


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
