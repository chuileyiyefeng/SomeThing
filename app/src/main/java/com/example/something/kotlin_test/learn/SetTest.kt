package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/6
 *  desc : set  set元素不可重复
 */
fun main() {
    val setList = setOf("this", "other", "that", "that")
    val s = setList.elementAt(2)
    println(s)

    val mutableSet = mutableSetOf("this", "other", "that", "that")
    mutableSet+="thats"
    val s2 = mutableSet.elementAt(3)
    println(s2)

    // list转set可以去重
    val newList=setList.toSet().toList()
    println(newList)
    // 更直接的方法
    val newList2=listOf("this", "other", "that", "that").distinct()
    println(newList2)
}