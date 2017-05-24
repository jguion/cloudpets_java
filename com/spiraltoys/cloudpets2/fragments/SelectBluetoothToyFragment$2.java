package com.spiraltoys.cloudpets2.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class SelectBluetoothToyFragment$2 implements OnClickListener {
    final /* synthetic */ SelectBluetoothToyFragment this$0;

    SelectBluetoothToyFragment$2(SelectBluetoothToyFragment this$0) {
        this.this$0 = this$0;
    }

    public void onClick(DialogInterface dialog, int which) {
        this.this$0.getActivity().onBackPressed();
    }
}
