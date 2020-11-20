package com.wuba.car.myspace.utils

import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

open class ImageLoadUtils {

    companion object {
        fun loadPic(url: String, iv: ImageView) {
            if (TextUtils.isEmpty(url) || null == iv) return
            Glide.with(iv.context)
                .load(url)
                .apply(RequestOptions())
                .into(iv)
        }

        fun loadCornerPic(url: String, iv: ImageView, corner: Int) {
            var options = RequestOptions()
                .transform(MultiTransformation(RoundedCorners(corner)))
            Glide.with(iv.context)
                .load(url)
                .apply(options)
                .into(iv)
        }
    }
}