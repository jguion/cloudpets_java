package com.spiraltoys.cloudpets2.model.util;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

class ModelHelper$8 implements SaveCallback {
    final /* synthetic */ SaveCallback val$callback;
    final /* synthetic */ ParseUser val$currentUser;
    final /* synthetic */ String val$originalEmail;

    ModelHelper$8(ParseUser parseUser, String str, SaveCallback saveCallback) {
        this.val$currentUser = parseUser;
        this.val$originalEmail = str;
        this.val$callback = saveCallback;
    }

    public void done(ParseException e) {
        this.val$currentUser.setEmail(this.val$originalEmail);
        this.val$currentUser.saveEventually(this.val$callback);
    }
}
