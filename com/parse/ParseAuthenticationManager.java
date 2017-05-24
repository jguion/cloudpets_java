package com.parse;

import bolts.Continuation;
import bolts.Task;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

class ParseAuthenticationManager {
    private final Map<String, AuthenticationCallback> callbacks = new HashMap();
    private final ParseCurrentUserController controller;
    private final Object lock = new Object();

    public ParseAuthenticationManager(ParseCurrentUserController controller) {
        this.controller = controller;
    }

    public void register(final String authType, AuthenticationCallback callback) {
        if (authType == null) {
            throw new IllegalArgumentException("Invalid authType: " + null);
        }
        synchronized (this.lock) {
            if (this.callbacks.containsKey(authType)) {
                throw new IllegalStateException("Callback already registered for <" + authType + ">: " + this.callbacks.get(authType));
            }
            this.callbacks.put(authType, callback);
        }
        if (!"anonymous".equals(authType)) {
            this.controller.getAsync(false).onSuccessTask(new Continuation<ParseUser, Task<Void>>() {
                public Task<Void> then(Task<ParseUser> task) throws Exception {
                    ParseUser user = (ParseUser) task.getResult();
                    if (user != null) {
                        return user.synchronizeAuthDataAsync(authType);
                    }
                    return null;
                }
            });
        }
    }

    public Task<Boolean> restoreAuthenticationAsync(String authType, final Map<String, String> authData) {
        synchronized (this.lock) {
            final AuthenticationCallback callback = (AuthenticationCallback) this.callbacks.get(authType);
        }
        if (callback == null) {
            return Task.forResult(Boolean.valueOf(true));
        }
        return Task.call(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return Boolean.valueOf(callback.onRestore(authData));
            }
        }, ParseExecutors.io());
    }

    public Task<Void> deauthenticateAsync(String authType) {
        synchronized (this.lock) {
            final AuthenticationCallback callback = (AuthenticationCallback) this.callbacks.get(authType);
        }
        if (callback != null) {
            return Task.call(new Callable<Void>() {
                public Void call() throws Exception {
                    callback.onRestore(null);
                    return null;
                }
            }, ParseExecutors.io());
        }
        return Task.forResult(null);
    }
}
