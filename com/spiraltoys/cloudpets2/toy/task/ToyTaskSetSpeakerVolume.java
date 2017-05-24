package com.spiraltoys.cloudpets2.toy.task;

import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetSpeakerVolume;
import com.spiraltoys.cloudpets2.toy.task.ToyTask.Listener;
import java.util.UUID;

public class ToyTaskSetSpeakerVolume extends ToyTask {
    private static byte COMMAND_SET_CURRENT_VOLUME = (byte) 0;
    private final ToyCommandSetSpeakerVolume mCommand;

    public ToyTaskSetSpeakerVolume(ToyPeripheral toyPeripheral, ToyCommandSetSpeakerVolume toyCommandSetSpeakerVolume) {
        super(toyPeripheral);
        this.mCommand = toyCommandSetSpeakerVolume;
    }

    public ToyState getState() {
        return ToyState.WRITING_CHARACTERISTIC;
    }

    public void start(Listener listener) throws NullPointerException {
        super.start(listener);
        getPeripheral().writeCharacteristic(ToyPeripheral.VOLUME_CHAR_UUID, new byte[]{COMMAND_SET_CURRENT_VOLUME, this.mCommand.getVolume()});
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
