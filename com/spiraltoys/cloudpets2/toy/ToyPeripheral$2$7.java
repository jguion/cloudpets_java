package com.spiraltoys.cloudpets2.toy;

import com.spiraltoys.cloudpets2.toy.ToyPeripheral.2;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral.Listener;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral.State;
import java.util.ArrayList;
import java.util.Iterator;

class ToyPeripheral$2$7 implements Runnable {
    final /* synthetic */ 2 this$1;
    final /* synthetic */ State val$newState;
    final /* synthetic */ State val$oldState;
    final /* synthetic */ ToyPeripheral val$peripheral;

    ToyPeripheral$2$7(2 this$1, ToyPeripheral toyPeripheral, State state, State state2) {
        this.this$1 = this$1;
        this.val$peripheral = toyPeripheral;
        this.val$oldState = state;
        this.val$newState = state2;
    }

    public void run() {
        Iterator it = new ArrayList(ToyPeripheral.access$700(this.this$1.this$0)).iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).onToyStateChange(this.val$peripheral, this.val$oldState, this.val$newState);
        }
    }
}
