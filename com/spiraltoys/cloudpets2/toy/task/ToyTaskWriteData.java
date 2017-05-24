package com.spiraltoys.cloudpets2.toy.task;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.isomorphism.util.TokenBucket;
import org.isomorphism.util.TokenBuckets;

public abstract class ToyTaskWriteData extends ToyTask {
    private static final long CHARACTERISTIC_WRITE_TIMEOUT_MS = 10000;
    private static final Boolean LOGGING = Boolean.valueOf(false);
    private static final long MINIMUM_TIME_BETWEEN_WRITES_MS = 14;
    private static final long RETRY_DELAY = 100;
    private static final String TAG = ToyTaskWriteData.class.getSimpleName();
    private static final int WRITES_PER_REQUIRED_RESPONSE = 128;
    private final UUID mCharacteristic;
    private final LinkedList<byte[]> mDataQueue;
    private Handler mHandler;
    private long mNumWrites;
    final Runnable mTimeoutRunnable = new 3(this);
    private TokenBucket mTokenBucket;
    private long mWriteCycleStartTime;
    final Runnable mWriteRunnable = new 2(this);

    abstract void onCompleted(Error error);

    abstract void onProgress(int i);

    public ToyTaskWriteData(ToyPeripheral peripheral, UUID characteristic) throws NullPointerException {
        super(peripheral);
        if (characteristic == null) {
            throw new NullPointerException();
        }
        this.mCharacteristic = characteristic;
        this.mDataQueue = new LinkedList();
        this.mHandler = null;
        this.mTokenBucket = TokenBuckets.builder().withCapacity(1).withFixedIntervalRefillStrategy(1, MINIMUM_TIME_BETWEEN_WRITES_MS, TimeUnit.MILLISECONDS).withSleepStrategy(new 1(this)).build();
    }

    public void stop() {
        super.stop();
        stopWriting();
    }

    void addData(byte[] data) {
        this.mDataQueue.add(data);
    }

    boolean hasDataRemaining() {
        return !this.mDataQueue.isEmpty();
    }

    void startWriting() {
        synchronized (this) {
            if (this.mHandler == null) {
                this.mHandler = new Handler(Looper.getMainLooper());
                write(true);
            }
        }
    }

    private void write(boolean delayed) {
        if (this.mHandler == null) {
            return;
        }
        if (delayed) {
            this.mHandler.postDelayed(this.mWriteRunnable, RETRY_DELAY);
        } else {
            this.mWriteRunnable.run();
        }
    }

    void stopWriting() {
        synchronized (this) {
            if (this.mHandler != null) {
                this.mHandler.removeCallbacksAndMessages(null);
                this.mHandler = null;
            }
        }
    }

    public void onCharacteristicWrite(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
        super.onCharacteristicWrite(peripheral, characteristic, value, error);
        if (characteristic.equals(this.mCharacteristic)) {
            if (this.mHandler != null) {
                this.mHandler.removeCallbacks(this.mTimeoutRunnable);
            }
            if (error != null) {
                Log.e(TAG, "Writing chunk failed with " + this.mDataQueue.size() + " remaining...");
                stopWriting();
                onCompleted(error);
                return;
            }
            if (LOGGING.booleanValue()) {
                Log.v(TAG, "Writing chunk succeeded with " + this.mDataQueue.size() + " remaining...");
            }
            this.mTokenBucket.consume();
            if (this.mDataQueue.isEmpty()) {
                stopWriting();
                onCompleted(null);
                return;
            }
            write(false);
        }
    }
}
