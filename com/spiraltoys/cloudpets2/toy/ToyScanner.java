package com.spiraltoys.cloudpets2.toy;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.spiraltoys.cloudpets2.toy.event.ToyEventDiscovered;
import com.spiraltoys.cloudpets2.toy.util.ScanRecord;
import de.greenrobot.event.EventBus;
import java.util.LinkedList;
import java.util.List;

public class ToyScanner {
    public static final int MANUFACTURER_ID = 65535;
    private static String[] sValidNames = new String[]{"CloudPets", "Cloud Pets"};
    private BluetoothAdapter mAdapter;
    private Context mContext;
    private BluetoothCrashResolver mCrashResolver;
    private boolean mIsScanning;
    private Listener mListener;
    private List<ToyPeripheral> mPeripheralList;
    private LeScanCallback mScanCallback = new LeScanCallback() {
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecordBytes) {
            ToyScanner.this.mCrashResolver.notifyScannedDevice(device, this);
            if (device.getName() != null) {
                for (String name : ToyScanner.sValidNames) {
                    if (device.getName().startsWith(name) && rssi >= -80 && ToyScanner.this.isNewToyPeripheral(device)) {
                        ScanRecord scanRecord = ScanRecord.parseFromBytes(scanRecordBytes);
                        byte[] toyIdBytes = scanRecord.getManufacturerSpecificData(65535);
                        String toyId = null;
                        if (toyIdBytes != null) {
                            toyId = new String(toyIdBytes);
                        }
                        ToyPeripheral peripheral = new ToyPeripheral(ToyScanner.this.mContext, device, scanRecord.getDeviceName(), toyId);
                        synchronized (ToyScanner.this.mPeripheralList) {
                            ToyScanner.this.mPeripheralList.add(peripheral);
                        }
                        ToyScanner.this.onToyDiscovered(peripheral);
                    }
                }
            }
        }
    };

    public interface Listener {
        void onToyDiscovered(ToyPeripheral toyPeripheral);
    }

    public ToyScanner(Context context, Listener listener) throws NullPointerException, ToyException {
        if (context == null) {
            throw new NullPointerException();
        } else if (!context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            throw new ToyException();
        } else if (BluetoothAdapter.getDefaultAdapter() == null) {
            throw new ToyException();
        } else {
            this.mContext = context;
            this.mAdapter = BluetoothAdapter.getDefaultAdapter();
            this.mPeripheralList = new LinkedList();
            this.mListener = listener;
            this.mIsScanning = false;
            this.mCrashResolver = new BluetoothCrashResolver(context.getApplicationContext());
        }
    }

    public List<ToyPeripheral> getDiscoveredPeripherals() {
        List linkedList;
        synchronized (this.mPeripheralList) {
            linkedList = new LinkedList(this.mPeripheralList);
        }
        return linkedList;
    }

    public void start() {
        if (!this.mIsScanning) {
            this.mCrashResolver.start();
            this.mIsScanning = true;
            this.mAdapter.startLeScan(this.mScanCallback);
        }
    }

    public void stop() {
        if (this.mIsScanning) {
            this.mAdapter.stopLeScan(this.mScanCallback);
            this.mIsScanning = false;
            this.mCrashResolver.stop();
        }
    }

    public void forget(ToyPeripheral peripheral) {
        synchronized (this.mPeripheralList) {
            this.mPeripheralList.remove(peripheral);
        }
    }

    public void forgetAll() {
        synchronized (this.mPeripheralList) {
            this.mPeripheralList.clear();
        }
    }

    private void onToyDiscovered(final ToyPeripheral peripheral) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                EventBus.getDefault().post(new ToyEventDiscovered(peripheral.getDeviceAddress()));
                if (ToyScanner.this.mListener != null) {
                    ToyScanner.this.mListener.onToyDiscovered(peripheral);
                }
            }
        });
    }

    private boolean isNewToyPeripheral(BluetoothDevice device) {
        for (ToyPeripheral testPeripheral : this.mPeripheralList) {
            if (testPeripheral.equalsDevice(device)) {
                return false;
            }
        }
        return true;
    }
}
