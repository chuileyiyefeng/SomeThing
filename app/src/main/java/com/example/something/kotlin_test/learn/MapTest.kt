package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/7
 *  desc :
 */
fun main() {
    val map = mapOf("that" to 20, "this" to 10, "what" to 30)
    val map2 = mapOf(Pair("that", 20), Pair("this", 10), Pair("what", 30))
    println(map)
    println(map2)

    println(map["that"])
    println(map.getValue("that"))
    println(map.getOrElse("those") {
        "嘻嘻"
    })
    println(map.getOrDefault("what", 20))

    map.forEach {
        println("${it.key} , ${it.value}")
    }
    map.forEach { (ss, ii) ->
        println("$ss , $ii")
    }

    // 可变MAP
    val mutableMap = mutableMapOf("that" to 20, "this" to 10, "what" to 30)
    mutableMap += "add" to 30
    mutableMap.put("add", 40)
    mutableMap.getOrPut("there", { 50 })//没有就添加一个键值对

    println(mutableMap)
}