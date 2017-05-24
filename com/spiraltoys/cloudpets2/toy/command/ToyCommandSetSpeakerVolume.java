package com.spiraltoys.cloudpets2.toy.command;

import android.content.Context;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskSetSpeakerVolume;

public class ToyCommandSetSpeakerVolume extends ToyCommand {
    private final byte mVolume;

    public ToyCommandSetSpeakerVolume(byte volume) {
        this.mVolume = volume;
    }

    public byte getVolume() {
        return this.mVolume;
    }

    public ToyTask newTask(Context context, ToyPeripheral peripheral) {
        return new ToyTaskSetSpeakerVolume(peripheral, this);
    }
}
