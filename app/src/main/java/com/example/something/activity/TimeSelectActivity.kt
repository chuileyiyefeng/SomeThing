package com.example.something.activity

import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.something.R
import com.example.something.net_work.base.BaseActivity
import kotlinx.android.synthetic.main.activity_time_select.*
import org.weishe.baselibrary.dialog.SelectTimeDialog

/**
 * create by pan yi on 2020/10/13
 * desc : 时间选择器
 */
class TimeSelectActivity : BaseActivity(), SelectTimeDialog.DismissListener,
    SelectTimeDialog.ConfirmListener {
    private val selectTimeDialog by lazy {
        SelectTimeDialog().apply {
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialogStyle)
        }
    }

    override fun bindLayout(): Int {
        return R.layout.activity_time_select
    }

    override fun initView() {
        selectTimeDialog.confirmListener = this
        selectTimeDialog.dismissListener = this
        tv_selected.setOnClickListener {
            selectTimeDialog.show(supportFragmentManager, "TAG")
        }
        selectTimeDialog.change(
            {
                Log.e(TAG, "initView: ")
            }, onComplete = {
                Log.e(TAG, "onComplete: $it")
            }
        )
        selectTimeDialog.addConfirmListener(object :SelectTimeDialog.ConfirmListener{
            override fun confirmTime(time: String) {

            }

        })
    }

    override fun timeDialogDismiss() {

    }

    override fun confirmTime(time: String) {
        tv_content.text = time
    }
}