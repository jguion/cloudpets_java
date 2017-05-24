package com.spiraltoys.cloudpets2.events;

import com.spiraltoys.cloudpets2.model.Profile;

public class ProfileSelectedToSendMessageEvent {
    private Profile mSelectedProfile;

    public ProfileSelectedToSendMessageEvent(Profile selectedProfile) {
        this.mSelectedProfile = selectedProfile;
    }

    public Profile getSelectedProfile() {
        return this.mSelectedProfile;
    }
}
