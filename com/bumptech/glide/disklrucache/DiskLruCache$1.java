package com.bumptech.glide.disklrucache;

import java.util.concurrent.Callable;

class DiskLruCache$1 implements Callable<Void> {
    final /* synthetic */ DiskLruCache this$0;

    DiskLruCache$1(DiskLruCache diskLruCache) {
        this.this$0 = diskLruCache;
    }

    public Void call() throws Exception {
        synchronized (this.this$0) {
            if (DiskLruCache.access$000(this.this$0) == null) {
            } else {
                DiskLruCache.access$100(this.this$0);
                if (DiskLruCache.access$200(this.this$0)) {
                    DiskLruCache.access$300(this.this$0);
                    DiskLruCache.access$402(this.this$0, 0);
                }
            }
        }
        return null;
    }
}
