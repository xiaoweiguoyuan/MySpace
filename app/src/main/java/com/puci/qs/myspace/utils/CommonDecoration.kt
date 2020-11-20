package com.puci.qs.myspace.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class CommonDecoration: RecyclerView.ItemDecoration {

    private var bottom: Int
    private var right: Int
    private var top: Int
    private var left: Int

    constructor (left: Int, top: Int, right: Int, bottom: Int) {
        this.left = left
        this.top = top
        this.right = right
        this.bottom = bottom
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(left, top, right, bottom)
    }
}