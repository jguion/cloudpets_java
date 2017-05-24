package com.spiraltoys.cloudpets2.model.util;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.Profile;
import java.util.List;

class ModelHelper$1 implements FindCallback<Profile> {
    final /* synthetic */ GetCallback val$callback;

    ModelHelper$1(GetCallback getCallback) {
        this.val$callback = getCallback;
    }

    public void done(List<Profile> list, ParseException e) {
        if (e != null) {
            this.val$callback.done(ParseUser.getCurrentUser(), e);
        } else {
            ModelHelper.fetchAllFriendsToLocalDatastore(new FindCallback<FriendRecord>() {
                public void done(List<FriendRecord> list, ParseException e) {
                    ModelHelper$1.this.val$callback.done(ParseUser.getCurrentUser(), e);
                }
            });
        }
    }
}
