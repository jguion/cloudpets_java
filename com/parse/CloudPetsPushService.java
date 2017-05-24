package com.parse;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.parse.ParsePlugins.Android;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class CloudPetsPushService extends Service {
    private ExecutorService mExecutorService;

    static void runGcmIntentInService(Context context, Intent intent) {
    }

    public void onCreate() {
        super.onCreate();
        if (Android.get().applicationContext() == null) {
            PLog.e("com.parse.CloudPetsPushService", "The Parse push service cannot start because Parse.initialize has not yet been called. If you call Parse.initialize from an Activity's onCreate, that call should instead be in the Application.onCreate. Be sure your Application class is registered in your AndroidManifest.xml with the android:name property of your <application> tag.");
            stopSelf();
            return;
        }
        this.mExecutorService = Executors.newSingleThreadExecutor();
    }

    public int onStartCommand(final Intent intent, int flags, final int startId) {
        wipeRoutingAndUpgradePushStateIfNeeded();
        this.mExecutorService.execute(new Runnable() {
            public void run() {
                try {
                    CloudPetsPushService.this.onHandleGcmIntent(intent);
                } finally {
                    ServiceUtils.completeWakefulIntent(intent);
                    CloudPetsPushService.this.stopSelf(startId);
                }
            }
        });
        return 2;
    }

    private void wipeRoutingAndUpgradePushStateIfNeeded() {
    }

    private void onHandleGcmIntent(Intent intent) {
        Log.i("CloudPetsPushService", "Got intent:" + intent);
    }

    public IBinder onBind(Intent intent) {
        throw new IllegalArgumentException("You cannot bind directly to the CloudPetsPushService. Use PushService.subscribe instead.");
    }

    public void onDestroy() {
        if (this.mExecutorService != null) {
            this.mExecutorService.shutdown();
        }
        super.onDestroy();
    }
}
