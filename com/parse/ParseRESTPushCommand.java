package com.parse;

import com.parse.http.ParseHttpRequest.Method;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ParseRESTPushCommand extends ParseRESTCommand {
    static final String KEY_CHANNELS = "channels";
    static final String KEY_DATA = "data";
    static final String KEY_DEVICE_TYPE = "deviceType";
    static final String KEY_EXPIRATION_INTERVAL = "expiration_interval";
    static final String KEY_EXPIRATION_TIME = "expiration_time";
    static final String KEY_WHERE = "where";

    public ParseRESTPushCommand(String httpPath, Method httpMethod, JSONObject parameters, String sessionToken) {
        super(httpPath, httpMethod, parameters, sessionToken);
    }

    public static ParseRESTPushCommand sendPushCommand(State<ParseInstallation> query, Set<String> targetChannels, String targetDeviceType, Long expirationTime, Long expirationInterval, JSONObject payload, String sessionToken) {
        JSONObject parameters = new JSONObject();
        if (targetChannels != null) {
            try {
                parameters.put(KEY_CHANNELS, new JSONArray(targetChannels));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        JSONObject jSONObject = null;
        if (query != null) {
            jSONObject = (JSONObject) PointerEncoder.get().encode(query.constraints());
        }
        if (targetDeviceType != null) {
            jSONObject = new JSONObject();
            jSONObject.put(KEY_DEVICE_TYPE, targetDeviceType);
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        parameters.put(KEY_WHERE, jSONObject);
        if (expirationTime != null) {
            parameters.put(KEY_EXPIRATION_TIME, expirationTime);
        } else if (expirationInterval != null) {
            parameters.put(KEY_EXPIRATION_INTERVAL, expirationInterval);
        }
        if (payload != null) {
            parameters.put(KEY_DATA, payload);
        }
        return new ParseRESTPushCommand("push", Method.POST, parameters, sessionToken);
    }
}
