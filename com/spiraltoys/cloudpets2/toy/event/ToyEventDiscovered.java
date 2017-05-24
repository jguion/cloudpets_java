package com.spiraltoys.cloudpets2.toy.event;

public class ToyEventDiscovered {
    private String mDeviceAddress;

    public ToyEventDiscovered(String deviceAddress) {
        this.mDeviceAddress = deviceAddress;
    }

    public String getDeviceAddress() {
        return this.mDeviceAddress;
    }
}
