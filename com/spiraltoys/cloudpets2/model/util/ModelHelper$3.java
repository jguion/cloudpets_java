package com.spiraltoys.cloudpets2.model.util;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.events.VoiceMessageSavedToLocalDatastoreEvent;
import de.greenrobot.event.EventBus;
import java.util.List;

class ModelHelper$3 implements SaveCallback {
    final /* synthetic */ SaveCallback val$callback;
    final /* synthetic */ List val$voiceMessages;

    ModelHelper$3(SaveCallback saveCallback, List list) {
        this.val$callback = saveCallback;
        this.val$voiceMessages = list;
    }

    public void done(ParseException e) {
        if (e != null) {
            e.printStackTrace();
            this.val$callback.done(e);
            return;
        }
        ParseObject.pinAllInBackground(ModelHelper.VOICE_MESSAGES_TAG, this.val$voiceMessages, new SaveCallback() {
            public void done(ParseException e) {
                if (e != null) {
                    ModelHelper$3.this.val$callback.done(e);
                    return;
                }
                EventBus.getDefault().post(new VoiceMessageSavedToLocalDatastoreEvent());
                ModelHelper$3.this.val$callback.done(null);
            }
        });
    }
}
