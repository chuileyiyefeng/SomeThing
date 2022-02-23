package com.example.something.kotlin_test

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.test.withTestContext

/**
 *  create by pan yi on 2020/10/27
 *  desc : 协程测试
 */
open class CoroutineTest {
    companion object {
        @JvmStatic
        fun main(strings: Array<String>) {
//            GlobalScope.launch {
//                delay(1000L)
//                println("协程延迟输出")
//            }
//            println("程序开始")
//            runBlocking {
//                delay(2000L)
//                job.join()
//            }
//            Thread.sleep(2200)
//            println("程序最终停止")
//            runBlocking {
//                delay(2000L)
//            }
//            testBlock2()
//            runOutTime()

            // 并发创建任务
//            GlobalScope.launch {
//                val deferredOne: Deferred<Int> = async {
//                    delay(1000)
//                    println("asyncOne")
//                    100//这里返回值为100
//                }
//                val deferredTwo: Deferred<Int> = async {
//                    delay(2000)
//                    println("asyncTwo")
//                    100//这里返回值为100
//                }
//                val deferredThree: Deferred<Int> = async {
//                    delay(3000)
//                    println("asyncThree")
//                    100//这里返回值为100
//                }
//                println("time= ${deferredOne.await() + deferredTwo.await() + deferredThree.await()}s")
//            }
//

            GlobalScope.launch(Dispatchers.IO) {
                for (i in 0 until  10){
                    launch {
                        println("test dispatcher $i")
                    }
                }
            }
            Thread.sleep(5000)
        }

        // 协程witchContext是串行
        // launch是非阻塞的，runBlocking是阻塞的
        private fun testBlock() = runBlocking {
            val job = launch {
                repeat(1000) {
                    println("method $it")
                    delay(500)
                }
            }
            delay(1300)
            job.cancel()
            delay(1500)
            job.join()
            println("end of method")
        }

        private fun testBlock2() = runBlocking {
            val startTime = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var nextPrintTime = startTime
                var i = 0
                while (i < 5) {
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        println("job: I'm sleeping ${i++} ... ${System.currentTimeMillis()} ... $nextPrintTime")
                        nextPrintTime += 500L
                    }
                }
            }
            delay(1300L)
            println("main: I'm tired of waiting!")
            job.cancelAndJoin()
            println("main: Now I can quit.")
        }

        private fun runOutTime() = runBlocking {
            val result = withTimeoutOrNull(300) {
                repeat(10) {
                    println("test out time")
                    delay(100)
                }
            }
            println("runOutTime $result")
        }



    }
}