package com.example.something.kotlin_test

import android.annotation.SuppressLint
import android.util.Log
import com.example.something.R
import com.example.something.net_work.base.BaseActivity
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class KotlinActivity : BaseActivity() {

    override fun bindLayout(): Int {
        return R.layout.activity_kotlin
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        val scope = MainScope()
        scope.launch {
//            flow {
//                for (i in 1..3) {
//                    Log.e("Flow", "$i emit")
//                    emit(i)
//                }
//            }.filter {
//                Log.e("Flow","$it filter")
//                it % 2 != 0
//            }.map {
//                Log.e("Flow","$it map")
//                "${it * it} money"
//            }.collect {
//                Log.e("Flow", "i get $it")
//            }
            val channel = Channel<Int>()
            launch {
                for (i in 1..3) {
                    delay(200)
                    channel.send(i)
                }
                channel.close()
            }
            launch {
                for (y in channel) {
                    Log.e(TAG, "get $y")
                }
            }
            val produce = produce<Int> {
                for (i in 1..3) {
                    delay(200)
                    send(i * i)
                }
                close()
            }
            launch {
                for (y in produce) {
                    Log.e(TAG, "produce: $y")
                }
            }
        }

    }
}