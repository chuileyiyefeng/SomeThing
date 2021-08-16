package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/8
 *  desc :伴生对象
 */
class Config {
    // 伴生对象
    companion object {
        private const val name = "小王"
        fun getName() = name
    }
}

fun main() {
    // 只有初始化Config类或者调用伴生对象的函数时，伴生对象的内容才会载入
    // 无论实例化Config多少次，伴生对象只有一个实例存在
    println(Config.getName())
}