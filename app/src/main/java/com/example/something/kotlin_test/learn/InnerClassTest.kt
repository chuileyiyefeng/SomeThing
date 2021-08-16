package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/8
 *  desc :嵌套类(内部类)测试
 */
private class Players {
    class Experience {


        fun showExp() {
            println("经验值2000")
        }
    }
}

fun main() {
    println(Players.Experience().showExp())
}