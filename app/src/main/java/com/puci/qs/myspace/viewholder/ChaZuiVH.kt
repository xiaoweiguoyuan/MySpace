package com.puci.qs.myspace.viewholder

import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.puci.qs.myspace.adapter.BaseViewHolder
import com.puci.qs.myspace.entity.CommentBean
import com.puci.qs.qishuier.R

class ChaZuiVH(itemview: View): BaseViewHolder<CommentBean>(itemview) {
    lateinit var avatar: ImageView
//    lateinit var name: TextView
    lateinit var content: TextView
    override fun initView() {
        avatar = itemView.findViewById(R.id.avatar)
//        name = itemView.findViewById(R.id.name)
        content = itemView.findViewById(R.id.content)
    }

    override fun bindData(pos: Int, t: CommentBean) {
        Glide.with(itemView.context)
            .load(t.user?.avatar)
            .apply(RequestOptions().circleCrop())
            .into(avatar)
        var end = t.user?.username?.length
        if (null == end) {
            end = 0
        }
        if (end > 0) {
            var spannableString = SpannableString(t.user!!.username + ":" +
                    "" +
                    "" +
                    "" +
                    " " + t.content)
            spannableString.setSpan(ForegroundColorSpan(itemView.context.resources.getColor(R.color.color_46F8B7)), 0, end + 1, SpannableString.SPAN_INCLUSIVE_INCLUSIVE)
            content.text = spannableString
        }
    }
}