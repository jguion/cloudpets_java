package com.spiraltoys.cloudpets2.toy.command;

import android.content.Context;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskSetIdentifier;

public class ToyCommandSetIdentifier extends ToyCommand {
    private String mIdentifier;

    public ToyCommandSetIdentifier(String identifier) {
        this.mIdentifier = identifier;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public ToyTask newTask(Context context, ToyPeripheral peripheral) {
        return new ToyTaskSetIdentifier(peripheral, this);
    }
}
