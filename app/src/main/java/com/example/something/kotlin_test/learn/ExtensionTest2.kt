package com.example.something.kotlin_test.learn

import java.io.File

/**
 *  create by pan yi on 2021/7/22
 *  desc : 扩展函数分解测试
 */
inline fun <T> T.apply2(block: T.() -> Unit): T {
    block()
    return this
}

fun main() {
    val file = File("pathName").apply2 {
        setReadable(true)
    }

    // 分解这个apply2方法
    fun File.ext(): Unit {
        setReadable(true)
    }

    val block = File::ext

    File("FileName").apply2(block)
}