package com.spiraltoys.cloudpets2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.spiraltoys.cloudpets2.BarnyardSoundsGameActivity.15;

class BarnyardSoundsGameActivity$15$1 extends AnimatorListenerAdapter {
    final /* synthetic */ 15 this$1;

    BarnyardSoundsGameActivity$15$1(15 this$1) {
        this.this$1 = this$1;
    }

    public void onAnimationEnd(Animator animation) {
        this.this$1.val$successText.setVisibility(8);
        BarnyardSoundsGameActivity.access$2600(this.this$1.this$0).removeAllViews();
        BarnyardSoundsGameActivity.access$2700(this.this$1.this$0);
    }
}
