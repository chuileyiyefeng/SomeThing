package com.example.something.kotlin_test.learn

import com.example.something.kotlin_test.randomTake

// 给扩展文件取别名
import com.example.something.kotlin_test.randomTake2  as randomAlias

/**
 *  create by pan yi on 2021/7/20
 *  desc : 扩展函数测试
 */
fun String.addText(amount: Int = 1) = this + "!".repeat(amount)

fun Any.easyPrint() = println(this)


val String.numVowels  // 扩展属性
    get() = count { "aeiou".contains(it) }

fun <T> T.easyPrint2(): T {
    println(this)
    return this
}

// 可空类型的扩展函数

fun String?.printWithDefault(defaultContent: String) = print(this ?: defaultContent)

// infix 适用于有单个参数的扩展函数 接收者和函数之间的点操作以及参数的一对括号都可以不要
infix fun String?.printWithDefault2(defaultContent: String) = print(this ?: defaultContent)

fun String?.printWithDefault3(defaultContent: Int.()->Unit) = print(this ?: defaultContent)

fun main() {
//println("test".addText(3).easyPrint())
//println("test".easyPrint().addText(3)) 这样写不行 any!=string
    println("Test".easyPrint2().addText(3))
    val count = "the people's Republic of china".numVowels.easyPrint2()
    println(count)

    var defaultContent: String? = "not null"
    defaultContent = null
    defaultContent.printWithDefault("this is null")

    defaultContent printWithDefault2 "this is infix not null"

    val list = listOf<String>("这些", "这个", "那个")
    val item = list.randomTake()
    // 这个是扩展文件取别名
    val item2 = list.randomAlias()

    "test".printWithDefault3 {
        plus(10)
    }
}