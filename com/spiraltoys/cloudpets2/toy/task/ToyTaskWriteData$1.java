package com.spiraltoys.cloudpets2.toy.task;

import com.google.common.util.concurrent.Uninterruptibles;
import java.util.concurrent.TimeUnit;
import org.isomorphism.util.TokenBucket.SleepStrategy;

class ToyTaskWriteData$1 implements SleepStrategy {
    final /* synthetic */ ToyTaskWriteData this$0;

    ToyTaskWriteData$1(ToyTaskWriteData this$0) {
        this.this$0 = this$0;
    }

    public void sleep() {
        Uninterruptibles.sleepUninterruptibly(7, TimeUnit.MILLISECONDS);
    }
}
