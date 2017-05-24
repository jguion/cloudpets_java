package org.isomorphism.util;

import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import org.isomorphism.util.TokenBucket.RefillStrategy;
import org.isomorphism.util.TokenBucket.SleepStrategy;

class TokenBucketImpl implements TokenBucket {
    private final long capacity;
    private final RefillStrategy refillStrategy;
    private long size;
    private final SleepStrategy sleepStrategy;

    TokenBucketImpl(long capacity, long initialTokens, RefillStrategy refillStrategy, SleepStrategy sleepStrategy) {
        boolean z = true;
        Preconditions.checkArgument(capacity > 0);
        if (initialTokens > capacity) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.capacity = capacity;
        this.refillStrategy = (RefillStrategy) Preconditions.checkNotNull(refillStrategy);
        this.sleepStrategy = (SleepStrategy) Preconditions.checkNotNull(sleepStrategy);
        this.size = initialTokens;
    }

    public long getCapacity() {
        return this.capacity;
    }

    public synchronized long getNumTokens() {
        refill();
        return this.size;
    }

    public long getDurationUntilNextRefill(TimeUnit unit) throws UnsupportedOperationException {
        return this.refillStrategy.getDurationUntilNextRefill(unit);
    }

    public boolean tryConsume() {
        return tryConsume(1);
    }

    public synchronized boolean tryConsume(long numTokens) {
        boolean z = true;
        synchronized (this) {
            boolean z2;
            Preconditions.checkArgument(numTokens > 0, "Number of tokens to consume must be positive");
            if (numTokens <= this.capacity) {
                z2 = true;
            } else {
                z2 = false;
            }
            Preconditions.checkArgument(z2, "Number of tokens to consume must be less than the capacity of the bucket.");
            refill();
            if (numTokens <= this.size) {
                this.size -= numTokens;
            } else {
                z = false;
            }
        }
        return z;
    }

    public void consume() {
        consume(1);
    }

    public void consume(long numTokens) {
        while (!tryConsume(numTokens)) {
            this.sleepStrategy.sleep();
        }
    }

    private synchronized void refill() {
        this.size = Math.max(0, Math.min(this.size + Math.min(this.capacity, Math.max(0, this.refillStrategy.refill())), this.capacity));
    }
}
