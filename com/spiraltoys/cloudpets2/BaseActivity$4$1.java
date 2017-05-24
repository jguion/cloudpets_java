package com.spiraltoys.cloudpets2;

import android.content.Intent;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.spiraltoys.cloudpets2.BaseActivity.4;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.util.AccountHelper;
import com.spiraltoys.cloudpets2.util.Utils;

class BaseActivity$4$1 implements GetCallback<ParseUser> {
    final /* synthetic */ 4 this$1;

    BaseActivity$4$1(4 this$1) {
        this.this$1 = this$1;
    }

    public void done(ParseUser parseUser, ParseException e) {
        if (parseUser == null || !AccountHelper.isEmailVerified(parseUser)) {
            Utils.showHintDialog(this.this$1.this$0, R.string.message_account_not_yet_verified);
        } else {
            this.this$1.this$0.startActivityAsNewTask(new Intent(this.this$1.this$0, OnboardingSurveyActivity.class));
            this.this$1.this$0.finish();
        }
        this.this$1.this$0.hideProgressDelayed();
    }
}
