package com.bumptech.glide.disklrucache;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

final class DiskLruCache$Entry {
    File[] cleanFiles;
    private DiskLruCache$Editor currentEditor;
    File[] dirtyFiles;
    private final String key;
    private final long[] lengths;
    private boolean readable;
    private long sequenceNumber;
    final /* synthetic */ DiskLruCache this$0;

    private DiskLruCache$Entry(DiskLruCache diskLruCache, String key) {
        this.this$0 = diskLruCache;
        this.key = key;
        this.lengths = new long[DiskLruCache.access$1800(diskLruCache)];
        this.cleanFiles = new File[DiskLruCache.access$1800(diskLruCache)];
        this.dirtyFiles = new File[DiskLruCache.access$1800(diskLruCache)];
        StringBuilder fileBuilder = new StringBuilder(key).append('.');
        int truncateTo = fileBuilder.length();
        for (int i = 0; i < DiskLruCache.access$1800(diskLruCache); i++) {
            fileBuilder.append(i);
            this.cleanFiles[i] = new File(DiskLruCache.access$1900(diskLruCache), fileBuilder.toString());
            fileBuilder.append(".tmp");
            this.dirtyFiles[i] = new File(DiskLruCache.access$1900(diskLruCache), fileBuilder.toString());
            fileBuilder.setLength(truncateTo);
        }
    }

    public String getLengths() throws IOException {
        StringBuilder result = new StringBuilder();
        for (long size : this.lengths) {
            result.append(' ').append(size);
        }
        return result.toString();
    }

    private void setLengths(String[] strings) throws IOException {
        if (strings.length != DiskLruCache.access$1800(this.this$0)) {
            throw invalidLengths(strings);
        }
        int i = 0;
        while (i < strings.length) {
            try {
                this.lengths[i] = Long.parseLong(strings[i]);
                i++;
            } catch (NumberFormatException e) {
                throw invalidLengths(strings);
            }
        }
    }

    private IOException invalidLengths(String[] strings) throws IOException {
        throw new IOException("unexpected journal line: " + Arrays.toString(strings));
    }

    public File getCleanFile(int i) {
        return this.cleanFiles[i];
    }

    public File getDirtyFile(int i) {
        return this.dirtyFiles[i];
    }
}
