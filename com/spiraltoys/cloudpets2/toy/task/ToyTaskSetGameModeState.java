package com.spiraltoys.cloudpets2.toy.task;

import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetGameModeState;
import com.spiraltoys.cloudpets2.toy.task.ToyTask.Listener;
import java.util.UUID;

public class ToyTaskSetGameModeState extends ToyTask {
    private static final byte COMMAND_SET_GAME_MODE = (byte) 11;
    private static final byte FLAG_GAME_MODE_OFF = (byte) 0;
    private static final byte FLAG_GAME_MODE_ON = (byte) 1;
    private ToyCommandSetGameModeState mCommand;

    public ToyTaskSetGameModeState(ToyPeripheral peripheral, ToyCommandSetGameModeState toyCommandSetGameModeState) throws NullPointerException {
        super(peripheral);
        this.mCommand = toyCommandSetGameModeState;
    }

    public ToyState getState() {
        return ToyState.WRITING_CHARACTERISTIC;
    }

    public void start(Listener listener) throws NullPointerException {
        byte gameMode;
        super.start(listener);
        if (this.mCommand.getGameModeState()) {
            gameMode = (byte) 1;
        } else {
            gameMode = (byte) 0;
        }
        getPeripheral().writeCharacteristic(ToyPeripheral.COMMAND_CHAR_UUID, new byte[]{(byte) 11, gameMode});
    }

    public void onCharacteristicWrite(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
        super.onCharacteristicWrite(peripheral, characteristic, value, error);
        if (error != null) {
            getListener().onFailure(this, this.mCommand.getCommandIdentifier(), error);
        } else {
            getListener().onSuccess(this, this.mCommand.getCommandIdentifier(), this.mCommand);
        }
    }
}
