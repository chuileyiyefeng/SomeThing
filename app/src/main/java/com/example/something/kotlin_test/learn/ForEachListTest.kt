package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/6
 *  desc :list遍历方式
 */
fun main() {
    val list = listOf("this", "that", "other")
    for (s in list) {
        println(s)
    }
    list.forEach {
        println(it)
    }
    list.forEachIndexed { index, s ->
        println("$index is $s")
    }

    // 结构元素赋值  _表示不对这个元素赋值
    val (thisVlaue:String, _:String, otherValue:String) = list;
    println(otherValue)
}