package com.example.something.kotlin_test

import java.io.File

/**
 *  create by pan yi on 2021/1/29
 *  desc :
 */
open class KotlinMain {
    val b = 3//只读变量不能叫常量 作为局部变量等价于Java的：final int b=3
    val a: Int  // 例如这里是只读变量，它的值就是会变化的
        get() {
            return (Math.random() * 100).toInt()
        }


    companion object {
        const val c = 4 //只能定义在全局范围 只能修饰基本类型 必须初始化  // 编译时常量，编译时即可调用的

        // SAM  是 single abstract method 简称  SAM转换 在Java里的含义 一个参数类型只有一个方法的接口方法
        private val intList = intArrayOf(1, 2, 3, 4, 5)
        var stest = "测试set"

        private fun listForEach() {
            intList.forEach {// forEach是内联函数
                if (it == 3) {
                    return@forEach //跳出这一次,相当于for循环得 continue
//                    return // 相当于跳出listForEach这个方法
                }
//                println(it)
            }
//            intList.asSequence()// 加了asSequence forEach就相当于阀门，水流式的往下走 懒序列模式
            intList.asSequence()// 不加 asSequence 每经过一个操作，全部遍历完
                .filter {
                    print("filter ")
                    it % 2 == 0
                }.map {
                    print("map ")
                    it * 2 + 1
                }.forEach {//forEach是阀门，调用这个方法上面的才会调用
                    println(it)
                }
        }

        @JvmStatic
        fun main(strings: Array<String>) {
            val c0 = intArrayOf(1, 2, 3, 4, 5)//数组
            val c1 = IntArray(5) { it + 2 }
//            println(c1.contentToString())
//            listForEach()

            val testBean = TestBean()
            val pair = "hello" to "word"
//            println(pair)
            run outSide@{// 退出forEach循环的正确姿势
                c1.forEach {
//                    println("it的值是$it")
                    if (it > 3) {
                        return@outSide
                    }
//                    println("----it的结果值----是$it")
                }
            }
            TestEnum.Busy.run()
            TestEnum.Idle.run()

            val s = 3
            val b = testBean.isEmptyStr("")
            // 枚举类 值可数 密封类 类型可数
//            val d=s+testBean
//            println(b)
//            listForEach()
//            testBean.let(::println)
//            testBean.run {
//                println()
//            }
            val file = File("build.gradle").readText().toCharArray()
                .filterNot { it.isWhitespace() }
//            println(file)
            val listInt = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
            listInt.filter {
                it % 2 == 0
            }.forEach {
//                println(it)
            }


        }
    }

    // 运算符重载
}

enum class TestEnum : Runnable {
    Idle, Busy {
        override fun run() {
            println("自己实现的run方法")
        }
    };

    override fun run() {
        println("公众区域的实现方法")
    }
}

operator fun Int.plus(b: TestBean): Int {
    return this + b.name.length
}