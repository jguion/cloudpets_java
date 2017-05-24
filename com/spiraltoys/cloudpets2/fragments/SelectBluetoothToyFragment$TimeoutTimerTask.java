package com.spiraltoys.cloudpets2.fragments;

import java.util.TimerTask;

class SelectBluetoothToyFragment$TimeoutTimerTask extends TimerTask {
    final /* synthetic */ SelectBluetoothToyFragment this$0;

    private SelectBluetoothToyFragment$TimeoutTimerTask(SelectBluetoothToyFragment selectBluetoothToyFragment) {
        this.this$0 = selectBluetoothToyFragment;
    }

    public void run() {
        if (SelectBluetoothToyFragment.access$200(this.this$0) == null) {
            return;
        }
        if (SelectBluetoothToyFragment.access$300(this.this$0) >= SelectBluetoothToyFragment.access$400(this.this$0).size()) {
            SelectBluetoothToyFragment.access$200(this.this$0).onToyScanTimeout(SelectBluetoothToyFragment.access$400(this.this$0).size());
        } else {
            SelectBluetoothToyFragment.access$200(this.this$0).onToyConnectionTimeout();
        }
    }
}
