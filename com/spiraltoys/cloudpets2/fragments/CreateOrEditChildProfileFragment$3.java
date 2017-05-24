package com.spiraltoys.cloudpets2.fragments;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

class CreateOrEditChildProfileFragment$3 implements OnClickListener {
    final /* synthetic */ CreateOrEditChildProfileFragment this$0;
    final /* synthetic */ Dialog val$dialog;

    CreateOrEditChildProfileFragment$3(CreateOrEditChildProfileFragment this$0, Dialog dialog) {
        this.this$0 = this$0;
        this.val$dialog = dialog;
    }

    public void onClick(View v) {
        this.val$dialog.dismiss();
    }
}
