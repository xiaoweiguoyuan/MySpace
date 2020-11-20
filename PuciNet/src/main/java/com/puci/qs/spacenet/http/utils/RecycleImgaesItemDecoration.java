package com.puci.qs.spacenet.http.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleImgaesItemDecoration extends RecyclerView.ItemDecoration {

    private int mLeft;
    private int mTop;
    private int mRight;
    private int mBottom;

    private int padding;

    public void setItemMargin(int left, int top, int right, int bottom) {
        this.mLeft = left;
        this.mTop = top;
        this.mRight = right;
        this.mBottom = bottom;
    }

    public void setTopPadding(int padding) {
        this.padding = padding;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int currPos = parent.getChildAdapterPosition(view);
        if (currPos == 0) {
            outRect.set(mLeft, 0, 0, 0);
        } else if (currPos > 3) {
//            outRect.set(0, 0, mRight, 0);
            outRect.set(0, mTop, 0, 0);
        }
    }
}
