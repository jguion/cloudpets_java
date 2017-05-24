package com.spiraltoys.cloudpets2.toy.task;

import android.os.Handler;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandStartLoopPlayback;
import com.spiraltoys.cloudpets2.toy.task.ToyTask.Listener;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

public class ToyTaskStartLoopPlayback extends ToyTask {
    private static final byte COMMAND_PLAY_AUDIO_LOOPED = (byte) 12;
    private static final long SUCCESS_SAFETY_DELAY = 250;
    private final ToyCommandStartLoopPlayback mCommand;

    public ToyTaskStartLoopPlayback(ToyPeripheral toyPeripheral, ToyCommandStartLoopPlayback toyCommandStartLoopPlayback) {
        super(toyPeripheral);
        this.mCommand = toyCommandStartLoopPlayback;
    }

    public ToyState getState() {
        return ToyState.WRITING_CHARACTERISTIC;
    }

    public void start(Listener listener) throws NullPointerException {
        super.start(listener);
        ByteBuffer playAudioDataBuffer = ByteBuffer.allocate(5);
        playAudioDataBuffer.order(ByteOrder.LITTLE_ENDIAN);
        playAudioDataBuffer.put((byte) 12);
        playAudioDataBuffer.putShort(this.mCommand.getNumberOfLoops());
        playAudioDataBuffer.putShort((short) this.mCommand.getAudioLengthMs());
        getPeripheral().writeCharacteristic(ToyPeripheral.COMMAND_CHAR_UUID, playAudioDataBuffer.array());
    }

    public void onCharacteristicWrite(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
        super.onCharacteristicWrite(peripheral, characteristic, value, error);
        if (error != null) {
            getListener().onFailure(this, this.mCommand.getCommandIdentifier(), error);
        } else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    ToyTaskStartLoopPlayback.this.getListener().onSuccess(ToyTaskStartLoopPlayback.this, ToyTaskStartLoopPlayback.this.mCommand.getCommandIdentifier(), ToyTaskStartLoopPlayback.this.mCommand);
                }
            }, SUCCESS_SAFETY_DELAY);
        }
    }
}
