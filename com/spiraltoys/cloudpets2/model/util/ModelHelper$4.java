package com.spiraltoys.cloudpets2.model.util;

import com.parse.ParseException;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.model.Profile;

class ModelHelper$4 implements SaveCallback {
    final /* synthetic */ SaveCallback val$callback;
    final /* synthetic */ Profile val$profile;

    ModelHelper$4(SaveCallback saveCallback, Profile profile) {
        this.val$callback = saveCallback;
        this.val$profile = profile;
    }

    public void done(ParseException e) {
        if (e != null) {
            e.printStackTrace();
            this.val$callback.done(e);
            return;
        }
        this.val$profile.pinInBackground(ModelHelper.PROFILES_TAG, this.val$callback);
    }
}
