package com.spiraltoys.cloudpets2.toy.command;

import android.content.Context;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.ToyService;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskReceiveAudio;
import com.spiraltoys.cloudpets2.toy.task.ToyTaskStartLoopPlayback;

import java.io.File;
import java.util.UUID;

public class ToyCommandStartLoopPlayback extends ToyCommand {
    private static final int MAX_AUDIO_LENGTH_MS = 65535;
    private final int mAudioLengthMs;
    private final short mNumberOfLoops;

    public ToyCommandStartLoopPlayback(short numberOfLoops, int audioLengthMs) {
        if (audioLengthMs > 65535) {
            throw new IllegalArgumentException("Audio length must be less than 65535");
        }
        this.mNumberOfLoops = numberOfLoops;
        this.mAudioLengthMs = audioLengthMs;
    }

    public short getNumberOfLoops() {
        return this.mNumberOfLoops;
    }

    public int getAudioLengthMs() {
        return this.mAudioLengthMs;
    }

    public ToyTask newTask(Context context, ToyPeripheral peripheral) {
        return new ToyTaskReceiveAudio(peripheral, context.getCacheDir() + File.separator + ToyService.TOY_RECORDINGS_FOLDER_NAME + File.separator + UUID.randomUUID() + ".wmv");
    }
}
