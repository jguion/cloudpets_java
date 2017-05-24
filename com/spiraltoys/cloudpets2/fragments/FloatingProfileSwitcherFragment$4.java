package com.spiraltoys.cloudpets2.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class FloatingProfileSwitcherFragment$4 extends AnimatorListenerAdapter {
    final /* synthetic */ FloatingProfileSwitcherFragment this$0;

    FloatingProfileSwitcherFragment$4(FloatingProfileSwitcherFragment this$0) {
        this.this$0 = this$0;
    }

    public void onAnimationEnd(Animator animation) {
        FloatingProfileSwitcherFragment.access$000(this.this$0).setVisibility(0);
        FloatingProfileSwitcherFragment.access$000(this.this$0).setAlpha(1.0f);
        FloatingProfileSwitcherFragment.access$100(this.this$0).floatingMenu.setVisibility(0);
        FloatingProfileSwitcherFragment.access$100(this.this$0).floatingMenu.setAlpha(1.0f);
    }
}
