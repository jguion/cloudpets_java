package com.spiraltoys.cloudpets2.model;

import android.content.Context;
import com.spiraltoys.cloudpets2.free.R;

public enum FriendRelationship {
    MOTHER(R.string.relation_mother, "Mother"),
    FATHER(R.string.relation_father, "Father"),
    GRANDMOTHER(R.string.relation_grandmother, "Grandmother"),
    GRANDFATHER(R.string.relation_grandfather, "Grandfather"),
    AUNT(R.string.relation_aunt, "Aunt"),
    UNCLE(R.string.relation_uncle, "Uncle"),
    COUSIN(R.string.relation_cousin, "Cousin"),
    OTHER(R.string.relation_other, "Other");
    
    private String mModelName;
    private int mStringResId;

    private FriendRelationship(int stringResId, String modelName) {
        this.mStringResId = stringResId;
        this.mModelName = modelName;
    }

    public String getLocalizedString(Context c) {
        return c.getString(this.mStringResId);
    }

    public String getModelName() {
        return this.mModelName;
    }

    public static FriendRelationship fromModelName(String modelName) {
        for (FriendRelationship r : values()) {
            if (r.getModelName().equals(modelName)) {
                return r;
            }
        }
        return OTHER;
    }

    public static FriendRelationship fromLocalizedString(Context c, String localizedName) {
        for (FriendRelationship r : values()) {
            if (r.getLocalizedString(c).equals(localizedName)) {
                return r;
            }
        }
        return OTHER;
    }
}
