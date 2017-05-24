package com.spiraltoys.cloudpets2.toy;

import com.spiraltoys.cloudpets2.toy.ToyTaskDispatcher.1;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandIdentifier;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;

class ToyTaskDispatcher$1$2 implements Runnable {
    final /* synthetic */ 1 this$1;
    final /* synthetic */ Object val$data;
    final /* synthetic */ ToyCommandIdentifier val$identifier;
    final /* synthetic */ ToyTask val$task;

    ToyTaskDispatcher$1$2(1 this$1, ToyTask toyTask, ToyCommandIdentifier toyCommandIdentifier, Object obj) {
        this.this$1 = this$1;
        this.val$task = toyTask;
        this.val$identifier = toyCommandIdentifier;
        this.val$data = obj;
    }

    public void run() {
        ToyTaskDispatcher.access$100(this.this$1.this$0).onSuccess(this.val$task, this.val$identifier, this.val$data);
    }
}
