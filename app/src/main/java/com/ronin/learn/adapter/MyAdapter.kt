package com.ronin.learn.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.ronin.learn.activity.testInline

/**
 * Created by Administrator on 2017/3/10.
 */
class MyAdapter(val items: List<String>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent?.context))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.tv?.text = items.get(position)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val tv: TextView) : RecyclerView.ViewHolder(tv)
}