package com.spiraltoys.cloudpets2.fragments;

import android.net.Uri;
import android.os.Bundle;
import icepick.Injector.Helper;
import icepick.Injector.Object;

public class CreateOrEditChildProfileFragment$$Icepick<T extends CreateOrEditChildProfileFragment> extends Object<T> {
    private static final Helper H = new Helper("com.spiraltoys.cloudpets2.fragments.CreateOrEditChildProfileFragment$$Icepick.");

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mProfilePhotoUri = (Uri) H.getParcelable(state, "mProfilePhotoUri");
            target.mIsEditMode = H.getBoolean(state, "mIsEditMode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        H.putParcelable(state, "mProfilePhotoUri", target.mProfilePhotoUri);
        H.putBoolean(state, "mIsEditMode", target.mIsEditMode);
    }
}
