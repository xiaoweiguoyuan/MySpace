package com.wuba.car.myspace.testmvvm

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.bumptech.glide.Glide


open class BindAdapter {
    companion object {
        @BindingAdapter("url")
        @JvmStatic
        fun loadImage(view: ImageView?, url: String?) {
            Glide.with(view!!.context).load(url).into(view)
        }

        @BindingAdapter("android:text_add")//value可以自定义，但是要与xml里面的保持一致
        @JvmStatic
        fun setText(view: TextView, text: String?) {
            view.text = "$text-追加的数据"
        }

    }

}