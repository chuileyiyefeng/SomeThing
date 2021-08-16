package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/8
 *  desc :object对象声明 单例的一种写法
 */
object ApplicationConfig {
    init {
        println("这个是初始化")
    }

    fun printInfo() {
        println("打印信息")
    }
}

private open class Player {
    open fun load() = "loading something"
}

fun main() {
    // 用object修饰的类 既是类名也是实例名
    ApplicationConfig.printInfo()
    println(ApplicationConfig)
    println(ApplicationConfig)

    // 相当于匿名内部类 Java的例子 new onClickListener()
    val player = object : Player() {
        override fun load(): String {
            return "我继承了，我修改了"
        }
    }
    println(player.load())
}