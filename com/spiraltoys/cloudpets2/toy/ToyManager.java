package com.spiraltoys.cloudpets2.toy;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import com.spiraltoys.cloudpets2.toy.command.ToyCommand;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandConnect;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandDisconnect;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandIdentifier;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSendAudio;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetGameModeState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetIdentifier;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetLedState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetScanState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetScanState.ScanState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSetSpeakerVolume;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandStartCommandSequence;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandStartLoopPlayback;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandTriggerSlotPlayback;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandUpdateFirmware;
import com.spiraltoys.cloudpets2.toy.event.ToyEventState;
import de.greenrobot.event.EventBus;
import java.io.IOException;
import java.util.ArrayList;

public class ToyManager {
    private static final int LED_BLINK_INTERVAL_MS = 500;

    private ToyManager() {
    }

    public static ToyState getState() {
        ToyEventState toyEventState = (ToyEventState) EventBus.getDefault().getStickyEvent(ToyEventState.class);
        if (toyEventState != null) {
            return toyEventState.getState();
        }
        return ToyState.UNKNOWN;
    }

    private static ToyCommandIdentifier postToyCommand(ToyCommand toyCommand) {
        EventBus.getDefault().post(toyCommand);
        return toyCommand.getCommandIdentifier();
    }

    public static ToyCommandIdentifier startScan() {
        return postToyCommand(new ToyCommandSetScanState(ScanState.SCANNING));
    }

    public static ToyCommandIdentifier stopScan() {
        return postToyCommand(new ToyCommandSetScanState(ScanState.STOPPED));
    }

    public static ToyCommandIdentifier pauseScan() {
        return postToyCommand(new ToyCommandSetScanState(ScanState.PAUSED));
    }

    public static ToyCommandIdentifier connectToToy(String identifier, String address, boolean acceptAudio) {
        return postToyCommand(new ToyCommandConnect(identifier, address, acceptAudio));
    }

    public static ToyCommandIdentifier disconnectFromToy() {
        return postToyCommand(new ToyCommandDisconnect(true));
    }

    public static ToyCommandIdentifier disconnectFromToyEventually() {
        return postToyCommand(new ToyCommandDisconnect(false));
    }

    public static ToyCommandIdentifier exitGameModeAndStopPlaybackLoop() {
        return setGameModeAndStopPlaybackLoop(false);
    }

    public static ToyCommandIdentifier setGameModeAndStopPlaybackLoop(boolean isGameModeOn) {
        ArrayList<ToyCommand> commandSequence = new ArrayList();
        commandSequence.add(new ToyCommandStartLoopPlayback((short) 1, 1));
        commandSequence.add(new ToyCommandSetGameModeState(isGameModeOn));
        return postToyCommand(new ToyCommandStartCommandSequence(commandSequence));
    }

    public static ToyCommandIdentifier sendGameAudioToToy(Uri localAudioUri, ToyAudioSlot slot) {
        ArrayList<ToyCommand> commandSequence = new ArrayList();
        commandSequence.add(new ToyCommandSetGameModeState(true));
        commandSequence.add(new ToyCommandStartLoopPlayback((short) 1, 1));
        commandSequence.add(new ToyCommandSendAudio(localAudioUri, slot));
        return postToyCommand(new ToyCommandStartCommandSequence(commandSequence));
    }

    public static ToyCommandIdentifier sendAudioToToy(Uri localAudioUri, ToyAudioSlot slot) {
        ArrayList<ToyCommand> commandSequence = new ArrayList();
        commandSequence.add(new ToyCommandStartLoopPlayback((short) 1, 1));
        commandSequence.add(new ToyCommandSendAudio(localAudioUri, slot));
        commandSequence.add(new ToyCommandSetGameModeState(false));
        return postToyCommand(new ToyCommandStartCommandSequence(commandSequence));
    }

    public static ToyCommandIdentifier sendAudioToToyAndBlinkLed(Uri localAudioUri, ToyAudioSlot slot) {
        ArrayList<ToyCommand> commandSequence = new ArrayList();
        commandSequence.add(new ToyCommandStartLoopPlayback((short) 1, 1));
        commandSequence.add(new ToyCommandSendAudio(localAudioUri, slot));
        commandSequence.add(new ToyCommandSetGameModeState(false));
        commandSequence.add(new ToyCommandSetLedState(ToyLedState.BLINKING, 500));
        return postToyCommand(new ToyCommandStartCommandSequence(commandSequence));
    }

    public static ToyCommandIdentifier setLedState(ToyLedState state, int period) {
        return postToyCommand(new ToyCommandSetLedState(state, period));
    }

    public static ToyCommandIdentifier setIdentifier(String identifier) {
        return postToyCommand(new ToyCommandSetIdentifier(identifier));
    }

    public static ToyCommandIdentifier setGameModeState(boolean isGameModeOn) {
        return postToyCommand(new ToyCommandSetGameModeState(isGameModeOn));
    }

    public static ToyCommandIdentifier triggerSlotPlayback(ToyAudioSlot slot, double volume) {
        return triggerSlotPlayback(slot, volume, false);
    }

    public static ToyCommandIdentifier triggerSlotPlayback(ToyAudioSlot slot, double volume, boolean isSuccessDelayedUntilPlaybackCompletion) {
        ArrayList<ToyCommand> commandSequence = new ArrayList();
        commandSequence.add(new ToyCommandSetSpeakerVolume((byte) ((int) Math.min(Math.floor(volume * 255.0d), 255.0d))));
        commandSequence.add(new ToyCommandTriggerSlotPlayback(slot, isSuccessDelayedUntilPlaybackCompletion));
        return postToyCommand(new ToyCommandStartCommandSequence(commandSequence));
    }

    public static ToyCommandIdentifier startLullaby(Context context, Uri lullabyAudioUri, double speakerVolume, long playbackDuration) throws IOException {
        MediaPlayer mp = new MediaPlayer();
        try {
            mp.setDataSource(context, lullabyAudioUri);
            mp.prepare();
            int audioFileDurationMs = mp.getDuration();
            ArrayList<ToyCommand> commandSequence = new ArrayList();
            commandSequence.add(new ToyCommandSetGameModeState(true));
            commandSequence.add(new ToyCommandStartLoopPlayback((short) 1, 1));
            commandSequence.add(new ToyCommandSendAudio(lullabyAudioUri, ToyAudioSlot.UPLOAD_1));
            commandSequence.add(new ToyCommandSetSpeakerVolume((byte) ((int) Math.floor(255.0d * speakerVolume))));
            commandSequence.add(new ToyCommandStartLoopPlayback((short) Math.round(((float) playbackDuration) / ((float) audioFileDurationMs)), audioFileDurationMs));
            return postToyCommand(new ToyCommandStartCommandSequence(commandSequence));
        } finally {
            mp.release();
        }
    }

    public static ToyCommandIdentifier startLoopPlayback(short numberOfLoops, int audioLengthMs) {
        return postToyCommand(new ToyCommandStartLoopPlayback(numberOfLoops, audioLengthMs));
    }

    public static ToyCommandIdentifier setSpeakerVolume(double speakerVolume) {
        return postToyCommand(new ToyCommandSetSpeakerVolume((byte) ((int) Math.floor(255.0d * speakerVolume))));
    }

    public static ToyCommandIdentifier updateFirmware() {
        return postToyCommand(new ToyCommandUpdateFirmware());
    }
}
