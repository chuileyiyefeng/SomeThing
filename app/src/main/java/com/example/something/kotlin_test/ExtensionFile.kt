package com.example.something.kotlin_test

import android.view.View

/**
 *  create by pan yi on 2021/7/20
 *  desc :扩展文件 调用需要引入包
 */
fun <T> Iterable<T>.randomTake(): T = this.shuffled().first()

fun <T> Iterable<T>.randomTake2(): T = this.shuffled().first()

fun View.visible() {
    this.visibility = View.VISIBLE
}
