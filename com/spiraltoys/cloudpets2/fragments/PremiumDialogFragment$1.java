package com.spiraltoys.cloudpets2.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class PremiumDialogFragment$1 implements OnClickListener {
    final /* synthetic */ PremiumDialogFragment this$0;

    PremiumDialogFragment$1(PremiumDialogFragment this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        if (this.this$0.getDialog() != null) {
            this.this$0.getDialog().onBackPressed();
        }
    }
}
