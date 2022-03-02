package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/27
 *  desc :过滤函数
 */
fun main() {
    val listFilter = list.filter { it.contains("J") }
    println(listFilter)

    val listFilterAndMap = listOf(list, list2).flatMap { it.filter { it1 -> it1.contains("J") } }
    println(listFilterAndMap)

    // 取质数算法
    val number = listOf(7, 4, 8, 4, 3, 22, 23, 13, 77, 33)
    val numberTest = number.filter(fun(number: Int): Boolean {
        return (2 until number).map { number % it }.none { it == 0 }
    }).apply {
        println(this)
    }
}

val list = listOf<String>("Jimmy", "Jack", "Tom", "Coach")
val list2 = listOf<String>("Jimmy2", "Jack2", "Tom2", "Coach2")