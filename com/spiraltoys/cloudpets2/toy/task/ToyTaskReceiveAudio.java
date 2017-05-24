package com.spiraltoys.cloudpets2.toy.task;

import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral.State;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.task.ToyTask.Listener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ToyTaskReceiveAudio extends ToyTask {
    private static final byte COMMAND_DOWNLOAD = (byte) 2;
    private String mFilePath;
    private ByteArrayOutputStream mOutputStream = new ByteArrayOutputStream();

    public ToyTaskReceiveAudio(ToyPeripheral peripheral, String filePath) throws NullPointerException {
        super(peripheral);
        if (filePath == null) {
            throw new NullPointerException();
        }
        this.mFilePath = filePath;
    }

    public ToyState getState() {
        return ToyState.RECEIVING_AUDIO;
    }

    public void start(Listener listener) throws NullPointerException {
        super.start(listener);
        getPeripheral().writeCharacteristic(ToyPeripheral.COMMAND_CHAR_UUID, new byte[]{(byte) 2});
    }

    private void writeChunk(byte[] chunk) {
        try {
            this.mOutputStream.write(chunk);
        } catch (IOException e) {
            getListener().onFailure(this, null, new Error(getPeripheral().getContext().getString(R.string.error_decode_audio_failed)));
        }
    }

    private void saveAudio() {
        new 1(this).execute(new ByteArrayOutputStream[]{this.mOutputStream});
    }

    public void onCharacteristicWrite(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
        super.onCharacteristicWrite(peripheral, characteristic, value, error);
        if (error != null) {
            getListener().onFailure(this, null, error);
        }
    }

    public void onCharacteristicChanged(ToyPeripheral peripheral, UUID characteristic, byte[] value) {
        super.onCharacteristicChanged(peripheral, characteristic, value);
        if (ToyPeripheral.RECEIVE_AUDIO_CHAR_UUID.equals(characteristic)) {
            writeChunk(value);
        }
    }

    public void onToyStateChange(ToyPeripheral peripheral, State oldState, State newState) {
        if (newState == State.IDLE && oldState == State.AUDIO_DOWNLOAD) {
            saveAudio();
        }
    }
}
