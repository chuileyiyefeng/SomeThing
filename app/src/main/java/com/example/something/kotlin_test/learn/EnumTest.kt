package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/12
 *  desc :枚举测试  kotlin的枚举类可以写函数 可以有属性
 */
fun main() {
    println(ForEnum.EAST.upDateData(Student("测试",100)))
}

private enum class ForEnum(private val student: Student) {
    // 枚举类里east、west...都这个类ForEnum的实例
    EAST(Student("张三",2))
    ,WEST(Student("李四",4))
    ,SOUTH(Student("王五",6))
    ,NORTH(Student("赵六",8));


    fun upDateData(upStudent: Student)=Student(student.name+upStudent.name,student.age+upStudent.age)
}