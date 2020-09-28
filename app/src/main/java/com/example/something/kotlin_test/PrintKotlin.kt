package com.example.something.kotlin_test

class PrintKotlin {
    fun run() {
        val array = arrayOf("测试一", "测试二")
        println(toList(*array))
    }

    fun <T> toList(vararg items: T): ArrayList<T> {
        val result = ArrayList<T>()
        for (item in items) {
            result.add(item)
        }
        return result
    }

    // 局部函数 函数里定义另外一个函数
    fun validate(bean: TestBean) {
        fun validateInput(string: String?) {
            if (string != null && string.isEmpty()) {
                return
            }
            println(string)
        }
        validateInput(bean.name)
        validateInput(bean.time)
    }

}