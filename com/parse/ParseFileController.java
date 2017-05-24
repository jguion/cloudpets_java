package com.parse;

import bolts.Continuation;
import bolts.Task;
import com.parse.ParseRESTFileCommand.Builder;
import com.parse.http.ParseHttpRequest.Method;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import org.json.JSONObject;

class ParseFileController {
    private ParseHttpClient awsClient;
    private final File cachePath;
    private final Object lock = new Object();
    private final ParseHttpClient restClient;

    public ParseFileController(ParseHttpClient restClient, File cachePath) {
        this.restClient = restClient;
        this.cachePath = cachePath;
    }

    ParseHttpClient awsClient() {
        ParseHttpClient parseHttpClient;
        synchronized (this.lock) {
            if (this.awsClient == null) {
                this.awsClient = ParsePlugins.get().newHttpClient();
            }
            parseHttpClient = this.awsClient;
        }
        return parseHttpClient;
    }

    ParseFileController awsClient(ParseHttpClient awsClient) {
        synchronized (this.lock) {
            this.awsClient = awsClient;
        }
        return this;
    }

    public File getCacheFile(State state) {
        return new File(this.cachePath, state.name());
    }

    File getTempFile(State state) {
        if (state.url() == null) {
            return null;
        }
        return new File(this.cachePath, state.url() + ".tmp");
    }

    public boolean isDataAvailable(State state) {
        return getCacheFile(state).exists();
    }

    public void clearCache() {
        File[] files = this.cachePath.listFiles();
        if (files != null) {
            for (File file : files) {
                ParseFileUtils.deleteQuietly(file);
            }
        }
    }

    public Task<State> saveAsync(final State state, final byte[] data, String sessionToken, ProgressCallback uploadProgressCallback, Task<Void> cancellationToken) {
        if (state.url() != null) {
            return Task.forResult(state);
        }
        if (cancellationToken != null && cancellationToken.isCancelled()) {
            return Task.cancelled();
        }
        ParseRESTCommand command = ((Builder) new Builder().fileName(state.name()).data(data).contentType(state.mimeType()).sessionToken(sessionToken)).build();
        command.enableRetrying();
        return command.executeAsync(this.restClient, uploadProgressCallback, null, cancellationToken).onSuccess(new Continuation<JSONObject, State>() {
            public State then(Task<JSONObject> task) throws Exception {
                JSONObject result = (JSONObject) task.getResult();
                State newState = new Builder(state).name(result.getString(FriendRecord.NAME)).url(result.getString("url")).build();
                try {
                    ParseFileUtils.writeByteArrayToFile(ParseFileController.this.getCacheFile(newState), data);
                } catch (IOException e) {
                }
                return newState;
            }
        }, ParseExecutors.io());
    }

    public Task<State> saveAsync(final State state, final File file, String sessionToken, ProgressCallback uploadProgressCallback, Task<Void> cancellationToken) {
        if (state.url() != null) {
            return Task.forResult(state);
        }
        if (cancellationToken != null && cancellationToken.isCancelled()) {
            return Task.cancelled();
        }
        ParseRESTCommand command = ((Builder) new Builder().fileName(state.name()).file(file).contentType(state.mimeType()).sessionToken(sessionToken)).build();
        command.enableRetrying();
        return command.executeAsync(this.restClient, uploadProgressCallback, null, cancellationToken).onSuccess(new Continuation<JSONObject, State>() {
            public State then(Task<JSONObject> task) throws Exception {
                JSONObject result = (JSONObject) task.getResult();
                State newState = new Builder(state).name(result.getString(FriendRecord.NAME)).url(result.getString("url")).build();
                try {
                    ParseFileUtils.copyFile(file, ParseFileController.this.getCacheFile(newState));
                } catch (IOException e) {
                }
                return newState;
            }
        }, ParseExecutors.io());
    }

    public Task<File> fetchAsync(State state, String sessionToken, ProgressCallback downloadProgressCallback, Task<Void> cancellationToken) {
        if (cancellationToken != null && cancellationToken.isCancelled()) {
            return Task.cancelled();
        }
        final File cacheFile = getCacheFile(state);
        final Task<Void> task = cancellationToken;
        final State state2 = state;
        final ProgressCallback progressCallback = downloadProgressCallback;
        return Task.call(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return Boolean.valueOf(cacheFile.exists());
            }
        }, ParseExecutors.io()).continueWithTask(new Continuation<Boolean, Task<File>>() {
            public Task<File> then(Task<Boolean> task) throws Exception {
                if (((Boolean) task.getResult()).booleanValue()) {
                    return Task.forResult(cacheFile);
                }
                if (task != null && task.isCancelled()) {
                    return Task.cancelled();
                }
                final File tempFile = ParseFileController.this.getTempFile(state2);
                return new ParseAWSRequest(Method.GET, state2.url(), tempFile).executeAsync(ParseFileController.this.awsClient(), null, progressCallback, task).continueWithTask(new Continuation<Void, Task<File>>() {
                    public Task<File> then(Task<Void> task) throws Exception {
                        if (task != null && task.isCancelled()) {
                            throw new CancellationException();
                        } else if (task.isFaulted()) {
                            ParseFileUtils.deleteQuietly(tempFile);
                            return task.cast();
                        } else {
                            ParseFileUtils.deleteQuietly(cacheFile);
                            ParseFileUtils.moveFile(tempFile, cacheFile);
                            return Task.forResult(cacheFile);
                        }
                    }
                }, ParseExecutors.io());
            }
        });
    }
}
