package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/8/3
 *  desc :
 */
fun main() {
    // 方法找质数
    val list = (2..1000).toList().filter { it.isPrime() }.take(100)
    println(list)

    /** 针对某个序列，你可能会定义一个只要序列有新值产生就被调用一下的函数，
    这样的函数叫迭代器函数，要定义一个序列和它的迭代器，你可以使用Kotlin的序
    列构造函数generateSequence， generateSequence函数接受一个初始种子值
    列构造函数产生序列，产生序列函数接受一个初始种子值
    作为序列的起步值，在用generateSequence定义的序列上调用一-个函数时，ge
    nerateSequence函数会调用你指定的迭代器函数，决定下一-个要产生的值。
    与上面的方法相等 **/
    val primeSequence = generateSequence(2) { it -> it + 1 }.filter { it.isPrime() }.take(100)
    println(primeSequence.toList())

}

// 是否是素数
fun Int.isPrime(): Boolean {
    (2 until this).map {
        if (this % it == 0) {
            return false
        }
    }
    return true
}
