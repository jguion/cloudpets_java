package com.spiraltoys.cloudpets2.toy.command;

import android.content.Context;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskUpdateFirmware;

public class ToyCommandUpdateFirmware extends ToyCommand {
    public ToyTask newTask(Context context, ToyPeripheral peripheral) {
        return new ToyTaskUpdateFirmware(peripheral);
    }
}
