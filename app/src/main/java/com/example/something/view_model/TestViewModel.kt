package com.example.something.view_model

import android.os.Handler
import androidx.lifecycle.ViewModel
import android.os.HandlerThread
import android.os.Message
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.supervisorScope

class TestViewModel : ViewModel(),LifecycleObserver {
    private var time = 0
    override fun onCleared() {
        super.onCleared()
    }

    private var mainHandler: Handler? = null
    private var workHandler: Handler? = null
    private var handlerThread: HandlerThread? = null
    fun start() {
        
        mainHandler = Handler()
        handlerThread = HandlerThread("TAG")
        handlerThread!!.start()
        workHandler = object : Handler(handlerThread!!.looper) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                time++
                mainHandler!!.post {
                    if (listener != null) {
                        listener!!.change(time.toString() + "s")
                    }
                    if (time < 100) {
                        go()
                    }
                }
            }
        }
        go()
    }

    private fun go() {
        workHandler!!.sendEmptyMessage(0)
    }

    private var listener: ChangeListener? = null
    fun setListener(listener: ChangeListener?) {
        this.listener = listener
    }

    interface ChangeListener {
        fun change(str: String?)
    }
}