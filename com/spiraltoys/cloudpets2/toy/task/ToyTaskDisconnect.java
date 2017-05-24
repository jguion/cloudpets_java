package com.spiraltoys.cloudpets2.toy.task;

import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.task.ToyTask.Listener;

public class ToyTaskDisconnect extends ToyTask {
    public ToyTaskDisconnect(ToyPeripheral peripheral) throws NullPointerException {
        super(peripheral);
    }

    public ToyState getState() {
        return ToyState.DISCONNECTING;
    }

    public void start(Listener listener) {
        super.start(listener);
        if (getPeripheral().isConnected()) {
            getPeripheral().disconnect();
        } else {
            getListener().onSuccess(this, null, Boolean.valueOf(false));
        }
    }

    public void onConnectionStateChange(ToyPeripheral peripheral, Error error) {
        if (error != null) {
            getListener().onFailure(this, null, error);
        } else if (!peripheral.isConnected()) {
            getListener().onSuccess(this, null, Boolean.valueOf(false));
        }
    }
}
