package com.spiraltoys.cloudpets2.events;

import com.spiraltoys.cloudpets2.model.VoiceMessage;

public class VoiceMessageSelectedForPlaybackEvent {
    private VoiceMessage mVoiceMessage;

    public VoiceMessageSelectedForPlaybackEvent(VoiceMessage voiceMessage) {
        this.mVoiceMessage = voiceMessage;
    }

    public VoiceMessage getVoiceMessage() {
        return this.mVoiceMessage;
    }
}
