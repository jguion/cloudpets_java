package com.spiraltoys.cloudpets2.fragments;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import com.spiraltoys.cloudpets2.free.R;

class SelectBluetoothToyCompleteConnectionFragment$1 implements AnimatorListener {
    final /* synthetic */ SelectBluetoothToyCompleteConnectionFragment this$0;

    SelectBluetoothToyCompleteConnectionFragment$1(SelectBluetoothToyCompleteConnectionFragment this$0) {
        this.this$0 = this$0;
    }

    public void onAnimationStart(Animator animation) {
    }

    public void onAnimationEnd(Animator animation) {
        this.this$0.mProgress5.setEnabled(true);
        this.this$0.mConnectionText.setText(R.string.message_cloudpet_configured);
    }

    public void onAnimationCancel(Animator animation) {
    }

    public void onAnimationRepeat(Animator animation) {
    }
}
