package com.spiraltoys.cloudpets2.events;

import com.spiraltoys.cloudpets2.audio.WavAudio;

public class RecordingFinishedEvent {
    private WavAudio mAudio;

    public RecordingFinishedEvent(WavAudio audio) {
        this.mAudio = audio;
    }

    public WavAudio getAudio() {
        return this.mAudio;
    }
}
