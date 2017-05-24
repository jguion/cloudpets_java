package com.spiraltoys.cloudpets2.toy.command;

import android.content.Context;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskSetGameModeState;

public class ToyCommandSetGameModeState extends ToyCommand {
    private boolean mGameModeState;

    public ToyCommandSetGameModeState(boolean gameModeState) {
        this.mGameModeState = gameModeState;
    }

    public boolean getGameModeState() {
        return this.mGameModeState;
    }

    public ToyTask newTask(Context context, ToyPeripheral peripheral) {
        return new ToyTaskSetGameModeState(peripheral, this);
    }
}
