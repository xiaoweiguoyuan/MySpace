package com.puci.qs.myspace.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.puci.qs.basedependencies.entity.Episode
import com.puci.qs.myspace.viewholder.PlayListVH
import com.puci.qs.qishuier.R

open class PlayListAdapter(context: Context, mDatas: List<Episode>?):
    BaseAdapter<Episode>(context, mDatas) {
    override fun getHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Episode> {
        var view = View.inflate(context, R.layout.item_playlist, null)
        var vh = PlayListVH(view)
        return vh
    }
}