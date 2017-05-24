package com.parse;

import bolts.Task;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ParseAnonymousUtils {
    static final String AUTH_TYPE = "anonymous";

    public static boolean isLinked(ParseUser user) {
        return user.isLinked(AUTH_TYPE);
    }

    public static Task<ParseUser> logInInBackground() {
        return ParseUser.logInWithInBackground(AUTH_TYPE, getAuthData());
    }

    public static void logIn(LogInCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(logInInBackground(), (ParseCallback2) callback);
    }

    static Map<String, String> getAuthData() {
        Map<String, String> authData = new HashMap();
        authData.put("id", UUID.randomUUID().toString());
        return authData;
    }

    private ParseAnonymousUtils() {
    }
}
