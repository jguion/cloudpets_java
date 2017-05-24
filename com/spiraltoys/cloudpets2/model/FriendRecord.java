package com.spiraltoys.cloudpets2.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("FriendRecord")
public class FriendRecord extends ParseObject {
    public static final String NAME = "name";
    public static final String RECIPIENT = "recipient";
    public static final String RELATIONSHIP = "relationship";
    public static final String SOURCE_PROFILE = "sourceProfile";
    public static final String STATUS = "status";
    public static final String TARGET_EMAIL_ADDRESS = "targetEmailAddress";
    public static final String TARGET_PROFILE = "targetProfile";

    public String getName() {
        return getString(NAME);
    }

    public void setName(String name) {
        put(NAME, name);
    }

    public FriendRelationship getRelationship() {
        return FriendRelationship.fromModelName(getString(RELATIONSHIP));
    }

    public void setRelationship(FriendRelationship relationship) {
        put(RELATIONSHIP, relationship.getModelName());
    }

    public Profile getSourceProfile() {
        return (Profile) getParseObject(SOURCE_PROFILE);
    }

    public void setSourceProfile(Profile sourceProfile) {
        put(SOURCE_PROFILE, sourceProfile);
    }

    public FriendStatus getStatus() {
        return FriendStatus.fromModelName(getString("status"));
    }

    public void setStatus(FriendStatus status) {
        put("status", status.getModelName());
    }

    public String getTargetEmailAddress() {
        return getString(TARGET_EMAIL_ADDRESS);
    }

    public void setTargetEmailAddress(String targetEmailAddress) {
        put(TARGET_EMAIL_ADDRESS, targetEmailAddress);
    }

    public Profile getTargetProfile() {
        return (Profile) getParseObject(TARGET_PROFILE);
    }

    public void setTargetProfile(Profile targetProfile) {
        put(TARGET_PROFILE, targetProfile);
    }

    public boolean isRecipient() {
        return getBoolean("recipient");
    }
}
