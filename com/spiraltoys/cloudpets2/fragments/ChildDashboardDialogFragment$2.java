package com.spiraltoys.cloudpets2.fragments;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;

class ChildDashboardDialogFragment$2 extends AppCompatDialog {
    final /* synthetic */ ChildDashboardDialogFragment this$0;

    ChildDashboardDialogFragment$2(ChildDashboardDialogFragment this$0, Context x0, int x1) {
        this.this$0 = this$0;
        super(x0, x1);
    }

    public void onBackPressed() {
        if (this.this$0.getChildFragmentManager().getBackStackEntryCount() > 1) {
            this.this$0.getChildFragmentManager().popBackStackImmediate();
            ChildDashboardDialogFragment.access$000(this.this$0, this.this$0.mTitleStack);
            ChildDashboardDialogFragment.access$000(this.this$0, this.this$0.mTitleIconStack);
            ChildDashboardDialogFragment.access$200(this.this$0).toolbarTitle.setText((CharSequence) ChildDashboardDialogFragment.access$100(this.this$0, this.this$0.mTitleStack));
            ChildDashboardDialogFragment.access$200(this.this$0).toolbarTitleIcon.setImageResource(((Integer) ChildDashboardDialogFragment.access$100(this.this$0, this.this$0.mTitleIconStack)).intValue());
            return;
        }
        super.onBackPressed();
    }
}
