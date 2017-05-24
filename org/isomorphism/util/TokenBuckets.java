package org.isomorphism.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Ticker;
import com.google.common.util.concurrent.Uninterruptibles;
import java.util.concurrent.TimeUnit;
import org.isomorphism.util.TokenBucket.RefillStrategy;
import org.isomorphism.util.TokenBucket.SleepStrategy;

public final class TokenBuckets {
    private static final SleepStrategy BUSY_WAIT_SLEEP_STRATEGY = new SleepStrategy() {
        public void sleep() {
        }
    };
    private static final SleepStrategy YIELDING_SLEEP_STRATEGY = new SleepStrategy() {
        public void sleep() {
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.NANOSECONDS);
        }
    };

    public static class Builder {
        private Long capacity = null;
        private long initialTokens = 0;
        private RefillStrategy refillStrategy = null;
        private SleepStrategy sleepStrategy = TokenBuckets.YIELDING_SLEEP_STRATEGY;
        private final Ticker ticker = Ticker.systemTicker();

        public Builder withCapacity(long numTokens) {
            Preconditions.checkArgument(numTokens > 0, "Must specify a positive number of tokens");
            this.capacity = Long.valueOf(numTokens);
            return this;
        }

        public Builder withInitialTokens(long numTokens) {
            Preconditions.checkArgument(numTokens > 0, "Must specify a positive number of tokens");
            this.initialTokens = numTokens;
            return this;
        }

        public Builder withFixedIntervalRefillStrategy(long refillTokens, long period, TimeUnit unit) {
            return withRefillStrategy(new FixedIntervalRefillStrategy(this.ticker, refillTokens, period, unit));
        }

        public Builder withRefillStrategy(RefillStrategy refillStrategy) {
            this.refillStrategy = (RefillStrategy) Preconditions.checkNotNull(refillStrategy);
            return this;
        }

        public Builder withYieldingSleepStrategy() {
            return withSleepStrategy(TokenBuckets.YIELDING_SLEEP_STRATEGY);
        }

        public Builder withBusyWaitSleepStrategy() {
            return withSleepStrategy(TokenBuckets.BUSY_WAIT_SLEEP_STRATEGY);
        }

        public Builder withSleepStrategy(SleepStrategy sleepStrategy) {
            this.sleepStrategy = (SleepStrategy) Preconditions.checkNotNull(sleepStrategy);
            return this;
        }

        public TokenBucket build() {
            Preconditions.checkNotNull(this.capacity, "Must specify a capacity");
            Preconditions.checkNotNull(this.refillStrategy, "Must specify a refill strategy");
            return new TokenBucketImpl(this.capacity.longValue(), this.initialTokens, this.refillStrategy, this.sleepStrategy);
        }
    }

    private TokenBuckets() {
    }

    public int hashCode() {
        return super.hashCode();
    }

    public static Builder builder() {
        return new Builder();
    }
}
