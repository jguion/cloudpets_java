package com.spiraltoys.cloudpets2.toy;

import com.spiraltoys.cloudpets2.toy.ToyPeripheral.2;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral.Listener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

class ToyPeripheral$2$6 implements Runnable {
    final /* synthetic */ 2 this$1;
    final /* synthetic */ UUID val$characteristic;
    final /* synthetic */ Error val$error;
    final /* synthetic */ ToyPeripheral val$peripheral;

    ToyPeripheral$2$6(2 this$1, ToyPeripheral toyPeripheral, UUID uuid, Error error) {
        this.this$1 = this$1;
        this.val$peripheral = toyPeripheral;
        this.val$characteristic = uuid;
        this.val$error = error;
    }

    public void run() {
        Iterator it = new ArrayList(ToyPeripheral.access$700(this.this$1.this$0)).iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).onCharacteristicSetNotify(this.val$peripheral, this.val$characteristic, this.val$error);
        }
    }
}
