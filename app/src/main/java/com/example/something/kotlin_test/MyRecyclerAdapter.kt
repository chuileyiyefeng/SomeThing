package com.example.something.kotlin_test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.example.something.R

import java.util.ArrayList

class MyRecyclerAdapter(
    private val context: Context,
    private val arrayList: ArrayList<String>
) : RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_text, null)
        return ViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.setText(R.id.tv, arrayList!![position])
        val testBean = TestBean()
//        testBean.age = position
        testBean.name = arrayList[position]
//        testBean.time = ""
        holder.bindData(arrayList[position], testBean)
    }


    override fun getItemCount(): Int {
        return arrayList.size
    }

    // private constructor修饰构造方法
//    class ViewHolder private constructor(itemView: View, private val context: Context) :
    class ViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View) {
            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
        }

        private val tv: TextView = itemView.findViewById(R.id.tv)

        init {
            tv.setOnClickListener(this)
        }

        fun bindData(string: String, bean: TestBean) {
            tv.text = string
            with(bean) {
                tv.text = name
            }
        }

        fun setText(id: Int, text: String) {
            val tv = itemView.findViewById<TextView>(id)
            tv.text = text
        }
    }

}
