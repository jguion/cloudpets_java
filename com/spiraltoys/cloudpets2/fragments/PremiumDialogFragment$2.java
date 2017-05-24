package com.spiraltoys.cloudpets2.fragments;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

class PremiumDialogFragment$2 implements OnClickListener {
    final /* synthetic */ PremiumDialogFragment this$0;

    PremiumDialogFragment$2(PremiumDialogFragment this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.spiraltoys.cloudpets2.premium"));
        this.this$0.startActivity(intent);
    }
}
