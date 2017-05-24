package com.spiraltoys.cloudpets2.model.util;

import android.content.Context;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import java.util.List;

class AccountHelper$2 implements SaveCallback {
    final /* synthetic */ Context val$c;
    final /* synthetic */ SaveCallback val$callback;
    final /* synthetic */ String val$email;
    final /* synthetic */ String val$password;

    AccountHelper$2(SaveCallback saveCallback, String str, String str2, Context context) {
        this.val$callback = saveCallback;
        this.val$email = str;
        this.val$password = str2;
        this.val$c = context;
    }

    public void done(ParseException e) {
        if (e != null) {
            this.val$callback.done(e);
            return;
        }
        ParseUser user = ParseUser.getCurrentUser();
        user.setUsername(this.val$email.toLowerCase());
        user.setEmail(this.val$email);
        user.setPassword(this.val$password);
        user.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e != null) {
                    AccountHelper$2.this.val$callback.done(e);
                    return;
                }
                AccountHelper.access$500(AccountHelper$2.this.val$c, AccountHelper$2.this.val$email, AccountHelper$2.this.val$password);
                ParseInstallation installation = ParseInstallation.getCurrentInstallation();
                installation.put(VoiceMessage.USER, ParseUser.getCurrentUser());
                installation.saveEventually();
                ModelHelper.fetchAllProfilesToLocalDatastore(new FindCallback<Profile>() {
                    public void done(List<Profile> list, ParseException e) {
                        AccountHelper$2.this.val$callback.done(e);
                    }
                });
            }
        });
    }
}
