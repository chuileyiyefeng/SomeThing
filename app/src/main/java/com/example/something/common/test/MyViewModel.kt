package com.example.something.common.test

import androidx.lifecycle.MutableLiveData
import com.example.something.common.BaseViewModel

/**
 * @Description:
 * @Author: pan yi
 * @Date: 2022/6/22
 */
class MyViewModel : BaseViewModel() {
     val data: MutableLiveData<String> = MutableLiveData()

    fun getResult() {
        data.value = "点击点击点击"
    }
}