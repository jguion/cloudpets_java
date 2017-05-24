package com.spiraltoys.cloudpets2.toy;

import com.spiraltoys.cloudpets2.toy.ToyTaskDispatcher.1;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandIdentifier;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;

class ToyTaskDispatcher$1$3 implements Runnable {
    final /* synthetic */ 1 this$1;
    final /* synthetic */ Error val$error;
    final /* synthetic */ ToyTask val$task;
    final /* synthetic */ ToyCommandIdentifier val$toyCommandIdentifier;

    ToyTaskDispatcher$1$3(1 this$1, ToyTask toyTask, ToyCommandIdentifier toyCommandIdentifier, Error error) {
        this.this$1 = this$1;
        this.val$task = toyTask;
        this.val$toyCommandIdentifier = toyCommandIdentifier;
        this.val$error = error;
    }

    public void run() {
        ToyTaskDispatcher.access$100(this.this$1.this$0).onFailure(this.val$task, this.val$toyCommandIdentifier, this.val$error);
    }
}
