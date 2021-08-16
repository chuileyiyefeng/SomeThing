package com.example.something.kotlin_test.learn

import java.lang.Exception

/**
 *  create by pan yi on 2021/6/30
 *  desc :自定义异常
 */
fun main() {
    var number: Int? = null
    try {
        checkNumber(number)
        number!!.plus(1)
    } catch (e: Exception) {
        println(e)
    }
}

fun checkNumber(number: Int?) {
//    number ?: throw NumberCheckException()
    checkNotNull(number,{"不安全的数据"}) //先决条件函数
}

class NumberCheckException : IllegalArgumentException("自定义错误")