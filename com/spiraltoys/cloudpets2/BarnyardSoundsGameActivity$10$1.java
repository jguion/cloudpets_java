package com.spiraltoys.cloudpets2;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import com.spiraltoys.cloudpets2.BarnyardSoundsGameActivity.10;
import com.spiraltoys.cloudpets2.free.R;

class BarnyardSoundsGameActivity$10$1 implements AnimationListener {
    final /* synthetic */ 10 this$1;

    BarnyardSoundsGameActivity$10$1(10 this$1) {
        this.this$1 = this$1;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.this$1.val$indicator.startAnimation(AnimationUtils.loadAnimation(this.this$1.this$0, R.anim.barnyard_pulse_infinite_fast));
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
