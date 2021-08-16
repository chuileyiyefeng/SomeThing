package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/9
 *  desc : data类测试
 */
// data修饰会重写equal方法
data class Coordinate(var x: Int, var y: Int) {
    val isBound = x > 10 && y > 10
}

fun main() {
    // ==比较的是内容 ,equals,Any类默认实现===,比较引用
    // ===比较的是引用
    val classA = Coordinate(10, 20)
    val classB = Coordinate(10, 20)

    println(classA == classB)
    println(classA.equals(classB))
}