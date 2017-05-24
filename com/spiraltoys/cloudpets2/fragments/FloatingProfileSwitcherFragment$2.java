package com.spiraltoys.cloudpets2.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class FloatingProfileSwitcherFragment$2 extends AnimatorListenerAdapter {
    final /* synthetic */ FloatingProfileSwitcherFragment this$0;

    FloatingProfileSwitcherFragment$2(FloatingProfileSwitcherFragment this$0) {
        this.this$0 = this$0;
    }

    public void onAnimationEnd(Animator animation) {
        FloatingProfileSwitcherFragment.access$000(this.this$0).setVisibility(8);
        FloatingProfileSwitcherFragment.access$000(this.this$0).setAlpha(0.0f);
    }
}
