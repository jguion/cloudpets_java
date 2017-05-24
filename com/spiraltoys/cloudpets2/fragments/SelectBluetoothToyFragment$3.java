package com.spiraltoys.cloudpets2.fragments;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class SelectBluetoothToyFragment$3 implements OnClickListener {
    final /* synthetic */ SelectBluetoothToyFragment this$0;

    SelectBluetoothToyFragment$3(SelectBluetoothToyFragment this$0) {
        this.this$0 = this$0;
    }

    public void onClick(DialogInterface dialog, int which) {
        BluetoothAdapter.getDefaultAdapter().enable();
    }
}
