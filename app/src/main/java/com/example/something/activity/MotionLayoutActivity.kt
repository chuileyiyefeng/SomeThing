package com.example.something.activity

import android.content.Intent
import com.example.something.R
import com.example.something.net_work.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.tv_motion
import kotlinx.android.synthetic.main.activity_motion_list.*

/**
 * @Description:
 * @Author: pan yi
 * @Date: 2022/2/7
 */
class MotionLayoutActivity : BaseActivity() {
    override fun bindLayout(): Int {
        return R.layout.activity_motion_list
    }

    override fun initView() {
        tv_motion.setOnClickListener {
            startActivity(Intent(this, MotionMoveActivity::class.java))
        }
        tv_image.setOnClickListener {
            startActivity(Intent(this, MotionImageActivity::class.java))
        }
        tv_cycle.setOnClickListener {
            startActivity(Intent(this, MotionCycleMoveActivity::class.java))
        }
        tv_phone.setOnClickListener {
            startActivity(Intent(this, MotionPhoneActivity::class.java))
        }
        tv_shop.setOnClickListener {
            startActivity(Intent(this, MotionShopCarActivity::class.java))
        }
    }
}