package com.wuba.car.myspace.viewholder

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.wuba.car.basedependencies.entity.Episode
import com.wuba.car.basedependencies.utils.SaveLog
import com.wuba.car.myspace.adapter.BaseViewHolder
import com.wuba.car.myspace.view.CircularProgressView
import com.wuba.car.qishuier.R
import com.wuba.car.spacenet.http.utils.GlideImageLoader

open class PlayListVH(itemview: View): BaseViewHolder<Episode>(itemview) {
    lateinit var cover: ImageView
    lateinit var title_tv: TextView
    lateinit var desc_tv: TextView
    lateinit var time_tv: TextView
    lateinit var play_btn: ImageView
    lateinit var progress: CircularProgressView

    override fun initView() {
        cover = itemView.findViewById(R.id.cover_iv)
        title_tv = itemView.findViewById(R.id.title_tv)
        desc_tv = itemView.findViewById(R.id.desc_tv)
        time_tv = itemView.findViewById(R.id.time_tv)
        play_btn = itemView.findViewById(R.id.play_btn)
        progress = itemView.findViewById(R.id.progress)
    }

    @SuppressLint("ResourceType")
    override fun bindData(pos: Int, t: Episode) {
        Glide.with(itemView)
            .applyDefaultRequestOptions(RequestOptions.overrideOf(56, 56)
                .transform(MultiTransformation(RoundedCorners(6)))
            )
            .load(t.episode_art_url)
            .into(cover)
        title_tv.text = t.title
        desc_tv.text = t.showNotes
        time_tv.text = "还剩下2小时11分钟"
//        play_btn.setColorFilter(itemView.context.resources.getColor(R.color.color_46F8B7))
        t.maxSize?.let {
            if (it == 0) return@let
            (t.progress?.times(100))?.div(it)?.let {
//                SaveLog.i("@@@-progress", it)
                progress.setProgress(it)
            }
        }
    }
}