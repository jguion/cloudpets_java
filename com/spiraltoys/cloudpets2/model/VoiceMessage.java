package com.spiraltoys.cloudpets2.model;

import android.text.format.DateUtils;
import com.google.android.vending.expansion.downloader.Constants;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.spiraltoys.cloudpets2.model.PrivateProfile.ProfileType;

@ParseClassName("VoiceMessage")
public class VoiceMessage extends ParseObject {
    public static final String CREATION_DATE = "createdAt";
    public static final String FILE = "file";
    public static final String FLAGS = "flags";
    public static final String IS_PARENT_VIEWED = "isParentViewed";
    public static final String LOCAL_FILENAME = "localFilename";
    public static final String RECIPIENT = "recipient";
    public static final String SENDER = "sender";
    public static final String TYPE = "type";
    public static final String USER = "user";
    public static final int VOICE_MESSAGE_FLAG_APPROVED = 2;
    public static final int[] VOICE_MESSAGE_FLAG_APPROVED_MESSAGE_PERMUTATIONS = new int[]{2, 3, 10, 11};
    public static final int VOICE_MESSAGE_FLAG_REJECTED = 4;
    public static final int VOICE_MESSAGE_FLAG_SAVED = 8;
    public static final int[] VOICE_MESSAGE_FLAG_VALID_NEW_MESSAGE_PERMUTATIONS = new int[]{0, 2, 4, 8, 10, 12, 6, 14};
    public static final int VOICE_MESSAGE_FLAG_VIEWED = 1;

    public ParseFile getFile() {
        return getParseFile(FILE);
    }

    public void setFile(ParseFile file) {
        put(FILE, file);
    }

    public int getFlags() {
        return getInt("flags");
    }

    public void setFlags(int flags) {
        put("flags", Integer.valueOf(flags));
    }

    public String getLocalFilename() {
        return getString("localFilename");
    }

    public void setLocalFilename(String localFilename) {
        put("localFilename", localFilename);
    }

    public Profile getRecipient() {
        return (Profile) getParseObject("recipient");
    }

    public void setRecipient(Profile profile) {
        put("recipient", profile);
    }

    public Profile getSender() {
        return (Profile) getParseObject(SENDER);
    }

    public void setSender(Profile sender) {
        put(SENDER, sender);
    }

    public ProfileType getType() {
        return ProfileType.getProfileType(getString(TYPE));
    }

    public void setType(ProfileType type) {
        put(TYPE, type.toString());
    }

    public ParseUser getUser() {
        return getParseUser(USER);
    }

    public void setUser(ParseUser user) {
        put(USER, user);
    }

    public boolean isViewed() {
        return (getFlags() & 1) == 1;
    }

    public void setViewed(boolean isViewed) {
        if (isViewed) {
            setFlags(getFlags() | 1);
        } else {
            setFlags(getFlags() & -2);
        }
    }

    public boolean isParentViewed() {
        return getBoolean(IS_PARENT_VIEWED);
    }

    public void setParentViewed(boolean isParentViewed) {
        put(IS_PARENT_VIEWED, Boolean.valueOf(isParentViewed));
    }

    public boolean isApproved() {
        return (getFlags() & 2) == 2;
    }

    public void setApproved(boolean isApproved) {
        if (isApproved) {
            setFlags(getFlags() | 2);
        } else {
            setFlags(getFlags() & -3);
        }
    }

    public boolean isRejected() {
        return (getFlags() & 4) == 4;
    }

    public void setRejected(boolean isRejected) {
        if (isRejected) {
            setFlags(getFlags() | 4);
        } else {
            setFlags(getFlags() & -5);
        }
    }

    public boolean isSaved() {
        return (getFlags() & 8) == 8;
    }

    public void setSaved(boolean isSaved) {
        if (isSaved) {
            setFlags(getFlags() | 8);
        } else {
            setFlags(getFlags() & -9);
        }
    }

    public boolean isLessThanAMinuteOld() {
        return System.currentTimeMillis() - getCreatedAt().getTime() < Constants.WATCHDOG_WAKE_TIMER;
    }

    public String getRelativeCreationDateString() {
        return DateUtils.getRelativeTimeSpanString(getCreatedAt().getTime(), System.currentTimeMillis(), Constants.WATCHDOG_WAKE_TIMER).toString();
    }
}
