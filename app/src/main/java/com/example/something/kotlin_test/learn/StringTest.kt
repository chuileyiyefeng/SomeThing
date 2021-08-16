package com.example.something.kotlin_test.learn

/**
 *  create by pan yi on 2021/6/30
 *  desc : string测试
 */
const val testContent = "content's test"

const val testContents = "tom,jerry,jack,tony"
fun main() {
    val index = testContent.indexOf("\'")
    val result = testContent.substring(0 until index) //string截取
//    println(result)

    val contents = testContents.split(",")
    val (s1, s2, s3, s4) = contents //解构语法特性
//    println("$s1 $s2 $s3 $s4")

    val str1="KPMG: China's A-share listings doubled in 2021"

    val str2=str1.replace(Regex("[aeiou]")){ //replace语法
        when(it.value){
            "a"->"1"
            "e"->"2"
            "i"->"3"
            "o"->"4"
            "u"->"5"
            else->it.value
        }
    }
//    println(str2)

}