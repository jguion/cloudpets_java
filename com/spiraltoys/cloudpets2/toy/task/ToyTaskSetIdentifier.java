package com.spiraltoys.cloudpets2.toy.task;

import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetIdentifier;
import com.spiraltoys.cloudpets2.toy.task.ToyTask.Listener;
import java.util.UUID;

public class ToyTaskSetIdentifier extends ToyTask {
    private ToyCommandSetIdentifier mCommand;

    public ToyTaskSetIdentifier(ToyPeripheral peripheral, ToyCommandSetIdentifier command) throws NullPointerException {
        super(peripheral);
        this.mCommand = command;
    }

    public ToyState getState() {
        return ToyState.WRITING_CHARACTERISTIC;
    }

    public void start(Listener listener) throws NullPointerException {
        super.start(listener);
        getPeripheral().writeCharacteristic(ToyPeripheral.CONFIG_CHAR_UUID, this.mCommand.getIdentifier().getBytes());
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
