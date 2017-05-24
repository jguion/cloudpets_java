package com.spiraltoys.cloudpets2;

import android.net.Uri;
import android.os.Bundle;
import icepick.Injector.Helper;
import icepick.Injector.Object;

public class ChildDashboardActivity$$Icepick<T extends ChildDashboardActivity> extends Object<T> {
    private static final Helper H = new Helper("com.spiraltoys.cloudpets2.ChildDashboardActivity$$Icepick.");

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mTempMessageUri = (Uri) H.getParcelable(state, "mTempMessageUri");
            target.mLullabyAudioUri = (Uri) H.getParcelable(state, "mLullabyAudioUri");
            target.mIsSendingLullabyToToy = H.getBoolean(state, "mIsSendingLullabyToToy");
            target.mLullabyPlaybackDuration = H.getLong(state, "mLullabyPlaybackDuration");
            target.mLullabyVolume = H.getDouble(state, "mLullabyVolume");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        H.putParcelable(state, "mTempMessageUri", target.mTempMessageUri);
        H.putParcelable(state, "mLullabyAudioUri", target.mLullabyAudioUri);
        H.putBoolean(state, "mIsSendingLullabyToToy", target.mIsSendingLullabyToToy);
        H.putLong(state, "mLullabyPlaybackDuration", target.mLullabyPlaybackDuration);
        H.putDouble(state, "mLullabyVolume", target.mLullabyVolume);
    }
}
