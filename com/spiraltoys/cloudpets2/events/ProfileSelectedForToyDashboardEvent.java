package com.spiraltoys.cloudpets2.events;

import com.spiraltoys.cloudpets2.model.Profile;

public class ProfileSelectedForToyDashboardEvent {
    private Profile mSelectedProfile;

    public ProfileSelectedForToyDashboardEvent(Profile selectedProfile) {
        this.mSelectedProfile = selectedProfile;
    }

    public Profile getSelectedProfile() {
        return this.mSelectedProfile;
    }
}
