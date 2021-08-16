package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/5
 *  desc :
 */
fun main() {
    val list = listOf("this", "that", "other")
    println(list.getOrElse(4) { "no data" })
    println(list.getOrNull(4) ?: "null")

    //可变列表
    val mutableList = mutableListOf("this", "there", "that")
    mutableList.add("xixi")
    mutableList.remove("there")
    println(mutableList)

    //可以相互转换
    listOf("this", "that", "other").toMutableList()
    mutableListOf("this", "there", "that").toList()

    //mutator  能修改可变列表的函数 有统一名称mutable
    mutableList+="haha"
    mutableList-="xixi"
    mutableList.removeIf { it.contains("xixi") }
    println(mutableList)
}