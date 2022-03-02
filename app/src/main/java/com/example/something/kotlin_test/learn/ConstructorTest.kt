package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/7
 *  desc :构造函数test
 */
fun main() {

    val test = Test("Jack", 30)
    println("${test.name} , ${test.age} ")
    test.test()
}

// 初始化顺序
// 主函数声明的属性
// 类级别的属性赋值
// init代码块里的属性赋值和函数调用
// 次构造函数的属性赋值和函数调用
class Test(_name: String, var age: Int, var isGirl: Boolean) {
    var name = _name// 可以用这种_写法，也可以用var修饰

    var grade: String = "二年级"

    lateinit var latTest: String

    init {// init初始化块 类构造实例时执行
        require(age < 100) { "年纪小于100" }//先决条件函数
        require(name.isNotBlank()) { "名字不能为空" }//先决条件函数
    }

    constructor(name: String) : this(name, age = 10, isGirl = false) { //次构造函数

    }

    constructor(name: String, age: Int) : this(name, age = 10, isGirl = false) { //次构造函数
        this.name = "hahaha"
    }

    fun test() {
        if (::latTest.isInitialized) {// 检查延迟赋值属性
            println(latTest)
        }
    }
}