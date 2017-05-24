package com.spiraltoys.cloudpets2.model.util;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.model.Profile;

class ModelHelper$10 implements GetCallback<Profile> {
    final /* synthetic */ GetCallback val$callback;

    ModelHelper$10(GetCallback getCallback) {
        this.val$callback = getCallback;
    }

    public void done(Profile profile, ParseException e) {
        if (e != null) {
            this.val$callback.done(null, e);
        } else {
            this.val$callback.done(profile, null);
        }
    }
}
