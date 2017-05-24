package com.spiraltoys.cloudpets2.toy.command;

import android.content.Context;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskDisconnect;

public class ToyCommandDisconnect extends ToyCommand {
    private boolean mPerformImmediately;

    public ToyCommandDisconnect(boolean performImmediately) {
        this.mPerformImmediately = performImmediately;
    }

    public boolean isToBePerformedImmediately() {
        return this.mPerformImmediately;
    }

    public ToyTask newTask(Context context, ToyPeripheral peripheral) {
        return new ToyTaskDisconnect(peripheral);
    }
}
