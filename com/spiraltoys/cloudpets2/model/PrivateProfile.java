package com.spiraltoys.cloudpets2.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("PrivateProfile")
public class PrivateProfile extends ParseObject {
    public static final String BIRTH_DAY_OF_MONTH = "birthDayOfMonth";
    public static final String BIRTH_MONTH = "birthMonth";
    public static final String LAST_TOY_ID = "lastToyId";
    public static final String MESSAGE_FILTERED = "messageFiltered";
    public static final String PROFILE_TYPE = "profileType";

    public enum ProfileType {
        ADULT("Adult"),
        CHILD("Child");
        
        private String mValue;

        private ProfileType(String type) {
            this.mValue = type;
        }

        public String toString() {
            return this.mValue;
        }

        public static ProfileType getProfileType(String stringValue) {
            for (ProfileType profileType : values()) {
                if (profileType.mValue.equals(stringValue)) {
                    return profileType;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public String getLastToyId() {
        return getString(LAST_TOY_ID);
    }

    public void setLastToyId(String lastToyId) {
        put(LAST_TOY_ID, lastToyId);
    }

    public boolean isMessageFiltered() {
        return getBoolean(MESSAGE_FILTERED);
    }

    public void setMessageFiltered(boolean messageFiltered) {
        put(MESSAGE_FILTERED, Boolean.valueOf(messageFiltered));
    }

    public ProfileType getProfileType() {
        return ProfileType.getProfileType(getString(PROFILE_TYPE));
    }

    public void setProfileType(ProfileType type) {
        put(PROFILE_TYPE, type.toString());
    }

    public int getBirthMonth() {
        return getInt(BIRTH_MONTH);
    }

    public void setBirthMonth(int birthMonth) {
        put(BIRTH_MONTH, Integer.valueOf(birthMonth));
    }

    public int getBirthDayOfMonth() {
        return getInt(BIRTH_DAY_OF_MONTH);
    }

    public void setBirthDayOfMonth(int birthDayOfMonth) {
        put(BIRTH_DAY_OF_MONTH, Integer.valueOf(birthDayOfMonth));
    }

    public boolean isAdult() {
        return getProfileType() == ProfileType.ADULT;
    }
}
