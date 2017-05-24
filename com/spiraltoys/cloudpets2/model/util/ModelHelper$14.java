package com.spiraltoys.cloudpets2.model.util;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import java.util.List;

class ModelHelper$14 implements FindCallback<VoiceMessage> {
    final /* synthetic */ FindCallback val$callback;
    final /* synthetic */ boolean val$excludeProfile;
    final /* synthetic */ boolean val$isParentalControlled;
    final /* synthetic */ Profile val$profile;

    ModelHelper$14(Profile profile, boolean z, boolean z2, FindCallback findCallback) {
        this.val$profile = profile;
        this.val$excludeProfile = z;
        this.val$isParentalControlled = z2;
        this.val$callback = findCallback;
    }

    public void done(List<VoiceMessage> voiceMessages, ParseException e) {
        if (e != null || voiceMessages.isEmpty()) {
            ModelHelper.fetchAllVoiceMessagesToLocalDatastore(new FindCallback<VoiceMessage>() {
                public void done(List<VoiceMessage> list, ParseException e) {
                    ModelHelper.access$100(ModelHelper$14.this.val$profile, ModelHelper$14.this.val$excludeProfile, ModelHelper$14.this.val$isParentalControlled).fromPin(ModelHelper.VOICE_MESSAGES_TAG).findInBackground(ModelHelper$14.this.val$callback);
                }
            });
        } else {
            this.val$callback.done(voiceMessages, null);
        }
    }
}
