package com.spiraltoys.cloudpets2.views;

import android.os.Bundle;
import android.os.Parcelable;
import icepick.Injector.Helper;
import icepick.Injector.View;

public class PetVideoSurfaceView$$Icepick<T extends PetVideoSurfaceView> extends View<T> {
    private static final Helper H = new Helper("com.spiraltoys.cloudpets2.views.PetVideoSurfaceView$$Icepick.");

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.mCurrentlyPlayingAnimation = H.getString(state, "mCurrentlyPlayingAnimation");
        target.mCurrentlyQueuedAnimation = H.getString(state, "mCurrentlyQueuedAnimation");
        target.mSavedPosition = H.getInt(state, "mSavedPosition");
        target.mVolume = H.getFloat(state, "mVolume");
        return super.restore(target, H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = H.putParent(super.save(target, p));
        H.putString(state, "mCurrentlyPlayingAnimation", target.mCurrentlyPlayingAnimation);
        H.putString(state, "mCurrentlyQueuedAnimation", target.mCurrentlyQueuedAnimation);
        H.putInt(state, "mSavedPosition", target.mSavedPosition);
        H.putFloat(state, "mVolume", target.mVolume);
        return state;
    }
}
