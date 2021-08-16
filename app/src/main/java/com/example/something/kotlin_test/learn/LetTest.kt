package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/1
 *  desc :Let测试
 */
fun main() {
    val result = listOf(10, 1, 2, 3).first().let {
        it * it
    }
    println(result)
   println(welcome(null))
}

fun welcome(name: String?): String {// 链式调用风格
    return name?.let {
        "welcome $it"
    } ?: "what is your name?"
}
