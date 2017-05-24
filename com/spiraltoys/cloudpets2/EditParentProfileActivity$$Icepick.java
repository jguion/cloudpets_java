package com.spiraltoys.cloudpets2;

import android.net.Uri;
import android.os.Bundle;
import icepick.Injector.Helper;
import icepick.Injector.Object;

public class EditParentProfileActivity$$Icepick<T extends EditParentProfileActivity> extends Object<T> {
    private static final Helper H = new Helper("com.spiraltoys.cloudpets2.EditParentProfileActivity$$Icepick.");

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mProfilePhotoUri = (Uri) H.getParcelable(state, "mProfilePhotoUri");
            target.mDisplayName = H.getString(state, "mDisplayName");
            target.mEmailAddress = H.getString(state, "mEmailAddress");
            target.mIsDataPopulated = H.getBoolean(state, "mIsDataPopulated");
            target.mHasUnsavedChanges = H.getBoolean(state, "mHasUnsavedChanges");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        H.putParcelable(state, "mProfilePhotoUri", target.mProfilePhotoUri);
        H.putString(state, "mDisplayName", target.mDisplayName);
        H.putString(state, "mEmailAddress", target.mEmailAddress);
        H.putBoolean(state, "mIsDataPopulated", target.mIsDataPopulated);
        H.putBoolean(state, "mHasUnsavedChanges", target.mHasUnsavedChanges);
    }
}
