package com.spiraltoys.cloudpets2.fragments;

import android.os.Bundle;
import com.spiraltoys.cloudpets2.model.PlushToy.Character;
import icepick.Injector.Helper;
import icepick.Injector.Object;

public class SelectPetAvatarFragment$$Icepick<T extends SelectPetAvatarFragment> extends Object<T> {
    private static final Helper H = new Helper("com.spiraltoys.cloudpets2.fragments.SelectPetAvatarFragment$$Icepick.");

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mCharacter = (Character) H.getSerializable(state, "mCharacter");
            target.mPetNickname = H.getString(state, "mPetNickname");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        H.putSerializable(state, "mCharacter", target.mCharacter);
        H.putString(state, "mPetNickname", target.mPetNickname);
    }
}
