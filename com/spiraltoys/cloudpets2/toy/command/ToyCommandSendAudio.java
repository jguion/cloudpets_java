package com.spiraltoys.cloudpets2.toy.command;

import android.content.Context;
import android.net.Uri;
import com.spiraltoys.cloudpets2.toy.ToyAudioSlot;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskSendAudio;

public class ToyCommandSendAudio extends ToyCommand {
    private Uri mLocalUri;
    private ToyAudioSlot mSlot;

    public ToyCommandSendAudio(Uri localUri, ToyAudioSlot slot) {
        this.mLocalUri = localUri;
        this.mSlot = slot;
    }

    public Uri getLocalUri() {
        return this.mLocalUri;
    }

    public ToyAudioSlot getSlot() {
        return this.mSlot;
    }

    public byte getSlotAsByte() {
        return (byte) this.mSlot.getValue();
    }

    public ToyTask newTask(Context context, ToyPeripheral peripheral) {
        return new ToyTaskSendAudio(peripheral, this, context);
    }
}
