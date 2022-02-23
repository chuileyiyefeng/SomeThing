package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/12
 *  desc : 解构语法
 */
fun main() {
    val score = PlayerScore("科技", 20)
    val (x, y) = PlayerScore("科技", 20)

    println("$x $y")

    // map使用

    val map = HashMap<Int, String>()
    map[1] = "1"
    map[2] = "2"
    map[3] = "3"
    for ((k, v) in map) {
        println("k=$k v=$v")
    }
}

class PlayerScore(private val value1: String, private val value2: Int) {
    operator fun component1() = value1
    operator fun component2() = value2
}
