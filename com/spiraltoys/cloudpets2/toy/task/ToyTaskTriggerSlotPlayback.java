package com.spiraltoys.cloudpets2.toy.task;

import android.os.Handler;
import android.os.Looper;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral.State;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandTriggerSlotPlayback;
import com.spiraltoys.cloudpets2.toy.task.ToyTask.Listener;
import java.util.UUID;

public class ToyTaskTriggerSlotPlayback extends ToyTask {
    private static final byte COMMAND_SEND_SNC7232_COMMAND = (byte) 8;
    private static final byte SNC7232_COMMAND_PLAY_SLOT = (byte) 1;
    private static final long SUCCESS_SAFETY_DELAY = 250;
    private ToyCommandTriggerSlotPlayback mCommand;

    public ToyTaskTriggerSlotPlayback(ToyPeripheral peripheral, ToyCommandTriggerSlotPlayback command) {
        super(peripheral);
        this.mCommand = command;
    }

    public ToyState getState() {
        return ToyState.WRITING_CHARACTERISTIC;
    }

    public void start(Listener listener) throws NullPointerException {
        super.start(listener);
        getPeripheral().writeCharacteristic(ToyPeripheral.COMMAND_CHAR_UUID, new byte[]{(byte) 8, (byte) 1, (byte) (this.mCommand.getSlot().getValue() + 1)});
    }

    public void onCharacteristicWrite(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
        super.onCharacteristicWrite(peripheral, characteristic, value, error);
        if (error != null) {
            getListener().onFailure(this, this.mCommand.getCommandIdentifier(), error);
        } else if (!this.mCommand.isSuccessDelayedUntilPlaybackCompeltion()) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    ToyTaskTriggerSlotPlayback.this.getListener().onSuccess(ToyTaskTriggerSlotPlayback.this, ToyTaskTriggerSlotPlayback.this.mCommand.getCommandIdentifier(), ToyTaskTriggerSlotPlayback.this.mCommand);
                }
            }, SUCCESS_SAFETY_DELAY);
        }
    }

    public void onToyStateChange(ToyPeripheral peripheral, State oldState, State newState) {
        super.onToyStateChange(peripheral, oldState, newState);
        if (!this.mCommand.isSuccessDelayedUntilPlaybackCompeltion() || newState != State.IDLE) {
            return;
        }
        if (oldState == State.AUDIO_PLAYBACK || oldState == State.GAME_MODE_PLAY_PAW_PRESSED || oldState == State.GAME_MODE_RECORD_PAW_PRESSED) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    ToyTaskTriggerSlotPlayback.this.getListener().onSuccess(ToyTaskTriggerSlotPlayback.this, ToyTaskTriggerSlotPlayback.this.mCommand.getCommandIdentifier(), ToyTaskTriggerSlotPlayback.this.mCommand);
                }
            }, SUCCESS_SAFETY_DELAY);
        } else {
            getListener().onFailure(this, this.mCommand.getCommandIdentifier(), new Error("Unexpected toy state change."));
        }
    }
}
