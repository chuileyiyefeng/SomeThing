package com.example.something.net_work.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.something.R
import com.example.something.utils.StatusBarUtil

abstract class BaseActivity : AppCompatActivity() {
     var TAG = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindLayout())
        initView()
        StatusBarUtil.setStatusBarColor(this, R.color.white)
        StatusBarUtil.setStatusTextDark(this, true)
    }

    protected abstract fun bindLayout(): Int
    protected abstract fun initView()


    /**
     * 启动Fragment
     */
    protected fun startFragment(id: Int, fragment: Fragment?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(id, fragment!!)
        fragmentTransaction.commit()
    }
}