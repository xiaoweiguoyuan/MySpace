package com.wuba.car.myspace.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.wuba.car.basedependencies.entity.Podcast
import com.wuba.car.myspace.viewholder.SearchChildVH
import com.wuba.car.qishuier.R

class SearchChildAdapter(context: Context, mDatas: List<Podcast>?) : BaseAdapter<Podcast>(context, mDatas) {

    override fun getHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Podcast> {
        var view = View.inflate(context, R.layout.item_search_child, null)
        return SearchChildVH(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

}