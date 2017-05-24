package com.spiraltoys.cloudpets2.events;

import com.spiraltoys.cloudpets2.model.VoiceMessage;

public class VoiceMessageSelectedForEditEvent {
    private VoiceMessage mVoiceMessage;

    public VoiceMessageSelectedForEditEvent(VoiceMessage voiceMessage) {
        this.mVoiceMessage = voiceMessage;
    }

    public VoiceMessage getVoiceMessage() {
        return this.mVoiceMessage;
    }
}
