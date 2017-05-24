package com.spiraltoys.cloudpets2.toy.event;

import com.spiraltoys.cloudpets2.toy.ToyState;

public class ToyEventState {
    private String mDeviceAddress;
    private ToyState mState;
    private String mToyIdentifier;

    public ToyEventState(String toyIdentifier, String deviceAddress, ToyState state) {
        this.mToyIdentifier = toyIdentifier;
        this.mDeviceAddress = deviceAddress;
        this.mState = state;
    }

    public ToyState getState() {
        return this.mState;
    }

    public String getDeviceAddress() {
        return this.mDeviceAddress;
    }

    public String getToyIdentifier() {
        return this.mToyIdentifier;
    }
}
