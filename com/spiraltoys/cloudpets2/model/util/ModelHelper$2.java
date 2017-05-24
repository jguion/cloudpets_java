package com.spiraltoys.cloudpets2.model.util;

import com.parse.ParseException;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.events.VoiceMessageSavedToLocalDatastoreEvent;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import de.greenrobot.event.EventBus;

class ModelHelper$2 implements SaveCallback {
    final /* synthetic */ SaveCallback val$callback;
    final /* synthetic */ VoiceMessage val$voiceMessage;

    ModelHelper$2(SaveCallback saveCallback, VoiceMessage voiceMessage) {
        this.val$callback = saveCallback;
        this.val$voiceMessage = voiceMessage;
    }

    public void done(ParseException e) {
        if (e != null) {
            e.printStackTrace();
            this.val$callback.done(e);
            return;
        }
        this.val$voiceMessage.pinInBackground(ModelHelper.VOICE_MESSAGES_TAG, new SaveCallback() {
            public void done(ParseException e) {
                if (e != null) {
                    ModelHelper$2.this.val$callback.done(e);
                    return;
                }
                EventBus.getDefault().post(new VoiceMessageSavedToLocalDatastoreEvent());
                ModelHelper$2.this.val$callback.done(null);
            }
        });
    }
}
