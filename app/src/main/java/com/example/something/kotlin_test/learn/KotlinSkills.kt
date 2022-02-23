package com.example.something.kotlin_test.learn

import java.util.*
import kotlin.contracts.contract

/**
 * @Description: 一些kotlin使用技巧
 * @Author: pan yi
 * @Date: 2022/2/10
 */
fun main() {
    val oldName = "jack tomas"
    val newName = "Jack toMas"
    //
//    val isSameName=oldName.toUpperCase() == newName.toUpperCase()// 此方法会造成额外内存开销
    val isSameName = oldName.equals(newName, ignoreCase = true)
    val emptyContent = ""

    //判断空值然后赋值
    val contentVale = if (emptyContent.isEmpty()) "content" else "hasContent"
    val contentValue = emptyContent.ifBlank {
        "hasContent"
    }
    // 字符串转数字
    val numberContent = "5050"
    val number = numberContent.toIntOrNull() ?: 0

    val list = listOf("test", "happy", "cold")
    val listToContent = list.joinToString(
        separator = " 哈哈哈 "// 分隔符
        , prefix = "[" //前缀
        , postfix = "]" //后缀
    )
//    println(listToContent)

    val data = "** hello world **"
//    println(data.removePrefix("**")) //移除前缀
//    println(data.removeSuffix("**")) //移除后缀
//    println(data.substringAfter("o"))
//    println(data.substringBefore("o"))
//    println(data.substringBefore("哈哈哈"))
//    println(data.substringBefore("哈哈哈","no match"))

    // plus操作符
    val operatorMap = mapOf(
        "one" to "one",
        "two" to "two",
        "three" to "three"
    ).withDefault { "no value" }//withDefault设置默认值
//    println(operatorMap.plus(Pair("four", "four")))
//    println(operatorMap-"one")
//    println(operatorMap- listOf("one","two"))
//    println(operatorMap-"haha")
//    println(operatorMap.getValue("five"))

    val input = "kotlin"
    when (input) {
        in listOf("ha", "he", "xi", "kotlin") -> {
            println("good")
        }
        else -> {
            println("not found")
        }
    }

}