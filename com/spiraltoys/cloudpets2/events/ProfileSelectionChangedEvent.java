package com.spiraltoys.cloudpets2.events;

import com.spiraltoys.cloudpets2.model.Profile;
import java.util.Collection;

public class ProfileSelectionChangedEvent {
    private Collection<Profile> mSelectedProfiles;

    public ProfileSelectionChangedEvent(Collection<Profile> selectedProfiles) {
        this.mSelectedProfiles = selectedProfiles;
    }

    public Collection<Profile> getSelectedProfiles() {
        return this.mSelectedProfiles;
    }
}
