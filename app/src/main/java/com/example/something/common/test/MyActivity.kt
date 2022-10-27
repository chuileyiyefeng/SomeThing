package com.example.something.common.test

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.something.common.*
import com.example.something.databinding.ActivityCropBinding
import com.example.something.databinding.ActivityTestBinding

/**
 * @Description:
 * @Author: pan yi
 * @Date: 2022/6/22
 */
class MyActivity : BaseActivity<ActivityTestBinding, MyViewModel>() {


    override fun initBinding(): ActivityTestBinding {
        return inflateBinding(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.tvTitle.text = "测试结果测试结果"
        viewBinding.tvTitle.setOnClickListener {
            mViewModel.getResult()
        }

        mViewModel.data.observe(this) {
            viewBinding.tvTitle.text = it
        }
    }
}