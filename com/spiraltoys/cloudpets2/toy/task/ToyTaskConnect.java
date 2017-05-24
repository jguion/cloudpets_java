package com.spiraltoys.cloudpets2.toy.task;

import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.task.ToyTask.Listener;
import java.util.UUID;

public class ToyTaskConnect extends ToyTask {
    public ToyTaskConnect(ToyPeripheral peripheral) throws NullPointerException {
        super(peripheral);
    }

    public ToyState getState() {
        return ToyState.CONNECTING;
    }

    public void start(Listener listener) {
        super.start(listener);
        if (getPeripheral().isConnected()) {
            getPeripheral().discoverServices();
        } else {
            getPeripheral().connect();
        }
    }

    public void onConnectionStateChange(ToyPeripheral peripheral, Error error) {
        if (error != null) {
            getListener().onFailure(this, null, error);
        } else if (peripheral.isConnected()) {
            peripheral.discoverServices();
        } else {
            getListener().onFailure(this, null, new Error(getPeripheral().getContext().getString(R.string.error_toy_gatt)));
        }
    }

    public void onServicesDiscovered(ToyPeripheral peripheral, Error error) {
        super.onServicesDiscovered(peripheral, error);
        if (error != null) {
            getListener().onFailure(this, null, error);
        } else if (peripheral.isConnected()) {
            peripheral.readCharacteristic(ToyPeripheral.CONFIG_CHAR_UUID);
        }
    }

    public void onCharacteristicRead(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
        super.onCharacteristicRead(peripheral, characteristic, value, error);
        if (error != null) {
            getListener().onFailure(this, null, error);
        } else {
            peripheral.setCharacteristicNotification(ToyPeripheral.STATE_CHAR_UUID, true);
        }
    }

    public void onCharacteristicSetNotify(ToyPeripheral peripheral, UUID characteristic, Error error) {
        super.onCharacteristicSetNotify(peripheral, characteristic, error);
        if (error != null) {
            getListener().onFailure(this, null, error);
        } else if (ToyPeripheral.STATE_CHAR_UUID.equals(characteristic)) {
            peripheral.setCharacteristicNotification(ToyPeripheral.RECEIVE_AUDIO_CHAR_UUID, true);
        } else if (ToyPeripheral.RECEIVE_AUDIO_CHAR_UUID.equals(characteristic)) {
            peripheral.setCharacteristicNotification(ToyPeripheral.DATA_REQUEST_CHAR_UUID, true);
        } else if (ToyPeripheral.DATA_REQUEST_CHAR_UUID.equals(characteristic)) {
            getListener().onSuccess(this, null, peripheral.getIdentifier());
        }
    }
}
