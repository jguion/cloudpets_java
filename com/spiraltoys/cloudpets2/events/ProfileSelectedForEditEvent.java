package com.spiraltoys.cloudpets2.events;

import com.spiraltoys.cloudpets2.model.Profile;

public class ProfileSelectedForEditEvent {
    private Profile mSelectedProfile;

    public ProfileSelectedForEditEvent(Profile selectedProfile) {
        this.mSelectedProfile = selectedProfile;
    }

    public Profile getSelectedProfile() {
        return this.mSelectedProfile;
    }
}
