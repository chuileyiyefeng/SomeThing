package com.example.something.kotlin_test.learn

import java.util.*

/**
 *  create by pan yi on 2021/7/7
 *  desc :
 */
fun main() {
    val t = TestT()
    t.name = "  rose "
    println(t.name)
}

class TestT {
    var name = "Jack"
        get() = field.capitalize(Locale.CHINA)
        set(value) {
            field = value.trim()
        }
}