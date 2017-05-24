package com.spiraltoys.cloudpets2.toy.command;

import android.content.Context;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskSequence;
import java.util.ArrayList;

public class ToyCommandStartCommandSequence extends ToyCommand {
    private ArrayList<ToyCommand> mCommandSequence;

    public ToyCommandStartCommandSequence(ArrayList<ToyCommand> commandSequence) {
        this.mCommandSequence = commandSequence;
    }

    public ArrayList<ToyCommand> getCommandSequence() {
        return this.mCommandSequence;
    }

    public ToyTask newTask(Context context, ToyPeripheral peripheral) {
        return new ToyTaskSequence(context, peripheral, this);
    }
}
