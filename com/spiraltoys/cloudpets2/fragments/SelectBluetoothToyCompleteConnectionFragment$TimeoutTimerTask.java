package com.spiraltoys.cloudpets2.fragments;

import java.util.TimerTask;

class SelectBluetoothToyCompleteConnectionFragment$TimeoutTimerTask extends TimerTask {
    final /* synthetic */ SelectBluetoothToyCompleteConnectionFragment this$0;

    private SelectBluetoothToyCompleteConnectionFragment$TimeoutTimerTask(SelectBluetoothToyCompleteConnectionFragment selectBluetoothToyCompleteConnectionFragment) {
        this.this$0 = selectBluetoothToyCompleteConnectionFragment;
    }

    public void run() {
        if (SelectBluetoothToyCompleteConnectionFragment.access$100(this.this$0) != null) {
            SelectBluetoothToyCompleteConnectionFragment.access$100(this.this$0).onToyConnectionTimeout();
        }
    }
}
