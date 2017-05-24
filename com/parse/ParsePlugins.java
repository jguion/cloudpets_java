package com.parse;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.SSLSessionCache;
import android.os.Build.VERSION;
import android.support.v4.os.EnvironmentCompat;
import com.google.common.net.HttpHeaders;
import com.parse.http.ParseHttpRequest;
import com.parse.http.ParseHttpRequest.Builder;
import com.parse.http.ParseHttpResponse;
import com.parse.http.ParseNetworkInterceptor;
import com.parse.http.ParseNetworkInterceptor.Chain;
import java.io.File;
import java.io.IOException;

class ParsePlugins {
    private static final String INSTALLATION_ID_LOCATION = "installationId";
    private static final Object LOCK = new Object();
    private static ParsePlugins instance;
    private final String applicationId;
    File cacheDir;
    private final String clientKey;
    File filesDir;
    private InstallationId installationId;
    final Object lock;
    File parseDir;
    private ParseHttpClient restClient;

    static class Android extends ParsePlugins {
        private final Context applicationContext;

        static void initialize(Context context, String applicationId, String clientKey) {
            ParsePlugins.set(new Android(context, applicationId, clientKey));
        }

        static Android get() {
            return (Android) ParsePlugins.get();
        }

        private Android(Context context, String applicationId, String clientKey) {
            super(applicationId, clientKey);
            this.applicationContext = context.getApplicationContext();
        }

        Context applicationContext() {
            return this.applicationContext;
        }

        public ParseHttpClient newHttpClient() {
            return ParseHttpClient.createClient(10000, new SSLSessionCache(this.applicationContext));
        }

        String userAgent() {
            String packageVersion = EnvironmentCompat.MEDIA_UNKNOWN;
            try {
                String packageName = this.applicationContext.getPackageName();
                packageVersion = packageName + "/" + this.applicationContext.getPackageManager().getPackageInfo(packageName, 0).versionCode;
            } catch (NameNotFoundException e) {
            }
            return "Parse Android SDK 1.13.1 (" + packageVersion + ") API Level " + VERSION.SDK_INT;
        }

        File getParseDir() {
            File access$300;
            synchronized (this.lock) {
                if (this.parseDir == null) {
                    this.parseDir = this.applicationContext.getDir("Parse", 0);
                }
                access$300 = ParsePlugins.createFileDir(this.parseDir);
            }
            return access$300;
        }

        File getCacheDir() {
            File access$300;
            synchronized (this.lock) {
                if (this.cacheDir == null) {
                    this.cacheDir = new File(this.applicationContext.getCacheDir(), "com.parse");
                }
                access$300 = ParsePlugins.createFileDir(this.cacheDir);
            }
            return access$300;
        }

        File getFilesDir() {
            File access$300;
            synchronized (this.lock) {
                if (this.filesDir == null) {
                    this.filesDir = new File(this.applicationContext.getFilesDir(), "com.parse");
                }
                access$300 = ParsePlugins.createFileDir(this.filesDir);
            }
            return access$300;
        }
    }

    static void initialize(String applicationId, String clientKey) {
        set(new ParsePlugins(applicationId, clientKey));
    }

    static void set(ParsePlugins plugins) {
        synchronized (LOCK) {
            if (instance != null) {
                throw new IllegalStateException("ParsePlugins is already initialized");
            }
            instance = plugins;
        }
    }

    static ParsePlugins get() {
        ParsePlugins parsePlugins;
        synchronized (LOCK) {
            parsePlugins = instance;
        }
        return parsePlugins;
    }

    static void reset() {
        synchronized (LOCK) {
            instance = null;
        }
    }

    private ParsePlugins(String applicationId, String clientKey) {
        this.lock = new Object();
        this.applicationId = applicationId;
        this.clientKey = clientKey;
    }

    String applicationId() {
        return this.applicationId;
    }

    String clientKey() {
        return this.clientKey;
    }

    ParseHttpClient newHttpClient() {
        return ParseHttpClient.createClient(10000, null);
    }

    ParseHttpClient restClient() {
        ParseHttpClient parseHttpClient;
        synchronized (this.lock) {
            if (this.restClient == null) {
                this.restClient = newHttpClient();
                this.restClient.addInternalInterceptor(new ParseNetworkInterceptor() {
                    public ParseHttpResponse intercept(Chain chain) throws IOException {
                        ParseHttpRequest request = chain.getRequest();
                        Builder builder = new Builder(request).addHeader("X-Parse-Application-Id", ParsePlugins.this.applicationId).addHeader("X-Parse-Client-Key", ParsePlugins.this.clientKey).addHeader("X-Parse-Client-Version", Parse.externalVersionName()).addHeader("X-Parse-App-Build-Version", String.valueOf(ManifestInfo.getVersionCode())).addHeader("X-Parse-App-Display-Version", ManifestInfo.getVersionName()).addHeader("X-Parse-OS-Version", VERSION.RELEASE).addHeader(HttpHeaders.USER_AGENT, ParsePlugins.this.userAgent());
                        if (request.getHeader("X-Parse-Installation-Id") == null) {
                            builder.addHeader("X-Parse-Installation-Id", ParsePlugins.this.installationId().get());
                        }
                        return chain.proceed(builder.build());
                    }
                });
            }
            parseHttpClient = this.restClient;
        }
        return parseHttpClient;
    }

    String userAgent() {
        return "Parse Java SDK";
    }

    InstallationId installationId() {
        InstallationId installationId;
        synchronized (this.lock) {
            if (this.installationId == null) {
                this.installationId = new InstallationId(new File(getParseDir(), INSTALLATION_ID_LOCATION));
            }
            installationId = this.installationId;
        }
        return installationId;
    }

    @Deprecated
    File getParseDir() {
        throw new IllegalStateException("Stub");
    }

    File getCacheDir() {
        throw new IllegalStateException("Stub");
    }

    File getFilesDir() {
        throw new IllegalStateException("Stub");
    }

    private static File createFileDir(File file) {
        return (file.exists() || !file.mkdirs()) ? file : file;
    }
}
