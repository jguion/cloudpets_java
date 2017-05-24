package com.spiraltoys.cloudpets2.adapters;

import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.FriendStatus;
import java.util.Comparator;

class FriendPermissionsAdapter$3 implements Comparator<FriendRecord> {
    FriendPermissionsAdapter$3() {
    }

    public int compare(FriendRecord lhs, FriendRecord rhs) {
        FriendStatus left = lhs.getStatus();
        FriendStatus right = rhs.getStatus();
        String leftName = lhs.getTargetProfile() == null ? lhs.getName().toLowerCase() : lhs.getTargetProfile().getDisplayName().toLowerCase();
        String rightName = rhs.getTargetProfile() == null ? rhs.getName().toLowerCase() : rhs.getTargetProfile().getDisplayName().toLowerCase();
        if (left.equals(FriendStatus.PENDING)) {
            if (!right.equals(FriendStatus.PENDING)) {
                return -1;
            }
            if (rhs.getCreatedAt().equals(lhs.getCreatedAt())) {
                return leftName.compareTo(rightName);
            }
            return rhs.getCreatedAt().compareTo(lhs.getCreatedAt());
        } else if (right.equals(FriendStatus.PENDING)) {
            return 1;
        } else {
            return leftName.compareTo(rightName);
        }
    }
}
