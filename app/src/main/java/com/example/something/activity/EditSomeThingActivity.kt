package com.example.something.activity

import android.text.InputType
import com.example.something.R
import com.example.something.net_work.base.BaseActivity
import com.example.something.utils.NumberTextWatcher
import kotlinx.android.synthetic.main.activity_edit_some.*

/**
 * create by pan yi on 2020/10/13
 * desc : 输入法操作相关
 */
class EditSomeThingActivity : BaseActivity() {
    override fun bindLayout(): Int {
        return R.layout.activity_edit_some
    }

    override fun initView() {
        iv_pwd_status.setOnClickListener {
            checkInputStatus()
        }
        et_number.addTextChangedListener(NumberTextWatcher(et_number))
    }

    var checkStatus = false
    private fun checkInputStatus() {
        iv_pwd_status.setImageResource(
            when (checkStatus) {
                true -> {
                    et_pwd.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    R.mipmap.icon_eye_close
                }
                false -> {
                    et_pwd.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    R.mipmap.icon_eye_open
                }
            }
        )
        et_pwd.setSelection(et_pwd.text.toString().length)
        checkStatus = !checkStatus
    }
}