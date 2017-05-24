package com.spiraltoys.cloudpets2.unity;

import android.net.Uri;
import com.spiraltoys.cloudpets2.events.UnityCloseRequestedEvent;
import com.spiraltoys.cloudpets2.events.UnityLoadedEvent;
import com.spiraltoys.cloudpets2.toy.ToyAudioSlot;
import com.spiraltoys.cloudpets2.toy.ToyManager;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandIdentifier;
import com.spiraltoys.cloudpets2.toy.event.ToyEventCommandSucceeded;
import com.spiraltoys.cloudpets2.toy.event.ToyEventError;
import com.spiraltoys.cloudpets2.toy.event.ToyEventGameModeButtonPress;
import com.spiraltoys.cloudpets2.toy.event.ToyEventState;
import com.spiraltoys.cloudpets2.util.Utils;
import com.unity3d.player.UnityPlayer;
import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;
import java.io.File;
import timber.log.Timber;

public class UnityBridge {
    private static UnityBridge mInstance;
    private static ToyCommandIdentifier mPendingCommand;
    private ToyState mLastToyState = ToyState.UNKNOWN;

    private UnityBridge() {
    }

    public static synchronized UnityBridge getInstance() {
        UnityBridge unityBridge;
        synchronized (UnityBridge.class) {
            if (mInstance == null) {
                mInstance = new UnityBridge();
            }
            unityBridge = mInstance;
        }
        return unityBridge;
    }

    @DebugLog
    public static void writeAudioToSlot(String filePath, int slotNumber) {
        if (mPendingCommand != null) {
            Timber.e("skipping writeAudioToSlot(). Last command is not finished yet.", new Object[0]);
        } else if (getInstance().mLastToyState.isConnected()) {
            mPendingCommand = ToyManager.sendGameAudioToToy(Uri.fromFile(new File(filePath)), ToyAudioSlot.values()[slotNumber]);
        } else {
            notifyFailure();
        }
    }

    @DebugLog
    public static void playAudioFromSlot(int slotNumber) {
        if (mPendingCommand != null) {
            Timber.d("skipping playAudioFromSlot(). Last command is not finished yet.", new Object[0]);
        } else if (getInstance().mLastToyState.isConnected()) {
            mPendingCommand = ToyManager.triggerSlotPlayback(ToyAudioSlot.values()[slotNumber], Utils.getRelativeToyVolume(getUnityPlayerActivity()), true);
        } else {
            notifyFailure();
        }
    }

    @DebugLog
    public static void setGameMode(boolean isGameMode) {
        if (mPendingCommand != null) {
            Timber.d("skipping setGameMode(). Last command is not finished yet.", new Object[0]);
        } else if (getInstance().mLastToyState.isConnected()) {
            mPendingCommand = ToyManager.setGameModeAndStopPlaybackLoop(isGameMode);
        } else {
            notifyFailure();
        }
    }

    @DebugLog
    public static void onUnityReady() {
        EventBus.getDefault().post(new UnityLoadedEvent());
    }

    @DebugLog
    public static void initializeBridge() {
    }

    @DebugLog
    public static void dismissUnity() {
        EventBus.getDefault().post(new UnityCloseRequestedEvent());
    }

    public void onCreate() {
        getInstance().mLastToyState = ToyState.UNKNOWN;
        EventBus.getDefault().registerSticky(this);
    }

    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        mPendingCommand = null;
    }

    public void onEvent(ToyEventCommandSucceeded event) {
        if (event.getCommandIdentifier().equals(mPendingCommand)) {
            notifySuccess();
            mPendingCommand = null;
        }
    }

    public void onEvent(ToyEventGameModeButtonPress event) {
        switch (1.$SwitchMap$com$spiraltoys$cloudpets2$toy$event$ToyEventGameModeButtonPress$Button[event.getButton().ordinal()]) {
            case 1:
                notifyRedPawPressed();
                return;
            case 2:
                notifyBluePawPressed();
                return;
            default:
                return;
        }
    }

    public void onEvent(ToyEventState event) {
        if (!event.getState().isOnline()) {
            notifyBluetoothDisabled();
        } else if (event.getState().isConnected() && !this.mLastToyState.isConnected()) {
            notifyConnected();
        } else if ((!event.getState().isConnected() && this.mLastToyState.isConnected()) || !this.mLastToyState.isOnline()) {
            notifyConnecting();
        }
        this.mLastToyState = event.getState();
    }

    public void onEvent(ToyEventError event) {
        if (mPendingCommand != null) {
            mPendingCommand = null;
            notifyFailure();
        }
    }

    public static void startScene(String sceneName) {
        UnityPlayer.UnitySendMessage("Bridge", "StartScene", sceneName);
    }

    public static void notifySystemBackPressed() {
        UnityPlayer.UnitySendMessage("Bridge", "SystemBackPressed", "");
    }

    @DebugLog
    public static void notifyBluetoothDisabled() {
        UnityPlayer.UnitySendMessage("Bridge", "BluetoothDisabled", "");
    }

    @DebugLog
    public static void notifyBluetoothUnsupported() {
        UnityPlayer.UnitySendMessage("Bridge", "BluetoothUnsupported", "");
    }

    @DebugLog
    public static void notifyConnecting() {
        UnityPlayer.UnitySendMessage("Bridge", "Connecting", "");
    }

    @DebugLog
    public static void notifyConnected() {
        UnityPlayer.UnitySendMessage("Bridge", "Connected", "");
    }

    public static void notifyRedPawPressed() {
        UnityPlayer.UnitySendMessage("Bridge", "RedPawPressed", "");
    }

    public static void notifyBluePawPressed() {
        UnityPlayer.UnitySendMessage("Bridge", "BluePawPressed", "");
    }

    @DebugLog
    private static void notifySuccess() {
        UnityPlayer.UnitySendMessage("Bridge", "AsyncCallCompleted", "Success");
    }

    @DebugLog
    private static void notifyFailure() {
        UnityPlayer.UnitySendMessage("Bridge", "AsyncCallCompleted", "Failure");
    }

    private static UnityPlayerActivity getUnityPlayerActivity() {
        return (UnityPlayerActivity) UnityPlayer.currentActivity;
    }
}
