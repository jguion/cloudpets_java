package com.spiraltoys.cloudpets2.util;

import android.content.Context;
import com.spiraltoys.cloudpets2.model.Profile;
import java.util.ArrayList;
import java.util.Comparator;

public class LastAccessedProfileComparator implements Comparator<Profile> {
    private ArrayList<String> mLastAccessedProfileIds;

    public LastAccessedProfileComparator(Context context) {
        this.mLastAccessedProfileIds = Profile.getLastAccessedProfileIds(context);
    }

    public int compare(Profile lhs, Profile rhs) {
        if (lhs.getPrivateProfile().isAdult() == rhs.getPrivateProfile().isAdult()) {
            int lhsIndex = this.mLastAccessedProfileIds.indexOf(lhs.getObjectId());
            int rhsIndex = this.mLastAccessedProfileIds.indexOf(rhs.getObjectId());
            int indexCompare = (rhsIndex == -1 || lhsIndex == -1) ? Integer.compare(rhsIndex, lhsIndex) : Integer.compare(lhsIndex, rhsIndex);
            if (indexCompare == 0) {
                indexCompare = lhs.getCreatedAt().compareTo(rhs.getCreatedAt());
            }
            return indexCompare;
        } else if (lhs.getPrivateProfile().isAdult()) {
            return -1;
        } else {
            return 1;
        }
    }
}
