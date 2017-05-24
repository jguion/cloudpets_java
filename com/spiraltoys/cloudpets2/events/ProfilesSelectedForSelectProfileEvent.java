package com.spiraltoys.cloudpets2.events;

import com.spiraltoys.cloudpets2.model.Profile;
import java.util.List;

public class ProfilesSelectedForSelectProfileEvent {
    List<Profile> mPendingProfiles;
    List<Profile> mSelectedProfiles;

    public ProfilesSelectedForSelectProfileEvent(List<Profile> selectedprofiles, List<Profile> pendingProfiles) {
        this.mSelectedProfiles = selectedprofiles;
        this.mPendingProfiles = pendingProfiles;
    }

    public List<Profile> getSelectedProfiles() {
        return this.mSelectedProfiles;
    }

    public List<Profile> getPendingProfiles() {
        return this.mPendingProfiles;
    }
}
