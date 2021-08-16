package com.example.something.kotlin_test.learn

import com.example.something.kotlin_test.learn.Project.*

/**
 *  create by pan yi on 2021/7/13
 *  desc :密封类测试 要继承密封类 子类必须和它定义在同一个文件里面
 */
fun main() {
println(Project.D("这个是测试"))
}

private sealed class Project {
    object A:Project()
    object B:Project()
    object C:Project()
    class D(content: String):Project()
}

private class TestSealed(var project: Project) {
    fun checkProject(): String = when (project) {
        is A -> "测试A"
        is B -> "测试B"
        is C -> "测试C"
        is D -> "这是有构造函数的！$project.D"
    }
}