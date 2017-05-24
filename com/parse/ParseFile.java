package com.parse;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseFile {
    private Set<TaskCompletionSource<?>> currentTasks;
    byte[] data;
    File file;
    private State state;
    final TaskQueue taskQueue;

    static class State {
        private final String contentType;
        private final String name;
        private final String url;

        static class Builder {
            private String mimeType;
            private String name;
            private String url;

            public Builder(State state) {
                this.name = state.name();
                this.mimeType = state.mimeType();
                this.url = state.url();
            }

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Builder mimeType(String mimeType) {
                this.mimeType = mimeType;
                return this;
            }

            public Builder url(String url) {
                this.url = url;
                return this;
            }

            public State build() {
                return new State();
            }
        }

        private State(Builder builder) {
            this.name = builder.name != null ? builder.name : VoiceMessage.FILE;
            this.contentType = builder.mimeType;
            this.url = builder.url;
        }

        public String name() {
            return this.name;
        }

        public String mimeType() {
            return this.contentType;
        }

        public String url() {
            return this.url;
        }
    }

    static ParseFileController getFileController() {
        return ParseCorePlugins.getInstance().getFileController();
    }

    private static ProgressCallback progressCallbackOnMainThread(final ProgressCallback progressCallback) {
        if (progressCallback == null) {
            return null;
        }
        return new ProgressCallback() {
            public void done(final Integer percentDone) {
                Task.call(new Callable<Void>() {
                    public Void call() throws Exception {
                        progressCallback.done(percentDone);
                        return null;
                    }
                }, ParseExecutors.main());
            }
        };
    }

    public ParseFile(File file) {
        this(file, null);
    }

    public ParseFile(File file, String contentType) {
        this(new Builder().name(file.getName()).mimeType(contentType).build());
        this.file = file;
    }

    public ParseFile(String name, byte[] data, String contentType) {
        this(new Builder().name(name).mimeType(contentType).build());
        this.data = data;
    }

    public ParseFile(byte[] data) {
        this(null, data, null);
    }

    public ParseFile(String name, byte[] data) {
        this(name, data, null);
    }

    public ParseFile(byte[] data, String contentType) {
        this(null, data, contentType);
    }

    ParseFile(State state) {
        this.taskQueue = new TaskQueue();
        this.currentTasks = Collections.synchronizedSet(new HashSet());
        this.state = state;
    }

    State getState() {
        return this.state;
    }

    public String getName() {
        return this.state.name();
    }

    public boolean isDirty() {
        return this.state.url() == null;
    }

    public boolean isDataAvailable() {
        return this.data != null || getFileController().isDataAvailable(this.state);
    }

    public String getUrl() {
        return this.state.url();
    }

    public void save() throws ParseException {
        ParseTaskUtils.wait(saveInBackground());
    }

    private Task<Void> saveAsync(final String sessionToken, final ProgressCallback uploadProgressCallback, Task<Void> toAwait, final Task<Void> cancellationToken) {
        if (!isDirty()) {
            return Task.forResult(null);
        }
        if (cancellationToken == null || !cancellationToken.isCancelled()) {
            return toAwait.continueWithTask(new Continuation<Void, Task<Void>>() {
                public Task<Void> then(Task<Void> task) throws Exception {
                    if (!ParseFile.this.isDirty()) {
                        return Task.forResult(null);
                    }
                    if (cancellationToken != null && cancellationToken.isCancelled()) {
                        return Task.cancelled();
                    }
                    Task<State> saveTask;
                    if (ParseFile.this.data != null) {
                        saveTask = ParseFile.getFileController().saveAsync(ParseFile.this.state, ParseFile.this.data, sessionToken, ParseFile.progressCallbackOnMainThread(uploadProgressCallback), cancellationToken);
                    } else {
                        saveTask = ParseFile.getFileController().saveAsync(ParseFile.this.state, ParseFile.this.file, sessionToken, ParseFile.progressCallbackOnMainThread(uploadProgressCallback), cancellationToken);
                    }
                    return saveTask.onSuccessTask(new Continuation<State, Task<Void>>() {
                        public Task<Void> then(Task<State> task) throws Exception {
                            ParseFile.this.state = (State) task.getResult();
                            ParseFile.this.data = null;
                            ParseFile.this.file = null;
                            return task.makeVoid();
                        }
                    });
                }
            });
        }
        return Task.cancelled();
    }

    public Task<Void> saveInBackground(final ProgressCallback uploadProgressCallback) {
        final TaskCompletionSource<Void> cts = new TaskCompletionSource();
        this.currentTasks.add(cts);
        return ParseUser.getCurrentSessionTokenAsync().onSuccessTask(new Continuation<String, Task<Void>>() {
            public Task<Void> then(Task<String> task) throws Exception {
                return ParseFile.this.saveAsync((String) task.getResult(), uploadProgressCallback, cts.getTask());
            }
        }).continueWithTask(new Continuation<Void, Task<Void>>() {
            public Task<Void> then(Task<Void> task) throws Exception {
                cts.trySetResult(null);
                ParseFile.this.currentTasks.remove(cts);
                return task;
            }
        });
    }

    Task<Void> saveAsync(final String sessionToken, final ProgressCallback uploadProgressCallback, final Task<Void> cancellationToken) {
        return this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() {
            public Task<Void> then(Task<Void> toAwait) throws Exception {
                return ParseFile.this.saveAsync(sessionToken, uploadProgressCallback, toAwait, cancellationToken);
            }
        });
    }

    public Task<Void> saveInBackground() {
        return saveInBackground((ProgressCallback) null);
    }

    public void saveInBackground(SaveCallback saveCallback, ProgressCallback progressCallback) {
        ParseTaskUtils.callbackOnMainThreadAsync(saveInBackground(progressCallback), (ParseCallback1) saveCallback);
    }

    public void saveInBackground(SaveCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(saveInBackground(), (ParseCallback1) callback);
    }

    public byte[] getData() throws ParseException {
        return (byte[]) ParseTaskUtils.wait(getDataInBackground());
    }

    public Task<byte[]> getDataInBackground(final ProgressCallback progressCallback) {
        final TaskCompletionSource<Void> cts = new TaskCompletionSource();
        this.currentTasks.add(cts);
        return this.taskQueue.enqueue(new Continuation<Void, Task<byte[]>>() {
            public Task<byte[]> then(Task<Void> toAwait) throws Exception {
                return ParseFile.this.fetchInBackground(progressCallback, toAwait, cts.getTask()).onSuccess(new Continuation<File, byte[]>() {
                    public byte[] then(Task<File> task) throws Exception {
                        try {
                            return ParseFileUtils.readFileToByteArray((File) task.getResult());
                        } catch (IOException e) {
                            return null;
                        }
                    }
                });
            }
        }).continueWithTask(new Continuation<byte[], Task<byte[]>>() {
            public Task<byte[]> then(Task<byte[]> task) throws Exception {
                cts.trySetResult(null);
                ParseFile.this.currentTasks.remove(cts);
                return task;
            }
        });
    }

    public Task<byte[]> getDataInBackground() {
        return getDataInBackground((ProgressCallback) null);
    }

    public void getDataInBackground(GetDataCallback dataCallback, ProgressCallback progressCallback) {
        ParseTaskUtils.callbackOnMainThreadAsync(getDataInBackground(progressCallback), (ParseCallback2) dataCallback);
    }

    public void getDataInBackground(GetDataCallback dataCallback) {
        ParseTaskUtils.callbackOnMainThreadAsync(getDataInBackground(), (ParseCallback2) dataCallback);
    }

    public File getFile() throws ParseException {
        return (File) ParseTaskUtils.wait(getFileInBackground());
    }

    public Task<File> getFileInBackground(final ProgressCallback progressCallback) {
        final TaskCompletionSource<Void> cts = new TaskCompletionSource();
        this.currentTasks.add(cts);
        return this.taskQueue.enqueue(new Continuation<Void, Task<File>>() {
            public Task<File> then(Task<Void> toAwait) throws Exception {
                return ParseFile.this.fetchInBackground(progressCallback, toAwait, cts.getTask());
            }
        }).continueWithTask(new Continuation<File, Task<File>>() {
            public Task<File> then(Task<File> task) throws Exception {
                cts.trySetResult(null);
                ParseFile.this.currentTasks.remove(cts);
                return task;
            }
        });
    }

    public Task<File> getFileInBackground() {
        return getFileInBackground((ProgressCallback) null);
    }

    public void getFileInBackground(GetFileCallback fileCallback, ProgressCallback progressCallback) {
        ParseTaskUtils.callbackOnMainThreadAsync(getFileInBackground(progressCallback), (ParseCallback2) fileCallback);
    }

    public void getFileInBackground(GetFileCallback fileCallback) {
        ParseTaskUtils.callbackOnMainThreadAsync(getFileInBackground(), (ParseCallback2) fileCallback);
    }

    public InputStream getDataStream() throws ParseException {
        return (InputStream) ParseTaskUtils.wait(getDataStreamInBackground());
    }

    public Task<InputStream> getDataStreamInBackground(final ProgressCallback progressCallback) {
        final TaskCompletionSource<Void> cts = new TaskCompletionSource();
        this.currentTasks.add(cts);
        return this.taskQueue.enqueue(new Continuation<Void, Task<InputStream>>() {
            public Task<InputStream> then(Task<Void> toAwait) throws Exception {
                return ParseFile.this.fetchInBackground(progressCallback, toAwait, cts.getTask()).onSuccess(new Continuation<File, InputStream>() {
                    public InputStream then(Task<File> task) throws Exception {
                        return new FileInputStream((File) task.getResult());
                    }
                });
            }
        }).continueWithTask(new Continuation<InputStream, Task<InputStream>>() {
            public Task<InputStream> then(Task<InputStream> task) throws Exception {
                cts.trySetResult(null);
                ParseFile.this.currentTasks.remove(cts);
                return task;
            }
        });
    }

    public Task<InputStream> getDataStreamInBackground() {
        return getDataStreamInBackground((ProgressCallback) null);
    }

    public void getDataStreamInBackground(GetDataStreamCallback dataStreamCallback, ProgressCallback progressCallback) {
        ParseTaskUtils.callbackOnMainThreadAsync(getDataStreamInBackground(progressCallback), (ParseCallback2) dataStreamCallback);
    }

    public void getDataStreamInBackground(GetDataStreamCallback dataStreamCallback) {
        ParseTaskUtils.callbackOnMainThreadAsync(getDataStreamInBackground(), (ParseCallback2) dataStreamCallback);
    }

    private Task<File> fetchInBackground(final ProgressCallback progressCallback, Task<Void> toAwait, final Task<Void> cancellationToken) {
        if (cancellationToken == null || !cancellationToken.isCancelled()) {
            return toAwait.continueWithTask(new Continuation<Void, Task<File>>() {
                public Task<File> then(Task<Void> task) throws Exception {
                    if (cancellationToken == null || !cancellationToken.isCancelled()) {
                        return ParseFile.getFileController().fetchAsync(ParseFile.this.state, null, ParseFile.progressCallbackOnMainThread(progressCallback), cancellationToken);
                    }
                    return Task.cancelled();
                }
            });
        }
        return Task.cancelled();
    }

    public void cancel() {
        Set<TaskCompletionSource<?>> tasks = new HashSet(this.currentTasks);
        for (TaskCompletionSource<?> tcs : tasks) {
            tcs.trySetCancelled();
        }
        this.currentTasks.removeAll(tasks);
    }

    ParseFile(JSONObject json, ParseDecoder decoder) {
        this(new Builder().name(json.optString(FriendRecord.NAME)).url(json.optString("url")).build());
    }

    JSONObject encode() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("__type", "File");
        json.put(FriendRecord.NAME, getName());
        if (getUrl() == null) {
            throw new IllegalStateException("Unable to encode an unsaved ParseFile.");
        }
        json.put("url", getUrl());
        return json;
    }
}
