package com.spiraltoys.cloudpets2.model.util;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.events.FriendRecordSavedToLocalDatastoreEvent;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import de.greenrobot.event.EventBus;
import java.util.List;

class ModelHelper$12 implements FindCallback<FriendRecord> {
    final /* synthetic */ FindCallback val$callback;

    ModelHelper$12(FindCallback findCallback) {
        this.val$callback = findCallback;
    }

    public void done(List<FriendRecord> list, ParseException e) {
        EventBus.getDefault().post(new FriendRecordSavedToLocalDatastoreEvent());
        if (this.val$callback != null) {
            this.val$callback.done(list, e);
        }
    }
}
