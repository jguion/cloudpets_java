package com.spiraltoys.cloudpets2;

import android.content.Intent;
import com.parse.CountCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.spiraltoys.cloudpets2.LogInActivity.1;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.SettingsManager;
import com.spiraltoys.cloudpets2.util.Utils;

class LogInActivity$1$1 implements GetCallback<ParseUser> {
    final /* synthetic */ 1 this$1;

    LogInActivity$1$1(1 this$1) {
        this.this$1 = this$1;
    }

    public void done(ParseUser parseUser, ParseException e) {
        if (e != null) {
            this.this$1.this$0.hideProgress();
            Utils.showErrorDialog(this.this$1.this$0, ErrorMessages.getStringResourceIdForLoginException(this.this$1.this$0, e));
        } else if (SettingsManager.isInitialToySetupComplete(this.this$1.this$0)) {
            this.this$1.this$0.hideProgress();
            this.this$1.this$0.startActivityAsNewTask(new Intent(this.this$1.this$0, AdultDashboardActivity.class));
        } else {
            ModelHelper.getLocalChildProfilesCount(new CountCallback() {
                public void done(int count, ParseException e) {
                    LogInActivity$1$1.this.this$1.this$0.hideProgress();
                    if (e != null) {
                        Utils.showErrorDialog(LogInActivity$1$1.this.this$1.this$0, ErrorMessages.getStringResourceIdForLoginException(LogInActivity$1$1.this.this$1.this$0, e));
                    } else if (count > 0) {
                        LogInActivity$1$1.this.this$1.this$0.startActivityAsNewTask(new Intent(LogInActivity$1$1.this.this$1.this$0, AdultDashboardActivity.class));
                    } else {
                        LogInActivity$1$1.this.this$1.this$0.startActivityAsNewTask(new Intent(LogInActivity$1$1.this.this$1.this$0, OnboardingSurveyActivity.class));
                    }
                }
            });
        }
    }
}
