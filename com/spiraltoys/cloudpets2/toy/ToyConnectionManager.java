package com.spiraltoys.cloudpets2.toy;

import com.spiraltoys.cloudpets2.toy.event.ToyEventState;
import de.greenrobot.event.EventBus;

public class ToyConnectionManager {
    private ToyState mLastToyState = ToyState.UNKNOWN;
    private boolean mShouldAutoConnect;
    private String mToyConfigId;

    public ToyConnectionManager(String toyConfigId) {
        this.mToyConfigId = toyConfigId;
    }

    public void onPause(boolean stayConnected) {
        EventBus.getDefault().unregister(this);
        if (!stayConnected) {
            this.mShouldAutoConnect = false;
            ToyManager.disconnectFromToyEventually();
        }
    }

    public void onResume() {
        EventBus.getDefault().registerSticky(this);
        this.mShouldAutoConnect = true;
        updateToyConnection();
    }

    public void onEvent(ToyEventState event) {
        if (event.getState() == ToyState.READY) {
            updateToyConnection();
        }
        this.mLastToyState = event.getState();
    }

    public ToyState getLastToyState() {
        return this.mLastToyState;
    }

    private void updateToyConnection() {
        if (this.mToyConfigId != null && this.mShouldAutoConnect) {
            ToyManager.connectToToy(this.mToyConfigId, null, true);
        } else if (this.mToyConfigId == null) {
            ToyManager.disconnectFromToy();
        }
    }
}
