package com.parse;

import android.content.Context;
import android.content.Intent;
import bolts.Capture;
import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.parse.ConnectivityNotifier.ConnectivityListener;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.logging.Logger;
import org.json.JSONObject;

class ParseCommandCache extends ParseEventuallyQueue {
    private static final String TAG = "com.parse.ParseCommandCache";
    private static int filenameCounter = 0;
    private static final Object lock = new Object();
    private File cachePath;
    private final ParseHttpClient httpClient;
    ConnectivityListener listener = new ConnectivityListener() {
        public void networkConnectivityStatusChanged(Context context, Intent intent) {
            final boolean connectionLost = intent.getBooleanExtra("noConnectivity", false);
            final boolean isConnected = ConnectivityNotifier.isConnected(context);
            Task.call(new Callable<Void>() {
                public Void call() throws Exception {
                    if (connectionLost) {
                        ParseCommandCache.this.setConnected(false);
                    } else {
                        ParseCommandCache.this.setConnected(isConnected);
                    }
                    return null;
                }
            }, ParseExecutors.io());
        }
    };
    private Logger log;
    private int maxCacheSizeBytes = 10485760;
    ConnectivityNotifier notifier;
    private HashMap<File, TaskCompletionSource<JSONObject>> pendingTasks = new HashMap();
    private boolean running;
    private final Object runningLock;
    private boolean shouldStop;
    private int timeoutMaxRetries = 5;
    private double timeoutRetryWaitSeconds = 600.0d;
    private boolean unprocessedCommandsExist;

    private void runLoop() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0078 in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r9 = this;
        r8 = 4;
        r3 = 0;
        r2 = 1;
        r4 = com.parse.Parse.getLogLevel();
        if (r8 < r4) goto L_0x0010;
    L_0x0009:
        r4 = r9.log;
        r5 = "Parse command cache has started processing queued commands.";
        r4.info(r5);
    L_0x0010:
        r4 = r9.runningLock;
        monitor-enter(r4);
        r5 = r9.running;
        if (r5 == 0) goto L_0x0019;
    L_0x0017:
        monitor-exit(r4);
    L_0x0018:
        return;
    L_0x0019:
        r5 = 1;
        r9.running = r5;
        r5 = r9.runningLock;
        r5.notifyAll();
        monitor-exit(r4);
        r4 = lock;
        monitor-enter(r4);
        r5 = r9.shouldStop;
        if (r5 != 0) goto L_0x0055;
    L_0x0029:
        r5 = java.lang.Thread.interrupted();
        if (r5 != 0) goto L_0x0055;
    L_0x002f:
        r1 = r2;
    L_0x0030:
        monitor-exit(r4);
    L_0x0031:
        if (r1 == 0) goto L_0x0083;
    L_0x0033:
        r5 = lock;
        monitor-enter(r5);
        r4 = r9.timeoutMaxRetries;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
        r9.maybeRunAllCommandsNow(r4);	 Catch:{ Exception -> 0x005f, all -> 0x007a }
        r4 = r9.shouldStop;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
        if (r4 != 0) goto L_0x0048;
    L_0x003f:
        r4 = r9.unprocessedCommandsExist;	 Catch:{ InterruptedException -> 0x005a }
        if (r4 != 0) goto L_0x0048;	 Catch:{ InterruptedException -> 0x005a }
    L_0x0043:
        r4 = lock;	 Catch:{ InterruptedException -> 0x005a }
        r4.wait();	 Catch:{ InterruptedException -> 0x005a }
    L_0x0048:
        r4 = r9.shouldStop;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
        if (r4 != 0) goto L_0x0076;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
    L_0x004c:
        r1 = r2;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
    L_0x004d:
        monitor-exit(r5);	 Catch:{ Exception -> 0x005f, all -> 0x007a }
        goto L_0x0031;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
    L_0x004f:
        r2 = move-exception;
        monitor-exit(r5);	 Catch:{ Exception -> 0x005f, all -> 0x007a }
        throw r2;
    L_0x0052:
        r2 = move-exception;
        monitor-exit(r4);
        throw r2;
    L_0x0055:
        r1 = r3;
        goto L_0x0030;
    L_0x0057:
        r2 = move-exception;
        monitor-exit(r4);
        throw r2;
    L_0x005a:
        r0 = move-exception;
        r4 = 1;
        r9.shouldStop = r4;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
        goto L_0x0048;
    L_0x005f:
        r0 = move-exception;
        r4 = 6;
        r6 = com.parse.Parse.getLogLevel();	 Catch:{ Exception -> 0x005f, all -> 0x007a }
        if (r4 < r6) goto L_0x0070;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
    L_0x0067:
        r4 = r9.log;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
        r6 = java.util.logging.Level.SEVERE;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
        r7 = "saveEventually thread had an error.";	 Catch:{ Exception -> 0x005f, all -> 0x007a }
        r4.log(r6, r7, r0);	 Catch:{ Exception -> 0x005f, all -> 0x007a }
    L_0x0070:
        r4 = r9.shouldStop;
        if (r4 != 0) goto L_0x0078;
    L_0x0074:
        r1 = r2;
    L_0x0075:
        goto L_0x004d;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
    L_0x0076:
        r1 = r3;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
        goto L_0x004d;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
    L_0x0078:
        r1 = r3;
        goto L_0x0075;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
    L_0x007a:
        r4 = move-exception;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
        r6 = r9.shouldStop;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
        if (r6 != 0) goto L_0x0081;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
    L_0x007f:
        r1 = r2;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
    L_0x0080:
        throw r4;	 Catch:{ Exception -> 0x005f, all -> 0x007a }
    L_0x0081:
        r1 = r3;
        goto L_0x0080;
    L_0x0083:
        r3 = r9.runningLock;
        monitor-enter(r3);
        r2 = 0;
        r9.running = r2;
        r2 = r9.runningLock;
        r2.notifyAll();
        monitor-exit(r3);
        r2 = com.parse.Parse.getLogLevel();
        if (r8 < r2) goto L_0x0018;
    L_0x0095:
        r2 = r9.log;
        r3 = "saveEventually thread has stopped processing commands.";
        r2.info(r3);
        goto L_0x0018;
    L_0x009e:
        r2 = move-exception;
        monitor-exit(r3);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.parse.ParseCommandCache.runLoop():void");
    }

    private static File getCacheDir() {
        File cacheDir = new File(Parse.getParseDir(), "CommandCache");
        cacheDir.mkdirs();
        return cacheDir;
    }

    public static int getPendingCount() {
        int length;
        synchronized (lock) {
            String[] files = getCacheDir().list();
            length = files == null ? 0 : files.length;
        }
        return length;
    }

    public ParseCommandCache(Context context, ParseHttpClient client) {
        setConnected(false);
        this.shouldStop = false;
        this.running = false;
        this.runningLock = new Object();
        this.httpClient = client;
        this.log = Logger.getLogger(TAG);
        this.cachePath = getCacheDir();
        if (Parse.hasPermission("android.permission.ACCESS_NETWORK_STATE")) {
            setConnected(ConnectivityNotifier.isConnected(context));
            this.notifier = ConnectivityNotifier.getNotifier(context);
            this.notifier.addListener(this.listener);
            resume();
        }
    }

    public void onDestroy() {
        this.notifier.removeListener(this.listener);
    }

    public void setTimeoutMaxRetries(int tries) {
        synchronized (lock) {
            this.timeoutMaxRetries = tries;
        }
    }

    public void setTimeoutRetryWaitSeconds(double seconds) {
        synchronized (lock) {
            this.timeoutRetryWaitSeconds = seconds;
        }
    }

    public void setMaxCacheSizeBytes(int bytes) {
        synchronized (lock) {
            this.maxCacheSizeBytes = bytes;
        }
    }

    public void resume() {
        synchronized (this.runningLock) {
            if (!this.running) {
                new Thread("ParseCommandCache.runLoop()") {
                    public void run() {
                        ParseCommandCache.this.runLoop();
                    }
                }.start();
                try {
                    this.runningLock.wait();
                } catch (InterruptedException e) {
                    synchronized (lock) {
                        this.shouldStop = true;
                        lock.notifyAll();
                    }
                }
            }
        }
    }

    public void pause() {
        synchronized (this.runningLock) {
            if (this.running) {
                synchronized (lock) {
                    this.shouldStop = true;
                    lock.notifyAll();
                }
            }
            while (this.running) {
                try {
                    this.runningLock.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private void removeFile(File file) {
        synchronized (lock) {
            this.pendingTasks.remove(file);
            try {
                commandFromJSON(ParseFileUtils.readFileToJSONObject(file)).releaseLocalIds();
            } catch (Exception e) {
            }
            ParseFileUtils.deleteQuietly(file);
        }
    }

    void simulateReboot() {
        synchronized (lock) {
            this.pendingTasks.clear();
        }
    }

    void fakeObjectUpdate() {
        notifyTestHelper(3);
        notifyTestHelper(1);
        notifyTestHelper(5);
    }

    public Task<JSONObject> enqueueEventuallyAsync(ParseRESTCommand command, ParseObject object) {
        return enqueueEventuallyAsync(command, false, object);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private bolts.Task<org.json.JSONObject> enqueueEventuallyAsync(com.parse.ParseRESTCommand r25, boolean r26, com.parse.ParseObject r27) {
        /*
        r24 = this;
        r19 = "android.permission.ACCESS_NETWORK_STATE";
        com.parse.Parse.requirePermission(r19);
        r17 = new bolts.TaskCompletionSource;
        r17.<init>();
        if (r27 == 0) goto L_0x001d;
    L_0x000c:
        r19 = r27.getObjectId();	 Catch:{ UnsupportedEncodingException -> 0x0061 }
        if (r19 != 0) goto L_0x001d;
    L_0x0012:
        r19 = r27.getOrCreateLocalId();	 Catch:{ UnsupportedEncodingException -> 0x0061 }
        r0 = r25;
        r1 = r19;
        r0.setLocalId(r1);	 Catch:{ UnsupportedEncodingException -> 0x0061 }
    L_0x001d:
        r11 = r25.toJSONObject();	 Catch:{ UnsupportedEncodingException -> 0x0061 }
        r19 = r11.toString();	 Catch:{ UnsupportedEncodingException -> 0x0061 }
        r20 = "UTF-8";
        r10 = r19.getBytes(r20);	 Catch:{ UnsupportedEncodingException -> 0x0061 }
        r0 = r10.length;
        r19 = r0;
        r0 = r24;
        r0 = r0.maxCacheSizeBytes;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 <= r1) goto L_0x0091;
    L_0x003a:
        r19 = 5;
        r20 = com.parse.Parse.getLogLevel();
        r0 = r19;
        r1 = r20;
        if (r0 < r1) goto L_0x0051;
    L_0x0046:
        r0 = r24;
        r0 = r0.log;
        r19 = r0;
        r20 = "Unable to save command for later because it's too big.";
        r19.warning(r20);
    L_0x0051:
        r19 = 4;
        r0 = r24;
        r1 = r19;
        r0.notifyTestHelper(r1);
        r19 = 0;
        r19 = bolts.Task.forResult(r19);
    L_0x0060:
        return r19;
    L_0x0061:
        r4 = move-exception;
        r19 = 5;
        r20 = com.parse.Parse.getLogLevel();
        r0 = r19;
        r1 = r20;
        if (r0 < r1) goto L_0x0081;
    L_0x006e:
        r0 = r24;
        r0 = r0.log;
        r19 = r0;
        r20 = java.util.logging.Level.WARNING;
        r21 = "UTF-8 isn't supported.  This shouldn't happen.";
        r0 = r19;
        r1 = r20;
        r2 = r21;
        r0.log(r1, r2, r4);
    L_0x0081:
        r19 = 4;
        r0 = r24;
        r1 = r19;
        r0.notifyTestHelper(r1);
        r19 = 0;
        r19 = bolts.Task.forResult(r19);
        goto L_0x0060;
    L_0x0091:
        r20 = lock;
        monitor-enter(r20);
        r0 = r24;
        r0 = r0.cachePath;	 Catch:{ IOException -> 0x0260 }
        r19 = r0;
        r7 = r19.list();	 Catch:{ IOException -> 0x0260 }
        if (r7 == 0) goto L_0x0161;
    L_0x00a0:
        java.util.Arrays.sort(r7);	 Catch:{ IOException -> 0x0260 }
        r16 = 0;
        r0 = r7.length;	 Catch:{ IOException -> 0x0260 }
        r21 = r0;
        r19 = 0;
    L_0x00aa:
        r0 = r19;
        r1 = r21;
        if (r0 >= r1) goto L_0x00cd;
    L_0x00b0:
        r6 = r7[r19];	 Catch:{ IOException -> 0x0260 }
        r5 = new java.io.File;	 Catch:{ IOException -> 0x0260 }
        r0 = r24;
        r0 = r0.cachePath;	 Catch:{ IOException -> 0x0260 }
        r22 = r0;
        r0 = r22;
        r5.<init>(r0, r6);	 Catch:{ IOException -> 0x0260 }
        r22 = r5.length();	 Catch:{ IOException -> 0x0260 }
        r0 = r22;
        r0 = (int) r0;	 Catch:{ IOException -> 0x0260 }
        r22 = r0;
        r16 = r16 + r22;
        r19 = r19 + 1;
        goto L_0x00aa;
    L_0x00cd:
        r0 = r10.length;	 Catch:{ IOException -> 0x0260 }
        r19 = r0;
        r16 = r16 + r19;
        r0 = r24;
        r0 = r0.maxCacheSizeBytes;	 Catch:{ IOException -> 0x0260 }
        r19 = r0;
        r0 = r16;
        r1 = r19;
        if (r0 <= r1) goto L_0x0161;
    L_0x00de:
        if (r26 == 0) goto L_0x010c;
    L_0x00e0:
        r19 = 5;
        r21 = com.parse.Parse.getLogLevel();	 Catch:{ IOException -> 0x0260 }
        r0 = r19;
        r1 = r21;
        if (r0 < r1) goto L_0x00fb;
    L_0x00ec:
        r0 = r24;
        r0 = r0.log;	 Catch:{ IOException -> 0x0260 }
        r19 = r0;
        r21 = "Unable to save command for later because storage is full.";
        r0 = r19;
        r1 = r21;
        r0.warning(r1);	 Catch:{ IOException -> 0x0260 }
    L_0x00fb:
        r19 = 0;
        r19 = bolts.Task.forResult(r19);	 Catch:{ IOException -> 0x0260 }
        r21 = lock;	 Catch:{ all -> 0x0109 }
        r21.notifyAll();	 Catch:{ all -> 0x0109 }
        monitor-exit(r20);	 Catch:{ all -> 0x0109 }
        goto L_0x0060;
    L_0x0109:
        r19 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x0109 }
        throw r19;
    L_0x010c:
        r19 = 5;
        r21 = com.parse.Parse.getLogLevel();	 Catch:{ IOException -> 0x0260 }
        r0 = r19;
        r1 = r21;
        if (r0 < r1) goto L_0x0127;
    L_0x0118:
        r0 = r24;
        r0 = r0.log;	 Catch:{ IOException -> 0x0260 }
        r19 = r0;
        r21 = "Deleting old commands to make room in command cache.";
        r0 = r19;
        r1 = r21;
        r0.warning(r1);	 Catch:{ IOException -> 0x0260 }
    L_0x0127:
        r8 = 0;
        r9 = r8;
    L_0x0129:
        r0 = r24;
        r0 = r0.maxCacheSizeBytes;	 Catch:{ IOException -> 0x0260 }
        r19 = r0;
        r0 = r16;
        r1 = r19;
        if (r0 <= r1) goto L_0x0161;
    L_0x0135:
        r0 = r7.length;	 Catch:{ IOException -> 0x0260 }
        r19 = r0;
        r0 = r19;
        if (r9 >= r0) goto L_0x0161;
    L_0x013c:
        r5 = new java.io.File;	 Catch:{ IOException -> 0x0260 }
        r0 = r24;
        r0 = r0.cachePath;	 Catch:{ IOException -> 0x0260 }
        r19 = r0;
        r8 = r9 + 1;
        r21 = r7[r9];	 Catch:{ IOException -> 0x0260 }
        r0 = r19;
        r1 = r21;
        r5.<init>(r0, r1);	 Catch:{ IOException -> 0x0260 }
        r22 = r5.length();	 Catch:{ IOException -> 0x0260 }
        r0 = r22;
        r0 = (int) r0;	 Catch:{ IOException -> 0x0260 }
        r19 = r0;
        r16 = r16 - r19;
        r0 = r24;
        r0.removeFile(r5);	 Catch:{ IOException -> 0x0260 }
        r9 = r8;
        goto L_0x0129;
    L_0x0161:
        r22 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x0260 }
        r14 = java.lang.Long.toHexString(r22);	 Catch:{ IOException -> 0x0260 }
        r19 = r14.length();	 Catch:{ IOException -> 0x0260 }
        r21 = 16;
        r0 = r19;
        r1 = r21;
        if (r0 >= r1) goto L_0x01a6;
    L_0x0175:
        r19 = r14.length();	 Catch:{ IOException -> 0x0260 }
        r19 = 16 - r19;
        r0 = r19;
        r0 = new char[r0];	 Catch:{ IOException -> 0x0260 }
        r18 = r0;
        r19 = 48;
        java.util.Arrays.fill(r18, r19);	 Catch:{ IOException -> 0x0260 }
        r19 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0260 }
        r19.<init>();	 Catch:{ IOException -> 0x0260 }
        r21 = new java.lang.String;	 Catch:{ IOException -> 0x0260 }
        r0 = r21;
        r1 = r18;
        r0.<init>(r1);	 Catch:{ IOException -> 0x0260 }
        r0 = r19;
        r1 = r21;
        r19 = r0.append(r1);	 Catch:{ IOException -> 0x0260 }
        r0 = r19;
        r19 = r0.append(r14);	 Catch:{ IOException -> 0x0260 }
        r14 = r19.toString();	 Catch:{ IOException -> 0x0260 }
    L_0x01a6:
        r19 = filenameCounter;	 Catch:{ IOException -> 0x0260 }
        r21 = r19 + 1;
        filenameCounter = r21;	 Catch:{ IOException -> 0x0260 }
        r15 = java.lang.Integer.toHexString(r19);	 Catch:{ IOException -> 0x0260 }
        r19 = r15.length();	 Catch:{ IOException -> 0x0260 }
        r21 = 8;
        r0 = r19;
        r1 = r21;
        if (r0 >= r1) goto L_0x01ed;
    L_0x01bc:
        r19 = r15.length();	 Catch:{ IOException -> 0x0260 }
        r19 = 8 - r19;
        r0 = r19;
        r0 = new char[r0];	 Catch:{ IOException -> 0x0260 }
        r18 = r0;
        r19 = 48;
        java.util.Arrays.fill(r18, r19);	 Catch:{ IOException -> 0x0260 }
        r19 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0260 }
        r19.<init>();	 Catch:{ IOException -> 0x0260 }
        r21 = new java.lang.String;	 Catch:{ IOException -> 0x0260 }
        r0 = r21;
        r1 = r18;
        r0.<init>(r1);	 Catch:{ IOException -> 0x0260 }
        r0 = r19;
        r1 = r21;
        r19 = r0.append(r1);	 Catch:{ IOException -> 0x0260 }
        r0 = r19;
        r19 = r0.append(r15);	 Catch:{ IOException -> 0x0260 }
        r15 = r19.toString();	 Catch:{ IOException -> 0x0260 }
    L_0x01ed:
        r19 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0260 }
        r19.<init>();	 Catch:{ IOException -> 0x0260 }
        r21 = "CachedCommand_";
        r0 = r19;
        r1 = r21;
        r19 = r0.append(r1);	 Catch:{ IOException -> 0x0260 }
        r0 = r19;
        r19 = r0.append(r14);	 Catch:{ IOException -> 0x0260 }
        r21 = "_";
        r0 = r19;
        r1 = r21;
        r19 = r0.append(r1);	 Catch:{ IOException -> 0x0260 }
        r0 = r19;
        r19 = r0.append(r15);	 Catch:{ IOException -> 0x0260 }
        r21 = "_";
        r0 = r19;
        r1 = r21;
        r19 = r0.append(r1);	 Catch:{ IOException -> 0x0260 }
        r13 = r19.toString();	 Catch:{ IOException -> 0x0260 }
        r19 = "";
        r0 = r24;
        r0 = r0.cachePath;	 Catch:{ IOException -> 0x0260 }
        r21 = r0;
        r0 = r19;
        r1 = r21;
        r12 = java.io.File.createTempFile(r13, r0, r1);	 Catch:{ IOException -> 0x0260 }
        r0 = r24;
        r0 = r0.pendingTasks;	 Catch:{ IOException -> 0x0260 }
        r19 = r0;
        r0 = r19;
        r1 = r17;
        r0.put(r12, r1);	 Catch:{ IOException -> 0x0260 }
        r25.retainLocalIds();	 Catch:{ IOException -> 0x0260 }
        com.parse.ParseFileUtils.writeByteArrayToFile(r12, r10);	 Catch:{ IOException -> 0x0260 }
        r19 = 3;
        r0 = r24;
        r1 = r19;
        r0.notifyTestHelper(r1);	 Catch:{ IOException -> 0x0260 }
        r19 = 1;
        r0 = r19;
        r1 = r24;
        r1.unprocessedCommandsExist = r0;	 Catch:{ IOException -> 0x0260 }
        r19 = lock;	 Catch:{ all -> 0x0109 }
        r19.notifyAll();	 Catch:{ all -> 0x0109 }
    L_0x0259:
        monitor-exit(r20);	 Catch:{ all -> 0x0109 }
        r19 = r17.getTask();
        goto L_0x0060;
    L_0x0260:
        r4 = move-exception;
        r19 = 5;
        r21 = com.parse.Parse.getLogLevel();	 Catch:{ all -> 0x0286 }
        r0 = r19;
        r1 = r21;
        if (r0 < r1) goto L_0x0280;
    L_0x026d:
        r0 = r24;
        r0 = r0.log;	 Catch:{ all -> 0x0286 }
        r19 = r0;
        r21 = java.util.logging.Level.WARNING;	 Catch:{ all -> 0x0286 }
        r22 = "Unable to save command for later.";
        r0 = r19;
        r1 = r21;
        r2 = r22;
        r0.log(r1, r2, r4);	 Catch:{ all -> 0x0286 }
    L_0x0280:
        r19 = lock;	 Catch:{ all -> 0x0109 }
        r19.notifyAll();	 Catch:{ all -> 0x0109 }
        goto L_0x0259;
    L_0x0286:
        r19 = move-exception;
        r21 = lock;	 Catch:{ all -> 0x0109 }
        r21.notifyAll();	 Catch:{ all -> 0x0109 }
        throw r19;	 Catch:{ all -> 0x0109 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.parse.ParseCommandCache.enqueueEventuallyAsync(com.parse.ParseRESTCommand, boolean, com.parse.ParseObject):bolts.Task<org.json.JSONObject>");
    }

    public int pendingCount() {
        return getPendingCount();
    }

    public void clear() {
        synchronized (lock) {
            File[] files = this.cachePath.listFiles();
            if (files == null) {
                return;
            }
            for (File file : files) {
                removeFile(file);
            }
            this.pendingTasks.clear();
        }
    }

    public void setConnected(boolean connected) {
        synchronized (lock) {
            if (isConnected() != connected && connected) {
                lock.notifyAll();
            }
            super.setConnected(connected);
        }
    }

    private <T> T waitForTaskWithoutLock(Task<T> task) throws ParseException {
        T wait;
        synchronized (lock) {
            final Capture<Boolean> finished = new Capture(Boolean.valueOf(false));
            task.continueWith(new Continuation<T, Void>() {
                public Void then(Task<T> task) throws Exception {
                    finished.set(Boolean.valueOf(true));
                    synchronized (ParseCommandCache.lock) {
                        ParseCommandCache.lock.notifyAll();
                    }
                    return null;
                }
            }, Task.BACKGROUND_EXECUTOR);
            while (!((Boolean) finished.get()).booleanValue()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    this.shouldStop = true;
                }
            }
            wait = ParseTaskUtils.wait(task);
        }
        return wait;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void maybeRunAllCommandsNow(int r25) {
        /*
        r24 = this;
        r17 = lock;
        monitor-enter(r17);
        r13 = 0;
        r0 = r24;
        r0.unprocessedCommandsExist = r13;	 Catch:{ all -> 0x001f }
        r13 = r24.isConnected();	 Catch:{ all -> 0x001f }
        if (r13 != 0) goto L_0x0010;
    L_0x000e:
        monitor-exit(r17);	 Catch:{ all -> 0x001f }
    L_0x000f:
        return;
    L_0x0010:
        r0 = r24;
        r13 = r0.cachePath;	 Catch:{ all -> 0x001f }
        r9 = r13.list();	 Catch:{ all -> 0x001f }
        if (r9 == 0) goto L_0x001d;
    L_0x001a:
        r13 = r9.length;	 Catch:{ all -> 0x001f }
        if (r13 != 0) goto L_0x0022;
    L_0x001d:
        monitor-exit(r17);	 Catch:{ all -> 0x001f }
        goto L_0x000f;
    L_0x001f:
        r13 = move-exception;
        monitor-exit(r17);	 Catch:{ all -> 0x001f }
        throw r13;
    L_0x0022:
        java.util.Arrays.sort(r9);	 Catch:{ all -> 0x001f }
        r0 = r9.length;	 Catch:{ all -> 0x001f }
        r18 = r0;
        r13 = 0;
        r16 = r13;
    L_0x002b:
        r0 = r16;
        r1 = r18;
        if (r0 >= r1) goto L_0x022a;
    L_0x0031:
        r8 = r9[r16];	 Catch:{ all -> 0x001f }
        r7 = new java.io.File;	 Catch:{ all -> 0x001f }
        r0 = r24;
        r13 = r0.cachePath;	 Catch:{ all -> 0x001f }
        r7.<init>(r13, r8);	 Catch:{ all -> 0x001f }
        r11 = com.parse.ParseFileUtils.readFileToJSONObject(r7);	 Catch:{ FileNotFoundException -> 0x008f, IOException -> 0x00a9, JSONException -> 0x00c8 }
        r0 = r24;
        r13 = r0.pendingTasks;	 Catch:{ all -> 0x001f }
        r13 = r13.containsKey(r7);	 Catch:{ all -> 0x001f }
        if (r13 == 0) goto L_0x00e7;
    L_0x004a:
        r0 = r24;
        r13 = r0.pendingTasks;	 Catch:{ all -> 0x001f }
        r13 = r13.get(r7);	 Catch:{ all -> 0x001f }
        r13 = (bolts.TaskCompletionSource) r13;	 Catch:{ all -> 0x001f }
        r12 = r13;
    L_0x0055:
        r0 = r24;
        r2 = r0.commandFromJSON(r11);	 Catch:{ JSONException -> 0x00ea }
        if (r2 != 0) goto L_0x0109;
    L_0x005d:
        r13 = 0;
        r3 = bolts.Task.forResult(r13);	 Catch:{ ParseException -> 0x0122 }
        if (r12 == 0) goto L_0x0068;
    L_0x0064:
        r13 = 0;
        r12.setResult(r13);	 Catch:{ ParseException -> 0x0122 }
    L_0x0068:
        r13 = 8;
        r0 = r24;
        r0.notifyTestHelper(r13);	 Catch:{ ParseException -> 0x0122 }
    L_0x006f:
        r0 = r24;
        r0.waitForTaskWithoutLock(r3);	 Catch:{ ParseException -> 0x0122 }
        if (r12 == 0) goto L_0x007f;
    L_0x0076:
        r13 = r12.getTask();	 Catch:{ ParseException -> 0x0122 }
        r0 = r24;
        r0.waitForTaskWithoutLock(r13);	 Catch:{ ParseException -> 0x0122 }
    L_0x007f:
        r0 = r24;
        r0.removeFile(r7);	 Catch:{ ParseException -> 0x0122 }
        r13 = 1;
        r0 = r24;
        r0.notifyTestHelper(r13);	 Catch:{ ParseException -> 0x0122 }
    L_0x008a:
        r13 = r16 + 1;
        r16 = r13;
        goto L_0x002b;
    L_0x008f:
        r6 = move-exception;
        r13 = 6;
        r19 = com.parse.Parse.getLogLevel();	 Catch:{ all -> 0x001f }
        r0 = r19;
        if (r13 < r0) goto L_0x008a;
    L_0x0099:
        r0 = r24;
        r13 = r0.log;	 Catch:{ all -> 0x001f }
        r19 = java.util.logging.Level.SEVERE;	 Catch:{ all -> 0x001f }
        r20 = "File disappeared from cache while being read.";
        r0 = r19;
        r1 = r20;
        r13.log(r0, r1, r6);	 Catch:{ all -> 0x001f }
        goto L_0x008a;
    L_0x00a9:
        r6 = move-exception;
        r13 = 6;
        r19 = com.parse.Parse.getLogLevel();	 Catch:{ all -> 0x001f }
        r0 = r19;
        if (r13 < r0) goto L_0x00c2;
    L_0x00b3:
        r0 = r24;
        r13 = r0.log;	 Catch:{ all -> 0x001f }
        r19 = java.util.logging.Level.SEVERE;	 Catch:{ all -> 0x001f }
        r20 = "Unable to read contents of file in cache.";
        r0 = r19;
        r1 = r20;
        r13.log(r0, r1, r6);	 Catch:{ all -> 0x001f }
    L_0x00c2:
        r0 = r24;
        r0.removeFile(r7);	 Catch:{ all -> 0x001f }
        goto L_0x008a;
    L_0x00c8:
        r6 = move-exception;
        r13 = 6;
        r19 = com.parse.Parse.getLogLevel();	 Catch:{ all -> 0x001f }
        r0 = r19;
        if (r13 < r0) goto L_0x00e1;
    L_0x00d2:
        r0 = r24;
        r13 = r0.log;	 Catch:{ all -> 0x001f }
        r19 = java.util.logging.Level.SEVERE;	 Catch:{ all -> 0x001f }
        r20 = "Error parsing JSON found in cache.";
        r0 = r19;
        r1 = r20;
        r13.log(r0, r1, r6);	 Catch:{ all -> 0x001f }
    L_0x00e1:
        r0 = r24;
        r0.removeFile(r7);	 Catch:{ all -> 0x001f }
        goto L_0x008a;
    L_0x00e7:
        r12 = 0;
        goto L_0x0055;
    L_0x00ea:
        r6 = move-exception;
        r13 = 6;
        r19 = com.parse.Parse.getLogLevel();	 Catch:{ all -> 0x001f }
        r0 = r19;
        if (r13 < r0) goto L_0x0103;
    L_0x00f4:
        r0 = r24;
        r13 = r0.log;	 Catch:{ all -> 0x001f }
        r19 = java.util.logging.Level.SEVERE;	 Catch:{ all -> 0x001f }
        r20 = "Unable to create ParseCommand from JSON.";
        r0 = r19;
        r1 = r20;
        r13.log(r0, r1, r6);	 Catch:{ all -> 0x001f }
    L_0x0103:
        r0 = r24;
        r0.removeFile(r7);	 Catch:{ all -> 0x001f }
        goto L_0x008a;
    L_0x0109:
        r0 = r24;
        r13 = r0.httpClient;	 Catch:{ ParseException -> 0x0122 }
        r13 = r2.executeAsync(r13);	 Catch:{ ParseException -> 0x0122 }
        r19 = new com.parse.ParseCommandCache$4;	 Catch:{ ParseException -> 0x0122 }
        r0 = r19;
        r1 = r24;
        r0.<init>(r2, r12);	 Catch:{ ParseException -> 0x0122 }
        r0 = r19;
        r3 = r13.continueWithTask(r0);	 Catch:{ ParseException -> 0x0122 }
        goto L_0x006f;
    L_0x0122:
        r6 = move-exception;
        r13 = r6.getCode();	 Catch:{ all -> 0x001f }
        r19 = 100;
        r0 = r19;
        if (r13 != r0) goto L_0x0205;
    L_0x012d:
        if (r25 <= 0) goto L_0x01f7;
    L_0x012f:
        r13 = 4;
        r19 = com.parse.Parse.getLogLevel();	 Catch:{ all -> 0x001f }
        r0 = r19;
        if (r13 < r0) goto L_0x016e;
    L_0x0138:
        r0 = r24;
        r13 = r0.log;	 Catch:{ all -> 0x001f }
        r19 = new java.lang.StringBuilder;	 Catch:{ all -> 0x001f }
        r19.<init>();	 Catch:{ all -> 0x001f }
        r20 = "Network timeout in command cache. Waiting for ";
        r19 = r19.append(r20);	 Catch:{ all -> 0x001f }
        r0 = r24;
        r0 = r0.timeoutRetryWaitSeconds;	 Catch:{ all -> 0x001f }
        r20 = r0;
        r19 = r19.append(r20);	 Catch:{ all -> 0x001f }
        r20 = " seconds and then retrying ";
        r19 = r19.append(r20);	 Catch:{ all -> 0x001f }
        r0 = r19;
        r1 = r25;
        r19 = r0.append(r1);	 Catch:{ all -> 0x001f }
        r20 = " times.";
        r19 = r19.append(r20);	 Catch:{ all -> 0x001f }
        r19 = r19.toString();	 Catch:{ all -> 0x001f }
        r0 = r19;
        r13.info(r0);	 Catch:{ all -> 0x001f }
    L_0x016e:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x001f }
        r0 = r24;
        r0 = r0.timeoutRetryWaitSeconds;	 Catch:{ all -> 0x001f }
        r20 = r0;
        r22 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r20 = r20 * r22;
        r0 = r20;
        r0 = (long) r0;	 Catch:{ all -> 0x001f }
        r20 = r0;
        r14 = r4 + r20;
    L_0x0186:
        r13 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1));
        if (r13 >= 0) goto L_0x01ee;
    L_0x018a:
        r13 = r24.isConnected();	 Catch:{ all -> 0x001f }
        if (r13 == 0) goto L_0x0196;
    L_0x0190:
        r0 = r24;
        r13 = r0.shouldStop;	 Catch:{ all -> 0x001f }
        if (r13 == 0) goto L_0x01ad;
    L_0x0196:
        r13 = 4;
        r16 = com.parse.Parse.getLogLevel();	 Catch:{ all -> 0x001f }
        r0 = r16;
        if (r13 < r0) goto L_0x01aa;
    L_0x019f:
        r0 = r24;
        r13 = r0.log;	 Catch:{ all -> 0x001f }
        r16 = "Aborting wait because runEventually thread should stop.";
        r0 = r16;
        r13.info(r0);	 Catch:{ all -> 0x001f }
    L_0x01aa:
        monitor-exit(r17);	 Catch:{ all -> 0x001f }
        goto L_0x000f;
    L_0x01ad:
        r13 = lock;	 Catch:{ InterruptedException -> 0x01e7 }
        r20 = r14 - r4;
        r0 = r20;
        r13.wait(r0);	 Catch:{ InterruptedException -> 0x01e7 }
    L_0x01b6:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x001f }
        r0 = r24;
        r0 = r0.timeoutRetryWaitSeconds;	 Catch:{ all -> 0x001f }
        r20 = r0;
        r22 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r20 = r20 * r22;
        r0 = r20;
        r0 = (long) r0;	 Catch:{ all -> 0x001f }
        r20 = r0;
        r20 = r14 - r20;
        r13 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1));
        if (r13 >= 0) goto L_0x0186;
    L_0x01d2:
        r0 = r24;
        r0 = r0.timeoutRetryWaitSeconds;	 Catch:{ all -> 0x001f }
        r20 = r0;
        r22 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r20 = r20 * r22;
        r0 = r20;
        r0 = (long) r0;	 Catch:{ all -> 0x001f }
        r20 = r0;
        r4 = r14 - r20;
        goto L_0x0186;
    L_0x01e7:
        r10 = move-exception;
        r13 = 1;
        r0 = r24;
        r0.shouldStop = r13;	 Catch:{ all -> 0x001f }
        goto L_0x01b6;
    L_0x01ee:
        r13 = r25 + -1;
        r0 = r24;
        r0.maybeRunAllCommandsNow(r13);	 Catch:{ all -> 0x001f }
        goto L_0x008a;
    L_0x01f7:
        r13 = 0;
        r0 = r24;
        r0.setConnected(r13);	 Catch:{ all -> 0x001f }
        r13 = 7;
        r0 = r24;
        r0.notifyTestHelper(r13);	 Catch:{ all -> 0x001f }
        goto L_0x008a;
    L_0x0205:
        r13 = 6;
        r19 = com.parse.Parse.getLogLevel();	 Catch:{ all -> 0x001f }
        r0 = r19;
        if (r13 < r0) goto L_0x021d;
    L_0x020e:
        r0 = r24;
        r13 = r0.log;	 Catch:{ all -> 0x001f }
        r19 = java.util.logging.Level.SEVERE;	 Catch:{ all -> 0x001f }
        r20 = "Failed to run command.";
        r0 = r19;
        r1 = r20;
        r13.log(r0, r1, r6);	 Catch:{ all -> 0x001f }
    L_0x021d:
        r0 = r24;
        r0.removeFile(r7);	 Catch:{ all -> 0x001f }
        r13 = 2;
        r0 = r24;
        r0.notifyTestHelper(r13, r6);	 Catch:{ all -> 0x001f }
        goto L_0x008a;
    L_0x022a:
        monitor-exit(r17);	 Catch:{ all -> 0x001f }
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.parse.ParseCommandCache.maybeRunAllCommandsNow(int):void");
    }
}
