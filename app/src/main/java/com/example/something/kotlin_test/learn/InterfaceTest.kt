package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/7/13
 *  desc :
 */
fun main() {

}

interface MoveAble {
    var move: String
    var speed: Int
    val distance: String
        get() = "一万米"

    fun speedTest(): String
}

class Car(override var speed: Int) : MoveAble {
    //重写属性可以改变val为var 反之不行
    override var distance: String  // get两种写法 是var时，可以不写set方法 val必须写set方法
        //        get() = "一千米"
        get() = super.distance
        set(value) {}
    override var move: String
        get() = "一千米"
        set(value) {}

    override fun speedTest(): String {
        return ""
    }

}