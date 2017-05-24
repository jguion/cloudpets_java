package com.spiraltoys.cloudpets2.toy.task;

import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import com.parse.ParseAnalytics;
import com.spiraltoys.cloudpets2.free.R;
import java.util.HashMap;

class ToyTaskWriteData$3 implements Runnable {
    final /* synthetic */ ToyTaskWriteData this$0;

    ToyTaskWriteData$3(ToyTaskWriteData this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        Log.e(ToyTaskWriteData.access$300(), "Writing data hung. onCharacteristicWrite() has not been called for 10000 ms. Cancelling.");
        this.this$0.stopWriting();
        this.this$0.onCompleted(new Error(this.this$0.getPeripheral().getContext().getString(R.string.error_toy_gatt)));
        HashMap<String, String> dimensions = new HashMap();
        dimensions.put("BRAND", Build.BRAND);
        dimensions.put("DEVICE", Build.DEVICE);
        dimensions.put("MANUFACTURER", Build.MANUFACTURER);
        dimensions.put("PRODUCT", Build.PRODUCT);
        dimensions.put("MODEL", Build.MODEL);
        dimensions.put("RADIO", Build.getRadioVersion());
        dimensions.put("RELEASE", VERSION.RELEASE);
        ParseAnalytics.trackEventInBackground("BLEWriteDataHung", dimensions);
    }
}
