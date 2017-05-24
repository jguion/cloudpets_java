package com.spiraltoys.cloudpets2.model.util;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.spiraltoys.cloudpets2.model.FriendAcceptanceNotification;
import java.util.List;

class ModelHelper$7 implements FindCallback<FriendAcceptanceNotification> {
    final /* synthetic */ DeleteCallback val$callback;

    ModelHelper$7(DeleteCallback deleteCallback) {
        this.val$callback = deleteCallback;
    }

    public void done(List<FriendAcceptanceNotification> friendAcceptanceNotifications, ParseException e) {
        if (e != null) {
            this.val$callback.done(e);
        } else if (friendAcceptanceNotifications != null && friendAcceptanceNotifications.size() > 0) {
            ParseObject.deleteAllInBackground(friendAcceptanceNotifications, this.val$callback);
        }
    }
}
