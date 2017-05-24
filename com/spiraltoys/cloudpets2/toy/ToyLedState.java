package com.spiraltoys.cloudpets2.toy;

public enum ToyLedState {
    OFF(0),
    ON(1),
    BLINKING(2);
    
    private final int mValue;

    private ToyLedState(int value) {
        this.mValue = value;
    }

    public int getValue() {
        return this.mValue;
    }
}
