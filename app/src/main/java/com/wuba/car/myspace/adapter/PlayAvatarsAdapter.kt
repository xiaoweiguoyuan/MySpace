package com.wuba.car.myspace.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wuba.car.myspace.viewholder.PlayingAvatarVH
import com.wuba.car.qishuier.R

open class PlayAvatarsAdapter(
    context: Context,
    mDatas: MutableList<String>?
): BaseAdapter<String>(context, mDatas) {

    override fun getHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        var iv = ImageView(context)
        iv.setImageResource(R.drawable.like_ic)
        val dimensionPixelSize = context.resources.getDimensionPixelSize(R.dimen.dp_18)
        iv.layoutParams = ViewGroup.LayoutParams(dimensionPixelSize, dimensionPixelSize)
        return PlayingAvatarVH(iv)
    }
}