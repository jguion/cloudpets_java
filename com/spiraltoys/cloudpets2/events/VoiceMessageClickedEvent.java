package com.spiraltoys.cloudpets2.events;

import com.spiraltoys.cloudpets2.model.VoiceMessage;

public class VoiceMessageClickedEvent {
    private VoiceMessage mVoiceMessage;

    public VoiceMessageClickedEvent(VoiceMessage voiceMessage) {
        this.mVoiceMessage = voiceMessage;
    }

    public VoiceMessage getVoiceMessage() {
        return this.mVoiceMessage;
    }
}
