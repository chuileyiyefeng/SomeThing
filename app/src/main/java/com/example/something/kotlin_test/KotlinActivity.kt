package com.example.something.kotlin_test

import android.annotation.SuppressLint
import android.util.Log
import com.example.something.R
import com.example.something.net_work.base.BaseActivity
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.coroutines.*


class KotlinActivity : BaseActivity() {

    override fun bindLayout(): Int {
        return R.layout.activity_kotlin
    }

    var token = ""
    var user = ""

    @SuppressLint("SetTextI18n")
    override fun initView() {
        tv_test.setOnClickListener {
            Log.e(TAG, "主线程id：${mainLooper.thread.id}")
//           runBlocking {
//               repeat(5){
//                   Log.e(TAG, "协程执行$it 线程id：${Thread.currentThread().id}")
//                   delay(300L)
//               }
//           }

            // 协程三个参数 协程上下文：协程执行的线程；启动模式；协程体
            GlobalScope.launch {
//                delay(3000L)
//                Log.e(TAG, "协程执行  线程id：${Thread.currentThread().id}")
                Log.e("TAG", "协程开始: ")
//                val token = getToken()
//                val user = getUser()

                // async是不阻塞线程的,也就是说getToken和getUser是同时进行的
                val result1 = GlobalScope.async {
                    getToken()
                }
                val result2 = GlobalScope.async {
                    getUser()
                }

//                Log.e(TAG, "$token  $user 线程id：${Thread.currentThread().id}")
                val result = result1.await() + result2.await()
                Log.e(TAG, result)
            }
            Log.e(TAG, "协程结束: ")
        }
        val tvCtrl: Controller<TestBean> = Controller()
        val bean = TestBean()
        val string = ""


        turnOn(bean)
        turnOff(string)

        val university: University<Student> = University("")
        val femaleUniversity: University<FemaleStudent> = University<FemaleStudent>("")

        val student: Student? = university.get()
        val femaleStudent: Student? = university.get()

    }

    // 使用处协变 out修饰，只往外取
    fun unGet(university: University<out Student>) {
        val femaleStudent: Student? = university.get()
    }
    // 使用处逆变 in修饰，只往里放
    fun unPut(university: University<in Student>) {
        university.put(FemaleStudent())
    }
    class University<T>(val name: String) {
        fun get(): T? {
            return null
        }

        fun put(student: T) {

        }
    }

    private fun <T> turnOn(obj: T) {

    }

    private fun <T> turnOff(obj: T) {

    }

    // 协程里 suspend方法 顺序执行
    private suspend fun getToken(): String {
        delay(5000)
        return "token"
    }

    private suspend fun getUser(): String {
        delay(3000)
        return "user"
    }

    open class Student
    class FemaleStudent : Student()
}