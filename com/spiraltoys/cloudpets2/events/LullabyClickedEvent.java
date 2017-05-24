package com.spiraltoys.cloudpets2.events;

import com.spiraltoys.cloudpets2.model.Lullaby;

public class LullabyClickedEvent {
    private Lullaby mLullaby;

    public LullabyClickedEvent(Lullaby lullaby) {
        this.mLullaby = lullaby;
    }

    public Lullaby getLullaby() {
        return this.mLullaby;
    }
}
