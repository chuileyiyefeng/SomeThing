package com.example.something.kotlin_test

/**
 *  create by pan yi on 2021/7/20
 *  desc :扩展文件 调用需要引入包
 */
fun <T> Iterable<T>.randomTake(): T = this.shuffled().first()

fun <T> Iterable<T>.randomTake2(): T = this.shuffled().first()
