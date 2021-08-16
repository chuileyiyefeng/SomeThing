package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/1
 *  desc :run测试 run返回 lambda最后一行返回值
 */
fun main() {
    "com.example.something.kotlin_test.learn"//链式调用
        .run(::isTooLong)
        .run(::showMessage)
        .run(::println)
}

fun isTooLong(content: String): Boolean = content.length >= 10;

fun showMessage(isL: Boolean): String {
    return if (isL) {
        "is too long"
    } else {
        "is not long"
    }
}