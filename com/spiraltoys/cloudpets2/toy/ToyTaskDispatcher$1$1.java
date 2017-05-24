package com.spiraltoys.cloudpets2.toy;

import com.spiraltoys.cloudpets2.toy.ToyTaskDispatcher.1;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;

class ToyTaskDispatcher$1$1 implements Runnable {
    final /* synthetic */ 1 this$1;
    final /* synthetic */ ToyTask val$task;

    ToyTaskDispatcher$1$1(1 this$1, ToyTask toyTask) {
        this.this$1 = this$1;
        this.val$task = toyTask;
    }

    public void run() {
        ToyTaskDispatcher.access$100(this.this$1.this$0).onStart(this.val$task);
    }
}
