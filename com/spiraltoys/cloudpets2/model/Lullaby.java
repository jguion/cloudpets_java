package com.spiraltoys.cloudpets2.model;

import java.io.Serializable;

public class Lullaby implements Serializable {
    private int mAudioResourceId;
    private int mImageResourceId;
    private int mNameResourceId;

    public Lullaby(int nameResourceId, int imageResourceId, int audioResourceId) {
        this.mNameResourceId = nameResourceId;
        this.mAudioResourceId = audioResourceId;
        this.mImageResourceId = imageResourceId;
    }

    public int getNameResourceId() {
        return this.mNameResourceId;
    }

    public void setNameResourceId(int nameResourceId) {
        this.mNameResourceId = nameResourceId;
    }

    public int getAudioResourceId() {
        return this.mAudioResourceId;
    }

    public void setAudioResourceId(int audioResourceId) {
        this.mAudioResourceId = audioResourceId;
    }

    public int getImageResourceId() {
        return this.mImageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.mImageResourceId = imageResourceId;
    }
}
