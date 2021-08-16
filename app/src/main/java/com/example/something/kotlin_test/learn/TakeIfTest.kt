package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/1
 *  desc : takeIf 判断lambda表达式 为true返回对象 为false返回null
 */
fun main() {
    val s = "这个作为测试take if的字符集合".takeIf {
        it.contains("s") && it.length > 10
    }
    println(s)
    val takeS = listOf(1, 2, 3, 4, 5, 6).takeIf {
        it.size > 5
    }
    println(takeS)

    //takeUnless函数  与takeIf使用相反
    val takeUnless = "作为takeUnless的测试".takeUnless {
        it.contains("嘻嘻哈哈")
    }
    println(takeUnless)
}