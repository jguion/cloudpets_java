package com.spiraltoys.cloudpets2.toy.command;

import android.content.Context;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;

public abstract class ToyCommand {
    private final ToyCommandIdentifier mCommandIdentifier = new ToyCommandIdentifier(getClass());

    public abstract ToyTask newTask(Context context, ToyPeripheral toyPeripheral);

    protected ToyCommand() {
    }

    public ToyCommandIdentifier getCommandIdentifier() {
        return this.mCommandIdentifier;
    }
}
