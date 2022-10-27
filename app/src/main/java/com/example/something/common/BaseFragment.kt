package com.example.something.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.something.R

/**
 * @Description:
 * @Author: pan yi
 * @Date: 2022/6/22
 */
abstract class BaseFragment : Fragment() {

    protected var parentView: ViewGroup? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = initLayout()
        parentView = view as ViewGroup?
        if (view == null) view = inflater.inflate(R.layout.base_fragment, container, false)
        return view
    }


    abstract fun initLayout(): View?
}