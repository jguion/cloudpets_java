package com.spiraltoys.cloudpets2;

import android.net.Uri;
import android.os.Bundle;
import com.spiraltoys.cloudpets2.model.PlushToy.Character;
import icepick.Injector.Helper;
import icepick.Injector.Object;

public class CreateOrEditChildProfileActivity$$Icepick<T extends CreateOrEditChildProfileActivity> extends Object<T> {
    private static final Helper H = new Helper("com.spiraltoys.cloudpets2.CreateOrEditChildProfileActivity$$Icepick.");

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mBirthMonth = H.getInt(state, "mBirthMonth");
            target.mBirthDayOfMonth = H.getInt(state, "mBirthDayOfMonth");
            target.mProfilePhotoUri = (Uri) H.getParcelable(state, "mProfilePhotoUri");
            target.mDisplayName = H.getString(state, "mDisplayName");
            target.mUsername = H.getString(state, "mUsername");
            target.mPetNickname = H.getString(state, "mPetNickname");
            target.mToyIdentifier = H.getString(state, "mToyIdentifier");
            target.mSelectedCharacter = (Character) H.getSerializable(state, "mSelectedCharacter");
            target.mIsDataPopulated = H.getBoolean(state, "mIsDataPopulated");
            target.mHasUnsavedChanges = H.getBoolean(state, "mHasUnsavedChanges");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        H.putInt(state, "mBirthMonth", target.mBirthMonth);
        H.putInt(state, "mBirthDayOfMonth", target.mBirthDayOfMonth);
        H.putParcelable(state, "mProfilePhotoUri", target.mProfilePhotoUri);
        H.putString(state, "mDisplayName", target.mDisplayName);
        H.putString(state, "mUsername", target.mUsername);
        H.putString(state, "mPetNickname", target.mPetNickname);
        H.putString(state, "mToyIdentifier", target.mToyIdentifier);
        H.putSerializable(state, "mSelectedCharacter", target.mSelectedCharacter);
        H.putBoolean(state, "mIsDataPopulated", target.mIsDataPopulated);
        H.putBoolean(state, "mHasUnsavedChanges", target.mHasUnsavedChanges);
    }
}
