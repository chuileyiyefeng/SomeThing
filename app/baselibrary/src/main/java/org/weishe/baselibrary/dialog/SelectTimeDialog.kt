package org.weishe.baselibrary.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter
import com.contrarywind.view.WheelView
import org.weishe.baselibrary.R
import org.weishe.baselibrary.utils.toast
import java.text.SimpleDateFormat
import java.util.*


/**
 *  create by pan yi on 2020/10/19
 *  desc :  时间选择器
 */
class SelectTimeDialog : DialogFragment() {
    private var viewParent: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (viewParent == null) {
            val rootView = inflater.inflate(R.layout.dialog_select_time, container, false)
            viewParent = rootView
            initView(rootView)
            viewParent
        } else {
            viewParent?.let {
                (it.parent as ViewGroup).removeView(it)
            }
            viewParent
        }
    }

    override fun onResume() {
        super.onResume()
        val dm = activity?.resources?.displayMetrics
        dialog?.window?.let { window ->
            val lp = window.attributes
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT
            dm?.let {
                lp.height = it.heightPixels / 2
            }
            window.setGravity(Gravity.BOTTOM)
            window.setLayout(lp.width, lp.height)
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    private val yearItems: ArrayList<String> = ArrayList()
    private val yearItems2: ArrayList<String> = ArrayList()
    private val monthItems: ArrayList<String> = ArrayList()
    private val monthItems2: ArrayList<String> = ArrayList()
    private val dayItems: ArrayList<String> = ArrayList()
    private val dayItems2: ArrayList<String> = ArrayList()

    private var year = "1970"
    private var month = "01"
    private var day = "01"
    private var year2 = "1970"
    private var month2 = "01"
    private var day2 = "01"
    fun initView(view: View) {
        val yearV = view.findViewById<WheelView>(R.id.year)
        val monthV = view.findViewById<WheelView>(R.id.month)
        val dayV = view.findViewById<WheelView>(R.id.day)
        val yearV2 = view.findViewById<WheelView>(R.id.year2)
        val monthV2 = view.findViewById<WheelView>(R.id.month2)
        val dayV2 = view.findViewById<WheelView>(R.id.day2)
        initType(yearV, monthV, dayV, yearV2, monthV2, dayV2)

        for (i in 1970..2099) {
            yearItems.add(i.toString())
            yearItems2.add(i.toString())
        }
        for (i in 1..12) {
            var head = ""
            if (i < 10) {
                head = "0"
            }
            monthItems.add(head + i.toString())
            monthItems2.add(head + i.toString())
        }
        for (i in 1..31) {
            dayItems.add(i.toString())
            dayItems2.add(i.toString())
        }

        yearV.adapter = ArrayWheelAdapter<String>(yearItems)
        yearV2.adapter = ArrayWheelAdapter<String>(yearItems2)

        monthV.adapter = ArrayWheelAdapter<String>(monthItems)
        monthV2.adapter = ArrayWheelAdapter<String>(monthItems2)

        dayV.adapter = ArrayWheelAdapter<String>(dayItems)
        dayV2.adapter = ArrayWheelAdapter<String>(dayItems2)



        yearV.setOnItemSelectedListener { index ->
            year = yearItems[index]
            when (month) {
                "02" -> {
                    dayItems.clear()
                    for (i in 1..28) {
                        dayItems.add(getRealDay(i))
                    }
                    when (year.toInt() % 4) {
                        0 -> {
                            dayItems.add("29")
                        }
                    }
                    dayV.adapter = ArrayWheelAdapter<String>(dayItems)
                    val currentItem = dayV.currentItem
                    dayV.currentItem = currentItem
                    day = dayItems[currentItem]
                }
            }
        }
        yearV2.setOnItemSelectedListener { index ->
            year2 = yearItems[index]
            when (month) {
                "02" -> {
                    dayItems2.clear()
                    for (i in 1..28) {
                        dayItems2.add(getRealDay(i))
                    }
                    when (year2.toInt() % 4) {
                        0 -> {
                            dayItems2.add("29")
                        }
                    }
                    dayV2.adapter = ArrayWheelAdapter<String>(dayItems2)
                    val currentItem = dayV2.currentItem
                    dayV2.currentItem = currentItem
                    day2 = dayItems2[currentItem]
                }
            }
        }

        monthV.setOnItemSelectedListener { index ->
            month = monthItems[index]
            dayItems.clear()
            when (month) {
                "01", "03", "05", "07", "08", "10", "12" -> {
                    for (i in 1..31) {
                        dayItems.add(getRealDay(i))
                    }
                }
                "02" -> {
                    for (i in 1..28) {
                        dayItems.add(getRealDay(i))
                    }
                    when (year.toInt() % 4) {
                        0 -> {
                            dayItems.add("29")
                        }
                    }
                }
                else -> {
                    for (i in 1..30) {
                        dayItems.add(getRealDay(i))
                    }
                }
            }
            dayV.adapter = ArrayWheelAdapter<String>(dayItems)
            val currentItem = dayV.currentItem
            dayV.currentItem = currentItem
            day = dayItems[currentItem]
        }
        monthV2.setOnItemSelectedListener { index ->
            month2 = monthItems2[index]
            dayItems2.clear()
            when (month2) {
                "01", "03", "05", "07", "08", "10", "12" -> {
                    for (i in 1..31) {
                        dayItems2.add(getRealDay(i))
                    }
                }
                "02" -> {
                    for (i in 1..28) {
                        dayItems2.add(getRealDay(i))
                    }
                    when (year2.toInt() % 4) {
                        0 -> {
                            dayItems2.add("29")
                        }
                    }
                }
                else -> {
                    for (i in 1..30) {
                        dayItems2.add(getRealDay(i))
                    }
                }
            }
            dayV2.adapter = ArrayWheelAdapter<String>(dayItems2)
            val currentItem = dayV2.currentItem
            dayV2.currentItem = currentItem
            day2 = dayItems2[currentItem]
        }

        dayV.setOnItemSelectedListener { index ->
            day = dayItems[index]
            Log.e("TAG  day change", "initView: " + day)
        }
        dayV2.setOnItemSelectedListener { index ->
            day2 = dayItems2[index]
        }
        yearV.currentItem = yearItems.indexOf(getCurrentYear())
        yearV2.currentItem = yearItems2.indexOf(getCurrentYear())

        monthV.currentItem = monthItems.indexOf(getCurrentMonth())
        monthV2.currentItem = monthItems.indexOf(getCurrentMonth())

        dayV.currentItem = dayItems.indexOf(getCurrentDay())
        dayV2.currentItem = dayItems2.indexOf(getCurrentDay())

        val tvComplete = view.findViewById<TextView>(R.id.tv_complete)
        val tvClear = view.findViewById<TextView>(R.id.tv_clear)
        tvComplete.setOnClickListener {
            confirmListener?.let { listener ->
                val startTime = (year + month + day).toInt()
                val endTime = (year2 + month2 + day2).toInt()
                if (startTime > endTime) {
                    context?.let {
                        toast(it, "结束时间不能小于开始时间")
                    }

                    return@setOnClickListener
                }
                onOperation.invoke()
                onComplete("message")
                val lineStr = "-"
                val lineStrLine = " - "
                listener.confirmTime(year + lineStr + month + lineStr + day + lineStrLine + year2 + lineStr + month2 + lineStr + day2)
            }
            dismiss()
        }
        tvClear.setOnClickListener {
            confirmListener?.confirmTime("")
            dismiss()
        }

    }

    private fun initType(vararg wheelViews: WheelView) {
        for (wheelView in wheelViews) {
            wheelView.setTextSize(14f)
            wheelView.setCyclic(false)
            wheelView.setLineSpacingMultiplier(2f)
            wheelView.setDividerType(WheelView.DividerType.WRAP)
        }
    }

    override fun dismiss() {
        super.dismiss()
        dismissListener?.timeDialogDismiss()
    }

    fun addConfirmListener(confirmListener: ConfirmListener) {
        this.confirmListener = confirmListener
    }

    var confirmListener: ConfirmListener? = null
    var dismissListener: DismissListener? = null

    interface ConfirmListener {
        fun confirmTime(time: String)
    }

    interface DismissListener {
        fun timeDialogDismiss()
    }

    private fun getCurrentYear(): String {
        val ca = Calendar.getInstance()
        val format = SimpleDateFormat("yyyy", Locale.getDefault())
        val currentYear = format.format(ca.time)
        year = currentYear
        year2 = currentYear
        return currentYear
    }

    private fun getCurrentMonth(): String {
        val ca = Calendar.getInstance()
        val format = SimpleDateFormat("MM", Locale.getDefault())
        val currentMonth = format.format(ca.time)
        month = currentMonth
        month2 = currentMonth
        return currentMonth
    }

    private fun getCurrentDay(): String {
        val ca = Calendar.getInstance()
        val format = SimpleDateFormat("dd", Locale.getDefault())
        val currentDay = format.format(ca.time)
        day = currentDay
        day2 = currentDay
        return currentDay
    }

    private fun getRealDay(day: Int): String {
        return if (day > 9) {
            day.toString()
        } else {
            "0$day"
        }

    }

    override fun onStop() {
        super.onStop()
        dismissListener?.timeDialogDismiss()
    }


    private var onOperation: () -> Unit = {}
    private var onComplete: (String) -> Unit = {}
    fun change(onOperation: () -> Unit = {}, onComplete: (String) -> Unit) {
        this.onOperation = onOperation
        this.onComplete = onComplete

    }
}