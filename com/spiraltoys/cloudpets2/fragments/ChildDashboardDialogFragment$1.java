package com.spiraltoys.cloudpets2.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class ChildDashboardDialogFragment$1 implements OnClickListener {
    final /* synthetic */ ChildDashboardDialogFragment this$0;

    ChildDashboardDialogFragment$1(ChildDashboardDialogFragment this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        if (this.this$0.getDialog() != null) {
            this.this$0.getDialog().onBackPressed();
        }
    }
}
