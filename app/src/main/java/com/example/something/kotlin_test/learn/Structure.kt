package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/12
 *  desc : 解构语法
 */
fun main() {
    val (x, y) = PlayerScore("科技", 20)
    println("$x $y")
}

class PlayerScore(private val value1: String, private val value2: Int) {
    operator fun component1() = value1
    operator fun component2() = value2
}
