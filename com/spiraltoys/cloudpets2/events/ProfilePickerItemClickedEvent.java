package com.spiraltoys.cloudpets2.events;

import com.spiraltoys.cloudpets2.model.Profile;

public class ProfilePickerItemClickedEvent {
    private Profile mProfile;

    public ProfilePickerItemClickedEvent(Profile clickedProfile) {
        this.mProfile = clickedProfile;
    }

    public Profile getClickedProfile() {
        return this.mProfile;
    }
}
