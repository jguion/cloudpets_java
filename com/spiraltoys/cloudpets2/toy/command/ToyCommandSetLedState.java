package com.spiraltoys.cloudpets2.toy.command;

import android.content.Context;
import com.spiraltoys.cloudpets2.toy.ToyLedState;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskSetLedState;

public class ToyCommandSetLedState extends ToyCommand {
    private ToyLedState mLedState;
    private int mPeriod;

    public ToyCommandSetLedState(ToyLedState state, int period) throws NullPointerException {
        this.mLedState = state;
        this.mPeriod = period;
    }

    public byte getLedState() {
        return (byte) this.mLedState.getValue();
    }

    public short getPeriod() {
        if (this.mLedState == ToyLedState.BLINKING) {
            return (short) this.mPeriod;
        }
        return (short) 0;
    }

    public ToyTask newTask(Context context, ToyPeripheral peripheral) {
        return new ToyTaskSetLedState(peripheral, this);
    }
}
