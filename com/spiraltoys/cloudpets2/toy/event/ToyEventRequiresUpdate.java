package com.spiraltoys.cloudpets2.toy.event;

public class ToyEventRequiresUpdate {
    private String mToyIdentifier;

    public ToyEventRequiresUpdate(String toyIdentifier) {
        this.mToyIdentifier = toyIdentifier;
    }

    public String getToyIdentifier() {
        return this.mToyIdentifier;
    }
}
