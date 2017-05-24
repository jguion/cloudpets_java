package com.spiraltoys.cloudpets2.toy;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.InputDeviceCompat;
import android.util.Log;
import com.spiraltoys.cloudpets2.free.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class ToyPeripheral {
    public static final UUID COMMAND_CHAR_UUID = UUID.fromString("AADDF75D-A95A-0000-0001-3CA499B74009");
    public static final UUID CONFIG_CHAR_UUID = UUID.fromString("AADDF75D-A95A-0000-0006-3CA499B74009");
    public static final UUID DATA_REQUEST_CHAR_UUID = UUID.fromString("AADDF75D-A95A-0000-0005-3CA499B74009");
    public static final UUID IMAGE_BLOCK_REQUEST_CHAR_UUID = UUID.fromString("F000FFC2-0451-4000-B000-000000000000");
    public static final UUID IMAGE_NOTIFY_CHAR_UUID = UUID.fromString("F000FFC1-0451-4000-B000-000000000000");
    public static final UUID LED_CHAR_UUID = UUID.fromString("AADDF75D-A95A-0000-0007-3CA499B74009");
    private static final UUID NOTIFY_DESCRIPTOR_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public static final UUID RECEIVE_AUDIO_CHAR_UUID = UUID.fromString("AADDF75D-A95A-0000-0003-3CA499B74009");
    public static final UUID SEND_AUDIO_CHAR_UUID = UUID.fromString("AADDF75D-A95A-0000-0002-3CA499B74009");
    public static final UUID STATE_CHAR_UUID = UUID.fromString("AADDF75D-A95A-0000-0004-3CA499B74009");
    private static final String TAG = ToyPeripheral.class.getSimpleName();
    public static final UUID VOLUME_CHAR_UUID = UUID.fromString("AADDF75D-A95A-0000-0008-3CA499B74009");
    private String mAdvertisedName;
    private Context mContext;
    private State mCurrentState;
    private BluetoothDevice mDevice;
    private BluetoothGatt mGatt;
    private final BluetoothGattCallback mGattCallback;
    private String mIdentifier;
    private State mLastState;
    private final ArrayList<Listener> mListeners;
    private Listener mProxyListener;

    public interface Listener {
        void onCharacteristicChanged(ToyPeripheral toyPeripheral, UUID uuid, byte[] bArr);

        void onCharacteristicRead(ToyPeripheral toyPeripheral, UUID uuid, byte[] bArr, Error error);

        void onCharacteristicSetNotify(ToyPeripheral toyPeripheral, UUID uuid, Error error);

        void onCharacteristicWrite(ToyPeripheral toyPeripheral, UUID uuid, byte[] bArr, Error error);

        void onConnectionStateChange(ToyPeripheral toyPeripheral, Error error);

        void onServicesDiscovered(ToyPeripheral toyPeripheral, Error error);

        void onToyStateChange(ToyPeripheral toyPeripheral, State state, State state2);
    }

    public enum State {
        INVALID(0),
        IDLE(1),
        AUDIO_RECORD(2),
        AUDIO_UPLOAD(3),
        UNPLAYED_RECORDING(4),
        AUDIO_PLAYBACK(5),
        AUDIO_STREAMING(6),
        AUDIO_DOWNLOAD(7),
        AUDIO_GET_RECORD_LENGTH(8),
        BUFFER_PRIME(9),
        TEST_MODE_STARTUP(10),
        TEST_MODE_RECORD(11),
        TEST_MODE_RECORD_END(12),
        TEST_MODE_IDLE(13),
        TEST_MODE_PLAYBACK(14),
        SECURITY_KEY_CHECK_FAILED(15),
        INITIALIZE(16),
        AUDIO_GET_RETURN_VALUE(17),
        GAME_MODE_RECORD_PAW_PRESSED(18),
        GAME_MODE_PLAY_PAW_PRESSED(19);
        
        private final int mValue;

        private State(int value) {
            this.mValue = value;
        }

        private static State getState(int value) throws IllegalArgumentException {
            for (State state : values()) {
                if (state.mValue == value) {
                    return state;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public ToyPeripheral(Context context, BluetoothDevice device, String advertisedName) {
        this(context, device, advertisedName, null);
    }

    public ToyPeripheral(Context context, BluetoothDevice device, String advertisedName, String toyIdentifier) {
        this.mGattCallback = new BluetoothGattCallback() {
            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                super.onConnectionStateChange(gatt, status, newState);
                ToyPeripheral.this.mLastState = ToyPeripheral.this.mCurrentState;
                ToyPeripheral.this.mCurrentState = newState == 2 ? State.IDLE : State.INVALID;
                if ((status == 133 || newState == 0) && gatt != null) {
                    ToyPeripheral.this.mCurrentState = State.INVALID;
                    gatt.close();
                    ToyPeripheral.this.mGatt = null;
                }
                ToyPeripheral.this.mProxyListener.onConnectionStateChange(ToyPeripheral.this, ToyPeripheral.this.getErrorForStatus(status));
            }

            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                super.onServicesDiscovered(gatt, status);
                ToyPeripheral.this.mProxyListener.onServicesDiscovered(ToyPeripheral.this, ToyPeripheral.this.getErrorForStatus(status));
            }

            public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onCharacteristicRead(gatt, characteristic, status);
                Error error = ToyPeripheral.this.getErrorForStatus(status);
                if (error == null && characteristic.getUuid().equals(ToyPeripheral.CONFIG_CHAR_UUID)) {
                    ToyPeripheral.this.mIdentifier = new String(characteristic.getValue());
                }
                ToyPeripheral.this.mProxyListener.onCharacteristicRead(ToyPeripheral.this, characteristic.getUuid(), characteristic.getValue(), error);
            }

            public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                super.onCharacteristicWrite(gatt, characteristic, status);
                Error error = ToyPeripheral.this.getErrorForStatus(status);
                if (error == null && characteristic.getUuid().equals(ToyPeripheral.CONFIG_CHAR_UUID)) {
                    ToyPeripheral.this.mIdentifier = new String(characteristic.getValue());
                }
                ToyPeripheral.this.mProxyListener.onCharacteristicWrite(ToyPeripheral.this, characteristic.getUuid(), characteristic.getValue(), error);
            }

            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                super.onCharacteristicChanged(gatt, characteristic);
                if (characteristic.getUuid().equals(ToyPeripheral.STATE_CHAR_UUID)) {
                    try {
                        ToyPeripheral.this.mLastState = ToyPeripheral.this.mCurrentState;
                        ToyPeripheral.this.mCurrentState = State.getState(characteristic.getValue()[0]);
                        ToyPeripheral.this.mProxyListener.onToyStateChange(ToyPeripheral.this, ToyPeripheral.this.mLastState, ToyPeripheral.this.mCurrentState);
                    } catch (IllegalArgumentException e) {
                        Log.e(ToyPeripheral.TAG, "Invalid toy state: " + characteristic.getValue()[0]);
                    }
                }
                ToyPeripheral.this.mProxyListener.onCharacteristicChanged(ToyPeripheral.this, characteristic.getUuid(), characteristic.getValue());
            }

            public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                super.onDescriptorWrite(gatt, descriptor, status);
                ToyPeripheral.this.mProxyListener.onCharacteristicSetNotify(ToyPeripheral.this, descriptor.getCharacteristic().getUuid(), ToyPeripheral.this.getErrorForStatus(status));
            }
        };
        this.mProxyListener = new Listener() {
            public void onConnectionStateChange(ToyPeripheral peripheral, Error error) {
                new Handler(Looper.getMainLooper()).post(new 1(this, peripheral, error));
            }

            public void onServicesDiscovered(ToyPeripheral peripheral, Error error) {
                new Handler(Looper.getMainLooper()).post(new 2(this, peripheral, error));
            }

            public void onCharacteristicRead(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
                new Handler(Looper.getMainLooper()).post(new 3(this, peripheral, characteristic, value, error));
            }

            public void onCharacteristicWrite(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
                new Handler(Looper.getMainLooper()).post(new 4(this, peripheral, characteristic, value, error));
            }

            public void onCharacteristicChanged(ToyPeripheral peripheral, UUID characteristic, byte[] value) {
                new Handler(Looper.getMainLooper()).post(new 5(this, peripheral, characteristic, value));
            }

            public void onCharacteristicSetNotify(ToyPeripheral peripheral, UUID characteristic, Error error) {
                new Handler(Looper.getMainLooper()).post(new 6(this, peripheral, characteristic, error));
            }

            public void onToyStateChange(ToyPeripheral peripheral, State oldState, State newState) {
                new Handler(Looper.getMainLooper()).post(new 7(this, peripheral, oldState, newState));
            }
        };
        if (device == null || context == null) {
            throw new NullPointerException();
        }
        this.mContext = context;
        this.mDevice = device;
        this.mAdvertisedName = advertisedName;
        this.mIdentifier = toyIdentifier;
        this.mGatt = null;
        State state = State.INVALID;
        this.mLastState = state;
        this.mCurrentState = state;
        this.mListeners = new ArrayList();
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public State getLastState() {
        return this.mLastState;
    }

    public State getCurrentState() {
        return this.mCurrentState;
    }

    public String getDeviceAddress() {
        return this.mDevice.getAddress();
    }

    public void addListener(Listener listener) {
        if (listener != null && !this.mListeners.contains(listener)) {
            this.mListeners.add(listener);
        }
    }

    public void removeListener(Listener listener) {
        if (listener != null) {
            this.mListeners.remove(listener);
        }
    }

    public boolean isConnected() {
        return (this.mCurrentState == State.INVALID || this.mGatt == null) ? false : true;
    }

    public String getName() {
        if (this.mAdvertisedName != null) {
            return this.mAdvertisedName;
        }
        if (this.mDevice != null) {
            return this.mDevice.getName();
        }
        return null;
    }

    public void connect() {
        if (isConnected()) {
            this.mProxyListener.onConnectionStateChange(this, getErrorForConnection());
            return;
        }
        this.mGatt = this.mDevice.connectGatt(this.mContext, false, this.mGattCallback);
        if (this.mGatt == null) {
            this.mProxyListener.onConnectionStateChange(this, getErrorForConnection());
        }
    }

    public void disconnect() {
        if (isConnected()) {
            this.mGatt.disconnect();
        } else {
            this.mProxyListener.onConnectionStateChange(this, getErrorForConnection());
        }
    }

    public void discoverServices() {
        if (isConnected()) {
            this.mGatt.discoverServices();
        } else {
            this.mProxyListener.onServicesDiscovered(this, getErrorForConnection());
        }
    }

    public boolean readCharacteristic(UUID uuid) {
        if (isConnected()) {
            BluetoothGattCharacteristic characteristic = getCharacteristic(uuid);
            if (characteristic != null) {
                return this.mGatt.readCharacteristic(characteristic);
            }
            this.mProxyListener.onCharacteristicRead(this, uuid, null, getErrorForStatus(InputDeviceCompat.SOURCE_KEYBOARD));
            return false;
        }
        this.mProxyListener.onCharacteristicRead(this, uuid, null, getErrorForConnection());
        return false;
    }

    public boolean writeCharacteristic(UUID uuid, byte[] value) {
        return writeCharacteristic(uuid, value, false);
    }

    public boolean writeCharacteristic(UUID uuid, byte[] value, boolean noResponse) {
        if (isConnected()) {
            BluetoothGattCharacteristic characteristic = getCharacteristic(uuid);
            if (characteristic == null) {
                this.mProxyListener.onCharacteristicWrite(this, uuid, null, getErrorForStatus(InputDeviceCompat.SOURCE_KEYBOARD));
                return false;
            }
            int writeType = noResponse ? 1 : 2;
            if (characteristic.getWriteType() != writeType) {
                characteristic.setWriteType(writeType);
            }
            characteristic.setValue(value);
            return this.mGatt.writeCharacteristic(characteristic);
        }
        this.mProxyListener.onCharacteristicWrite(this, uuid, null, getErrorForConnection());
        return false;
    }

    public void setCharacteristicNotification(UUID uuid, boolean enabled) {
        if (isConnected()) {
            BluetoothGattCharacteristic characteristic = getCharacteristic(uuid);
            if (characteristic == null) {
                this.mProxyListener.onCharacteristicSetNotify(this, uuid, getErrorForStatus(InputDeviceCompat.SOURCE_KEYBOARD));
                return;
            }
            this.mGatt.setCharacteristicNotification(characteristic, enabled);
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(NOTIFY_DESCRIPTOR_UUID);
            if (descriptor == null) {
                this.mProxyListener.onCharacteristicSetNotify(this, uuid, getErrorForStatus(InputDeviceCompat.SOURCE_KEYBOARD));
                return;
            }
            if (enabled) {
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            } else {
                descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
            }
            this.mGatt.writeDescriptor(descriptor);
            return;
        }
        this.mProxyListener.onCharacteristicSetNotify(this, uuid, getErrorForConnection());
    }

    private BluetoothGattCharacteristic getCharacteristic(UUID uuid) {
        for (BluetoothGattService service : this.mGatt.getServices()) {
            Iterator it = new ArrayList(service.getCharacteristics()).iterator();
            while (it.hasNext()) {
                BluetoothGattCharacteristic characteristic = (BluetoothGattCharacteristic) it.next();
                if (characteristic.getUuid().equals(uuid)) {
                    return characteristic;
                }
            }
        }
        return null;
    }

    protected Error getErrorForStatus(int status) {
        if (status == 0) {
            return null;
        }
        return new Error(this.mContext.getString(R.string.error_toy_gatt));
    }

    protected Error getErrorForConnection() {
        return new Error(this.mContext.getString(R.string.error_toy_not_connected));
    }

    public boolean equals(Object o) {
        if (o != null && (o instanceof ToyPeripheral)) {
            return equalsDevice(((ToyPeripheral) o).mDevice);
        }
        return false;
    }

    public boolean equalsDevice(BluetoothDevice device) {
        if (device == null) {
            return false;
        }
        return device.getAddress().equals(this.mDevice.getAddress());
    }
}
