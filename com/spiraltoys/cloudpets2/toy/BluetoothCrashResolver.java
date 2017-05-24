package com.spiraltoys.cloudpets2.toy;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@TargetApi(5)
public class BluetoothCrashResolver {
    private static final int BLUEDROID_MAX_BLUETOOTH_MAC_COUNT = 1990;
    private static final int BLUEDROID_POST_DISCOVERY_ESTIMATED_BLUETOOTH_MAC_COUNT = 400;
    private static final String DISTINCT_BLUETOOTH_ADDRESSES_FILE = "BluetoothCrashResolverState.txt";
    private static final long MIN_TIME_BETWEEN_STATE_SAVES_MILLIS = 60000;
    private static final boolean PREEMPTIVE_ACTION_ENABLED = true;
    private static final long SUSPICIOUSLY_SHORT_BLUETOOTH_OFF_INTERVAL_MILLIS = 600;
    private static final String TAG = "BluetoothCrashResolver";
    private static final int TIME_TO_LET_DISCOVERY_RUN_MILLIS = 5000;
    private Context context = null;
    private boolean debugEnabled = false;
    private int detectedCrashCount = 0;
    private DiscoveryCanceller discoveryCanceller = new DiscoveryCanceller(this, null);
    private boolean discoveryStartConfirmed = false;
    private Set<String> distinctBluetoothAddresses = new HashSet();
    private long lastBluetoothCrashDetectionTime = 0;
    private long lastBluetoothOffTime = 0;
    private long lastBluetoothTurningOnTime = 0;
    private boolean lastRecoverySucceeded = false;
    private long lastStateSaveTime = 0;
    private final BroadcastReceiver receiver = new 1(this);
    private int recoveryAttemptCount = 0;
    private boolean recoveryInProgress = false;
    private UpdateNotifier updateNotifier;

    public BluetoothCrashResolver(Context context) {
        this.context = context.getApplicationContext();
        if (isDebugEnabled()) {
            Log.d(TAG, "constructed");
        }
        loadState();
    }

    public void start() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        filter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        filter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        this.context.registerReceiver(this.receiver, filter);
        if (isDebugEnabled()) {
            Log.d(TAG, "started listening for BluetoothAdapter events");
        }
    }

    public void stop() {
        this.context.unregisterReceiver(this.receiver);
        if (isDebugEnabled()) {
            Log.d(TAG, "stopped listening for BluetoothAdapter events");
        }
        saveState();
    }

    public void enableDebug() {
        this.debugEnabled = true;
    }

    public void disableDebug() {
        this.debugEnabled = false;
    }

    @TargetApi(18)
    public void notifyScannedDevice(BluetoothDevice device, LeScanCallback scanner) {
        int oldSize = 0;
        if (isDebugEnabled()) {
            oldSize = this.distinctBluetoothAddresses.size();
        }
        this.distinctBluetoothAddresses.add(device.getAddress());
        if (isDebugEnabled()) {
            int newSize = this.distinctBluetoothAddresses.size();
            if (oldSize != newSize && newSize % 100 == 0 && isDebugEnabled()) {
                Log.d(TAG, "Distinct bluetooth devices seen: " + this.distinctBluetoothAddresses.size());
            }
        }
        if (this.distinctBluetoothAddresses.size() > getCrashRiskDeviceCount() && !this.recoveryInProgress) {
            Log.w(TAG, "Large number of bluetooth devices detected: " + this.distinctBluetoothAddresses.size() + " Proactively attempting to clear out address list to prevent a crash");
            Log.w(TAG, "Stopping LE Scan");
            BluetoothAdapter.getDefaultAdapter().stopLeScan(scanner);
            startRecovery();
            processStateChange();
        }
    }

    public void crashDetected() {
        if (VERSION.SDK_INT >= 18) {
            Log.w(TAG, "BluetoothService crash detected");
            if (this.distinctBluetoothAddresses.size() > 0 && isDebugEnabled()) {
                Log.d(TAG, "Distinct bluetooth devices seen at crash: " + this.distinctBluetoothAddresses.size());
            }
            this.lastBluetoothCrashDetectionTime = new Date().getTime();
            this.detectedCrashCount++;
            if (!this.recoveryInProgress) {
                startRecovery();
            } else if (isDebugEnabled()) {
                Log.d(TAG, "Ignoring bluetooth crash because recovery is already in progress.");
            }
            processStateChange();
        } else if (isDebugEnabled()) {
            Log.d(TAG, "Ignoring crashes before SDK 18, because BLE is unsupported.");
        }
    }

    public long getLastBluetoothCrashDetectionTime() {
        return this.lastBluetoothCrashDetectionTime;
    }

    public int getDetectedCrashCount() {
        return this.detectedCrashCount;
    }

    public int getRecoveryAttemptCount() {
        return this.recoveryAttemptCount;
    }

    public boolean isLastRecoverySucceeded() {
        return this.lastRecoverySucceeded;
    }

    public boolean isRecoveryInProgress() {
        return this.recoveryInProgress;
    }

    public void setUpdateNotifier(UpdateNotifier updateNotifier) {
        this.updateNotifier = updateNotifier;
    }

    public void forceFlush() {
        startRecovery();
        processStateChange();
    }

    private boolean isDebugEnabled() {
        return this.debugEnabled;
    }

    private int getCrashRiskDeviceCount() {
        return 1590;
    }

    private void processStateChange() {
        if (this.updateNotifier != null) {
            this.updateNotifier.dataUpdated();
        }
        if (new Date().getTime() - this.lastStateSaveTime > 60000) {
            saveState();
        }
    }

    @TargetApi(17)
    private void startRecovery() {
        this.recoveryAttemptCount++;
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (isDebugEnabled()) {
            Log.d(TAG, "about to check if discovery is active");
        }
        if (adapter.isDiscovering()) {
            Log.w(TAG, "Already discovering.  Recovery attempt abandoned.");
            return;
        }
        Log.w(TAG, "Recovery attempt started");
        this.recoveryInProgress = true;
        this.discoveryStartConfirmed = false;
        if (isDebugEnabled()) {
            Log.d(TAG, "about to command discovery");
        }
        if (!adapter.startDiscovery()) {
            Log.w(TAG, "Can't start discovery.  Is bluetooth turned on?");
        }
        if (isDebugEnabled()) {
            Log.d(TAG, "startDiscovery commanded.  isDiscovering()=" + adapter.isDiscovering());
        }
        if (isDebugEnabled()) {
            Log.d(TAG, "We will be cancelling this discovery in 5000 milliseconds.");
        }
        this.discoveryCanceller.doInBackground(new Void[0]);
    }

    private void finishRecovery() {
        Log.w(TAG, "Recovery attempt finished");
        this.distinctBluetoothAddresses.clear();
        this.recoveryInProgress = false;
    }

    private void saveState() {
        Throwable th;
        OutputStreamWriter outputStreamWriter = null;
        this.lastStateSaveTime = new Date().getTime();
        try {
            OutputStreamWriter writer = new OutputStreamWriter(this.context.openFileOutput(DISTINCT_BLUETOOTH_ADDRESSES_FILE, 0));
            try {
                writer.write(this.lastBluetoothCrashDetectionTime + "\n");
                writer.write(this.detectedCrashCount + "\n");
                writer.write(this.recoveryAttemptCount + "\n");
                writer.write(this.lastRecoverySucceeded ? "1\n" : "0\n");
                synchronized (this.distinctBluetoothAddresses) {
                    for (String mac : this.distinctBluetoothAddresses) {
                        writer.write(mac);
                        writer.write("\n");
                    }
                }
                if (writer != null) {
                    try {
                        writer.close();
                        outputStreamWriter = writer;
                    } catch (IOException e) {
                        outputStreamWriter = writer;
                    }
                }
            } catch (IOException e2) {
                outputStreamWriter = writer;
            } catch (Throwable th2) {
                th = th2;
                outputStreamWriter = writer;
                if (outputStreamWriter != null) {
                    try {
                        outputStreamWriter.close();
                    } catch (IOException e3) {
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            try {
                Log.w(TAG, "Can't write macs to BluetoothCrashResolverState.txt");
                if (outputStreamWriter != null) {
                    try {
                        outputStreamWriter.close();
                    } catch (IOException e5) {
                    }
                }
                if (isDebugEnabled()) {
                    Log.d(TAG, "Wrote " + this.distinctBluetoothAddresses.size() + " bluetooth addresses");
                }
            } catch (Throwable th3) {
                th = th3;
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                throw th;
            }
        }
        if (isDebugEnabled()) {
            Log.d(TAG, "Wrote " + this.distinctBluetoothAddresses.size() + " bluetooth addresses");
        }
    }

    private void loadState() {
        Throwable th;
        BufferedReader reader = null;
        try {
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(this.context.openFileInput(DISTINCT_BLUETOOTH_ADDRESSES_FILE)));
            try {
                String line = reader2.readLine();
                if (line != null) {
                    this.lastBluetoothCrashDetectionTime = Long.parseLong(line);
                }
                line = reader2.readLine();
                if (line != null) {
                    this.detectedCrashCount = Integer.parseInt(line);
                }
                line = reader2.readLine();
                if (line != null) {
                    this.recoveryAttemptCount = Integer.parseInt(line);
                }
                line = reader2.readLine();
                if (line != null) {
                    this.lastRecoverySucceeded = false;
                    if (line.equals("1")) {
                        this.lastRecoverySucceeded = true;
                    }
                }
                while (true) {
                    String mac = reader2.readLine();
                    if (mac == null) {
                        break;
                    }
                    this.distinctBluetoothAddresses.add(mac);
                }
                if (reader2 != null) {
                    try {
                        reader2.close();
                        reader = reader2;
                    } catch (IOException e) {
                        reader = reader2;
                    }
                }
            } catch (IOException e2) {
                reader = reader2;
                try {
                    Log.w(TAG, "Can't read macs from BluetoothCrashResolverState.txt");
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e3) {
                        }
                    }
                    if (isDebugEnabled()) {
                        Log.d(TAG, "Read " + this.distinctBluetoothAddresses.size() + " bluetooth addresses");
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (NumberFormatException e5) {
                reader = reader2;
                Log.w(TAG, "Can't parse file BluetoothCrashResolverState.txt");
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e6) {
                    }
                }
                if (isDebugEnabled()) {
                    Log.d(TAG, "Read " + this.distinctBluetoothAddresses.size() + " bluetooth addresses");
                }
            } catch (Throwable th3) {
                th = th3;
                reader = reader2;
                if (reader != null) {
                    reader.close();
                }
                throw th;
            }
        } catch (IOException e7) {
            Log.w(TAG, "Can't read macs from BluetoothCrashResolverState.txt");
            if (reader != null) {
                reader.close();
            }
            if (isDebugEnabled()) {
                Log.d(TAG, "Read " + this.distinctBluetoothAddresses.size() + " bluetooth addresses");
            }
        } catch (NumberFormatException e8) {
            Log.w(TAG, "Can't parse file BluetoothCrashResolverState.txt");
            if (reader != null) {
                reader.close();
            }
            if (isDebugEnabled()) {
                Log.d(TAG, "Read " + this.distinctBluetoothAddresses.size() + " bluetooth addresses");
            }
        }
        if (isDebugEnabled()) {
            Log.d(TAG, "Read " + this.distinctBluetoothAddresses.size() + " bluetooth addresses");
        }
    }
}
