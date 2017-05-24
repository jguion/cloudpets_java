package com.parse;

import android.net.Uri;
import com.parse.http.ParseHttpRequest.Method;
import java.util.Date;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class ParseRESTAnalyticsCommand extends ParseRESTCommand {
    static final String EVENT_APP_OPENED = "AppOpened";
    private static final String KEY_AT = "at";
    private static final String KEY_DIMENSIONS = "dimensions";
    private static final String KEY_PUSH_HASH = "push_hash";
    private static final String PATH = "events/%s";

    public static ParseRESTAnalyticsCommand trackAppOpenedCommand(String pushHash, String sessionToken) {
        return trackEventCommand(EVENT_APP_OPENED, pushHash, null, sessionToken);
    }

    public static ParseRESTAnalyticsCommand trackEventCommand(String eventName, Map<String, String> dimensions, String sessionToken) {
        return trackEventCommand(eventName, null, dimensions, sessionToken);
    }

    static ParseRESTAnalyticsCommand trackEventCommand(String eventName, String pushHash, Map<String, String> dimensions, String sessionToken) {
        String httpPath = String.format(PATH, new Object[]{Uri.encode(eventName)});
        JSONObject parameters = new JSONObject();
        try {
            parameters.put(KEY_AT, NoObjectsEncoder.get().encode(new Date()));
            if (pushHash != null) {
                parameters.put(KEY_PUSH_HASH, pushHash);
            }
            if (dimensions != null) {
                parameters.put(KEY_DIMENSIONS, NoObjectsEncoder.get().encode(dimensions));
            }
            return new ParseRESTAnalyticsCommand(httpPath, Method.POST, parameters, sessionToken);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public ParseRESTAnalyticsCommand(String httpPath, Method httpMethod, JSONObject parameters, String sessionToken) {
        super(httpPath, httpMethod, parameters, sessionToken);
    }
}
