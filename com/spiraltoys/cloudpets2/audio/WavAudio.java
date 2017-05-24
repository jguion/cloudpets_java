package com.spiraltoys.cloudpets2.audio;

import com.parse.ParseException;
import java.io.Serializable;

public class WavAudio implements Serializable {
    private int audioFormat;
    private int channelConfig;
    private int channelsNum;
    private short[] data;
    private int framesNum = -1;
    private int sampleRate;

    public short[] getData() {
        return this.data;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public int getChannelConfig() {
        return this.channelConfig;
    }

    public int getChannelsNum() {
        return this.channelsNum;
    }

    public int getAudioFormat() {
        return this.audioFormat;
    }

    public int getDataSizeBytes() {
        return this.data.length * 2;
    }

    public int getFramesNum() {
        if (this.framesNum < 0) {
            this.framesNum = this.data.length / this.channelsNum;
        }
        return this.framesNum;
    }

    public WavAudio(short[] inData, int inSampleRate, int inChannelConfig, int inAudioFormat) {
        this.data = inData;
        this.sampleRate = inSampleRate;
        this.channelConfig = inChannelConfig;
        this.audioFormat = inAudioFormat;
        this.channelsNum = 1;
        switch (this.channelConfig) {
            case 12:
                this.channelsNum = 2;
                return;
            case ParseException.EMAIL_MISSING /*204*/:
            case 1052:
                this.channelsNum = 4;
                return;
            default:
                return;
        }
    }
}
