package com.spiraltoys.cloudpets2.toy.task;

import android.util.Log;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import java.util.UUID;

class ToyTaskWriteData$2 implements Runnable {
    final /* synthetic */ ToyTaskWriteData this$0;

    ToyTaskWriteData$2(ToyTaskWriteData this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        boolean z = true;
        synchronized (ToyTaskWriteData.access$000(this.this$0)) {
            if (!ToyTaskWriteData.access$000(this.this$0).isEmpty()) {
                boolean isResponseRequired;
                byte[] chunk = (byte[]) ToyTaskWriteData.access$000(this.this$0).getFirst();
                boolean isLastChunk;
                if (ToyTaskWriteData.access$000(this.this$0).size() == 1) {
                    isLastChunk = true;
                } else {
                    isLastChunk = false;
                }
                if (ToyTaskWriteData.access$108(this.this$0) % 128 == 0 || isLastChunk) {
                    isResponseRequired = true;
                } else {
                    isResponseRequired = false;
                }
                if (ToyTaskWriteData.access$200().booleanValue()) {
                    Log.v(ToyTaskWriteData.access$300(), "Write cycle took: " + (System.currentTimeMillis() - ToyTaskWriteData.access$400(this.this$0)));
                    ToyTaskWriteData.access$402(this.this$0, System.currentTimeMillis());
                }
                ToyPeripheral peripheral = this.this$0.getPeripheral();
                UUID access$500 = ToyTaskWriteData.access$500(this.this$0);
                if (isResponseRequired) {
                    z = false;
                }
                if (peripheral.writeCharacteristic(access$500, chunk, z)) {
                    ToyTaskWriteData.access$600(this.this$0).postDelayed(this.this$0.mTimeoutRunnable, 10000);
                    ToyTaskWriteData.access$000(this.this$0).removeFirst();
                } else {
                    Log.w(ToyTaskWriteData.access$300(), "Writing chunk busy with " + ToyTaskWriteData.access$000(this.this$0).size() + " remaining...");
                    ToyTaskWriteData.access$700(this.this$0, true);
                }
            }
        }
        this.this$0.onProgress(ToyTaskWriteData.access$000(this.this$0).size());
    }
}
