package com.example.something.kotlin_test.learn

import java.io.IOException

/**
 *  create by pan yi on 2021/8/6
 *  desc : kotlin调用Java
 */
fun main() {
    val javaKt = JavaInvokeKt()
    val notNullString = javaKt.notNullString()
    println(notNullString)

    val nullString = javaKt.nullString()//平台类型 String!
    nullString?.toUpperCase()

    val nullAbleString = javaKt.nullAbleString()?.toUpperCase()//有注解变为String?类型

    println(javaKt.x.javaClass)//所有类型会映射成Java类型


    println(javaKt.hintPoint)//会直接调用get方法
    javaKt.hintPoint = 456//为hintPoint赋值需要提供set方法

    // Java的异常 可处理也可不处理
//    javaKt.extendHandlerException()

    // Java的异常 可处理也可不处理
    try {
        javaKt.extendHandlerException()
    } catch (e: Exception) {
        println("catch exception")
    }
}

val transfer = { params: String ->
    println(params)
}

// java直接调用catch不到IOException这个异常
// 使用注解
@Throws(IOException::class)
fun throwException() {
    throw  IOException()
}

fun makeKotlinName(): String {
    return "KotlinName"
}
