package com.spiraltoys.cloudpets2.toy.task;

import android.os.Handler;
import android.os.Looper;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetLedState;
import com.spiraltoys.cloudpets2.toy.task.ToyTask.Listener;
import java.util.UUID;

public class ToyTaskSetLedState extends ToyTask {
    private static final long SUCCESS_SAFETY_DELAY = 250;
    private ToyCommandSetLedState mCommand;

    public ToyTaskSetLedState(ToyPeripheral peripheral, ToyCommandSetLedState command) throws NullPointerException {
        super(peripheral);
        this.mCommand = command;
    }

    public ToyState getState() {
        return ToyState.WRITING_CHARACTERISTIC;
    }

    public void start(Listener listener) throws NullPointerException {
        super.start(listener);
        getPeripheral().writeCharacteristic(ToyPeripheral.LED_CHAR_UUID, new byte[]{this.mCommand.getLedState(), (byte) (this.mCommand.getPeriod() & 255), (byte) ((this.mCommand.getPeriod() >> 8) & 255), (byte) 100, (byte) 0});
    }

    public void onCharacteristicWrite(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
        super.onCharacteristicWrite(peripheral, characteristic, value, error);
        if (error != null) {
            getListener().onFailure(this, this.mCommand.getCommandIdentifier(), error);
        } else {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    ToyTaskSetLedState.this.getListener().onSuccess(ToyTaskSetLedState.this, ToyTaskSetLedState.this.mCommand.getCommandIdentifier(), ToyTaskSetLedState.this.mCommand);
                }
            }, SUCCESS_SAFETY_DELAY);
        }
    }
}
