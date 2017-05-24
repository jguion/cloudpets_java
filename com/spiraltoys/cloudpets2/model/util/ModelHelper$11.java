package com.spiraltoys.cloudpets2.model.util;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.events.ProfileSavedToLocalDatastoreEvent;
import com.spiraltoys.cloudpets2.model.Profile;
import de.greenrobot.event.EventBus;
import java.util.List;

class ModelHelper$11 implements FindCallback<Profile> {
    final /* synthetic */ FindCallback val$callback;

    ModelHelper$11(FindCallback findCallback) {
        this.val$callback = findCallback;
    }

    public void done(List<Profile> list, ParseException e) {
        EventBus.getDefault().post(new ProfileSavedToLocalDatastoreEvent());
        if (this.val$callback != null) {
            this.val$callback.done(list, e);
        }
    }
}
