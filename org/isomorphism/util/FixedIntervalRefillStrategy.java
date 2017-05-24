package org.isomorphism.util;

import com.google.common.base.Ticker;
import java.util.concurrent.TimeUnit;
import org.isomorphism.util.TokenBucket.RefillStrategy;

public class FixedIntervalRefillStrategy implements RefillStrategy {
    private long lastRefillTime = (-this.periodDurationInNanos);
    private long nextRefillTime = (-this.periodDurationInNanos);
    private final long numTokensPerPeriod;
    private final long periodDurationInNanos;
    private final Ticker ticker;

    public FixedIntervalRefillStrategy(Ticker ticker, long numTokensPerPeriod, long period, TimeUnit unit) {
        this.ticker = ticker;
        this.numTokensPerPeriod = numTokensPerPeriod;
        this.periodDurationInNanos = unit.toNanos(period);
    }

    public synchronized long refill() {
        long j = 0;
        synchronized (this) {
            long now = this.ticker.read();
            if (now >= this.nextRefillTime) {
                long numPeriods = Math.max(0, (now - this.lastRefillTime) / this.periodDurationInNanos);
                this.lastRefillTime += this.periodDurationInNanos * numPeriods;
                this.nextRefillTime = this.lastRefillTime + this.periodDurationInNanos;
                j = this.numTokensPerPeriod * numPeriods;
            }
        }
        return j;
    }

    public long getDurationUntilNextRefill(TimeUnit unit) {
        return unit.convert(Math.max(0, this.nextRefillTime - this.ticker.read()), TimeUnit.NANOSECONDS);
    }
}
