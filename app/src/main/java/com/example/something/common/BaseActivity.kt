package com.example.something.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/**
 * @Description:
 * @Author: pan yi
 * @Date: 2022/6/22
 */
abstract class BaseActivity<Binding : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    lateinit var mViewModel: VM

    lateinit var viewBinding: Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = createViewModel(1)
        viewBinding = initBinding().apply {
            setContentView(root)
        }
        mViewModel.errorLiveData.observe(this) {

        }
    }

    abstract fun initBinding(): Binding

}