package com.spiraltoys.cloudpets2.toy;

import android.os.Handler;
import android.os.Looper;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandIdentifier;
import com.spiraltoys.cloudpets2.toy.task.ToyTask;
import com.spiraltoys.cloudpets2.toy.task.ToyTask.Listener;
import hugo.weaving.DebugLog;
import java.util.LinkedList;

public class ToyTaskDispatcher {
    private ToyTask mActiveTask = null;
    private Handler mHandler;
    private final Listener mInternalListener = new Listener() {
        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onStart(com.spiraltoys.cloudpets2.toy.task.ToyTask r4) {
            /*
            r3 = this;
            r1 = com.spiraltoys.cloudpets2.toy.ToyTaskDispatcher.this;
            monitor-enter(r1);
            r0 = com.spiraltoys.cloudpets2.toy.ToyTaskDispatcher.this;	 Catch:{ all -> 0x0025 }
            r0 = r0.mActiveTask;	 Catch:{ all -> 0x0025 }
            if (r4 == r0) goto L_0x000d;
        L_0x000b:
            monitor-exit(r1);	 Catch:{ all -> 0x0025 }
        L_0x000c:
            return;
        L_0x000d:
            r0 = com.spiraltoys.cloudpets2.toy.ToyTaskDispatcher.this;	 Catch:{ all -> 0x0025 }
            r0 = r0.mListener;	 Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x0023;
        L_0x0015:
            r0 = com.spiraltoys.cloudpets2.toy.ToyTaskDispatcher.this;	 Catch:{ all -> 0x0025 }
            r0 = r0.mHandler;	 Catch:{ all -> 0x0025 }
            r2 = new com.spiraltoys.cloudpets2.toy.ToyTaskDispatcher$1$1;	 Catch:{ all -> 0x0025 }
            r2.<init>(r3, r4);	 Catch:{ all -> 0x0025 }
            r0.post(r2);	 Catch:{ all -> 0x0025 }
        L_0x0023:
            monitor-exit(r1);	 Catch:{ all -> 0x0025 }
            goto L_0x000c;
        L_0x0025:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0025 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.spiraltoys.cloudpets2.toy.ToyTaskDispatcher.1.onStart(com.spiraltoys.cloudpets2.toy.task.ToyTask):void");
        }

        public void onSuccess(ToyTask task, ToyCommandIdentifier identifier, Object data) {
            synchronized (ToyTaskDispatcher.this) {
                if (task != ToyTaskDispatcher.this.mActiveTask) {
                    return;
                }
                ToyTaskDispatcher.this.mActiveTask.stop();
                if (ToyTaskDispatcher.this.mListener != null) {
                    ToyTaskDispatcher.this.mHandler.post(new 2(this, task, identifier, data));
                }
                ToyTaskDispatcher.this.startNextTask();
            }
        }

        public void onFailure(ToyTask task, ToyCommandIdentifier toyCommandIdentifier, Error error) {
            synchronized (ToyTaskDispatcher.this) {
                if (task != ToyTaskDispatcher.this.mActiveTask) {
                    return;
                }
                ToyTaskDispatcher.this.mActiveTask.stop();
                if (ToyTaskDispatcher.this.mListener != null) {
                    ToyTaskDispatcher.this.mHandler.post(new 3(this, task, toyCommandIdentifier, error));
                }
                ToyTaskDispatcher.this.startNextTask();
            }
        }
    };
    private Listener mListener = null;
    private LinkedList<ToyTask> mTaskQueue = new LinkedList();

    public ToyTaskDispatcher(Listener listener) {
        this.mListener = listener;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    @DebugLog
    public synchronized void push(ToyTask task) {
        this.mTaskQueue.add(task);
        if (this.mActiveTask == null) {
            startNextTask();
        }
    }

    @DebugLog
    public synchronized void addToFront(ToyTask task) {
        this.mTaskQueue.addFirst(task);
        if (this.mActiveTask == null) {
            startNextTask();
        }
    }

    public synchronized boolean isEmpty() {
        boolean z;
        z = this.mActiveTask == null && this.mTaskQueue.isEmpty();
        return z;
    }

    @DebugLog
    public synchronized void clear() {
        this.mTaskQueue.clear();
        if (this.mActiveTask != null) {
            this.mActiveTask.stop();
            this.mActiveTask = null;
        }
    }

    @DebugLog
    private synchronized void startNextTask() {
        if (this.mTaskQueue.isEmpty()) {
            this.mActiveTask = null;
        } else {
            this.mActiveTask = (ToyTask) this.mTaskQueue.pop();
            this.mActiveTask.start(this.mInternalListener);
        }
    }
}
