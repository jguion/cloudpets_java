package com.spiraltoys.cloudpets2.toy;

import android.bluetooth.BluetoothAdapter;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.vending.expansion.downloader.Constants;

class BluetoothCrashResolver$DiscoveryCanceller extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ BluetoothCrashResolver this$0;

    private BluetoothCrashResolver$DiscoveryCanceller(BluetoothCrashResolver bluetoothCrashResolver) {
        this.this$0 = bluetoothCrashResolver;
    }

    protected Void doInBackground(Void... params) {
        try {
            Thread.sleep(Constants.ACTIVE_THREAD_WATCHDOG);
            if (!BluetoothCrashResolver.access$400(this.this$0)) {
                Log.w("BluetoothCrashResolver", "BluetoothAdapter.ACTION_DISCOVERY_STARTED never received.  Recovery may fail.");
            }
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            if (adapter.isDiscovering()) {
                if (BluetoothCrashResolver.access$200(this.this$0)) {
                    Log.d("BluetoothCrashResolver", "Cancelling discovery");
                }
                adapter.cancelDiscovery();
            } else if (BluetoothCrashResolver.access$200(this.this$0)) {
                Log.d("BluetoothCrashResolver", "Discovery not running.  Won't cancel it");
            }
        } catch (InterruptedException e) {
            if (BluetoothCrashResolver.access$200(this.this$0)) {
                Log.d("BluetoothCrashResolver", "DiscoveryCanceller sleep interrupted.");
            }
        }
        return null;
    }

    protected void onPostExecute(Void result) {
    }

    protected void onPreExecute() {
    }

    protected void onProgressUpdate(Void... values) {
    }
}
