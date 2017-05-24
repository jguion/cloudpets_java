package com.spiraltoys.cloudpets2.events;

import com.spiraltoys.cloudpets2.model.Profile;

public class ProfileSelectedEvent {
    private Profile mSelectedProfile;

    public ProfileSelectedEvent(Profile selectedProfile) {
        this.mSelectedProfile = selectedProfile;
    }

    public Profile getSelectedProfile() {
        return this.mSelectedProfile;
    }
}
