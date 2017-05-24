package com.spiraltoys.cloudpets2.model.util;

import android.content.Context;
import android.net.Uri;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import com.spiraltoys.cloudpets2.util.SettingsManager;
import org.mindrot.jbcrypt.BCrypt;

public class AccountHelper {
    public static final String EMAIL_VERIFIED = "emailVerified";
    public static final String USERNAME = "username";

    public static class SignupCredentials {
        private String displayName;
        private String email;
        private String password;
        private String passwordRepeat;
        private Uri profilePhotoUri;

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPasswordRepeat() {
            return this.passwordRepeat;
        }

        public void setPasswordRepeat(String passwordRepeat) {
            this.passwordRepeat = passwordRepeat;
        }

        public String getDisplayName() {
            return this.displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public Uri getProfilePhotoUri() {
            return this.profilePhotoUri;
        }

        public void setProfilePhotoUri(Uri profilePhotoUri) {
            this.profilePhotoUri = profilePhotoUri;
        }
    }

    private AccountHelper() {
    }

    public static void signUp(Context c, SignupCredentials credentials, SaveCallback callback) {
        if (isLoggedIn()) {
            callback.done(new ParseException(ParseException.OPERATION_FORBIDDEN, "Already logged in. Log out before attempting to create a new account."));
        } else {
            ParseAnonymousUtils.logIn(new 1(callback, credentials, c));
        }
    }

    private static void saveProfile(Context c, String email, String password, Profile profile, SaveCallback callback) {
        profile.saveInBackground(new 2(callback, email, password, c));
    }

    public static void logIn(Context c, String username, String password, LogInCallback callback) {
        ParseUser.logInInBackground(username, password, new 3(c, username, password, callback));
    }

    public static boolean isLoggedIn() {
        return ParseUser.getCurrentUser() != null;
    }

    public static void logOut(Context c) {
        if (isLoggedIn()) {
            ParseInstallation installation = ParseInstallation.getCurrentInstallation();
            installation.remove(VoiceMessage.USER);
            installation.saveEventually();
            ParseUser.logOut();
        }
        uncacheCredentials(c);
        ParseObject.unpinAllInBackground();
    }

    public static void checkPassword(Context c, String password, PasswordCheckCallback callback) {
        new 4(SettingsManager.getPassword(c), c, callback).execute(new String[]{password});
    }

    public static String getCachedUsername(Context c) {
        return SettingsManager.getUsername(c);
    }

    private static void cacheCredentials(Context c, String username, String password) {
        SettingsManager.setCredentials(c, username.toLowerCase(), BCrypt.hashpw(password, BCrypt.gensalt()));
    }

    private static void uncacheCredentials(Context c) {
        SettingsManager.setCredentials(c, null, null);
    }

    public static void refreshUser(GetCallback<ParseUser> callback) {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            callback.done(null, null);
        } else {
            currentUser.fetchInBackground(callback);
        }
    }

    public static String getUsername(ParseUser user) {
        return user.getString("username");
    }

    public static void setUsername(ParseUser user, String username) {
        user.put("username", username);
    }

    public static boolean isEmailVerified(ParseUser user) {
        return user.getBoolean(EMAIL_VERIFIED);
    }

    public static String getEmail(ParseUser user) {
        return user.getEmail();
    }
}
