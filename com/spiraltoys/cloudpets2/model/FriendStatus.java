package com.spiraltoys.cloudpets2.model;

import android.content.Context;
import com.spiraltoys.cloudpets2.free.R;

public enum FriendStatus {
    PENDING(R.string.friend_status_pending, "Pending"),
    ACCEPTED(R.string.friend_status_accepted, "Accepted"),
    REJECTED(R.string.friend_status_rejected, "Rejected");
    
    private String mModelName;
    private int mStringResId;

    private FriendStatus(int stringResId, String modelName) {
        this.mStringResId = stringResId;
        this.mModelName = modelName;
    }

    public String getString(Context c) {
        return c.getString(this.mStringResId);
    }

    public String getModelName() {
        return this.mModelName;
    }

    public static FriendStatus fromModelName(String modelName) {
        for (FriendStatus status : values()) {
            if (status.getModelName().equals(modelName)) {
                return status;
            }
        }
        return PENDING;
    }

    public static FriendStatus fromLocalizedString(Context c, String localizedName) {
        for (FriendStatus status : values()) {
            if (status.getString(c).equals(localizedName)) {
                return status;
            }
        }
        return PENDING;
    }
}
