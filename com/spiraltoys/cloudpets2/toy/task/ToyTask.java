package com.spiraltoys.cloudpets2.toy.task;

import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral.State;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandIdentifier;
import java.util.UUID;

public abstract class ToyTask implements com.spiraltoys.cloudpets2.toy.ToyPeripheral.Listener {
    private final Listener mDummyListener = new Listener() {
        public void onStart(ToyTask task) {
        }

        public void onSuccess(ToyTask task, ToyCommandIdentifier identifier, Object data) {
        }

        public void onFailure(ToyTask task, ToyCommandIdentifier identifier, Error error) {
        }
    };
    private Listener mListener;
    private ToyPeripheral mPeripheral;

    public interface Listener {
        void onFailure(ToyTask toyTask, ToyCommandIdentifier toyCommandIdentifier, Error error);

        void onStart(ToyTask toyTask);

        void onSuccess(ToyTask toyTask, ToyCommandIdentifier toyCommandIdentifier, Object obj);
    }

    public abstract ToyState getState();

    public ToyTask(ToyPeripheral peripheral) throws NullPointerException {
        if (peripheral == null) {
            throw new NullPointerException();
        }
        this.mPeripheral = peripheral;
        this.mListener = this.mDummyListener;
    }

    public ToyPeripheral getPeripheral() {
        return this.mPeripheral;
    }

    protected Listener getListener() {
        return this.mListener;
    }

    protected void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void start(Listener listener) throws NullPointerException {
        if (listener == null) {
            throw new NullPointerException();
        }
        this.mListener = listener;
        this.mPeripheral.addListener(this);
        this.mListener.onStart(this);
    }

    public void stop() {
        this.mListener = this.mDummyListener;
        this.mPeripheral.removeListener(this);
    }

    public void onConnectionStateChange(ToyPeripheral peripheral, Error error) {
        if (!peripheral.isConnected()) {
            if (error == null) {
                error = new Error(getPeripheral().getContext().getString(R.string.error_toy_disconnected));
            }
            this.mListener.onFailure(this, null, error);
        }
    }

    public void onServicesDiscovered(ToyPeripheral peripheral, Error error) {
    }

    public void onCharacteristicRead(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
    }

    public void onCharacteristicWrite(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
    }

    public void onCharacteristicChanged(ToyPeripheral peripheral, UUID characteristic, byte[] value) {
    }

    public void onCharacteristicSetNotify(ToyPeripheral peripheral, UUID characteristic, Error error) {
    }

    public void onToyStateChange(ToyPeripheral peripheral, State oldState, State newState) {
    }
}
