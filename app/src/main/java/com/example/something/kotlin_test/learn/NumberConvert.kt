package com.example.something.kotlin_test.learn

import kotlin.math.roundToInt

/**
 *  create by pan yi on 2021/6/30
 *  desc : 转换函数
 */
fun main() {

    val convertValue = "18.46546".toIntOrNull() //安全转换函数
    println(convertValue)//

    println(8.564654646.roundToInt())//四舍五入转换

    val s="%.2f".format(8.565545)
    println(s) // 保留小数位数转string 会四舍五入
}
