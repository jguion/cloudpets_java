package com.spiraltoys.cloudpets2.model;

import android.content.Context;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;
import hugo.weaving.DebugLog;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

@ParseClassName("Profile")
public class Profile extends ParseObject {
    public static final String CREATED_AT = "createdAt";
    public static final String DISPLAY_NAME = "displayName";
    public static final String LOCALLY_LAST_ACCESSED_AT = "_lastAccessedAt";
    public static final String LOCAL_ACCESS_STACK = "localProfileAccessStack";
    public static final int MAX_NAME_LENGTH = 32;
    public static final int MAX_USERNAME_LENGTH = 32;
    public static final int MIN_NAME_LENGTH = 1;
    public static final int MIN_USERNAME_LENGTH = 1;
    public static final String OBJECT_ID = "objectId";
    public static final String OWNER = "owner";
    public static final String PLUSH_TOY = "plushToy";
    public static final String PORTRAIT = "portrait";
    public static final String PRIVATE_PROFILE = "privateProfile";
    public static final String USERNAME = "username";

    public String getDisplayName() {
        return getString(DISPLAY_NAME);
    }

    public void setDisplayName(String displayName) {
        put(DISPLAY_NAME, displayName);
    }

    public String getUsername() {
        return getString("username");
    }

    public void setUsername(String username) {
        put("username", username);
    }

    public ProfilePortrait getPortrait() {
        return (ProfilePortrait) getParseObject("portrait");
    }

    public void setPortrait(ProfilePortrait portrait) {
        put("portrait", portrait);
    }

    public PrivateProfile getPrivateProfile() {
        return (PrivateProfile) getParseObject(PRIVATE_PROFILE);
    }

    public void setPrivateProfile(PrivateProfile profile) {
        put(PRIVATE_PROFILE, profile);
    }

    public PlushToy getPlushToy() {
        return (PlushToy) getParseObject(PLUSH_TOY);
    }

    public void setPlushToy(PlushToy plushToy) {
        put(PLUSH_TOY, plushToy);
    }

    public ParseUser getOwner() {
        return getParseUser(OWNER);
    }

    public void setOwner(ParseUser owner) {
        put(OWNER, owner);
    }

    @DebugLog
    public Date getLocallyLastAccessedAt(Context context) {
        return new Date(PreferenceManager.getDefaultSharedPreferences(context).getLong(getObjectId() + LOCALLY_LAST_ACCESSED_AT, 0));
    }

    @DebugLog
    private void setLocallyLastAccessedAt(Context context, Date accessedAt) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(getObjectId() + LOCALLY_LAST_ACCESSED_AT, accessedAt.getTime());
    }

    @DebugLog
    public void setJustAccessed(Context context) {
        Gson gson = new Gson();
        Object lastAccessedProfileIds = getLastAccessedProfileIds(context);
        lastAccessedProfileIds.remove(getObjectId());
        lastAccessedProfileIds.add(0, getObjectId());
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(LOCAL_ACCESS_STACK, gson.toJson(lastAccessedProfileIds)).apply();
        setLocallyLastAccessedAt(context, new Date());
    }

    @DebugLog
    public static ArrayList<String> getLastAccessedProfileIds(Context context) {
        Gson gson = new Gson();
        Type listType = new 1().getType();
        String serializedLocalAccessStack = PreferenceManager.getDefaultSharedPreferences(context).getString(LOCAL_ACCESS_STACK, null);
        if (serializedLocalAccessStack == null) {
            return new ArrayList();
        }
        return (ArrayList) gson.fromJson(serializedLocalAccessStack, listType);
    }
}
