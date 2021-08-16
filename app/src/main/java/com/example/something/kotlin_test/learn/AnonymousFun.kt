package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/6/28
 *  desc : 参数是函数的方法
 */
fun main() {
    val getBoardContent = { year: Int, name: String ->//匿名函数
        "今年是${year}年，名字是${name}"
    }
    showOnBoard("张三", getBoardContent)

    showOnBoard("李四") { year: Int, name: String -> //上面方法的简写
        "今年是${year}年，名字是${name}"
    }
    showOnBoard("王五", ::getBoardContent)// 具名函数的调用
}

private fun getBoardContent(year: Int, name: String): String {
    return "今年是${year}年，名字是${name}"
}

fun showOnBoard(name: String, getBoardContent: (Int, String) -> String) {
    val year = (1..24).shuffled().last() //随机数打乱 取最后一位
    println(getBoardContent(year, name))
}