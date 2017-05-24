package com.spiraltoys.cloudpets2.toy;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral.State;
import com.spiraltoys.cloudpets2.toy.ToyScanner.Listener;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandConnect;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandDisconnect;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandIdentifier;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSendAudio;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetGameModeState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetIdentifier;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetLedState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetScanState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetSpeakerVolume;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandStartCommandSequence;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandStartLoopPlayback;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandTriggerSlotPlayback;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandUpdateFirmware;
import com.spiraltoys.cloudpets2.toy.event.ToyEventCommandSucceeded;
import com.spiraltoys.cloudpets2.toy.event.ToyEventError;
import com.spiraltoys.cloudpets2.toy.event.ToyEventGameModeButtonPress;
import com.spiraltoys.cloudpets2.toy.event.ToyEventGameModeButtonPress.Button;
import com.spiraltoys.cloudpets2.toy.event.ToyEventReceivedAudio;
import com.spiraltoys.cloudpets2.toy.event.ToyEventRequiresUpdate;
import com.spiraltoys.cloudpets2.toy.event.ToyEventState;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskConnect;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskDisconnect;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskReceiveAudio;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskUpdateFirmware;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.util.List;
import java.util.UUID;

public class ToyService extends Service {
    private static final String TAG = ToyService.class.getSimpleName();
    public static final String TOY_RECORDINGS_FOLDER_NAME = "toy_recordings";
    private ToyPeripheral mActivePeripheral = null;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                ToyService.this.updateState();
                if (!ToyService.this.mState.isOnline() && ToyService.this.mState != ToyState.NOT_SUPPORTED) {
                    ToyService.this.mScanner.stop();
                    ToyService.this.mScanner.forgetAll();
                    if (!ToyService.this.mTaskDispatcher.isEmpty()) {
                        ToyService.this.mTaskDispatcher.clear();
                        ToyService.this.postError(null, ToyService.this.getString(R.string.error_toy_ble_offline));
                    }
                    ToyService.this.setActivePeripheral(null);
                    ToyService.this.mToyOfInterest = null;
                }
            }
        }
    };
    private ToyScanner mScanner = null;
    Listener mScannerListener = new Listener() {
        public void onToyDiscovered(ToyPeripheral peripheral) {
            Log.d(ToyService.TAG, "[" + ToyService.this.getPeripheralDescription(peripheral) + "] Discovered with name: " + peripheral.getName());
            boolean shouldConnect = ToyService.this.isToyOfInterest(peripheral);
            if (!(shouldConnect || ToyService.this.mToyOfInterest == null || ToyService.this.mToyOfInterest.getIdentifier() == null)) {
                shouldConnect = peripheral.getIdentifier() == null;
            }
            if (shouldConnect) {
                ToyService.this.mScanner.stop();
                ToyService.this.connectToy(peripheral);
            }
        }
    };
    private ToyState mState = ToyState.UNKNOWN;
    private ToyTaskDispatcher mTaskDispatcher = null;
    private ToyCommandConnect mToyOfInterest = null;
    ToyPeripheral.Listener mToyPeripheralListener = new ToyPeripheral.Listener() {
        public void onConnectionStateChange(ToyPeripheral peripheral, Error error) {
            if (!peripheral.equals(ToyService.this.mActivePeripheral) || !ToyService.this.mTaskDispatcher.isEmpty()) {
                return;
            }
            if (peripheral.isConnected()) {
                ToyService.this.setState(ToyState.CONNECTED);
            } else {
                ToyService.this.onDisconnected(true);
            }
        }

        public void onServicesDiscovered(ToyPeripheral peripheral, Error error) {
        }

        public void onCharacteristicRead(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
        }

        public void onCharacteristicWrite(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
        }

        public void onCharacteristicChanged(ToyPeripheral peripheral, UUID characteristic, byte[] value) {
        }

        public void onCharacteristicSetNotify(ToyPeripheral peripheral, UUID characteristic, Error error) {
        }

        public void onToyStateChange(ToyPeripheral peripheral, State oldState, State newState) {
            Log.d(ToyService.TAG, "[" + ToyService.this.getPeripheralDescription(peripheral) + "] State = " + newState + " (Last = " + oldState + ").");
            if (ToyService.this.shouldDownloadAudio(peripheral, oldState, newState)) {
                ToyService.this.mTaskDispatcher.push(new ToyTaskReceiveAudio(peripheral, ToyService.this.getCacheDir() + File.separator + ToyService.TOY_RECORDINGS_FOLDER_NAME + File.separator + UUID.randomUUID() + ".wmv"));
                return;
            }
            switch (newState) {
                case AUDIO_PLAYBACK:
                case AUDIO_GET_RETURN_VALUE:
                    ToyService.this.setState(ToyState.PLAYING_AUDIO);
                    return;
                case AUDIO_RECORD:
                    ToyService.this.setState(ToyState.RECORDING_AUDIO);
                    return;
                case IDLE:
                    if (oldState == State.AUDIO_PLAYBACK || oldState == State.AUDIO_RECORD || oldState == State.GAME_MODE_RECORD_PAW_PRESSED || oldState == State.GAME_MODE_PLAY_PAW_PRESSED) {
                        ToyService.this.setActivePeripheral(peripheral);
                        ToyService.this.setState(ToyState.CONNECTED);
                        return;
                    }
                    return;
                case GAME_MODE_RECORD_PAW_PRESSED:
                    ToyService.this.postEvent(new ToyEventGameModeButtonPress(Button.RECORD), false);
                    return;
                case GAME_MODE_PLAY_PAW_PRESSED:
                    ToyService.this.postEvent(new ToyEventGameModeButtonPress(Button.PLAY), false);
                    return;
                default:
                    return;
            }
        }
    };
    ToyTask.Listener mToyTaskListener = new ToyTask.Listener() {
        public void onStart(ToyTask task) {
            Log.d(ToyService.TAG, "[" + ToyService.this.getPeripheralDescription(task.getPeripheral()) + "] " + task.getClass().getSimpleName() + " started.");
            ToyService.this.setState(task.getState());
        }

        public void onSuccess(ToyTask task, ToyCommandIdentifier toyCommandIdentifier, Object data) {
            ToyPeripheral peripheral = task.getPeripheral();
            Log.d(ToyService.TAG, "[" + ToyService.this.getPeripheralDescription(peripheral) + "] " + task.getClass().getSimpleName() + " succeeded.");
            if (peripheral.isConnected() && peripheral.getCurrentState() != State.AUDIO_PLAYBACK) {
                ToyService.this.setState(ToyState.CONNECTED);
            }
            if (task instanceof ToyTaskConnect) {
                if (ToyService.this.isToyOfInterest(task.getPeripheral())) {
                    ToyService.this.setActivePeripheral(task.getPeripheral());
                    if (!ToyTaskUpdateFirmware.isValidFirmware(peripheral)) {
                        ToyService.this.postEvent(new ToyEventRequiresUpdate(task.getPeripheral().getIdentifier()), true);
                    }
                } else if (ToyService.this.isSearching()) {
                    ToyService.this.mTaskDispatcher.push(new ToyTaskDisconnect(ToyService.this.mActivePeripheral));
                }
            } else if (task instanceof ToyTaskDisconnect) {
                ToyService.this.onDisconnected(false);
            } else if (task instanceof ToyTaskReceiveAudio) {
                ToyService.this.postEvent(new ToyEventReceivedAudio((Uri) data, task.getPeripheral().getIdentifier()), true);
            }
            if (toyCommandIdentifier != null) {
                ToyService.this.postEvent(new ToyEventCommandSucceeded(toyCommandIdentifier), true);
            }
        }

        public void onFailure(ToyTask task, ToyCommandIdentifier toyCommandIdentifier, Error error) {
            ToyPeripheral peripheral = task.getPeripheral();
            Log.d(ToyService.TAG, "[" + ToyService.this.getPeripheralDescription(peripheral) + "] " + task.getClass().getSimpleName() + " failed: " + error.getLocalizedMessage());
            ToyService.this.postError(toyCommandIdentifier, error.getLocalizedMessage());
            if (peripheral.isConnected()) {
                ToyService.this.setState(ToyState.CONNECTED);
            } else {
                ToyService.this.onDisconnected(true);
            }
        }
    };

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (this.mTaskDispatcher == null) {
            this.mTaskDispatcher = new ToyTaskDispatcher(this.mToyTaskListener);
            try {
                this.mScanner = new ToyScanner(this, this.mScannerListener);
            } catch (ToyException e) {
                setState(ToyState.NOT_SUPPORTED);
            }
            registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            EventBus.getDefault().register(this);
            updateState();
        }
        return 1;
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
        postEvent(new ToyEventState(null, null, ToyState.NOT_READY), true);
        EventBus.getDefault().unregister(this);
    }

    private void updateState() {
        if (this.mScanner == null) {
            setState(ToyState.NOT_SUPPORTED);
        } else if (!getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            setState(ToyState.NOT_SUPPORTED);
        } else if (BluetoothAdapter.getDefaultAdapter().getState() != 12) {
            setState(ToyState.NOT_READY);
        } else if (this.mState == ToyState.UNKNOWN || this.mState == ToyState.NOT_READY) {
            setState(ToyState.READY);
        }
    }

    private void setState(ToyState state) {
        if (this.mState != state) {
            this.mState = state;
            postEvent(new ToyEventState(this.mActivePeripheral == null ? null : this.mActivePeripheral.getIdentifier(), this.mActivePeripheral == null ? null : this.mActivePeripheral.getDeviceAddress(), this.mState), true);
        }
    }

    private void searchForToyOfInterest() {
        if (isSearching() && this.mActivePeripheral == null) {
            List<ToyPeripheral> peripherals = this.mScanner.getDiscoveredPeripherals();
            for (ToyPeripheral peripheral : peripherals) {
                if (isToyOfInterest(peripheral)) {
                    this.mScanner.stop();
                    connectToy(peripheral);
                    return;
                }
            }
            for (ToyPeripheral peripheral2 : peripherals) {
                if (peripheral2.getIdentifier() == null) {
                    this.mScanner.stop();
                    connectToy(peripheral2);
                    return;
                }
            }
            if (this.mActivePeripheral == null) {
                this.mScanner.start();
            }
        }
    }

    private boolean isSearching() {
        return this.mToyOfInterest != null;
    }

    private boolean isToyOfInterest(ToyPeripheral peripheral) {
        if (!isSearching()) {
            return false;
        }
        if (this.mToyOfInterest.getAddress() != null) {
            return this.mToyOfInterest.getAddress().equals(peripheral.getDeviceAddress());
        }
        if (this.mToyOfInterest.getIdentifier() != null) {
            return this.mToyOfInterest.getIdentifier().equals(peripheral.getIdentifier());
        }
        return true;
    }

    private void connectToy(ToyPeripheral peripheral) {
        if (this.mActivePeripheral == null) {
            setActivePeripheral(peripheral);
            this.mTaskDispatcher.push(new ToyTaskConnect(peripheral));
        }
    }

    private void setActivePeripheral(ToyPeripheral peripheral) {
        if (this.mActivePeripheral != null) {
            this.mActivePeripheral.removeListener(this.mToyPeripheralListener);
        }
        this.mActivePeripheral = peripheral;
        if (this.mActivePeripheral != null) {
            this.mActivePeripheral.addListener(this.mToyPeripheralListener);
        }
    }

    public void postEvent(final Object event, final boolean isSticky) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (isSticky) {
                    EventBus.getDefault().postSticky(event);
                } else {
                    EventBus.getDefault().post(event);
                }
            }
        });
    }

    public void postError(ToyCommandIdentifier toyCommandIdentifier, String errorMessage) {
        postEvent(new ToyEventError(this.mState, toyCommandIdentifier, errorMessage), true);
    }

    public void onEventMainThread(ToyCommandSetScanState setScanStateCommand) {
        if (validateReady(setScanStateCommand.getCommandIdentifier(), false, false)) {
            switch (setScanStateCommand.getScanState()) {
                case SCANNING:
                    this.mScanner.start();
                    return;
                case PAUSED:
                    this.mScanner.stop();
                    return;
                case STOPPED:
                    this.mScanner.stop();
                    this.mScanner.forgetAll();
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(ToyCommandConnect toyCommandConnect) {
        if (toyCommandConnect.getIdentifier() != null) {
            Log.i(TAG, "Requested toy with identifier: " + toyCommandConnect.getIdentifier());
        } else if (toyCommandConnect.getAddress() != null) {
            Log.i(TAG, "Requested toy with address: " + toyCommandConnect.getAddress());
        } else {
            Log.i(TAG, "Requested any toy");
        }
        if (!validateReady(toyCommandConnect.getCommandIdentifier(), false, false)) {
            return;
        }
        if (this.mToyOfInterest == null || !this.mToyOfInterest.equals(toyCommandConnect)) {
            this.mToyOfInterest = toyCommandConnect;
            if (this.mActivePeripheral != null && isToyOfInterest(this.mActivePeripheral)) {
                Log.i(TAG, "Already connected to the right toy (rebroadcast?)");
                return;
            } else if (this.mActivePeripheral != null) {
                Log.i(TAG, "Disconnecting from last toy first.");
                this.mTaskDispatcher.clear();
                this.mTaskDispatcher.push(new ToyTaskDisconnect(this.mActivePeripheral));
                return;
            } else {
                searchForToyOfInterest();
                return;
            }
        }
        Log.i(TAG, "Already searching for this toy...don't interrupt");
    }

    public void onEventMainThread(ToyCommandDisconnect toyCommandDisconnect) {
        if (validateReady(toyCommandDisconnect.getCommandIdentifier(), false, false, true)) {
            this.mToyOfInterest = null;
            this.mScanner.stop();
            if (this.mActivePeripheral != null) {
                if (toyCommandDisconnect.isToBePerformedImmediately()) {
                    this.mTaskDispatcher.clear();
                }
                this.mTaskDispatcher.push(toyCommandDisconnect.newTask(this, this.mActivePeripheral));
            }
        }
    }

    public void onEventMainThread(ToyCommandSendAudio toyCommandSendAudio) {
        if (validateReady(toyCommandSendAudio.getCommandIdentifier(), true, true)) {
            this.mTaskDispatcher.push(toyCommandSendAudio.newTask(this, this.mActivePeripheral));
        }
    }

    public void onEventMainThread(ToyCommandSetLedState toyCommandSetLedState) {
        if (validateReady(toyCommandSetLedState.getCommandIdentifier(), true, true)) {
            this.mTaskDispatcher.push(toyCommandSetLedState.newTask(this, this.mActivePeripheral));
        }
    }

    public void onEventMainThread(ToyCommandUpdateFirmware toyCommandUpdateFirmware) {
        if (validateReady(toyCommandUpdateFirmware.getCommandIdentifier(), true, true)) {
            this.mTaskDispatcher.push(toyCommandUpdateFirmware.newTask(this, this.mActivePeripheral));
        }
    }

    public void onEventMainThread(ToyCommandSetIdentifier toyCommandSetIdentifier) {
        if (validateReady(toyCommandSetIdentifier.getCommandIdentifier(), true, true)) {
            this.mTaskDispatcher.push(toyCommandSetIdentifier.newTask(this, this.mActivePeripheral));
        }
    }

    public void onEventMainThread(ToyCommandSetGameModeState toyCommandSetGameModeState) {
        if (validateReady(toyCommandSetGameModeState.getCommandIdentifier(), false, true, true)) {
            this.mTaskDispatcher.push(toyCommandSetGameModeState.newTask(this, this.mActivePeripheral));
        }
    }

    public void onEventMainThread(ToyCommandStartLoopPlayback toyCommandStartLoopPlayback) {
        if (validateReady(toyCommandStartLoopPlayback.getCommandIdentifier(), true, true)) {
            this.mTaskDispatcher.push(toyCommandStartLoopPlayback.newTask(this, this.mActivePeripheral));
        }
    }

    public void onEventMainThread(ToyCommandSetSpeakerVolume toyCommandSetSpeakerVolume) {
        if (validateReady(toyCommandSetSpeakerVolume.getCommandIdentifier(), true, true)) {
            this.mTaskDispatcher.push(toyCommandSetSpeakerVolume.newTask(this, this.mActivePeripheral));
        }
    }

    public void onEventMainThread(ToyCommandStartCommandSequence toyCommandStartCommandSequence) {
        if (validateReady(toyCommandStartCommandSequence.getCommandIdentifier(), true, true, true)) {
            this.mTaskDispatcher.push(toyCommandStartCommandSequence.newTask(this, this.mActivePeripheral));
        }
    }

    public void onEventMainThread(ToyCommandTriggerSlotPlayback toyCommandTriggerSlotPlayback) {
        if (validateReady(toyCommandTriggerSlotPlayback.getCommandIdentifier(), true, true)) {
            this.mTaskDispatcher.push(toyCommandTriggerSlotPlayback.newTask(this, this.mActivePeripheral));
        }
    }

    private boolean validateReady(ToyCommandIdentifier toyCommandIdentifier, boolean requiresIdle, boolean requiresConnection) {
        return validateReady(toyCommandIdentifier, requiresIdle, requiresConnection, false);
    }

    private boolean validateReady(ToyCommandIdentifier toyCommandIdentifier, boolean requiresIdle, boolean requiresConnection, boolean isSafeToExecuteDuringAudioPlayback) {
        if (this.mState == ToyState.NOT_SUPPORTED) {
            postError(toyCommandIdentifier, getString(R.string.error_toy_ble_unsupported));
            return false;
        } else if (!this.mState.isOnline()) {
            postError(toyCommandIdentifier, getString(R.string.error_toy_ble_offline));
            return false;
        } else if (requiresIdle && this.mState.isInUse() && (!isSafeToExecuteDuringAudioPlayback || this.mState != ToyState.PLAYING_AUDIO)) {
            postError(toyCommandIdentifier, getString(R.string.error_toy_busy));
            return false;
        } else if (!requiresConnection || (this.mActivePeripheral != null && this.mState.isConnected())) {
            return true;
        } else {
            postError(toyCommandIdentifier, getString(R.string.error_toy_not_connected));
            return false;
        }
    }

    private void onDisconnected(boolean forget) {
        if (forget) {
            this.mScanner.forget(this.mActivePeripheral);
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                EventBus.getDefault().removeStickyEvent(ToyEventRequiresUpdate.class);
            }
        });
        setActivePeripheral(null);
        setState(ToyState.READY);
        this.mTaskDispatcher.clear();
        searchForToyOfInterest();
    }

    private boolean shouldDownloadAudio(ToyPeripheral peripheral, State oldState, State newState) {
        if (!peripheral.equals(this.mActivePeripheral) || this.mState != ToyState.RECORDING_AUDIO || newState != State.IDLE || oldState != State.AUDIO_RECORD || this.mToyOfInterest == null || !this.mToyOfInterest.shouldAcceptAudio()) {
            return false;
        }
        ToyEventRequiresUpdate toyEvent = (ToyEventRequiresUpdate) EventBus.getDefault().getStickyEvent(ToyEventRequiresUpdate.class);
        if (toyEvent == null || !peripheral.getIdentifier().equals(toyEvent.getToyIdentifier())) {
            return true;
        }
        return false;
    }

    private String getPeripheralDescription(ToyPeripheral peripheral) {
        return peripheral.getIdentifier() + "|" + peripheral.getDeviceAddress() + "|" + peripheral.getName();
    }
}
