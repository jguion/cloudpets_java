package com.spiraltoys.cloudpets2.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class FloatingProfileSwitcherFragment$3 extends AnimatorListenerAdapter {
    final /* synthetic */ FloatingProfileSwitcherFragment this$0;

    FloatingProfileSwitcherFragment$3(FloatingProfileSwitcherFragment this$0) {
        this.this$0 = this$0;
    }

    public void onAnimationEnd(Animator animation) {
        FloatingProfileSwitcherFragment.access$100(this.this$0).floatingMenu.setVisibility(4);
        FloatingProfileSwitcherFragment.access$100(this.this$0).floatingMenu.setAlpha(0.0f);
        if (FloatingProfileSwitcherFragment.access$200(this.this$0) != null) {
            FloatingProfileSwitcherFragment.access$200(this.this$0).onProfileFlyoutMenuDismissed();
        }
    }
}
