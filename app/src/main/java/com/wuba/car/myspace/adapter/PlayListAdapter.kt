package com.wuba.car.myspace.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.wuba.car.basedependencies.entity.Episode
import com.wuba.car.myspace.viewholder.PlayListVH
import com.wuba.car.qishuier.R

open class PlayListAdapter(context: Context, mDatas: List<Episode>?):
    BaseAdapter<Episode>(context, mDatas) {
    override fun getHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Episode> {
        var view = View.inflate(context, R.layout.item_playlist, null)
        var vh = PlayListVH(view)
        return vh
    }
}