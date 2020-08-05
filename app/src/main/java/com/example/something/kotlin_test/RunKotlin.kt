package com.example.something.kotlin_test


fun main() {
    val test = TestObj("age", 1, 2)
//    val inner = TestObj.Inner()
//    inner.toPrint()
//    println(testObj.testWhen())
//    println(TestSingle.printSingle())
    val a = { x: Int -> x * x }
//    println(testObj.sum(1, 10, a))

//    println(testObj.sum(1, 10) { it + it + it })
//    listOf(2, 4, 5, 6, 7, 9)
//        .filter { it > 6 }
//        .forEach { println(it) }
// 将集合中的字符串都转换成大写，并打印出来。
//    listOf("java", "kotlin", "flutter")
//        .map { it.toUpperCase() }
//        .forEach { println(it) }

//    遍历所有的元素，为每一个创建一个集合，最后把所有的集合放在一个集合中。
//    val newList = listOf(5, 6, 7, 8)
//        .flatMap {
//            listOf(it + 1)
//        }
//    println(newList)
//    listOf(1, 2, 3, 4, 5)
//        .filter { it > 2 }
//        .forEach { println(it) }
//    val runMe = Runnable {
//        println("i am new runnable")
//    }
//    Thread (runMe).start()
//    println("134234xyz".checkResult())

//    val result = "Hello".apply {
//        println("$this world")
//    }
//    run 函数类似于 apply 函数，但是 run 函数返回的是最后一行的值。
    val runResult="hello".run {
        println("$this world")
        "$this 1111"
    }
    println(runResult)
}

// 扩展函数
fun String.checkResult(): Boolean {
    return this.contains("xyz")
}


val sum = { x: Int, y: Int -> x + y }