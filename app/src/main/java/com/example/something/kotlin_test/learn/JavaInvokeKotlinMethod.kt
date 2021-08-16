package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/8/9
 *  desc : 注解供Java调用
 */
class JavaInvokeKotlinMethod {
    @JvmField // 此注解 可以将字段暴露给Java调用，从而避免使用get()方法
    val spells = listOf("1", "2", "3", "4", "5")

    @JvmOverloads// 此注解，生成kotlin函数的重载版本
    fun setInfo(name: String = "张三", age: Int = 10, money: Int = 1000) {
        println("名字是 $name 年龄 $age")
    }

    companion object {
        @JvmField// 此注解还能用来以静态方式提供伴生对象里定义的值
        val name = "张三"

        @JvmStatic // 此注解类似于JvmField,允许直接调用伴生对象的函数
        fun getNameLength() = name.length
    }
}