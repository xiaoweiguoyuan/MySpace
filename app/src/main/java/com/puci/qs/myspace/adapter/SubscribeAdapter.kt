package com.puci.qs.myspace.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.puci.qs.myspace.entity.EpisodeDetail
import com.puci.qs.myspace.viewholder.SubscribeVH
import com.puci.qs.qishuier.R

open class SubscribeAdapter(context: Context, datas: List<EpisodeDetail.CurEpisode>?): BaseAdapter<EpisodeDetail.CurEpisode>(context, datas) {

    private var itemclickListener: OnItemViewClickListener? = null

    open fun setData(datas: List<EpisodeDetail.CurEpisode>?) {
        mDatas = datas
    }

    override fun getHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<EpisodeDetail.CurEpisode> {
        var view = View.inflate(context, R.layout.item_subscribe, null)
        val subscribeVH = SubscribeVH(view)
        subscribeVH.itemclickListener = this.itemclickListener
        return subscribeVH
    }

    open fun setOnItemViewClickListener(itemclickListener: OnItemViewClickListener) {
        this.itemclickListener = itemclickListener
    }

    open interface OnItemViewClickListener {
        fun onItemViewClick(episode: EpisodeDetail.CurEpisode, pos: Int, view: View)
    }
}