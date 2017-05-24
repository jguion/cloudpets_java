package com.spiraltoys.cloudpets2.fragments;

import android.os.Bundle;
import icepick.Injector.Helper;
import icepick.Injector.Object;

public class ChildDashboardDialogFragment$$Icepick<T extends ChildDashboardDialogFragment> extends Object<T> {
    private static final Helper H = new Helper("com.spiraltoys.cloudpets2.fragments.ChildDashboardDialogFragment$$Icepick.");

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mTitleStack = H.getStringArrayList(state, "mTitleStack");
            target.mTitleIconStack = H.getIntegerArrayList(state, "mTitleIconStack");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        H.putStringArrayList(state, "mTitleStack", target.mTitleStack);
        H.putIntegerArrayList(state, "mTitleIconStack", target.mTitleIconStack);
    }
}
