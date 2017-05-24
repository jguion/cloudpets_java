package com.spiraltoys.cloudpets2.events;

public class NetworkStateChangedEvent {
    private boolean mIsConnected;

    public NetworkStateChangedEvent(boolean isConnected) {
        this.mIsConnected = isConnected;
    }

    public boolean isConnected() {
        return this.mIsConnected;
    }
}
