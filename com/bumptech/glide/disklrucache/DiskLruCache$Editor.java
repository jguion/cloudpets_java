package com.bumptech.glide.disklrucache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public final class DiskLruCache$Editor {
    private boolean committed;
    private final DiskLruCache$Entry entry;
    final /* synthetic */ DiskLruCache this$0;
    private final boolean[] written;

    private DiskLruCache$Editor(DiskLruCache diskLruCache, DiskLruCache$Entry entry) {
        this.this$0 = diskLruCache;
        this.entry = entry;
        this.written = entry.readable ? null : new boolean[DiskLruCache.access$1800(diskLruCache)];
    }

    private InputStream newInputStream(int index) throws IOException {
        synchronized (this.this$0) {
            if (this.entry.currentEditor != this) {
                throw new IllegalStateException();
            } else if (this.entry.readable) {
                try {
                    InputStream fileInputStream = new FileInputStream(this.entry.getCleanFile(index));
                    return fileInputStream;
                } catch (FileNotFoundException e) {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    public String getString(int index) throws IOException {
        InputStream in = newInputStream(index);
        return in != null ? DiskLruCache.access$1700(in) : null;
    }

    public File getFile(int index) throws IOException {
        File dirtyFile;
        synchronized (this.this$0) {
            if (this.entry.currentEditor != this) {
                throw new IllegalStateException();
            }
            if (!this.entry.readable) {
                this.written[index] = true;
            }
            dirtyFile = this.entry.getDirtyFile(index);
            if (!DiskLruCache.access$1900(this.this$0).exists()) {
                DiskLruCache.access$1900(this.this$0).mkdirs();
            }
        }
        return dirtyFile;
    }

    public void set(int index, String value) throws IOException {
        Throwable th;
        Writer writer = null;
        try {
            Writer writer2 = new OutputStreamWriter(new FileOutputStream(getFile(index)), Util.UTF_8);
            try {
                writer2.write(value);
                Util.closeQuietly(writer2);
            } catch (Throwable th2) {
                th = th2;
                writer = writer2;
                Util.closeQuietly(writer);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            Util.closeQuietly(writer);
            throw th;
        }
    }

    public void commit() throws IOException {
        DiskLruCache.access$2000(this.this$0, this, true);
        this.committed = true;
    }

    public void abort() throws IOException {
        DiskLruCache.access$2000(this.this$0, this, false);
    }

    public void abortUnlessCommitted() {
        if (!this.committed) {
            try {
                abort();
            } catch (IOException e) {
            }
        }
    }
}
