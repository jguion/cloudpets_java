package com.bumptech.glide.disklrucache;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public final class DiskLruCache$Value {
    private final File[] files;
    private final String key;
    private final long[] lengths;
    private final long sequenceNumber;
    final /* synthetic */ DiskLruCache this$0;

    private DiskLruCache$Value(DiskLruCache diskLruCache, String key, long sequenceNumber, File[] files, long[] lengths) {
        this.this$0 = diskLruCache;
        this.key = key;
        this.sequenceNumber = sequenceNumber;
        this.files = files;
        this.lengths = lengths;
    }

    public DiskLruCache$Editor edit() throws IOException {
        return DiskLruCache.access$1600(this.this$0, this.key, this.sequenceNumber);
    }

    public File getFile(int index) {
        return this.files[index];
    }

    public String getString(int index) throws IOException {
        return DiskLruCache.access$1700(new FileInputStream(this.files[index]));
    }

    public long getLength(int index) {
        return this.lengths[index];
    }
}
