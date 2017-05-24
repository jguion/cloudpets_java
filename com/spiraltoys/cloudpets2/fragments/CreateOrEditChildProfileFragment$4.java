package com.spiraltoys.cloudpets2.fragments;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import com.spiraltoys.cloudpets2.util.Utils;

class CreateOrEditChildProfileFragment$4 implements OnGlobalLayoutListener {
    final /* synthetic */ CreateOrEditChildProfileFragment this$0;

    CreateOrEditChildProfileFragment$4(CreateOrEditChildProfileFragment this$0) {
        this.this$0 = this$0;
    }

    public void onGlobalLayout() {
        int i = 0;
        if (this.this$0.isAdded() && !this.this$0.isRemoving()) {
            this.this$0.mProfileBear.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            ImageView imageView = this.this$0.mProfileBear;
            if (Utils.isColliding(this.this$0.mProfileBear, this.this$0.mTitleText, Utils.dpToPx(this.this$0.getActivity(), 40.0f), 0)) {
                i = 8;
            }
            imageView.setVisibility(i);
        }
    }
}
