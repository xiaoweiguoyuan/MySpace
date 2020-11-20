package com.wuba.car.myspace.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open abstract class BaseAdapter<T>(val context: Context, var mDatas: List<T>?) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    fun addDatas(list: List<T>) {
        if (mDatas == null) {
            mDatas = ArrayList<T>()
        }
        val plus = mDatas!!.plus(list)
        mDatas = plus
    }

    fun addData(t: T) {
        val plus = mDatas!!.plus(t)
        mDatas = plus
    }

    override fun getItemCount(): Int {
        if (mDatas == null) {
            return 0
        }
        return mDatas!!.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindData(position, mDatas!!.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return getHolder(parent, viewType)
    }

    abstract fun getHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>
}