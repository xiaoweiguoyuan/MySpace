package com.wuba.car.myspace.viewholder

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.wuba.car.basedependencies.utils.SaveLog
import com.wuba.car.myspace.adapter.BaseViewHolder
import com.wuba.car.spacenet.http.utils.GlideImageLoader

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