package com.example.something.activity

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.something.R
import com.example.something.net_work.base.BaseActivity
import com.example.something.utils.NumberTextWatcher
import kotlinx.android.synthetic.main.activity_edit_some.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * create by pan yi on 2020/10/13
 * desc : 输入法操作相关
 */
class EditSomeThingActivity : BaseActivity() {

    var maxLength = 10
    val name: String by lazy {
        intent.getStringExtra("name") ?: ""
    }

    //中文过滤器
    private val filter by lazy {
        InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!isChinese(source[i])) {
                    return@InputFilter ""
                }
            }
            null
        }
    }

    //空格过滤器
    private val blankFilter by lazy {
        InputFilter { source, start, end, dest, dstart, dend ->
            if (source == " " || source.toString().contentEquals("\n")) "" else null
        }
    }

    /**
     * 判断中文字符
     * @param c
     * @return
     */
    private fun isChinese(c: Char): Boolean {
        val ub = Character.UnicodeBlock.of(c)
        return ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub === Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub === Character.UnicodeBlock.GENERAL_PUNCTUATION || ub === Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub === Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
    }

    override fun bindLayout(): Int {
        return R.layout.activity_edit_some
    }

    override fun initView() {
        tv_hide_input.setOnClickListener {
            hideInput(it)
        }
        iv_pwd_status.setOnClickListener {
            checkInputStatus()
        }
        et_number.addTextChangedListener(NumberTextWatcher(et_number))

        et_max_count.filters = arrayOf(filter, blankFilter)
        et_max_count.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    val totalLength = it.length
                    Log.e("addTextChangedListener", "$totalLength")
                    if (totalLength > maxLength) {
                        val text = it.subSequence(0, maxLength).toString()
                        Log.e("addTextChangedListener", "$totalLength  $text")
                        et_max_count.setText(text)
                        et_max_count.setSelection(et_max_count.text.length)
                    }
                }
            }

        })

    }

    private var checkStatus = false
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
        val uiScope = CoroutineScope(SupervisorJob())

    }

    private fun hideInput(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive) {
            imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)
        }
    }
}