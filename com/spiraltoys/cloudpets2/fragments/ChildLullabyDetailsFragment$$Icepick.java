package com.spiraltoys.cloudpets2.fragments;

import android.os.Bundle;
import icepick.Injector.Helper;
import icepick.Injector.Object;

public class ChildLullabyDetailsFragment$$Icepick<T extends ChildLullabyDetailsFragment> extends Object<T> {
    private static final Helper H = new Helper("com.spiraltoys.cloudpets2.fragments.ChildLullabyDetailsFragment$$Icepick.");

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mSelectedVolumeIndex = H.getInt(state, "mSelectedVolumeIndex");
            target.mSelectedDurationIndex = H.getInt(state, "mSelectedDurationIndex");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        H.putInt(state, "mSelectedVolumeIndex", target.mSelectedVolumeIndex);
        H.putInt(state, "mSelectedDurationIndex", target.mSelectedDurationIndex);
    }
}
