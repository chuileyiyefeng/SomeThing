package com.example.something.kotlin_test

class TestObj(private val name: String, age: Int) {

    val i = 2
    val l = 3
    val j: Double = i.toDouble()
    val k: Float = j.toFloat()
    val oRandOr = i or l
    val andAnd = i and l


    val s = "example"
    val c = s[2]
    // 延迟属性
    val lazyName: String by lazy {
        print("这是延迟属性")
        "123"
    }

    //    如果没有任何指定，属性会默认使用getter和setter
    var introduct = ""

    //    自定义 getter和setter
    var custom: String = "default"
        get() = field.toUpperCase()
        set(value) {
            field = "custom $custom"
//            field=value
        }

    //    一个String可以像数组那样访问，并且被迭代：
    fun inTest() {
        for (c in s) {
            print(c)
        }
    }

    fun print() {
        println(name)
    }

    fun sumNumber(a: Int, b: Int): Int {
        return a + b
    }

    fun sumNumber2(a: Int, b: Int, c: Int): Int = a + b + c

    // kotlin的可变参数，定义方法的时候给参数一个默认值
    fun toast(tag: String = javaClass.name, time: Int = 3000, message: String = "这是一个默认提示") {
        println("tag为 $tag time时长 $time 提示为 $message")
    }

    // map映射
    fun makeMap(map: Map<String, Any?>) {
        val width: Int by map
        print("width is $width")
    }

    val list = listOf(1, 2, 3, 4, 5)
    //    any 如果至少有一个元素符合给出的判断条件，则返回true。
    //    all  如果全部的元素符合给出的判断条件，则返回true。
    //    count 返回符合给出判断条件的元素总数。
    //    forEachIndexed 相当于forEach，但是我们同时可以得到元素的index。
    //    drop 返回包含去掉前n个元素的所有元素的列表。
    //    dropWhile 返回根据给定函数从第一项开始去掉指定元素的列表。
    //     contains 如果指定元素可以在集合中找到，则返回true。
    //     elementAt 返回给定index对应的元素，如果index数组越界则会抛出IndexOutOfBoundsException。
    fun testList(): Boolean {
//        val count = list.foldRight("fold test") { total, next ->
//            total.toString() + next
//        }
//        list.forEachIndexed { index, value ->
//            println("index is $index value is $value")
//        }
//        kotlin ==是比较值  ===比较内存地址
//        println(listOf(4, 5) == list.drop(3))
//        println(listOf(4, 5) == list.dropWhile { it < 4 })
        return 4 == list.count {
            it > 2
        }
    }

    val a: Int? = null
    //    非空断言运算符（!!）将任何值转换为非空类型，若该值为空则抛出异常。
//    if 结果赋值给一个变量
    var nullStr: String? = null

    fun testNullSafe(): String {
        if (a != null) {
            print(a)
        }

        val b: Int = if (a == null) 1 else 2
        return a.toString()
    }

    //    when表达式
    var x = 2

    fun testWhen() {
        val testResult: String = when (x) {
            in 1..10 -> {
                "in 10"
            }
            in 1..5 -> {
                "in 5"
            }
            else -> {
                "no result"
            }
        }
        print(testResult)
    }

}