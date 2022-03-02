package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/9
 *  desc :
 */
data class Student(var name: String, var age: Int) {
    var score = 10;
    private val hobby = "Music"
    private val subject: String

    init {
        println("init student")
        subject = "math"
    }

    constructor(name: String) : this(name, age = 10) {
        score = 20
    }

    override fun toString(): String {
        return "Student(name='$name', age=$age, score=$score, hobby='$hobby', subject='$subject')"
    }

    // 运算符重载
    operator fun plus(other: Student) = Student(name + other.name, age + other.age)
}

fun main() {
    val student1 = Student("张三")
    val student2 = student1.copy(name = "李四")// copy不会调用次构造函数
    println(student1.toString())
    println(student2.toString())

    //dataClass支持解构语法
    val (x, y) = Student("张三", 20)
    println("$x , $y")
    println((student1 + student2).toString())
}