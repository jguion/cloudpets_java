package com.spiraltoys.cloudpets2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.spiraltoys.cloudpets2.events.NetworkStateChangedEvent;
import de.greenrobot.event.EventBus;

public class NetworkChangeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        NetworkInfo activeNetwork = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        EventBus.getDefault().post(new NetworkStateChangedEvent(isConnected));
    }
}
