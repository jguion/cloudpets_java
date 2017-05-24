package com.spiraltoys.cloudpets2;

import android.net.Uri;
import android.os.Bundle;
import icepick.Injector.Helper;
import icepick.Injector.Object;

public class CreateAccountActivity$$Icepick<T extends CreateAccountActivity> extends Object<T> {
    private static final Helper H = new Helper("com.spiraltoys.cloudpets2.CreateAccountActivity$$Icepick.");

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mProfilePhotoUri = (Uri) H.getParcelable(state, "mProfilePhotoUri");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        H.putParcelable(state, "mProfilePhotoUri", target.mProfilePhotoUri);
    }
}
