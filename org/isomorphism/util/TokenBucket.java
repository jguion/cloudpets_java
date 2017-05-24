package org.isomorphism.util;

import java.util.concurrent.TimeUnit;

public interface TokenBucket {

    public interface RefillStrategy {
        long getDurationUntilNextRefill(TimeUnit timeUnit) throws UnsupportedOperationException;

        long refill();
    }

    public interface SleepStrategy {
        void sleep();
    }

    void consume();

    void consume(long j);

    long getCapacity();

    long getDurationUntilNextRefill(TimeUnit timeUnit) throws UnsupportedOperationException;

    long getNumTokens();

    boolean tryConsume();

    boolean tryConsume(long j);
}
