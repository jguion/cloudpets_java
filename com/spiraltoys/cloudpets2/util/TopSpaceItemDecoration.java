package com.spiraltoys.cloudpets2.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class TopSpaceItemDecoration extends ItemDecoration {
    private int space;

    public TopSpaceItemDecoration(int space) {
        this.space = space;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = this.space;
        }
    }
}
