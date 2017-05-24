package com.spiraltoys.cloudpets2.toy.event;

public class ToyEventUpdateProgress {
    private float mPercentage;

    public ToyEventUpdateProgress(float percentage) {
        this.mPercentage = percentage;
    }

    public float getPercentage() {
        return this.mPercentage;
    }
}
