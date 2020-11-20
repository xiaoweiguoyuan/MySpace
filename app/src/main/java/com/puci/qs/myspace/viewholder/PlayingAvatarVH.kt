package com.puci.qs.myspace.viewholder

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.puci.qs.basedependencies.utils.SaveLog
import com.puci.qs.myspace.adapter.BaseViewHolder

open class PlayingAvatarVH(itemview: View): BaseViewHolder<String>(itemview) {
    override fun initView() {
    }

    override fun bindData(pos: Int, t: String) {
        SaveLog.i("@@@", "bindData-thread: " + Thread.currentThread().name)
        Glide.with(itemView)
            .load(t)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(itemView as ImageView)
    }
}