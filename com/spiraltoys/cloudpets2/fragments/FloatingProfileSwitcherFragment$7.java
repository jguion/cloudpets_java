package com.spiraltoys.cloudpets2.fragments;

import android.annotation.TargetApi;
import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

class FloatingProfileSwitcherFragment$7 extends ViewOutlineProvider {
    final /* synthetic */ FloatingProfileSwitcherFragment this$0;

    FloatingProfileSwitcherFragment$7(FloatingProfileSwitcherFragment this$0) {
        this.this$0 = this$0;
    }

    @TargetApi(21)
    public void getOutline(View view, Outline outline) {
        outline.setOval(0, 0, FloatingProfileSwitcherFragment.access$1000(this.this$0), FloatingProfileSwitcherFragment.access$1000(this.this$0));
    }
}
