package com.puci.qs.myspace.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open abstract class BaseViewHolder<T> : RecyclerView.ViewHolder {

    constructor(itemView: View) : super(itemView) {
        initView()
    }

    abstract fun initView()
    abstract fun bindData(pos: Int, t: T)
}