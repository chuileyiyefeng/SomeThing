package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/27
 *  desc :变换函数
 *  变换是函数式编程的第一大类函数，变换函数会遍历集合内容，用一个以值参形
式传入的变换器函数，变换每一-个元素，然后返回包含已修改元素的集合给链.上
的其他函数。
 */

fun main() {
    val list= listOf("张三","李四","王五","赵六")
    val list2=list.map {//原来的list值没有改变
     "$it age=18"
    }.map {
        "$it money=1000$"
    }
    println(list2)
    val mapListTrans = mapList.map { "$it 年纪是100" }
    println(mapListTrans)
    // flatMap将集合转化为另一个集合
    val flapMapTrans = listOf(flatMap1, flatMap2).flatMap { it }
    println(flapMapTrans)

}

val mapList = listOf("张三", "李四", "王五", "赵六")

val flatMap1 = listOf(1, 2, 3)
val flatMap2 = listOf(4, 5, 6)
