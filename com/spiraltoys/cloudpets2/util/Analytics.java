package com.spiraltoys.cloudpets2.util;

import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Analytics {
    private Analytics() {
    }

    public static void logLocalDatastoreException(ParseException e, String pinName) {
        logLocalDatastoreException(e, pinName, "");
    }

    public static void logLocalDatastoreException(ParseException e, String pinName, String extra) {
        Map<String, String> dimensions = new HashMap();
        dimensions.put("CODE", Integer.toString(e.getCode()));
        dimensions.put("MESSAGE", e.getMessage());
        dimensions.put("STACK_TRACE", Log.getStackTraceString(new Throwable()));
        dimensions.put("PIN_NAME", pinName);
        dimensions.put("BRAND", Build.BRAND);
        dimensions.put("DEVICE", Build.DEVICE);
        dimensions.put("MANUFACTURER", Build.MANUFACTURER);
        dimensions.put("PRODUCT", Build.PRODUCT);
        dimensions.put("MODEL", Build.MODEL);
        dimensions.put("RADIO", Build.getRadioVersion());
        dimensions.put("RELEASE", VERSION.RELEASE);
        dimensions.put("EXTRA", extra);
        ParseAnalytics.trackEventInBackground("parse_local_datastore_error", dimensions);
    }

    public static void logLocalDatastoreRecoveryException(ParseException e) {
        logLocalDatastoreRecoveryException(e, "");
    }

    public static void logLocalDatastoreRecoveryException(ParseException e, String extra) {
        Map<String, String> dimensions = new HashMap();
        dimensions.put("CODE", Integer.toString(e.getCode()));
        dimensions.put("MESSAGE", e.getMessage());
        dimensions.put("STACK_TRACE", Log.getStackTraceString(new Throwable()));
        dimensions.put("BRAND", Build.BRAND);
        dimensions.put("DEVICE", Build.DEVICE);
        dimensions.put("MANUFACTURER", Build.MANUFACTURER);
        dimensions.put("PRODUCT", Build.PRODUCT);
        dimensions.put("MODEL", Build.MODEL);
        dimensions.put("RADIO", Build.getRadioVersion());
        dimensions.put("RELEASE", VERSION.RELEASE);
        dimensions.put("EXTRA", extra);
        ParseAnalytics.trackEventInBackground("parse_local_datastore_recovery_error", dimensions);
    }
}
