package com.spiraltoys.cloudpets2.model.util;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import java.util.List;

class ModelHelper$6 implements SaveCallback {
    final /* synthetic */ SaveCallback val$callback;

    ModelHelper$6(SaveCallback saveCallback) {
        this.val$callback = saveCallback;
    }

    public void done(ParseException e) {
        if (e == null) {
            ModelHelper.fetchAllFriendsToLocalDatastore(new FindCallback<FriendRecord>() {
                public void done(List<FriendRecord> list, ParseException e) {
                    ModelHelper$6.this.val$callback.done(e);
                }
            });
        } else {
            this.val$callback.done(e);
        }
    }
}
