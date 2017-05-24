package com.spiraltoys.cloudpets2.toy.task;

import android.util.Log;
import com.google.common.collect.ComparisonChain;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.ToyUtil;
import com.spiraltoys.cloudpets2.toy.event.ToyEventUpdateProgress;
import com.spiraltoys.cloudpets2.toy.task.ToyTask.Listener;
import de.greenrobot.event.EventBus;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ToyTaskUpdateFirmware extends ToyTaskWriteData {
    private static final int DATA_CHUNK_BYTES = 16;
    private static final int DATA_SIZE_BYTE_OFFSET = 6;
    private static final int FIRMWARE_UPDATE_ENABLED = 1;
    private static final String FIRMWARE_VERSION = "1.0.19";
    private static final int HEADER_BYTE_OFFSET = 2;
    private static final int HEADER_SIZE_BYTES = 12;
    private static final String TAG = ToyTaskUpdateFirmware.class.getSimpleName();
    private static final int UID_BYTE_OFFSET = 8;
    private static final int VERSION_BYTE_OFFSET = 4;
    private boolean mImageA = false;
    private byte[] mImageHeader = null;
    private int mImageSize = -1;
    private int mPercentage = -1;
    private State mState = State.IDLE;

    public static boolean isValidFirmware(ToyPeripheral peripheral) {
        boolean z = true;
        String name = peripheral.getName();
        if (name == null) {
            return false;
        }
        String[] components = name.split("\\s+");
        String firmwareVersion = components[components.length - 1];
        try {
            int[] bundledFirmwareVersionParts = splitVersionStringToIntegers(FIRMWARE_VERSION);
            int[] toyFirmwareVersionParts = splitVersionStringToIntegers(firmwareVersion);
            if (bundledFirmwareVersionParts.length != toyFirmwareVersionParts.length) {
                return false;
            }
            if (ComparisonChain.start().compare(bundledFirmwareVersionParts[0], toyFirmwareVersionParts[0]).compare(bundledFirmwareVersionParts[1], toyFirmwareVersionParts[1]).compare(bundledFirmwareVersionParts[2], toyFirmwareVersionParts[2]).result() > 0) {
                z = false;
            }
            return z;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private static int[] splitVersionStringToIntegers(String version) throws NumberFormatException {
        String[] parts = version.split("\\.");
        int[] ints = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            ints[i] = Integer.parseInt(parts[i]);
        }
        return ints;
    }

    public ToyTaskUpdateFirmware(ToyPeripheral peripheral) throws NullPointerException {
        super(peripheral, ToyPeripheral.IMAGE_BLOCK_REQUEST_CHAR_UUID);
    }

    public ToyState getState() {
        return ToyState.UPDATING_FIRMWARE;
    }

    public void start(Listener listener) throws NullPointerException {
        super.start(listener);
        String name = getPeripheral().getName();
        if (name != null) {
            String[] components = name.split("\\s+");
            this.mImageA = !components[components.length + -2].equals("A");
            loadFirmware();
            return;
        }
        getListener().onFailure(this, null, new Error(getPeripheral().getContext().getString(R.string.error_firmware_not_valid)));
    }

    private void loadFirmware() {
        this.mState = State.LOAD;
        new 1(this).execute(new Void[0]);
    }

    private void setNotifying(boolean enabled) {
        getPeripheral().setCharacteristicNotification(ToyPeripheral.IMAGE_NOTIFY_CHAR_UUID, enabled);
    }

    private boolean isCompatibleFirmware(byte[] firmware) {
        if (this.mImageSize < 6 || firmware == null || firmware.length < 2) {
            return false;
        }
        byte[] versionBytes = new byte[2];
        System.arraycopy(this.mImageHeader, 4, versionBytes, 0, 2);
        int pendingVersion = ToyUtil.getNumber(versionBytes, 0, 2);
        System.arraycopy(firmware, 0, versionBytes, 0, 2);
        if ((pendingVersion & 1) != (ToyUtil.getNumber(versionBytes, 0, 2) & 1)) {
            return true;
        }
        return false;
    }

    private void requestSendFirmware() {
        int i = 1;
        byte[] data = new byte[1];
        if (!this.mImageA) {
            i = 0;
        }
        data[0] = (byte) i;
        getPeripheral().writeCharacteristic(ToyPeripheral.IMAGE_NOTIFY_CHAR_UUID, data);
    }

    private void startSendFirmware() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(this.mImageHeader, 4, 2);
        outputStream.write(this.mImageHeader, 6, 2);
        outputStream.write(this.mImageHeader, 8, 4);
        try {
            outputStream.write(ToyUtil.getBytes((short) 12));
            outputStream.write(ToyUtil.getBytes((short) 15));
            outputStream.close();
            this.mState = State.WRITE;
            getPeripheral().writeCharacteristic(ToyPeripheral.IMAGE_NOTIFY_CHAR_UUID, outputStream.toByteArray());
        } catch (IOException e) {
            getListener().onFailure(this, null, new Error(getPeripheral().getContext().getString(R.string.error_firmware_not_valid)));
        }
    }

    void onProgress(int dataRemaining) {
        float progress = ((float) (this.mImageSize - dataRemaining)) / ((float) this.mImageSize);
        int percentage = Math.max(0, Math.min((int) (100.0f * progress), 100));
        if (this.mPercentage != percentage) {
            this.mPercentage = percentage;
            EventBus.getDefault().post(new ToyEventUpdateProgress(progress));
        }
    }

    void onCompleted(Error error) {
        if (hasDataRemaining()) {
            getListener().onFailure(this, null, error);
            return;
        }
        Log.v(TAG, "Firmware update completed.");
        getListener().onSuccess(this, null, Boolean.valueOf(true));
    }

    public void onCharacteristicWrite(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
        super.onCharacteristicWrite(peripheral, characteristic, value, error);
        if (!ToyPeripheral.IMAGE_NOTIFY_CHAR_UUID.equals(characteristic)) {
            return;
        }
        if (error != null) {
            getListener().onFailure(this, null, error);
        } else if (this.mState == State.WRITE) {
            startWriting();
        } else if (this.mState == State.VALID) {
            setNotifying(false);
        }
    }

    public void onCharacteristicChanged(ToyPeripheral peripheral, UUID characteristic, byte[] value) {
        super.onCharacteristicChanged(peripheral, characteristic, value);
        if (!ToyPeripheral.IMAGE_NOTIFY_CHAR_UUID.equals(characteristic) || this.mState != State.VERIFY) {
            return;
        }
        if (isCompatibleFirmware(value)) {
            this.mState = State.VALID;
            setNotifying(false);
            return;
        }
        this.mState = State.INVALID;
    }

    public synchronized void onCharacteristicSetNotify(ToyPeripheral peripheral, UUID characteristic, Error error) {
        super.onCharacteristicSetNotify(peripheral, characteristic, error);
        if (error != null) {
            getListener().onFailure(this, null, error);
        } else if (ToyPeripheral.IMAGE_NOTIFY_CHAR_UUID.equals(characteristic)) {
            if (this.mState == State.VERIFY) {
                requestSendFirmware();
            } else if (this.mState == State.VALID) {
                startSendFirmware();
            } else {
                Log.d(TAG, "Ignoring onCharacteristicChanged()");
            }
        }
    }
}
