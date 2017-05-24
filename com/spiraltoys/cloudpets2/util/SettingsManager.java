package com.spiraltoys.cloudpets2.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SettingsManager {
    public static final String KEY_ENABLE_ADULT_DASHBOARD_BACKGROUND_MUSIC = "pref_adult_dashboard_background_music_enable";
    public static final String KEY_ENABLE_CHILD_DASHBOARD_BACKGROUND_MUSIC = "pref_child_dashboard_background_music_enable";
    public static final String KEY_ENABLE_CHILD_DASHBOARD_SOUNDS = "pref_child_dashboard_sounds_enable";
    public static final String KEY_INITIAL_ADULT_HELP_COMPLETE = "key-initial-adult_help-complete";
    public static final String KEY_INITIAL_ADULT_TOOLTIP_COMPLETE = "key-initial-adult-tooltip-complete";
    public static final String KEY_INITIAL_CHILD_HELP_COMPLETE = "key-initial-child_help-complete";
    public static final String KEY_INITIAL_TOY_SETUP_COMPLETE = "key-initial-toy-setup-complete";
    public static final String KEY_LAST_USED_PROFILE_ID = "key-last-used-profile-id";
    public static final String KEY_PARENTAL_CONTROL = "key-parental-control";
    public static final String KEY_PASSWORD = "key-password";
    public static final String KEY_USERNAME = "key-username";
    public static final String KEY_USE_ADMIN_PASSWORD = "key-use-admin-password";
    public static final String KEY_USE_CHILD_PINS = "key-use-child-pins";
    public static final String PREFS_NAME = "cloud-pets-2-shared-prefs";

    private SettingsManager() {
    }

    public static boolean isParentalControlEnabled(Context c) {
        return getBoolean(c, getUsername(c) + KEY_PARENTAL_CONTROL, false);
    }

    public static void setParentalControl(Context c, boolean value) {
        setBoolean(c, getUsername(c) + KEY_PARENTAL_CONTROL, value);
    }

    public static boolean isUseAdminPasswordEnabled(Context c) {
        return getBoolean(c, getUsername(c) + KEY_USE_ADMIN_PASSWORD, false);
    }

    public static void setUseAdminPassword(Context c, boolean value) {
        setBoolean(c, getUsername(c) + KEY_USE_ADMIN_PASSWORD, value);
    }

    public static String getLastUsedProfileId(Context c) {
        return getString(c, getUsername(c) + KEY_LAST_USED_PROFILE_ID, null);
    }

    public static void setLastUsedProfileId(Context c, String value) {
        setString(c, getUsername(c) + KEY_LAST_USED_PROFILE_ID, value);
    }

    public static String getUsername(Context c) {
        return getString(c, KEY_USERNAME, null);
    }

    public static String getPassword(Context c) {
        return getString(c, KEY_PASSWORD, null);
    }

    public static boolean isInitialToySetupComplete(Context c) {
        return getBoolean(c, getUsername(c) + KEY_INITIAL_TOY_SETUP_COMPLETE, false);
    }

    public static void setInitialToySetupComplete(Context c, boolean isComplete) {
        setBoolean(c, getUsername(c) + KEY_INITIAL_TOY_SETUP_COMPLETE, isComplete);
    }

    public static boolean isInitialAdultHelpComplete(Context c) {
        return getBoolean(c, getUsername(c) + KEY_INITIAL_ADULT_HELP_COMPLETE, false);
    }

    public static void setInitialAdultHelpComplete(Context c, boolean isComplete) {
        setBoolean(c, getUsername(c) + KEY_INITIAL_ADULT_HELP_COMPLETE, isComplete);
    }

    public static boolean isInitialChildHelpComplete(Context c, String childUsername) {
        return getBoolean(c, getUsername(c) + childUsername + KEY_INITIAL_CHILD_HELP_COMPLETE, false);
    }

    public static void setInitialChildHelpComplete(Context c, String childUsername, boolean isComplete) {
        setBoolean(c, getUsername(c) + childUsername + KEY_INITIAL_CHILD_HELP_COMPLETE, isComplete);
    }

    public static boolean isInitialAdultTooltipComplete(Context c) {
        return getBoolean(c, getUsername(c) + KEY_INITIAL_ADULT_TOOLTIP_COMPLETE, false);
    }

    public static void setInitialAdultTooltipComplete(Context c, boolean isComplete) {
        setBoolean(c, getUsername(c) + KEY_INITIAL_ADULT_TOOLTIP_COMPLETE, isComplete);
    }

    public static boolean isAdultDashboardMusicEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_ENABLE_ADULT_DASHBOARD_BACKGROUND_MUSIC, true);
    }

    public static boolean isChildDashboardMusicEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_ENABLE_CHILD_DASHBOARD_BACKGROUND_MUSIC, true);
    }

    public static boolean isChildDashboardSoundEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_ENABLE_CHILD_DASHBOARD_SOUNDS, true);
    }

    public static void setCredentials(Context c, String username, String password) {
        setString(c, KEY_USERNAME, username);
        setString(c, KEY_PASSWORD, password);
    }

    private static void setBoolean(Context c, String key, boolean value) {
        Editor edit = c.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    private static void setString(Context c, String key, String value) {
        Editor edit = c.getSharedPreferences(PREFS_NAME, 0).edit();
        if (value != null) {
            edit.putString(key, value);
        } else {
            edit.remove(key);
        }
        edit.apply();
    }

    private static boolean getBoolean(Context c, String key, boolean defaultValue) {
        return c.getSharedPreferences(PREFS_NAME, 0).getBoolean(key, defaultValue);
    }

    private static String getString(Context c, String key, String defaultValue) {
        return c.getSharedPreferences(PREFS_NAME, 0).getString(key, defaultValue);
    }
}
