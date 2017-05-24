package com.spiraltoys.cloudpets2.toy.task;

import android.content.Context;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.ToyTaskDispatcher;
import com.spiraltoys.cloudpets2.toy.command.ToyCommand;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandIdentifier;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandStartCommandSequence;
import com.spiraltoys.cloudpets2.toy.task.ToyTask.Listener;
import java.util.ArrayList;

public class ToyTaskSequence extends ToyTask implements Listener {
    private ToyCommandStartCommandSequence mCommand;
    private Context mContext;
    private ToyTask mCurrentTask;
    private int mCurrentTaskIndex;
    private ToyPeripheral mPeripheral;
    private ToyTaskDispatcher mTaskDispatcher = new ToyTaskDispatcher(this);

    public ToyTaskSequence(Context context, ToyPeripheral peripheral, ToyCommandStartCommandSequence command) throws NullPointerException {
        super(peripheral);
        this.mCommand = command;
        this.mContext = context;
        this.mCurrentTask = ((ToyCommand) command.getCommandSequence().get(this.mCurrentTaskIndex)).newTask(context, peripheral);
        this.mPeripheral = peripheral;
    }

    public void start(Listener listener) throws NullPointerException {
        super.start(listener);
        this.mTaskDispatcher.push(this.mCurrentTask);
    }

    public ToyState getState() {
        return this.mCurrentTask.getState();
    }

    public void onStart(ToyTask task) {
    }

    public void onSuccess(ToyTask task, ToyCommandIdentifier toyCommandIdentifier, Object data) {
        if (hasMoreTasks()) {
            this.mTaskDispatcher.push(nextTask());
        } else {
            getListener().onSuccess(this, this.mCommand.getCommandIdentifier(), this.mCommand);
        }
    }

    public void onFailure(ToyTask task, ToyCommandIdentifier toyCommandIdentifier, Error error) {
        getListener().onFailure(this, this.mCommand.getCommandIdentifier(), error);
    }

    private ToyTask nextTask() {
        ArrayList commandSequence = this.mCommand.getCommandSequence();
        int i = this.mCurrentTaskIndex + 1;
        this.mCurrentTaskIndex = i;
        ToyTask newTask = ((ToyCommand) commandSequence.get(i)).newTask(this.mContext, this.mPeripheral);
        this.mCurrentTask = newTask;
        return newTask;
    }

    private boolean hasMoreTasks() {
        return this.mCurrentTaskIndex < this.mCommand.getCommandSequence().size() + -1;
    }
}
