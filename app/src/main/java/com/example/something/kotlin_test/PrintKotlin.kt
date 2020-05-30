package com.example.something.kotlin_test

class PrintKotlin {
    fun run() {
        val testObj = TestObj("String", 2)
//        testObj.print()
        println(testObj.lazyName)
    }
}