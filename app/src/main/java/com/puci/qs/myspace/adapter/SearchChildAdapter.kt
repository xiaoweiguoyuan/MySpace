package com.puci.qs.myspace.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.puci.qs.basedependencies.entity.Podcast
import com.puci.qs.myspace.viewholder.SearchChildVH
import com.puci.qs.qishuier.R

class SearchChildAdapter(context: Context, mDatas: List<Podcast>?) : BaseAdapter<Podcast>(context, mDatas) {

    override fun getHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Podcast> {
        var view = View.inflate(context, R.layout.item_search_child, null)
        return SearchChildVH(view)
    }

}