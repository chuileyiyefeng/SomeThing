package com.example.something.kotlin_test

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
            GlobalScope.launch {
                delay(1000L)
                println("协程延迟输出")
            }
//            println("程序开始")
//            runBlocking {
//                delay(2000L)
//                job.join()
//            }
            Thread.sleep(2200)
            println("程序最终停止")
//            runBlocking {
//                delay(2000L)
//            }
//            testBlock()
        }

        // 协程witchContext是串行
        // launch是非阻塞的，runBlocking是阻塞的
        private fun testBlock() = runBlocking {
            launch {
                println("走到launch")
                delay(1000L)
                println("launch delay")
            }
            println("开始协程")
            coroutineScope {
                launch {
                    println("开始 coroutineScope")
                    delay(2000L)
                    println("中间 coroutineScope")
                }
                delay(3000L)
                println("coroutineScope delay")
            }
            println("end of method")
        }
    }


}