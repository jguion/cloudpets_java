package com.spiraltoys.cloudpets2.toy;

public enum ToyAudioSlot {
    STREAMING(0),
    UPLOAD_1(1),
    UPLOAD_2(2),
    UPLOAD_3(3),
    UPLOAD_4(4);
    
    private final int mValue;

    private ToyAudioSlot(int value) {
        this.mValue = value;
    }

    public int getValue() {
        return this.mValue;
    }
}
