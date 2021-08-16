package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/27
 *  desc :map压缩  合并一个集合 返回一个包含键值对的新集合
 */
fun main() {
    val nameList = listOf("Jack", "Tom", "Jimmy")
    val ageList = listOf(20, 25, 30, 10)
    val map = nameList.zip(ageList).toMap()
    println(map)


    // fold 这个合并函数接受一个初始累加器值 随后根据匿名函数结果更新
    val foldedValue = listOf(2, 3)
        .fold(10) { accmulator, number ->
            accmulator + number * 3
        }
    println(foldedValue)
}