package com.example.something.kotlin_test

import kotlinx.coroutines.*

/**
 *  create by pan yi on 2020/10/27
 *  desc : 协程测试
 */
open class CoroutineTest {
    companion object {

        private fun testC() = runBlocking {
            launch {
                delay(1000)
                println("(Main.kt:19)    ")
            }

            launch {
                withContext(NonCancellable) {
                    delay(2000)
                    println("(Main.kt:26)    ")
                }
            }

            delay(500) // 防止launch还未开启withContext就被取消
            cancel()
        }

        //无法try catch捕捉launch作用域的异常
        private fun testB() = runBlocking {
            try {
                launch {
                    throw  RuntimeException()
                }
            } catch (e: Exception) {

            }

        }

        @JvmStatic
        fun main(strings: Array<String>) {
            println("start")
            GlobalScope.launch {
                launch {
                    delay(400)
                    println("delay a")
                }
                supervisorScope {
                    launch(CoroutineExceptionHandler { _, _ -> }) {
                        throw NullPointerException()
                    }
                    launch {
                        delay(1000) // 即使上面的launch抛出异常也会继续执行这里
                        println("( Process.kt:18 )    ")
                    }
                }
                launch {
                    delay(350)
                    println("delay b")
                }

            }
            val job = GlobalScope.launch {
                val asyncA = async {
                    delay(1000)
                    2
                }
                val asyncB = async {
                    delay(2000)
                    3
                }
                println("哈哈哈")
                println(asyncA.await() + asyncB.await())
                val s = true
                while (s) {
                    println("ssss")
                }

            }


            println("job is cancel " + job.isCancelled)
            Thread.sleep(3000)

            println("the end")

            CoroutineScope(SupervisorJob()).launch {

            }
            testB()

        }
        // coroutineScope  独立协程作用域

    }


}