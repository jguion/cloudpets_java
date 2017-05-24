package com.parse;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import bolts.Continuation;
import bolts.Task;
import com.bumptech.glide.load.Key;
import com.parse.http.ParseNetworkInterceptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class Parse {
    public static final int LOG_LEVEL_DEBUG = 3;
    public static final int LOG_LEVEL_ERROR = 6;
    public static final int LOG_LEVEL_INFO = 4;
    public static final int LOG_LEVEL_NONE = Integer.MAX_VALUE;
    public static final int LOG_LEVEL_VERBOSE = 2;
    public static final int LOG_LEVEL_WARNING = 5;
    private static final Object MUTEX = new Object();
    private static final Object MUTEX_CALLBACKS = new Object();
    private static final String PARSE_APPLICATION_ID = "com.parse.APPLICATION_ID";
    private static final String PARSE_CLIENT_KEY = "com.parse.CLIENT_KEY";
    private static Set<ParseCallbacks> callbacks = new HashSet();
    static ParseEventuallyQueue eventuallyQueue = null;
    private static List<ParseNetworkInterceptor> interceptors;
    private static boolean isLocalDatastoreEnabled;
    private static OfflineStore offlineStore;

    public static final class Configuration {
        final String applicationId;
        final String clientKey;
        final Context context;
        final List<ParseNetworkInterceptor> interceptors;
        final boolean localDataStoreEnabled;
        final String server;

        public static final class Builder {
            private String applicationId;
            private String clientKey;
            private Context context;
            private List<ParseNetworkInterceptor> interceptors;
            private boolean localDataStoreEnabled;
            private String server = "https://api.parse.com/1/";

            public Builder(Context context) {
                this.context = context;
                if (context != null) {
                    Bundle metaData = ManifestInfo.getApplicationMetadata(context.getApplicationContext());
                    if (metaData != null) {
                        this.applicationId = metaData.getString(Parse.PARSE_APPLICATION_ID);
                        this.clientKey = metaData.getString(Parse.PARSE_CLIENT_KEY);
                    }
                }
            }

            public Builder applicationId(String applicationId) {
                this.applicationId = applicationId;
                return this;
            }

            public Builder clientKey(String clientKey) {
                this.clientKey = clientKey;
                return this;
            }

            public Builder server(String server) {
                if (!server.endsWith("/")) {
                    server = server + "/";
                }
                this.server = server;
                return this;
            }

            public Builder addNetworkInterceptor(ParseNetworkInterceptor interceptor) {
                if (this.interceptors == null) {
                    this.interceptors = new ArrayList();
                }
                this.interceptors.add(interceptor);
                return this;
            }

            public Builder enableLocalDataStore() {
                this.localDataStoreEnabled = true;
                return this;
            }

            Builder setNetworkInterceptors(Collection<ParseNetworkInterceptor> interceptors) {
                if (this.interceptors == null) {
                    this.interceptors = new ArrayList();
                } else {
                    this.interceptors.clear();
                }
                if (interceptors != null) {
                    this.interceptors.addAll(interceptors);
                }
                return this;
            }

            private Builder setLocalDatastoreEnabled(boolean enabled) {
                this.localDataStoreEnabled = enabled;
                return this;
            }

            public Configuration build() {
                return new Configuration();
            }
        }

        private Configuration(Builder builder) {
            this.context = builder.context;
            this.applicationId = builder.applicationId;
            this.clientKey = builder.clientKey;
            this.server = builder.server;
            this.localDataStoreEnabled = builder.localDataStoreEnabled;
            this.interceptors = builder.interceptors != null ? Collections.unmodifiableList(new ArrayList(builder.interceptors)) : null;
        }
    }

    interface ParseCallbacks {
        void onParseInitialized();
    }

    public static void enableLocalDatastore(Context context) {
        if (isInitialized()) {
            throw new IllegalStateException("`Parse#enableLocalDatastore(Context)` must be invoked before `Parse#initialize(Context)`");
        }
        isLocalDatastoreEnabled = true;
    }

    static void disableLocalDatastore() {
        setLocalDatastore(null);
        ParseCorePlugins.getInstance().reset();
    }

    static OfflineStore getLocalDatastore() {
        return offlineStore;
    }

    static void setLocalDatastore(OfflineStore offlineStore) {
        isLocalDatastoreEnabled = offlineStore != null;
        offlineStore = offlineStore;
    }

    static boolean isLocalDatastoreEnabled() {
        return isLocalDatastoreEnabled;
    }

    public static void initialize(Context context) {
        Builder builder = new Builder(context);
        if (builder.applicationId == null) {
            throw new RuntimeException("ApplicationId not defined. You must provide ApplicationId in AndroidManifest.xml.\n<meta-data\n    android:name=\"com.parse.APPLICATION_ID\"\n    android:value=\"<Your Application Id>\" />");
        } else if (builder.clientKey == null) {
            throw new RuntimeException("ClientKey not defined. You must provide ClientKey in AndroidManifest.xml.\n<meta-data\n    android:name=\"com.parse.CLIENT_KEY\"\n    android:value=\"<Your Client Key>\" />");
        } else {
            initialize(builder.setNetworkInterceptors(interceptors).setLocalDatastoreEnabled(isLocalDatastoreEnabled).build());
        }
    }

    public static void initialize(Context context, String applicationId, String clientKey) {
        initialize(new Builder(context).applicationId(applicationId).clientKey(clientKey).setNetworkInterceptors(interceptors).setLocalDatastoreEnabled(isLocalDatastoreEnabled).build());
    }

    public static void initialize(Configuration configuration) {
        isLocalDatastoreEnabled = configuration.localDataStoreEnabled;
        Android.initialize(configuration.context, configuration.applicationId, configuration.clientKey);
        try {
            ParseRESTCommand.server = new URL(configuration.server);
            Context applicationContext = configuration.context.getApplicationContext();
            ParseHttpClient.setKeepAlive(true);
            ParseHttpClient.setMaxConnections(20);
            if (configuration.interceptors != null && configuration.interceptors.size() > 0) {
                initializeParseHttpClientsWithParseNetworkInterceptors(configuration.interceptors);
            }
            ParseObject.registerParseSubclasses();
            if (configuration.localDataStoreEnabled) {
                offlineStore = new OfflineStore(configuration.context);
            } else {
                ParseKeyValueCache.initialize(configuration.context);
            }
            checkCacheApplicationId();
            final Context context = configuration.context;
            Task.callInBackground(new Callable<Void>() {
                public Void call() throws Exception {
                    Parse.getEventuallyQueue(context);
                    return null;
                }
            });
            ParseFieldOperations.registerDefaultDecoders();
            if (allParsePushIntentReceiversInternal()) {
                GcmRegistrar.getInstance().registerAsync().continueWithTask(new Continuation<Void, Task<Void>>() {
                    public Task<Void> then(Task<Void> task) throws Exception {
                        return ParseUser.getCurrentUserAsync().makeVoid();
                    }
                }).continueWith(new Continuation<Void, Void>() {
                    public Void then(Task<Void> task) throws Exception {
                        ParseConfig.getCurrentConfig();
                        return null;
                    }
                }, Task.BACKGROUND_EXECUTOR);
                if (ManifestInfo.getPushType() == PushType.PPNS) {
                    PushService.startServiceIfRequired(applicationContext);
                }
                dispatchOnParseInitialized();
                synchronized (MUTEX_CALLBACKS) {
                    callbacks = null;
                }
                return;
            }
            throw new SecurityException("To prevent external tampering to your app's notifications, all receivers registered to handle the following actions must have their exported attributes set to false: com.parse.push.intent.RECEIVE, com.parse.push.intent.OPEN, com.parse.push.intent.DELETE");
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }

    static void destroy() {
        synchronized (MUTEX) {
            ParseEventuallyQueue queue = eventuallyQueue;
            eventuallyQueue = null;
        }
        if (queue != null) {
            queue.onDestroy();
        }
        ParseCorePlugins.getInstance().reset();
        ParsePlugins.reset();
    }

    static boolean isInitialized() {
        return ParsePlugins.get() != null;
    }

    static Context getApplicationContext() {
        checkContext();
        return Android.get().applicationContext();
    }

    private static boolean allParsePushIntentReceiversInternal() {
        for (ResolveInfo resolveInfo : ManifestInfo.getIntentReceivers(ParsePushBroadcastReceiver.ACTION_PUSH_RECEIVE, ParsePushBroadcastReceiver.ACTION_PUSH_DELETE, ParsePushBroadcastReceiver.ACTION_PUSH_OPEN)) {
            if (resolveInfo.activityInfo.exported) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    static File getParseDir() {
        return ParsePlugins.get().getParseDir();
    }

    static File getParseCacheDir() {
        return ParsePlugins.get().getCacheDir();
    }

    static File getParseCacheDir(String subDir) {
        File dir;
        synchronized (MUTEX) {
            dir = new File(getParseCacheDir(), subDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
        return dir;
    }

    static File getParseFilesDir() {
        return ParsePlugins.get().getFilesDir();
    }

    static File getParseFilesDir(String subDir) {
        File dir;
        synchronized (MUTEX) {
            dir = new File(getParseFilesDir(), subDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
        return dir;
    }

    static void checkCacheApplicationId() {
        synchronized (MUTEX) {
            String applicationId = ParsePlugins.get().applicationId();
            if (applicationId != null) {
                File dir = getParseCacheDir();
                File applicationIdFile = new File(dir, "applicationId");
                if (applicationIdFile.exists()) {
                    boolean matches = false;
                    try {
                        RandomAccessFile f = new RandomAccessFile(applicationIdFile, "r");
                        byte[] bytes = new byte[((int) f.length())];
                        f.readFully(bytes);
                        f.close();
                        matches = new String(bytes, Key.STRING_CHARSET_NAME).equals(applicationId);
                    } catch (FileNotFoundException e) {
                    } catch (IOException e2) {
                    }
                    if (!matches) {
                        try {
                            ParseFileUtils.deleteDirectory(dir);
                        } catch (IOException e3) {
                        }
                    }
                }
                try {
                    FileOutputStream out = new FileOutputStream(new File(dir, "applicationId"));
                    out.write(applicationId.getBytes(Key.STRING_CHARSET_NAME));
                    out.close();
                } catch (FileNotFoundException e4) {
                } catch (UnsupportedEncodingException e5) {
                } catch (IOException e6) {
                }
            }
        }
    }

    static ParseEventuallyQueue getEventuallyQueue() {
        return getEventuallyQueue(Android.get().applicationContext());
    }

    private static ParseEventuallyQueue getEventuallyQueue(Context context) {
        ParseEventuallyQueue parsePinningEventuallyQueue;
        synchronized (MUTEX) {
            boolean isLocalDatastoreEnabled = isLocalDatastoreEnabled();
            if (eventuallyQueue == null || ((isLocalDatastoreEnabled && (eventuallyQueue instanceof ParseCommandCache)) || (!isLocalDatastoreEnabled && (eventuallyQueue instanceof ParsePinningEventuallyQueue)))) {
                checkContext();
                ParseHttpClient httpClient = ParsePlugins.get().restClient();
                if (isLocalDatastoreEnabled) {
                    parsePinningEventuallyQueue = new ParsePinningEventuallyQueue(context, httpClient);
                } else {
                    parsePinningEventuallyQueue = new ParseCommandCache(context, httpClient);
                }
                eventuallyQueue = parsePinningEventuallyQueue;
                if (isLocalDatastoreEnabled && ParseCommandCache.getPendingCount() > 0) {
                    ParseCommandCache parseCommandCache = new ParseCommandCache(context, httpClient);
                }
            }
            parsePinningEventuallyQueue = eventuallyQueue;
        }
        return parsePinningEventuallyQueue;
    }

    static void checkInit() {
        if (ParsePlugins.get() == null) {
            throw new RuntimeException("You must call Parse.initialize(Context) before using the Parse library.");
        } else if (ParsePlugins.get().applicationId() == null) {
            throw new RuntimeException("applicationId is null. You must call Parse.initialize(Context) before using the Parse library.");
        } else if (ParsePlugins.get().clientKey() == null) {
            throw new RuntimeException("clientKey is null. You must call Parse.initialize(Context) before using the Parse library.");
        }
    }

    static void checkContext() {
        if (Android.get().applicationContext() == null) {
            throw new RuntimeException("applicationContext is null. You must call Parse.initialize(Context) before using the Parse library.");
        }
    }

    static boolean hasPermission(String permission) {
        return getApplicationContext().checkCallingOrSelfPermission(permission) == 0;
    }

    static void requirePermission(String permission) {
        if (!hasPermission(permission)) {
            throw new IllegalStateException("To use this functionality, add this to your AndroidManifest.xml:\n<uses-permission android:name=\"" + permission + "\" />");
        }
    }

    static void registerParseCallbacks(ParseCallbacks listener) {
        if (isInitialized()) {
            throw new IllegalStateException("You must register callbacks before Parse.initialize(Context)");
        }
        synchronized (MUTEX_CALLBACKS) {
            if (callbacks == null) {
                return;
            }
            callbacks.add(listener);
        }
    }

    static void unregisterParseCallbacks(ParseCallbacks listener) {
        synchronized (MUTEX_CALLBACKS) {
            if (callbacks == null) {
                return;
            }
            callbacks.remove(listener);
        }
    }

    private static void dispatchOnParseInitialized() {
        ParseCallbacks[] callbacks = collectParseCallbacks();
        if (callbacks != null) {
            for (ParseCallbacks callback : callbacks) {
                callback.onParseInitialized();
            }
        }
    }

    private static ParseCallbacks[] collectParseCallbacks() {
        ParseCallbacks[] callbacks;
        synchronized (MUTEX_CALLBACKS) {
            if (callbacks == null) {
                callbacks = null;
            } else {
                callbacks = new ParseCallbacks[callbacks.size()];
                if (callbacks.size() > 0) {
                    callbacks = (ParseCallbacks[]) callbacks.toArray(callbacks);
                }
            }
        }
        return callbacks;
    }

    public static void setLogLevel(int logLevel) {
        PLog.setLogLevel(logLevel);
    }

    public static int getLogLevel() {
        return PLog.getLogLevel();
    }

    private Parse() {
        throw new AssertionError();
    }

    private static void initializeParseHttpClientsWithParseNetworkInterceptors(List<ParseNetworkInterceptor> interceptors) {
        if (interceptors != null) {
            List<ParseHttpClient> clients = new ArrayList();
            clients.add(ParsePlugins.get().restClient());
            clients.add(ParseCorePlugins.getInstance().getFileController().awsClient());
            for (ParseHttpClient parseHttpClient : clients) {
                parseHttpClient.addInternalInterceptor(new ParseDecompressInterceptor());
                for (ParseNetworkInterceptor interceptor : interceptors) {
                    parseHttpClient.addExternalInterceptor(interceptor);
                }
            }
        }
    }

    public static void addParseNetworkInterceptor(ParseNetworkInterceptor interceptor) {
        if (isInitialized()) {
            throw new IllegalStateException("`Parse#addParseNetworkInterceptor(ParseNetworkInterceptor)` must be invoked before `Parse#initialize(Context)`");
        }
        if (interceptors == null) {
            interceptors = new ArrayList();
        }
        interceptors.add(interceptor);
    }

    public static void removeParseNetworkInterceptor(ParseNetworkInterceptor interceptor) {
        if (isInitialized()) {
            throw new IllegalStateException("`Parse#addParseNetworkInterceptor(ParseNetworkInterceptor)` must be invoked before `Parse#initialize(Context)`");
        } else if (interceptors != null) {
            interceptors.remove(interceptor);
        }
    }

    static String externalVersionName() {
        return "a1.13.1";
    }
}
