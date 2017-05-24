package com.spiraltoys.cloudpets2.util;

import android.content.Context;
import android.net.Uri;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request.Builder;
import hugo.weaving.DebugLog;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CacheUtil {
    private static final int CONNECTION_TIMEOUT_SECONDS = 30;
    private static final int MAX_STALE_CACHE_DAYS = 365;
    private static final int READ_TIMEOUT_SECONDS = 30;
    private static final long SIZE_OF_CACHE_BYTES = 52428800;

    public interface Callback {
        void done(Uri uri, Exception exception);
    }

    public static void fetchToTemporaryFileAndCache(Context context, Uri remoteUri, Callback callback) {
        getClient(context).newCall(new Builder().url(remoteUri.toString()).cacheControl(new CacheControl.Builder().maxStale(MAX_STALE_CACHE_DAYS, TimeUnit.DAYS).build()).build()).enqueue(new 1(callback, context));
    }

    @DebugLog
    public static boolean isAvailableInOfflineCache(Context context, Uri remoteUri) {
        boolean z = false;
        if (!remoteUri.isRelative()) {
            try {
                z = getClient(context).newCall(new Builder().url(remoteUri.toString()).cacheControl(CacheControl.FORCE_CACHE).build()).execute().isSuccessful();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    private static OkHttpClient getClient(Context context) {
        OkHttpClient client = new OkHttpClient();
        try {
            client.setCache(new Cache(context.getCacheDir(), SIZE_OF_CACHE_BYTES));
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.setReadTimeout(30, TimeUnit.SECONDS);
        client.setConnectTimeout(30, TimeUnit.SECONDS);
        return client;
    }

    private static File getTempFile(Context context) {
        return new File(context.getCacheDir() + "/" + "tempFile.tmp");
    }

    private static Uri getTmpFileUri(Context context) {
        return Uri.fromFile(getTempFile(context));
    }
}
