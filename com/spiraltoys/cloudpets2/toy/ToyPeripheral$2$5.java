package com.spiraltoys.cloudpets2.toy;

import com.spiraltoys.cloudpets2.toy.ToyPeripheral.2;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral.Listener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

class ToyPeripheral$2$5 implements Runnable {
    final /* synthetic */ 2 this$1;
    final /* synthetic */ UUID val$characteristic;
    final /* synthetic */ ToyPeripheral val$peripheral;
    final /* synthetic */ byte[] val$value;

    ToyPeripheral$2$5(2 this$1, ToyPeripheral toyPeripheral, UUID uuid, byte[] bArr) {
        this.this$1 = this$1;
        this.val$peripheral = toyPeripheral;
        this.val$characteristic = uuid;
        this.val$value = bArr;
    }

    public void run() {
        Iterator it = new ArrayList(ToyPeripheral.access$700(this.this$1.this$0)).iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).onCharacteristicChanged(this.val$peripheral, this.val$characteristic, this.val$value);
        }
    }
}
