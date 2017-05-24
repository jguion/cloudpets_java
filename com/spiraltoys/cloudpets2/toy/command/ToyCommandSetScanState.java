package com.spiraltoys.cloudpets2.toy.command;

import android.content.Context;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;

public class ToyCommandSetScanState extends ToyCommand {
    private ScanState mScanState;

    public enum ScanState {
        SCANNING,
        PAUSED,
        STOPPED
    }

    public ToyCommandSetScanState(ScanState scanState) {
        this.mScanState = scanState;
    }

    public ScanState getScanState() {
        return this.mScanState;
    }

    public ToyTask newTask(Context context, ToyPeripheral peripheral) {
        throw new UnsupportedOperationException("No toy task exists for ToyCommandSetScanState");
    }
}
