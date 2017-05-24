package com.spiraltoys.cloudpets2.toy.command;

import android.content.Context;
import com.spiraltoys.cloudpets2.toy.ToyAudioSlot;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskTriggerSlotPlayback;

public class ToyCommandTriggerSlotPlayback extends ToyCommand {
    private boolean mIsSuccessDelayedUntilPlaybackCompletion;
    private ToyAudioSlot mSlot;

    public ToyCommandTriggerSlotPlayback(ToyAudioSlot slot, boolean isSuccessDelayedUntilPlaybackCompletion) {
        this.mSlot = slot;
        this.mIsSuccessDelayedUntilPlaybackCompletion = isSuccessDelayedUntilPlaybackCompletion;
    }

    public ToyAudioSlot getSlot() {
        return this.mSlot;
    }

    public boolean isSuccessDelayedUntilPlaybackCompeltion() {
        return this.mIsSuccessDelayedUntilPlaybackCompletion;
    }

    public ToyTask newTask(Context context, ToyPeripheral peripheral) {
        return new ToyTaskTriggerSlotPlayback(peripheral, this);
    }
}
