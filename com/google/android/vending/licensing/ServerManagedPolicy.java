package com.google.android.vending.licensing;

import android.content.Context;
import android.util.Log;
import com.bumptech.glide.load.Key;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class ServerManagedPolicy implements Policy {
    private static final String DEFAULT_MAX_RETRIES = "0";
    private static final String DEFAULT_RETRY_COUNT = "0";
    private static final String DEFAULT_RETRY_UNTIL = "0";
    private static final String DEFAULT_VALIDITY_TIMESTAMP = "0";
    private static final long MILLIS_PER_MINUTE = 60000;
    private static final String PREFS_FILE = "com.android.vending.licensing.ServerManagedPolicy";
    private static final String PREF_LAST_RESPONSE = "lastResponse";
    private static final String PREF_MAX_RETRIES = "maxRetries";
    private static final String PREF_RETRY_COUNT = "retryCount";
    private static final String PREF_RETRY_UNTIL = "retryUntil";
    private static final String PREF_VALIDITY_TIMESTAMP = "validityTimestamp";
    private static final String TAG = "ServerManagedPolicy";
    private int mLastResponse;
    private long mLastResponseTime = 0;
    private long mMaxRetries;
    private PreferenceObfuscator mPreferences;
    private long mRetryCount;
    private long mRetryUntil;
    private long mValidityTimestamp;

    public ServerManagedPolicy(Context context, Obfuscator obfuscator) {
        this.mPreferences = new PreferenceObfuscator(context.getSharedPreferences(PREFS_FILE, 0), obfuscator);
        this.mLastResponse = Integer.parseInt(this.mPreferences.getString(PREF_LAST_RESPONSE, Integer.toString(Policy.RETRY)));
        this.mValidityTimestamp = Long.parseLong(this.mPreferences.getString(PREF_VALIDITY_TIMESTAMP, "0"));
        this.mRetryUntil = Long.parseLong(this.mPreferences.getString(PREF_RETRY_UNTIL, "0"));
        this.mMaxRetries = Long.parseLong(this.mPreferences.getString(PREF_MAX_RETRIES, "0"));
        this.mRetryCount = Long.parseLong(this.mPreferences.getString(PREF_RETRY_COUNT, "0"));
    }

    public void processServerResponse(int response, ResponseData rawData) {
        if (response != Policy.RETRY) {
            setRetryCount(0);
        } else {
            setRetryCount(this.mRetryCount + 1);
        }
        if (response == 256) {
            Map<String, String> extras = decodeExtras(rawData.extra);
            this.mLastResponse = response;
            setValidityTimestamp((String) extras.get("VT"));
            setRetryUntil((String) extras.get("GT"));
            setMaxRetries((String) extras.get("GR"));
        } else if (response == Policy.NOT_LICENSED) {
            setValidityTimestamp("0");
            setRetryUntil("0");
            setMaxRetries("0");
        }
        setLastResponse(response);
        this.mPreferences.commit();
    }

    private void setLastResponse(int l) {
        this.mLastResponseTime = System.currentTimeMillis();
        this.mLastResponse = l;
        this.mPreferences.putString(PREF_LAST_RESPONSE, Integer.toString(l));
    }

    private void setRetryCount(long c) {
        this.mRetryCount = c;
        this.mPreferences.putString(PREF_RETRY_COUNT, Long.toString(c));
    }

    public long getRetryCount() {
        return this.mRetryCount;
    }

    private void setValidityTimestamp(String validityTimestamp) {
        Long lValidityTimestamp;
        try {
            lValidityTimestamp = Long.valueOf(Long.parseLong(validityTimestamp));
        } catch (NumberFormatException e) {
            Log.w(TAG, "License validity timestamp (VT) missing, caching for a minute");
            lValidityTimestamp = Long.valueOf(System.currentTimeMillis() + 60000);
            validityTimestamp = Long.toString(lValidityTimestamp.longValue());
        }
        this.mValidityTimestamp = lValidityTimestamp.longValue();
        this.mPreferences.putString(PREF_VALIDITY_TIMESTAMP, validityTimestamp);
    }

    public long getValidityTimestamp() {
        return this.mValidityTimestamp;
    }

    private void setRetryUntil(String retryUntil) {
        Long lRetryUntil;
        try {
            lRetryUntil = Long.valueOf(Long.parseLong(retryUntil));
        } catch (NumberFormatException e) {
            Log.w(TAG, "License retry timestamp (GT) missing, grace period disabled");
            retryUntil = "0";
            lRetryUntil = Long.valueOf(0);
        }
        this.mRetryUntil = lRetryUntil.longValue();
        this.mPreferences.putString(PREF_RETRY_UNTIL, retryUntil);
    }

    public long getRetryUntil() {
        return this.mRetryUntil;
    }

    private void setMaxRetries(String maxRetries) {
        Long lMaxRetries;
        try {
            lMaxRetries = Long.valueOf(Long.parseLong(maxRetries));
        } catch (NumberFormatException e) {
            Log.w(TAG, "Licence retry count (GR) missing, grace period disabled");
            maxRetries = "0";
            lMaxRetries = Long.valueOf(0);
        }
        this.mMaxRetries = lMaxRetries.longValue();
        this.mPreferences.putString(PREF_MAX_RETRIES, maxRetries);
    }

    public long getMaxRetries() {
        return this.mMaxRetries;
    }

    public boolean allowAccess() {
        long ts = System.currentTimeMillis();
        if (this.mLastResponse == 256) {
            if (ts <= this.mValidityTimestamp) {
                return true;
            }
            return false;
        } else if (this.mLastResponse != Policy.RETRY || ts >= this.mLastResponseTime + 60000) {
            return false;
        } else {
            if (ts <= this.mRetryUntil || this.mRetryCount <= this.mMaxRetries) {
                return true;
            }
            return false;
        }
    }

    private Map<String, String> decodeExtras(String extras) {
        Map<String, String> results = new HashMap();
        try {
            for (NameValuePair item : URLEncodedUtils.parse(new URI("?" + extras), Key.STRING_CHARSET_NAME)) {
                results.put(item.getName(), item.getValue());
            }
        } catch (URISyntaxException e) {
            Log.w(TAG, "Invalid syntax error while decoding extras data from server.");
        }
        return results;
    }
}
