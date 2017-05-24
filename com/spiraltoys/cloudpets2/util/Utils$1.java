package com.spiraltoys.cloudpets2.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.spiraltoys.cloudpets2.WelcomeActivity;
import com.spiraltoys.cloudpets2.model.util.AccountHelper;

class Utils$1 implements OnClickListener {
    final /* synthetic */ Context val$context;

    Utils$1(Context context) {
        this.val$context = context;
    }

    public void onClick(DialogInterface dialog, int which) {
        AccountHelper.logOut(this.val$context);
        this.val$context.startActivity(new Intent(this.val$context, WelcomeActivity.class).setFlags(268468224));
    }
}
