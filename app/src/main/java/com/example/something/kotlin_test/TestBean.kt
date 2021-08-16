package com.example.something.kotlin_test

class TestBean {
    var name = "123"
    var age = 0
    private var time = ""
        private set// 设置只能内部set  private修饰的set，只能类本部使用
        private get// private修饰的get 变量修饰符必须也为private

    val float1 = 1.0// 这个是double类型
    val float2 = 1f // 这个才是float类型

    val html = """
        <这是html>

    """.trimIndent()// trimIndent去除公共的空白

    val c0 = intArrayOf(1, 2, 3, 4, 5)//数组

    companion object {
        const val str = ""

    }

    fun eq() {
        if (name === time) {// ===比较的是引用

        }
        if (name == time) {// ==比较的是内容

        }
        val s = name.windowed(name.length, 1)
        s.forEach() {// 高阶函数最后一个可以写在最外面，{}表示
            println(it)
        }
    }

    // 匿名函数
    val function: () -> String = {
        val testValue = "testValue"
        "test $testValue "
    }

    // 匿名函数
    val function2 = {
        val testValue = "testValue"
        "test $testValue "
    }

    // 匿名函数
    val function3: (String) -> String = {
        val testValue = "testValue"
        "test $testValue $it"
    }

    // 匿名函数
    val function4: (String, Int) -> String = { strTest, age ->
        val testValue = "testValue"
        "test $testValue "
    }

    // 匿名函数
    val function5 = { strTest: String, age: Int ->
        val testValue = "testValue"
        "test $testValue "
    }

    // 中缀表达式
    infix fun isEmptyStr(str: String): Boolean {

        return str == ""
    }

}