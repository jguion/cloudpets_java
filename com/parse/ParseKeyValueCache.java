package com.parse;

import android.content.Context;
import com.bumptech.glide.load.Key;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

class ParseKeyValueCache {
    static final int DEFAULT_MAX_KEY_VALUE_CACHE_BYTES = 2097152;
    static final int DEFAULT_MAX_KEY_VALUE_CACHE_FILES = 1000;
    private static final String DIR_NAME = "ParseKeyValueCache";
    private static final Object MUTEX_IO = new Object();
    private static final String TAG = "ParseKeyValueCache";
    private static File directory;
    static int maxKeyValueCacheBytes = 2097152;
    static int maxKeyValueCacheFiles = 1000;

    ParseKeyValueCache() {
    }

    static void initialize(Context context) {
        initialize(new File(context.getCacheDir(), "ParseKeyValueCache"));
    }

    static void initialize(File path) {
        if (path.isDirectory() || path.mkdir()) {
            directory = path;
            return;
        }
        throw new RuntimeException("Could not create ParseKeyValueCache directory");
    }

    private static File getKeyValueCacheDir() {
        if (!(directory == null || directory.exists())) {
            directory.mkdir();
        }
        return directory;
    }

    static int size() {
        File[] files = getKeyValueCacheDir().listFiles();
        if (files == null) {
            return 0;
        }
        return files.length;
    }

    private static File getKeyValueCacheFile(String key) {
        final String suffix = '.' + key;
        File[] matches = getKeyValueCacheDir().listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.endsWith(suffix);
            }
        });
        return (matches == null || matches.length == 0) ? null : matches[0];
    }

    private static long getKeyValueCacheAge(File cacheFile) {
        String name = cacheFile.getName();
        try {
            return Long.parseLong(name.substring(0, name.indexOf(46)));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static File createKeyValueCacheFile(String key) {
        return new File(getKeyValueCacheDir(), String.valueOf(new Date().getTime()) + '.' + key);
    }

    static void clearKeyValueCacheDir() {
        synchronized (MUTEX_IO) {
            File dir = getKeyValueCacheDir();
            if (dir == null) {
                return;
            }
            File[] entries = dir.listFiles();
            if (entries == null) {
                return;
            }
            for (File entry : entries) {
                entry.delete();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void saveToKeyValueCache(java.lang.String r14, java.lang.String r15) {
        /*
        r6 = 0;
        r8 = MUTEX_IO;
        monitor-enter(r8);
        r5 = getKeyValueCacheFile(r14);	 Catch:{ all -> 0x0045 }
        if (r5 == 0) goto L_0x000d;
    L_0x000a:
        r5.delete();	 Catch:{ all -> 0x0045 }
    L_0x000d:
        r0 = createKeyValueCacheFile(r14);	 Catch:{ all -> 0x0045 }
        r7 = "UTF-8";
        r7 = r15.getBytes(r7);	 Catch:{ UnsupportedEncodingException -> 0x0070, IOException -> 0x006e }
        com.parse.ParseFileUtils.writeByteArrayToFile(r0, r7);	 Catch:{ UnsupportedEncodingException -> 0x0070, IOException -> 0x006e }
    L_0x001a:
        r7 = getKeyValueCacheDir();	 Catch:{ all -> 0x0045 }
        r2 = r7.listFiles();	 Catch:{ all -> 0x0045 }
        if (r2 == 0) goto L_0x0027;
    L_0x0024:
        r7 = r2.length;	 Catch:{ all -> 0x0045 }
        if (r7 != 0) goto L_0x0029;
    L_0x0027:
        monitor-exit(r8);	 Catch:{ all -> 0x0045 }
    L_0x0028:
        return;
    L_0x0029:
        r4 = r2.length;	 Catch:{ all -> 0x0045 }
        r3 = 0;
        r9 = r2.length;	 Catch:{ all -> 0x0045 }
        r7 = r6;
    L_0x002d:
        if (r7 >= r9) goto L_0x003b;
    L_0x002f:
        r1 = r2[r7];	 Catch:{ all -> 0x0045 }
        r10 = (long) r3;	 Catch:{ all -> 0x0045 }
        r12 = r1.length();	 Catch:{ all -> 0x0045 }
        r10 = r10 + r12;
        r3 = (int) r10;	 Catch:{ all -> 0x0045 }
        r7 = r7 + 1;
        goto L_0x002d;
    L_0x003b:
        r7 = maxKeyValueCacheFiles;	 Catch:{ all -> 0x0045 }
        if (r4 > r7) goto L_0x0048;
    L_0x003f:
        r7 = maxKeyValueCacheBytes;	 Catch:{ all -> 0x0045 }
        if (r3 > r7) goto L_0x0048;
    L_0x0043:
        monitor-exit(r8);	 Catch:{ all -> 0x0045 }
        goto L_0x0028;
    L_0x0045:
        r6 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x0045 }
        throw r6;
    L_0x0048:
        r7 = new com.parse.ParseKeyValueCache$2;	 Catch:{ all -> 0x0045 }
        r7.<init>();	 Catch:{ all -> 0x0045 }
        java.util.Arrays.sort(r2, r7);	 Catch:{ all -> 0x0045 }
        r7 = r2.length;	 Catch:{ all -> 0x0045 }
    L_0x0051:
        if (r6 >= r7) goto L_0x0069;
    L_0x0053:
        r1 = r2[r6];	 Catch:{ all -> 0x0045 }
        r4 = r4 + -1;
        r10 = (long) r3;	 Catch:{ all -> 0x0045 }
        r12 = r1.length();	 Catch:{ all -> 0x0045 }
        r10 = r10 - r12;
        r3 = (int) r10;	 Catch:{ all -> 0x0045 }
        r1.delete();	 Catch:{ all -> 0x0045 }
        r9 = maxKeyValueCacheFiles;	 Catch:{ all -> 0x0045 }
        if (r4 > r9) goto L_0x006b;
    L_0x0065:
        r9 = maxKeyValueCacheBytes;	 Catch:{ all -> 0x0045 }
        if (r3 > r9) goto L_0x006b;
    L_0x0069:
        monitor-exit(r8);	 Catch:{ all -> 0x0045 }
        goto L_0x0028;
    L_0x006b:
        r6 = r6 + 1;
        goto L_0x0051;
    L_0x006e:
        r7 = move-exception;
        goto L_0x001a;
    L_0x0070:
        r7 = move-exception;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.parse.ParseKeyValueCache.saveToKeyValueCache(java.lang.String, java.lang.String):void");
    }

    static void clearFromKeyValueCache(String key) {
        synchronized (MUTEX_IO) {
            File file = getKeyValueCacheFile(key);
            if (file != null) {
                file.delete();
            }
        }
    }

    static String loadFromKeyValueCache(String key, long maxAgeMilliseconds) {
        String str;
        synchronized (MUTEX_IO) {
            File file = getKeyValueCacheFile(key);
            if (file == null) {
                str = null;
            } else {
                Date now = new Date();
                if (getKeyValueCacheAge(file) < Math.max(0, now.getTime() - maxAgeMilliseconds)) {
                    str = null;
                } else {
                    file.setLastModified(now.getTime());
                    try {
                        RandomAccessFile f = new RandomAccessFile(file, "r");
                        byte[] bytes = new byte[((int) f.length())];
                        f.readFully(bytes);
                        f.close();
                        str = new String(bytes, Key.STRING_CHARSET_NAME);
                    } catch (IOException e) {
                        PLog.e("ParseKeyValueCache", "error reading from cache", e);
                        str = null;
                    }
                }
            }
        }
        return str;
    }

    static JSONObject jsonFromKeyValueCache(String key, long maxAgeMilliseconds) {
        String raw = loadFromKeyValueCache(key, maxAgeMilliseconds);
        if (raw == null) {
            return null;
        }
        try {
            return new JSONObject(raw);
        } catch (JSONException e) {
            PLog.e("ParseKeyValueCache", "corrupted cache for " + key, e);
            clearFromKeyValueCache(key);
            return null;
        }
    }
}
