package com.spiraltoys.cloudpets2.toy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.Date;

class BluetoothCrashResolver$1 extends BroadcastReceiver {
    final /* synthetic */ BluetoothCrashResolver this$0;

    BluetoothCrashResolver$1(BluetoothCrashResolver this$0) {
        this.this$0 = this$0;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
            if (BluetoothCrashResolver.access$100(this.this$0)) {
                if (BluetoothCrashResolver.access$200(this.this$0)) {
                    Log.d("BluetoothCrashResolver", "Bluetooth discovery finished");
                }
                BluetoothCrashResolver.access$300(this.this$0);
            } else if (BluetoothCrashResolver.access$200(this.this$0)) {
                Log.d("BluetoothCrashResolver", "Bluetooth discovery finished (external)");
            }
        }
        if (action.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED")) {
            if (BluetoothCrashResolver.access$100(this.this$0)) {
                BluetoothCrashResolver.access$402(this.this$0, true);
                if (BluetoothCrashResolver.access$200(this.this$0)) {
                    Log.d("BluetoothCrashResolver", "Bluetooth discovery started");
                }
            } else if (BluetoothCrashResolver.access$200(this.this$0)) {
                Log.d("BluetoothCrashResolver", "Bluetooth discovery started (external)");
            }
        }
        if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
            switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE)) {
                case Integer.MIN_VALUE:
                    if (BluetoothCrashResolver.access$200(this.this$0)) {
                        Log.d("BluetoothCrashResolver", "Bluetooth state is ERROR");
                        return;
                    }
                    return;
                case 10:
                    if (BluetoothCrashResolver.access$200(this.this$0)) {
                        Log.d("BluetoothCrashResolver", "Bluetooth state is OFF");
                    }
                    BluetoothCrashResolver.access$502(this.this$0, new Date().getTime());
                    return;
                case 11:
                    BluetoothCrashResolver.access$602(this.this$0, new Date().getTime());
                    if (BluetoothCrashResolver.access$200(this.this$0)) {
                        Log.d("BluetoothCrashResolver", "Bluetooth state is TURNING_ON");
                        return;
                    }
                    return;
                case 12:
                    if (BluetoothCrashResolver.access$200(this.this$0)) {
                        Log.d("BluetoothCrashResolver", "Bluetooth state is ON");
                    }
                    if (BluetoothCrashResolver.access$200(this.this$0)) {
                        Log.d("BluetoothCrashResolver", "Bluetooth was turned off for " + (BluetoothCrashResolver.access$600(this.this$0) - BluetoothCrashResolver.access$500(this.this$0)) + " milliseconds");
                    }
                    if (BluetoothCrashResolver.access$600(this.this$0) - BluetoothCrashResolver.access$500(this.this$0) < 600) {
                        this.this$0.crashDetected();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
