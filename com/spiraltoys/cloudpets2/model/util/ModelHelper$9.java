package com.spiraltoys.cloudpets2.model.util;

import com.parse.FunctionCallback;
import com.parse.ParseException;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.model.FriendRecord;

class ModelHelper$9 implements FunctionCallback<FriendRecord> {
    final /* synthetic */ FunctionCallback val$callback;

    ModelHelper$9(FunctionCallback functionCallback) {
        this.val$callback = functionCallback;
    }

    public void done(final FriendRecord friendRecord, ParseException e) {
        if (e != null) {
            this.val$callback.done(null, e);
        } else if (friendRecord == null) {
            this.val$callback.done(null, new ParseException(-1, "An error occurred resolving friend request."));
        } else {
            friendRecord.pinInBackground(ModelHelper.FRIENDS_TAG, new SaveCallback() {
                public void done(ParseException e) {
                    ModelHelper$9.this.val$callback.done(friendRecord, e);
                }
            });
        }
    }
}
