package com.example.something.kotlin_test.learn

import java.util.*

/**
 *  create by pan yi on 2021/6/29
 *  desc : 闭包 返回值是函数的函数
 */
fun main() {
    val content = getBoardContent()
    println(content("赵六"))
}

fun getBoardContent(): (String?) -> String {
    val year = (1..24).shuffled().last() //随机数打乱 取最后一位
    return {
        val s = it!!.capitalize(Locale.ROOT)// 两个感叹号 非空断言操作符
        "今年是${year}年，名字是${it}"
    }
}

fun getValue(str: String) = run { println(str) }

