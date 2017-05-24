package com.spiraltoys.cloudpets2.model.util;

import com.parse.ParseException;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.events.FriendRecordSavedToLocalDatastoreEvent;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import de.greenrobot.event.EventBus;

class ModelHelper$5 implements SaveCallback {
    final /* synthetic */ SaveCallback val$callback;
    final /* synthetic */ FriendRecord val$friend;

    ModelHelper$5(SaveCallback saveCallback, FriendRecord friendRecord) {
        this.val$callback = saveCallback;
        this.val$friend = friendRecord;
    }

    public void done(ParseException e) {
        if (e != null) {
            this.val$callback.done(e);
        } else {
            this.val$friend.pinInBackground(ModelHelper.FRIENDS_TAG, new SaveCallback() {
                public void done(ParseException e) {
                    if (e != null) {
                        ModelHelper$5.this.val$callback.done(e);
                        return;
                    }
                    EventBus.getDefault().post(new FriendRecordSavedToLocalDatastoreEvent());
                    ModelHelper$5.this.val$callback.done(null);
                }
            });
        }
    }
}
