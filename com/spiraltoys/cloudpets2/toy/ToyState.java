package com.spiraltoys.cloudpets2.toy;

import com.spiraltoys.cloudpets2.free.R;

public enum ToyState {
    UNKNOWN,
    NOT_SUPPORTED,
    NOT_READY,
    READY,
    CONNECTED,
    CONNECTING,
    DISCONNECTING,
    PLAYING_AUDIO,
    RECORDING_AUDIO,
    SENDING_AUDIO,
    RECEIVING_AUDIO,
    UPDATING_FIRMWARE,
    WRITING_CHARACTERISTIC;

    public boolean isOnline() {
        return (this == UNKNOWN || this == NOT_SUPPORTED || this == NOT_READY) ? false : true;
    }

    public boolean isConnected() {
        return this == CONNECTED || isInUse();
    }

    public boolean isIdle() {
        return this == READY || this == CONNECTED;
    }

    public boolean isInUse() {
        return this == PLAYING_AUDIO || this == RECORDING_AUDIO || this == SENDING_AUDIO || this == RECEIVING_AUDIO || this == UPDATING_FIRMWARE || this == WRITING_CHARACTERISTIC;
    }

    public int getLocalizedStringId() {
        switch (this) {
            case UNKNOWN:
            case NOT_SUPPORTED:
            case NOT_READY:
                return R.string.label_bluetooth_off;
            case READY:
                return R.string.label_disconnected;
            case RECEIVING_AUDIO:
                return R.string.label_downloading;
            case CONNECTING:
                return R.string.label_connecting;
            case DISCONNECTING:
                return R.string.label_disconnecting;
            default:
                return R.string.label_connected;
        }
    }
}
