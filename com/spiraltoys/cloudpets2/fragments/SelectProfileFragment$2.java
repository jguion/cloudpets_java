package com.spiraltoys.cloudpets2.fragments;

import com.spiraltoys.cloudpets2.model.Profile;
import java.util.Comparator;

class SelectProfileFragment$2 implements Comparator<Profile> {
    SelectProfileFragment$2() {
    }

    public int compare(Profile lhs, Profile rhs) {
        return lhs.getDisplayName().toLowerCase().compareTo(rhs.getDisplayName().toLowerCase());
    }
}
