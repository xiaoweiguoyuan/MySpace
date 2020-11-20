package com.wuba.car.myspace.adapter

import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import com.wuba.car.myspace.entity.CommentBean
import com.wuba.car.myspace.viewholder.ChaZuiVH
import com.wuba.car.qishuier.R

open class ChaZuiAdapter(context: Context, mDatas: List<CommentBean>?): BaseAdapter<CommentBean>(
    context,
    mDatas
) {
    override fun getHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CommentBean> {
        return ChaZuiVH(itemview = View.inflate(context, R.layout.item_chazui, null))
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<CommentBean>) {
        super.onViewAttachedToWindow(holder)
        if (holder.layoutPosition == itemCount - 1) {
            addAnimation(holder)
        }
    }

    private fun addAnimation(holder: BaseViewHolder<CommentBean>) {
        for (anim in getAnimators(holder.itemView)!!) {
            anim.setDuration(1000).start()
            anim.interpolator = LinearInterpolator()
            anim.start()
        }
    }

    open fun getAnimators(view: View?): Array<ObjectAnimator> {
//        val scaleX: ObjectAnimator = ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1f)
//        val scaleY: ObjectAnimator = ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1f)
        val alphaY: ObjectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val translateY: ObjectAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f, 1f)
        return arrayOf<ObjectAnimator>(alphaY, translateY)
    }

}