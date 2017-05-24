package com.spiraltoys.cloudpets2.toy.command;

import android.content.Context;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskConnect;

public class ToyCommandConnect extends ToyCommand {
    private boolean mAcceptAudio;
    private String mAddress;
    private String mIdentifier;

    public ToyCommandConnect(String identifier, String address, boolean acceptAudio) {
        this.mIdentifier = identifier;
        this.mAddress = address;
        this.mAcceptAudio = acceptAudio;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public boolean shouldAcceptAudio() {
        return this.mAcceptAudio;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ToyCommandConnect)) {
            return false;
        }
        ToyCommandConnect other = (ToyCommandConnect) o;
        if (this.mIdentifier != null) {
            if (!this.mIdentifier.equals(other.mIdentifier)) {
                return false;
            }
        } else if (other.mIdentifier != null) {
            return false;
        }
        if (this.mAddress != null) {
            if (!this.mAddress.equals(other.mAddress)) {
                return false;
            }
        } else if (other.mAddress != null) {
            return false;
        }
        if (this.mAcceptAudio == other.mAcceptAudio) {
            return true;
        }
        return false;
    }

    public ToyTask newTask(Context context, ToyPeripheral peripheral) {
        return new ToyTaskConnect(peripheral);
    }
}
