package com.example.something.activity

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.example.something.R
import com.example.something.net_work.base.BaseActivity
import kotlinx.android.synthetic.main.activity_screen.*
import org.weishe.baselibrary.utils.toast


class ScreenChangeActivity : BaseActivity() {
    var isLan = false// 是否是横屏
    override fun bindLayout(): Int {
        return R.layout.activity_screen
    }

    override fun initView() {

        tv_change.setOnClickListener {
            if (!isLan) {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE //横屏
            } else {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //竖屏
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addStatusView()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toast(this, "变换屏幕")
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {//横屏
            isLan = true
            fullScreen()
        } else {//竖屏
            isLan = false
            notFullScreen()
        }
    }

    private fun notFullScreen() {
        val params = window.attributes
        params.flags = params.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
        window.attributes = params
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun fullScreen() {
        val params = window.attributes
        params.flags = params.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
        window.attributes = params
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun addStatusView() {
        val navi=true
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        if (navi) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //状态栏不会被隐藏但activity布局会扩展到状态栏所在位置
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //导航栏不会被隐藏但activity布局会扩展到导航栏所在位置
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.navigationBarColor = Color.TRANSPARENT
        } else {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        }

    }



}