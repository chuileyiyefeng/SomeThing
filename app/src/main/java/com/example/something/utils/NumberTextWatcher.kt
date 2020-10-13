package com.example.something.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 *  create by pan yi on 2020/10/13
 *  desc : 输入框小数监听
 */
class NumberTextWatcher(private val editText: EditText) : TextWatcher {
    override fun onTextChanged(
        s: CharSequence, start: Int, before: Int,
        count: Int
    ) {
        //删除.后面超过两位的数字
        var realResult = s
        if (realResult.toString().contains(".")) {
            if (realResult.length - 1 - realResult.toString().indexOf(".") > 2) {
                realResult = realResult.toString().subSequence(
                    0,
                    realResult.toString().indexOf(".") + 3
                )
                editText.setText(realResult)
                editText.setSelection(realResult.length)
            }
        }

        //如果.在起始位置,则起始位置自动补0
        if (realResult.toString().trim { it <= ' ' }.substring(0) == ".") {
            realResult = "0$realResult"
            editText.setText(realResult)
            editText.setSelection(2)
        }

        //如果起始位置为0并且第二位跟的不是".",则无法后续输入
        if (realResult.toString().startsWith("0")
            && realResult.toString().trim { it <= ' ' }.length > 1
        ) {
            if (realResult.toString().substring(1, 2) != ".") {
                editText.setText(realResult.subSequence(0, 1))
                editText.setSelection(1)
            }
        }
    }

    override fun beforeTextChanged(
        s: CharSequence, start: Int, count: Int,
        after: Int
    ) {
    }

    override fun afterTextChanged(s: Editable) {

    }
}
