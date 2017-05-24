package com.spiraltoys.cloudpets2.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.common.io.ByteStreams;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.FileOutputStream;
import java.io.IOException;

class CacheUtil$1 implements Callback {
    final /* synthetic */ CacheUtil.Callback val$callback;
    final /* synthetic */ Context val$context;

    CacheUtil$1(CacheUtil.Callback callback, Context context) {
        this.val$callback = callback;
        this.val$context = context;
    }

    public void onFailure(Request request, final IOException e) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                CacheUtil$1.this.val$callback.done(null, e);
            }
        });
    }

    public void onResponse(Response response) throws IOException {
        Exception exception = null;
        if (response.body() == null) {
            this.val$callback.done(null, new RuntimeException("Response body was null"));
            return;
        }
        try {
            ByteStreams.copy(response.body().byteStream(), new FileOutputStream(CacheUtil.access$000(this.val$context)));
        } catch (Exception e) {
            exception = e;
            e.printStackTrace();
        } finally {
            response.body().close();
        }
        final Exception responseException = exception;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                CacheUtil$1.this.val$callback.done(responseException == null ? CacheUtil.access$100(CacheUtil$1.this.val$context) : null, responseException);
            }
        });
    }
}
