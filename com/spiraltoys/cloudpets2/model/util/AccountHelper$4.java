package com.spiraltoys.cloudpets2.model.util;

import android.content.Context;
import android.os.AsyncTask;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.spiraltoys.cloudpets2.util.SettingsManager;
import com.spiraltoys.cloudpets2.util.Utils;
import org.mindrot.jbcrypt.BCrypt;

class AccountHelper$4 extends AsyncTask<String, Void, Boolean> {
    final /* synthetic */ Context val$c;
    final /* synthetic */ AccountHelper$PasswordCheckCallback val$callback;
    final /* synthetic */ String val$hashed;

    AccountHelper$4(String str, Context context, AccountHelper$PasswordCheckCallback accountHelper$PasswordCheckCallback) {
        this.val$hashed = str;
        this.val$c = context;
        this.val$callback = accountHelper$PasswordCheckCallback;
    }

    protected Boolean doInBackground(String... password) {
        if (!(this.val$hashed == null || password == null)) {
            boolean isLocalPasswordValid = BCrypt.checkpw(password[0], this.val$hashed);
            if (isLocalPasswordValid || !Utils.isNetworkConnectionAvailable(this.val$c)) {
                return Boolean.valueOf(isLocalPasswordValid);
            }
            try {
                ParseUser newUser = ParseUser.logIn(ParseUser.getCurrentUser().getUsername(), password[0]);
                if (newUser != null && newUser.isAuthenticated()) {
                    SettingsManager.setCredentials(this.val$c, newUser.getUsername(), BCrypt.hashpw(password[0], BCrypt.gensalt()));
                    return Boolean.valueOf(true);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return Boolean.valueOf(false);
            }
        }
        return Boolean.valueOf(false);
    }

    protected void onPostExecute(Boolean isCorrect) {
        this.val$callback.onPasswordChecked(isCorrect.booleanValue());
    }
}
