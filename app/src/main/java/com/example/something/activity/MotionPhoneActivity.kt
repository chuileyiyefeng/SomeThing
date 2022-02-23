package com.example.something.activity

import android.graphics.Paint
import android.view.View
import androidx.lifecycle.LiveData
import com.example.something.R
import com.example.something.net_work.base.BaseActivity

/**
 * @Description: Motion拨号键盘
 * @Author: pan yi
 * @Date: 2022/2/8
 */
class MotionPhoneActivity : BaseActivity() {
    override fun bindLayout(): Int {
        return R.layout.activity_motion_phone
    }

    override fun initView() {
        val paint = Paint()
        val view = View(this)
        val test = "test"
    }

    private fun showName() {
        epic("input", 10, 10)
    }

    private fun epic(name: String, color: Int, age: Int) {
    }
}