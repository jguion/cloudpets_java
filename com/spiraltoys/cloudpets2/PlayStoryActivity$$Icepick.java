package com.spiraltoys.cloudpets2;

import android.os.Bundle;
import icepick.Injector.Helper;
import icepick.Injector.Object;

public class PlayStoryActivity$$Icepick<T extends PlayStoryActivity> extends Object<T> {
    private static final Helper H = new Helper("com.spiraltoys.cloudpets2.PlayStoryActivity$$Icepick.");

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mCurrentPage = H.getInt(state, "mCurrentPage");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        H.putInt(state, "mCurrentPage", target.mCurrentPage);
    }
}
