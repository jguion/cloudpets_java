package com.spiraltoys.cloudpets2;

import com.parse.ParseException;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.BaseActivity.5;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.Utils;

class BaseActivity$5$1 implements SaveCallback {
    final /* synthetic */ 5 this$1;

    BaseActivity$5$1(5 this$1) {
        this.this$1 = this$1;
    }

    public void done(ParseException e) {
        this.this$1.this$0.hideProgress();
        if (e != null) {
            Utils.showErrorDialog(this.this$1.this$0, ErrorMessages.getStringResourceIdForErrorSendingVerificationEmail(this.this$1.this$0, e));
        }
    }
}
