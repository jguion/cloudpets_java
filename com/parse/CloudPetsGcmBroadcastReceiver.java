package com.parse;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CloudPetsGcmBroadcastReceiver extends BroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        Log.i("CloudPetsGcmBroadcast", "Push notification:" + intent);
    }
}
