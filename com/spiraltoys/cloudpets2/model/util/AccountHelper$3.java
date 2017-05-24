package com.spiraltoys.cloudpets2.model.util;

import android.content.Context;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.model.VoiceMessage;

class AccountHelper$3 implements LogInCallback {
    final /* synthetic */ Context val$c;
    final /* synthetic */ LogInCallback val$callback;
    final /* synthetic */ String val$password;
    final /* synthetic */ String val$username;

    AccountHelper$3(Context context, String str, String str2, LogInCallback logInCallback) {
        this.val$c = context;
        this.val$username = str;
        this.val$password = str2;
        this.val$callback = logInCallback;
    }

    public void done(final ParseUser parseUser, ParseException e) {
        if (e == null) {
            AccountHelper.access$500(this.val$c, this.val$username, this.val$password);
            ParseInstallation installation = ParseInstallation.getCurrentInstallation();
            installation.put(VoiceMessage.USER, parseUser);
            installation.saveInBackground(new SaveCallback() {
                public void done(ParseException e) {
                    AccountHelper$3.this.val$callback.done(parseUser, e);
                }
            });
            return;
        }
        this.val$callback.done(parseUser, e);
    }
}
