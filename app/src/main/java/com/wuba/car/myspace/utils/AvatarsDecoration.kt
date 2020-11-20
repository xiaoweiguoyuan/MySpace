package com.wuba.car.myspace.utils

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

open class AvatarsDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        if (itemPosition == 0) {
            outRect.set(0, 0, 0, 0)
        } else {
            outRect.set(-15, 0, 0, 0)
        }
    }
}