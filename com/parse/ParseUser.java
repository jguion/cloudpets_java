package com.parse;

import bolts.Continuation;
import bolts.Task;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

@ParseClassName("_User")
public class ParseUser extends ParseObject {
    private static final String KEY_AUTH_DATA = "authData";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_SESSION_TOKEN = "sessionToken";
    private static final String KEY_USERNAME = "username";
    private static final List<String> READ_ONLY_KEYS = Collections.unmodifiableList(Arrays.asList(new String[]{KEY_SESSION_TOKEN, KEY_AUTH_DATA}));
    private static boolean autoUserEnabled;
    private static final Object isAutoUserEnabledMutex = new Object();
    private boolean isCurrentUser = false;

    static class State extends State {
        private final boolean isNew;

        static class Builder extends Init<Builder> {
            private boolean isNew;

            public Builder() {
                super("_User");
            }

            Builder(State state) {
                super((State) state);
                this.isNew = state.isNew();
            }

            Builder self() {
                return this;
            }

            public State build() {
                return new State();
            }

            public Builder apply(State other) {
                isNew(((State) other).isNew());
                return (Builder) super.apply(other);
            }

            public Builder sessionToken(String sessionToken) {
                return (Builder) put(ParseUser.KEY_SESSION_TOKEN, sessionToken);
            }

            public Builder authData(Map<String, Map<String, String>> authData) {
                return (Builder) put(ParseUser.KEY_AUTH_DATA, authData);
            }

            public Builder putAuthData(String authType, Map<String, String> authData) {
                Map<String, Map<String, String>> newAuthData = (Map) this.serverData.get(ParseUser.KEY_AUTH_DATA);
                if (newAuthData == null) {
                    newAuthData = new HashMap();
                }
                newAuthData.put(authType, authData);
                this.serverData.put(ParseUser.KEY_AUTH_DATA, newAuthData);
                return this;
            }

            public Builder isNew(boolean isNew) {
                this.isNew = isNew;
                return this;
            }
        }

        private State(Builder builder) {
            super(builder);
            this.isNew = builder.isNew;
        }

        public Builder newBuilder() {
            return new Builder(this);
        }

        public String sessionToken() {
            return (String) get(ParseUser.KEY_SESSION_TOKEN);
        }

        public Map<String, Map<String, String>> authData() {
            Map<String, Map<String, String>> authData = (Map) get(ParseUser.KEY_AUTH_DATA);
            if (authData == null) {
                return new HashMap();
            }
            return authData;
        }

        public boolean isNew() {
            return this.isNew;
        }
    }

    public static ParseQuery<ParseUser> getQuery() {
        return ParseQuery.getQuery(ParseUser.class);
    }

    static ParseUserController getUserController() {
        return ParseCorePlugins.getInstance().getUserController();
    }

    static ParseCurrentUserController getCurrentUserController() {
        return ParseCorePlugins.getInstance().getCurrentUserController();
    }

    static ParseAuthenticationManager getAuthenticationManager() {
        return ParseCorePlugins.getInstance().getAuthenticationManager();
    }

    boolean needsDefaultACL() {
        return false;
    }

    boolean isKeyMutable(String key) {
        return !READ_ONLY_KEYS.contains(key);
    }

    Builder newStateBuilder(String className) {
        return new Builder();
    }

    State getState() {
        return (State) super.getState();
    }

    boolean isLazy() {
        boolean z;
        synchronized (this.mutex) {
            z = getObjectId() == null && ParseAnonymousUtils.isLinked(this);
        }
        return z;
    }

    public boolean isAuthenticated() {
        boolean z;
        synchronized (this.mutex) {
            ParseUser current = getCurrentUser();
            z = isLazy() || !(getState().sessionToken() == null || current == null || !getObjectId().equals(current.getObjectId()));
        }
        return z;
    }

    public void remove(String key) {
        if ("username".equals(key)) {
            throw new IllegalArgumentException("Can't remove the username key.");
        }
        super.remove(key);
    }

    JSONObject toRest(State state, List<ParseOperationSet> operationSetQueue, ParseEncoder objectEncoder) {
        List<ParseOperationSet> cleanOperationSetQueue = operationSetQueue;
        for (int i = 0; i < operationSetQueue.size(); i++) {
            ParseOperationSet operations = (ParseOperationSet) operationSetQueue.get(i);
            if (operations.containsKey(KEY_PASSWORD)) {
                if (cleanOperationSetQueue == operationSetQueue) {
                    cleanOperationSetQueue = new LinkedList(operationSetQueue);
                }
                ParseOperationSet cleanOperations = new ParseOperationSet(operations);
                cleanOperations.remove(KEY_PASSWORD);
                cleanOperationSetQueue.set(i, cleanOperations);
            }
        }
        return super.toRest(state, cleanOperationSetQueue, objectEncoder);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    bolts.Task<java.lang.Void> cleanUpAuthDataAsync() {
        /*
        r9 = this;
        r8 = 0;
        r1 = getAuthenticationManager();
        r7 = r9.mutex;
        monitor-enter(r7);
        r6 = r9.getState();	 Catch:{ all -> 0x0052 }
        r0 = r6.authData();	 Catch:{ all -> 0x0052 }
        r6 = r0.size();	 Catch:{ all -> 0x0052 }
        if (r6 != 0) goto L_0x001d;
    L_0x0016:
        r6 = 0;
        r6 = bolts.Task.forResult(r6);	 Catch:{ all -> 0x0052 }
        monitor-exit(r7);	 Catch:{ all -> 0x0052 }
    L_0x001c:
        return r6;
    L_0x001d:
        monitor-exit(r7);	 Catch:{ all -> 0x0052 }
        r5 = new java.util.ArrayList;
        r5.<init>();
        r6 = r0.entrySet();
        r3 = r6.iterator();
    L_0x002b:
        r6 = r3.hasNext();
        if (r6 == 0) goto L_0x0055;
    L_0x0031:
        r2 = r3.next();
        r2 = (java.util.Map.Entry) r2;
        r6 = r2.getValue();
        if (r6 != 0) goto L_0x002b;
    L_0x003d:
        r3.remove();
        r6 = r2.getKey();
        r6 = (java.lang.String) r6;
        r6 = r1.restoreAuthenticationAsync(r6, r8);
        r6 = r6.makeVoid();
        r5.add(r6);
        goto L_0x002b;
    L_0x0052:
        r6 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x0052 }
        throw r6;
    L_0x0055:
        r6 = r9.getState();
        r6 = r6.newBuilder();
        r6 = r6.authData(r0);
        r4 = r6.build();
        r9.setState(r4);
        r6 = bolts.Task.whenAll(r5);
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.parse.ParseUser.cleanUpAuthDataAsync():bolts.Task<java.lang.Void>");
    }

    Task<Void> handleSaveResultAsync(State result, ParseOperationSet operationsBeforeSave) {
        if (result != null) {
            operationsBeforeSave.remove(KEY_PASSWORD);
        }
        return super.handleSaveResultAsync(result, operationsBeforeSave);
    }

    void validateSaveEventually() throws ParseException {
        if (isDirty(KEY_PASSWORD)) {
            throw new ParseException(-1, "Unable to saveEventually on a ParseUser with dirty password");
        }
    }

    boolean isCurrentUser() {
        boolean z;
        synchronized (this.mutex) {
            z = this.isCurrentUser;
        }
        return z;
    }

    void setIsCurrentUser(boolean isCurrentUser) {
        synchronized (this.mutex) {
            this.isCurrentUser = isCurrentUser;
        }
    }

    public String getSessionToken() {
        return getState().sessionToken();
    }

    private Task<Void> setSessionTokenInBackground(String newSessionToken) {
        Task<Void> forResult;
        synchronized (this.mutex) {
            State state = getState();
            if (newSessionToken.equals(state.sessionToken())) {
                forResult = Task.forResult(null);
            } else {
                setState(state.newBuilder().sessionToken(newSessionToken).build());
                forResult = saveCurrentUserAsync(this);
            }
        }
        return forResult;
    }

    Map<String, Map<String, String>> getAuthData() {
        Map<String, Map<String, String>> authData;
        synchronized (this.mutex) {
            authData = getMap(KEY_AUTH_DATA);
            if (authData == null) {
                authData = new HashMap();
            }
        }
        return authData;
    }

    private Map<String, String> getAuthData(String authType) {
        return (Map) getAuthData().get(authType);
    }

    void putAuthData(String authType, Map<String, String> authData) {
        synchronized (this.mutex) {
            Map<String, Map<String, String>> newAuthData = getAuthData();
            newAuthData.put(authType, authData);
            performPut(KEY_AUTH_DATA, newAuthData);
        }
    }

    private void removeAuthData(String authType) {
        synchronized (this.mutex) {
            Map<String, Map<String, String>> newAuthData = getAuthData();
            newAuthData.remove(authType);
            performPut(KEY_AUTH_DATA, newAuthData);
        }
    }

    public void setUsername(String username) {
        put("username", username);
    }

    public String getUsername() {
        return getString("username");
    }

    public void setPassword(String password) {
        put(KEY_PASSWORD, password);
    }

    String getPassword() {
        return getString(KEY_PASSWORD);
    }

    public void setEmail(String email) {
        put("email", email);
    }

    public String getEmail() {
        return getString("email");
    }

    public boolean isNew() {
        return getState().isNew();
    }

    public void put(String key, Object value) {
        synchronized (this.mutex) {
            if ("username".equals(key)) {
                stripAnonymity();
            }
            super.put(key, value);
        }
    }

    private void stripAnonymity() {
        synchronized (this.mutex) {
            if (ParseAnonymousUtils.isLinked(this)) {
                if (getObjectId() != null) {
                    putAuthData("anonymous", null);
                } else {
                    removeAuthData("anonymous");
                }
            }
        }
    }

    private void restoreAnonymity(Map<String, String> anonymousData) {
        synchronized (this.mutex) {
            if (anonymousData != null) {
                putAuthData("anonymous", anonymousData);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void validateSave() {
        /*
        r4 = this;
        r2 = r4.mutex;
        monitor-enter(r2);
        r1 = r4.getObjectId();	 Catch:{ all -> 0x0011 }
        if (r1 != 0) goto L_0x0014;
    L_0x0009:
        r1 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x0011 }
        r3 = "Cannot save a ParseUser until it has been signed up. Call signUp first.";
        r1.<init>(r3);	 Catch:{ all -> 0x0011 }
        throw r1;	 Catch:{ all -> 0x0011 }
    L_0x0011:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0011 }
        throw r1;
    L_0x0014:
        r1 = r4.isAuthenticated();	 Catch:{ all -> 0x0011 }
        if (r1 != 0) goto L_0x0026;
    L_0x001a:
        r1 = r4.isDirty();	 Catch:{ all -> 0x0011 }
        if (r1 == 0) goto L_0x0026;
    L_0x0020:
        r1 = r4.isCurrentUser();	 Catch:{ all -> 0x0011 }
        if (r1 == 0) goto L_0x0028;
    L_0x0026:
        monitor-exit(r2);	 Catch:{ all -> 0x0011 }
    L_0x0027:
        return;
    L_0x0028:
        monitor-exit(r2);	 Catch:{ all -> 0x0011 }
        r1 = com.parse.Parse.isLocalDatastoreEnabled();
        if (r1 != 0) goto L_0x0043;
    L_0x002f:
        r0 = getCurrentUser();
        if (r0 == 0) goto L_0x0043;
    L_0x0035:
        r1 = r4.getObjectId();
        r2 = r0.getObjectId();
        r1 = r1.equals(r2);
        if (r1 != 0) goto L_0x0027;
    L_0x0043:
        r1 = new java.lang.IllegalArgumentException;
        r2 = "Cannot save a ParseUser that is not authenticated.";
        r1.<init>(r2);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.parse.ParseUser.validateSave():void");
    }

    Task<Void> saveAsync(String sessionToken, Task<Void> toAwait) {
        return saveAsync(sessionToken, isLazy(), toAwait);
    }

    Task<Void> saveAsync(String sessionToken, boolean isLazy, Task<Void> toAwait) {
        Task<Void> task;
        if (isLazy) {
            task = resolveLazinessAsync(toAwait);
        } else {
            task = super.saveAsync(sessionToken, toAwait);
        }
        if (isCurrentUser()) {
            return task.onSuccessTask(new Continuation<Void, Task<Void>>() {
                public Task<Void> then(Task<Void> task) throws Exception {
                    return ParseUser.this.cleanUpAuthDataAsync();
                }
            }).onSuccessTask(new Continuation<Void, Task<Void>>() {
                public Task<Void> then(Task<Void> task) throws Exception {
                    return ParseUser.saveCurrentUserAsync(ParseUser.this);
                }
            });
        }
        return task;
    }

    void setState(State newState) {
        if (isCurrentUser()) {
            Builder newStateBuilder = (Builder) newState.newBuilder();
            if (getSessionToken() != null && newState.get(KEY_SESSION_TOKEN) == null) {
                newStateBuilder.put(KEY_SESSION_TOKEN, getSessionToken());
            }
            if (getAuthData().size() > 0 && newState.get(KEY_AUTH_DATA) == null) {
                newStateBuilder.put(KEY_AUTH_DATA, getAuthData());
            }
            newState = newStateBuilder.build();
        }
        super.setState(newState);
    }

    void validateDelete() {
        synchronized (this.mutex) {
            super.validateDelete();
            if (isAuthenticated() || !isDirty()) {
            } else {
                throw new IllegalArgumentException("Cannot delete a ParseUser that is not authenticated.");
            }
        }
    }

    public ParseUser fetch() throws ParseException {
        return (ParseUser) super.fetch();
    }

    <T extends ParseObject> Task<T> fetchAsync(String sessionToken, Task<Void> toAwait) {
        if (isLazy()) {
            return Task.forResult(this);
        }
        Task<T> task = super.fetchAsync(sessionToken, toAwait);
        if (isCurrentUser()) {
            return task.onSuccessTask(new Continuation<T, Task<Void>>() {
                public Task<Void> then(Task<T> task) throws Exception {
                    return ParseUser.this.cleanUpAuthDataAsync();
                }
            }).onSuccessTask(new Continuation<Void, Task<Void>>() {
                public Task<Void> then(Task<Void> task) throws Exception {
                    return ParseUser.saveCurrentUserAsync(ParseUser.this);
                }
            }).onSuccess(new Continuation<Void, T>() {
                public T then(Task<Void> task) throws Exception {
                    return ParseUser.this;
                }
            });
        }
        return task;
    }

    public Task<Void> signUpInBackground() {
        return this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() {
            public Task<Void> then(Task<Void> task) throws Exception {
                return ParseUser.this.signUpAsync(task);
            }
        });
    }

    Task<Void> signUpAsync(Task<Void> toAwait) {
        Task<Void> forError;
        final ParseUser user = getCurrentUser();
        synchronized (this.mutex) {
            final String sessionToken = user != null ? user.getSessionToken() : null;
            if (ParseTextUtils.isEmpty(getUsername())) {
                forError = Task.forError(new IllegalArgumentException("Username cannot be missing or blank"));
            } else if (ParseTextUtils.isEmpty(getPassword())) {
                forError = Task.forError(new IllegalArgumentException("Password cannot be missing or blank"));
            } else if (getObjectId() != null) {
                Map<String, Map<String, String>> authData = getAuthData();
                if (authData.containsKey("anonymous") && authData.get("anonymous") == null) {
                    forError = saveAsync(sessionToken, toAwait);
                } else {
                    forError = Task.forError(new IllegalArgumentException("Cannot sign up a user that has already signed up."));
                }
            } else if (this.operationSetQueue.size() > 1) {
                forError = Task.forError(new IllegalArgumentException("Cannot sign up a user that is already signing up."));
            } else if (user == null || !ParseAnonymousUtils.isLinked(user)) {
                final ParseOperationSet operations = startSave();
                forError = toAwait.onSuccessTask(new Continuation<Void, Task<Void>>() {
                    public Task<Void> then(Task<Void> task) throws Exception {
                        return ParseUser.getUserController().signUpAsync(ParseUser.this.getState(), operations, sessionToken).continueWithTask(new Continuation<State, Task<Void>>() {
                            public Task<Void> then(final Task<State> signUpTask) throws Exception {
                                return ParseUser.this.handleSaveResultAsync((State) signUpTask.getResult(), operations).continueWithTask(new Continuation<Void, Task<Void>>() {
                                    public Task<Void> then(Task<Void> task) throws Exception {
                                        if (signUpTask.isCancelled() || signUpTask.isFaulted()) {
                                            return signUpTask.makeVoid();
                                        }
                                        return ParseUser.saveCurrentUserAsync(ParseUser.this);
                                    }
                                });
                            }
                        });
                    }
                });
            } else if (this == user) {
                forError = Task.forError(new IllegalArgumentException("Attempt to merge currentUser with itself."));
            } else {
                boolean isLazy = user.isLazy();
                final String oldUsername = user.getUsername();
                final String oldPassword = user.getPassword();
                final Map<String, String> anonymousData = user.getAuthData("anonymous");
                user.copyChangesFrom(this);
                user.setUsername(getUsername());
                user.setPassword(getPassword());
                revert();
                forError = user.saveAsync(sessionToken, isLazy, toAwait).continueWithTask(new Continuation<Void, Task<Void>>() {
                    public Task<Void> then(Task<Void> task) throws Exception {
                        if (task.isCancelled() || task.isFaulted()) {
                            synchronized (user.mutex) {
                                if (oldUsername != null) {
                                    user.setUsername(oldUsername);
                                } else {
                                    user.revert("username");
                                }
                                if (oldPassword != null) {
                                    user.setPassword(oldPassword);
                                } else {
                                    user.revert(ParseUser.KEY_PASSWORD);
                                }
                                user.restoreAnonymity(anonymousData);
                            }
                            return task;
                        }
                        user.revert(ParseUser.KEY_PASSWORD);
                        ParseUser.this.revert(ParseUser.KEY_PASSWORD);
                        ParseUser.this.mergeFromObject(user);
                        return ParseUser.saveCurrentUserAsync(ParseUser.this);
                    }
                });
            }
        }
        return forError;
    }

    public void signUp() throws ParseException {
        ParseTaskUtils.wait(signUpInBackground());
    }

    public void signUpInBackground(SignUpCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(signUpInBackground(), (ParseCallback1) callback);
    }

    public static Task<ParseUser> logInInBackground(String username, String password) {
        if (username == null) {
            throw new IllegalArgumentException("Must specify a username for the user to log in with");
        } else if (password != null) {
            return getUserController().logInAsync(username, password).onSuccessTask(new Continuation<State, Task<ParseUser>>() {
                public Task<ParseUser> then(Task<State> task) throws Exception {
                    final ParseUser newCurrent = (ParseUser) ParseObject.from((State) task.getResult());
                    return ParseUser.saveCurrentUserAsync(newCurrent).onSuccess(new Continuation<Void, ParseUser>() {
                        public ParseUser then(Task<Void> task) throws Exception {
                            return newCurrent;
                        }
                    });
                }
            });
        } else {
            throw new IllegalArgumentException("Must specify a password for the user to log in with");
        }
    }

    public static ParseUser logIn(String username, String password) throws ParseException {
        return (ParseUser) ParseTaskUtils.wait(logInInBackground(username, password));
    }

    public static void logInInBackground(String username, String password, LogInCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(logInInBackground(username, password), (ParseCallback2) callback);
    }

    public static Task<ParseUser> becomeInBackground(String sessionToken) {
        if (sessionToken != null) {
            return getUserController().getUserAsync(sessionToken).onSuccessTask(new Continuation<State, Task<ParseUser>>() {
                public Task<ParseUser> then(Task<State> task) throws Exception {
                    final ParseUser user = (ParseUser) ParseObject.from((State) task.getResult());
                    return ParseUser.saveCurrentUserAsync(user).onSuccess(new Continuation<Void, ParseUser>() {
                        public ParseUser then(Task<Void> task) throws Exception {
                            return user;
                        }
                    });
                }
            });
        }
        throw new IllegalArgumentException("Must specify a sessionToken for the user to log in with");
    }

    public static ParseUser become(String sessionToken) throws ParseException {
        return (ParseUser) ParseTaskUtils.wait(becomeInBackground(sessionToken));
    }

    public static void becomeInBackground(String sessionToken, LogInCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(becomeInBackground(sessionToken), (ParseCallback2) callback);
    }

    static Task<ParseUser> getCurrentUserAsync() {
        return getCurrentUserController().getAsync();
    }

    public static ParseUser getCurrentUser() {
        return getCurrentUser(isAutomaticUserEnabled());
    }

    private static ParseUser getCurrentUser(boolean shouldAutoCreateUser) {
        try {
            return (ParseUser) ParseTaskUtils.wait(getCurrentUserController().getAsync(shouldAutoCreateUser));
        } catch (ParseException e) {
            return null;
        }
    }

    static String getCurrentSessionToken() {
        ParseUser current = getCurrentUser();
        return current != null ? current.getSessionToken() : null;
    }

    static Task<String> getCurrentSessionTokenAsync() {
        return getCurrentUserController().getCurrentSessionTokenAsync();
    }

    private static Task<Void> saveCurrentUserAsync(ParseUser user) {
        return getCurrentUserController().setAsync(user);
    }

    static Task<Void> pinCurrentUserIfNeededAsync(ParseUser user) {
        if (Parse.isLocalDatastoreEnabled()) {
            return getCurrentUserController().setIfNeededAsync(user);
        }
        throw new IllegalStateException("Method requires Local Datastore. Please refer to `Parse#enableLocalDatastore(Context)`.");
    }

    public static Task<Void> logOutInBackground() {
        return getCurrentUserController().logOutAsync();
    }

    public static void logOutInBackground(LogOutCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(logOutInBackground(), (ParseCallback1) callback);
    }

    public static void logOut() {
        try {
            ParseTaskUtils.wait(logOutInBackground());
        } catch (ParseException e) {
        }
    }

    Task<Void> logOutAsync() {
        return logOutAsync(true);
    }

    Task<Void> logOutAsync(boolean revoke) {
        ParseAuthenticationManager controller = getAuthenticationManager();
        List<Task<Void>> tasks = new ArrayList();
        synchronized (this.mutex) {
            String oldSessionToken = getState().sessionToken();
            for (Entry<String, Map<String, String>> entry : getAuthData().entrySet()) {
                tasks.add(controller.deauthenticateAsync((String) entry.getKey()));
            }
            State newState = getState().newBuilder().sessionToken(null).isNew(false).build();
            this.isCurrentUser = false;
            setState(newState);
        }
        if (revoke) {
            tasks.add(ParseSession.revokeAsync(oldSessionToken));
        }
        return Task.whenAll(tasks);
    }

    public static Task<Void> requestPasswordResetInBackground(String email) {
        return getUserController().requestPasswordResetAsync(email);
    }

    public static void requestPasswordReset(String email) throws ParseException {
        ParseTaskUtils.wait(requestPasswordResetInBackground(email));
    }

    public static void requestPasswordResetInBackground(String email, RequestPasswordResetCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(requestPasswordResetInBackground(email), (ParseCallback1) callback);
    }

    public ParseUser fetchIfNeeded() throws ParseException {
        return (ParseUser) super.fetchIfNeeded();
    }

    public static void registerAuthenticationCallback(String authType, AuthenticationCallback callback) {
        getAuthenticationManager().register(authType, callback);
    }

    public static Task<ParseUser> logInWithInBackground(final String authType, final Map<String, String> authData) {
        if (authType == null) {
            throw new IllegalArgumentException("Invalid authType: " + null);
        }
        final Continuation<Void, Task<ParseUser>> logInWithTask = new Continuation<Void, Task<ParseUser>>() {
            public Task<ParseUser> then(Task<Void> task) throws Exception {
                return ParseUser.getUserController().logInAsync(authType, authData).onSuccessTask(new Continuation<State, Task<ParseUser>>() {
                    public Task<ParseUser> then(Task<State> task) throws Exception {
                        final ParseUser user = (ParseUser) ParseObject.from((State) task.getResult());
                        return ParseUser.saveCurrentUserAsync(user).onSuccess(new Continuation<Void, ParseUser>() {
                            public ParseUser then(Task<Void> task) throws Exception {
                                return user;
                            }
                        });
                    }
                });
            }
        };
        return getCurrentUserController().getAsync(false).onSuccessTask(new Continuation<ParseUser, Task<ParseUser>>() {
            public Task<ParseUser> then(Task<ParseUser> task) throws Exception {
                final ParseUser user = (ParseUser) task.getResult();
                if (user != null) {
                    synchronized (user.mutex) {
                        if (ParseAnonymousUtils.isLinked(user)) {
                            Task<ParseUser> enqueue;
                            if (user.isLazy()) {
                                final Map<String, String> oldAnonymousData = user.getAuthData("anonymous");
                                enqueue = user.taskQueue.enqueue(new Continuation<Void, Task<ParseUser>>() {
                                    public Task<ParseUser> then(Task<Void> toAwait) throws Exception {
                                        return toAwait.continueWithTask(new Continuation<Void, Task<Void>>() {
                                            public Task<Void> then(Task<Void> task) throws Exception {
                                                Task<Void> resolveLazinessAsync;
                                                synchronized (user.mutex) {
                                                    user.stripAnonymity();
                                                    user.putAuthData(authType, authData);
                                                    resolveLazinessAsync = user.resolveLazinessAsync(task);
                                                }
                                                return resolveLazinessAsync;
                                            }
                                        }).continueWithTask(new Continuation<Void, Task<ParseUser>>() {
                                            public Task<ParseUser> then(Task<Void> task) throws Exception {
                                                Task<ParseUser> forError;
                                                synchronized (user.mutex) {
                                                    if (task.isFaulted()) {
                                                        user.removeAuthData(authType);
                                                        user.restoreAnonymity(oldAnonymousData);
                                                        forError = Task.forError(task.getError());
                                                    } else if (task.isCancelled()) {
                                                        forError = Task.cancelled();
                                                    } else {
                                                        forError = Task.forResult(user);
                                                    }
                                                }
                                                return forError;
                                            }
                                        });
                                    }
                                });
                                return enqueue;
                            }
                            enqueue = user.linkWithInBackground(authType, authData).continueWithTask(new Continuation<Void, Task<ParseUser>>() {
                                public Task<ParseUser> then(Task<Void> task) throws Exception {
                                    if (task.isFaulted()) {
                                        Exception error = task.getError();
                                        if ((error instanceof ParseException) && ((ParseException) error).getCode() == ParseException.ACCOUNT_ALREADY_LINKED) {
                                            return Task.forResult(null).continueWithTask(logInWithTask);
                                        }
                                    }
                                    if (task.isCancelled()) {
                                        return Task.cancelled();
                                    }
                                    return Task.forResult(user);
                                }
                            });
                            return enqueue;
                        }
                    }
                }
                return Task.forResult(null).continueWithTask(logInWithTask);
            }
        });
    }

    public boolean isLinked(String authType) {
        Map<String, Map<String, String>> authData = getAuthData();
        return authData.containsKey(authType) && authData.get(authType) != null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    bolts.Task<java.lang.Void> synchronizeAllAuthDataAsync() {
        /*
        r5 = this;
        r4 = r5.mutex;
        monitor-enter(r4);
        r3 = r5.isCurrentUser();	 Catch:{ all -> 0x003a }
        if (r3 != 0) goto L_0x0010;
    L_0x0009:
        r3 = 0;
        r3 = bolts.Task.forResult(r3);	 Catch:{ all -> 0x003a }
        monitor-exit(r4);	 Catch:{ all -> 0x003a }
    L_0x000f:
        return r3;
    L_0x0010:
        r0 = r5.getAuthData();	 Catch:{ all -> 0x003a }
        monitor-exit(r4);	 Catch:{ all -> 0x003a }
        r2 = new java.util.ArrayList;
        r3 = r0.size();
        r2.<init>(r3);
        r3 = r0.keySet();
        r3 = r3.iterator();
    L_0x0026:
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x003d;
    L_0x002c:
        r1 = r3.next();
        r1 = (java.lang.String) r1;
        r4 = r5.synchronizeAuthDataAsync(r1);
        r2.add(r4);
        goto L_0x0026;
    L_0x003a:
        r3 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x003a }
        throw r3;
    L_0x003d:
        r3 = bolts.Task.whenAll(r2);
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.parse.ParseUser.synchronizeAllAuthDataAsync():bolts.Task<java.lang.Void>");
    }

    Task<Void> synchronizeAuthDataAsync(String authType) {
        synchronized (this.mutex) {
            if (isCurrentUser()) {
                Map<String, String> authData = getAuthData(authType);
                return synchronizeAuthDataAsync(getAuthenticationManager(), authType, authData);
            }
            Task<Void> forResult = Task.forResult(null);
            return forResult;
        }
    }

    private Task<Void> synchronizeAuthDataAsync(ParseAuthenticationManager manager, final String authType, Map<String, String> authData) {
        return manager.restoreAuthenticationAsync(authType, authData).continueWithTask(new Continuation<Boolean, Task<Void>>() {
            public Task<Void> then(Task<Boolean> task) throws Exception {
                boolean success = !task.isFaulted() && ((Boolean) task.getResult()).booleanValue();
                if (success) {
                    return task.makeVoid();
                }
                return ParseUser.this.unlinkFromInBackground(authType);
            }
        });
    }

    private Task<Void> linkWithAsync(final String authType, Map<String, String> authData, Task<Void> toAwait, String sessionToken) {
        Task<Void> continueWithTask;
        synchronized (this.mutex) {
            boolean isLazy = isLazy();
            final Map<String, String> oldAnonymousData = getAuthData("anonymous");
            stripAnonymity();
            putAuthData(authType, authData);
            continueWithTask = saveAsync(sessionToken, isLazy, toAwait).continueWithTask(new Continuation<Void, Task<Void>>() {
                public Task<Void> then(Task<Void> task) throws Exception {
                    synchronized (ParseUser.this.mutex) {
                        if (task.isFaulted() || task.isCancelled()) {
                            ParseUser.this.restoreAnonymity(oldAnonymousData);
                        } else {
                            task = ParseUser.this.synchronizeAuthDataAsync(authType);
                        }
                    }
                    return task;
                }
            });
        }
        return continueWithTask;
    }

    private Task<Void> linkWithAsync(final String authType, final Map<String, String> authData, final String sessionToken) {
        return this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() {
            public Task<Void> then(Task<Void> task) throws Exception {
                return ParseUser.this.linkWithAsync(authType, authData, task, sessionToken);
            }
        });
    }

    public Task<Void> linkWithInBackground(String authType, Map<String, String> authData) {
        if (authType != null) {
            return linkWithAsync(authType, authData, getSessionToken());
        }
        throw new IllegalArgumentException("Invalid authType: " + null);
    }

    public Task<Void> unlinkFromInBackground(String authType) {
        if (authType == null) {
            return Task.forResult(null);
        }
        synchronized (this.mutex) {
            if (getAuthData().containsKey(authType)) {
                putAuthData(authType, null);
                return saveInBackground();
            }
            Task<Void> forResult = Task.forResult(null);
            return forResult;
        }
    }

    Task<Void> resolveLazinessAsync(Task<Void> toAwait) {
        Task<Void> signUpAsync;
        synchronized (this.mutex) {
            if (getAuthData().size() == 0) {
                signUpAsync = signUpAsync(toAwait);
            } else {
                final ParseOperationSet operations = startSave();
                signUpAsync = toAwait.onSuccessTask(new Continuation<Void, Task<Void>>() {
                    public Task<Void> then(Task<Void> task) throws Exception {
                        return ParseUser.getUserController().logInAsync(ParseUser.this.getState(), operations).onSuccessTask(new Continuation<State, Task<Void>>() {
                            public Task<Void> then(Task<State> task) throws Exception {
                                Task<State> resultTask;
                                final State result = (State) task.getResult();
                                if (!Parse.isLocalDatastoreEnabled() || result.isNew()) {
                                    resultTask = ParseUser.this.handleSaveResultAsync(result, operations).onSuccess(new Continuation<Void, State>() {
                                        public State then(Task<Void> task) throws Exception {
                                            return result;
                                        }
                                    });
                                } else {
                                    resultTask = Task.forResult(result);
                                }
                                return resultTask.onSuccessTask(new Continuation<State, Task<Void>>() {
                                    public Task<Void> then(Task<State> task) throws Exception {
                                        State result = (State) task.getResult();
                                        if (result.isNew()) {
                                            return task.makeVoid();
                                        }
                                        return ParseUser.saveCurrentUserAsync((ParseUser) ParseObject.from(result));
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
        return signUpAsync;
    }

    <T extends ParseObject> Task<T> fetchFromLocalDatastoreAsync() {
        if (isLazy()) {
            return Task.forResult(this);
        }
        return super.fetchFromLocalDatastoreAsync();
    }

    public static void enableAutomaticUser() {
        synchronized (isAutoUserEnabledMutex) {
            autoUserEnabled = true;
        }
    }

    static void disableAutomaticUser() {
        synchronized (isAutoUserEnabledMutex) {
            autoUserEnabled = false;
        }
    }

    static boolean isAutomaticUserEnabled() {
        boolean z;
        synchronized (isAutoUserEnabledMutex) {
            z = autoUserEnabled;
        }
        return z;
    }

    public static Task<Void> enableRevocableSessionInBackground() {
        ParseCorePlugins.getInstance().registerUserController(new NetworkUserController(ParsePlugins.get().restClient(), true));
        return getCurrentUserController().getAsync(false).onSuccessTask(new Continuation<ParseUser, Task<Void>>() {
            public Task<Void> then(Task<ParseUser> task) throws Exception {
                ParseUser user = (ParseUser) task.getResult();
                if (user == null) {
                    return Task.forResult(null);
                }
                return user.upgradeToRevocableSessionAsync();
            }
        });
    }

    Task<Void> upgradeToRevocableSessionAsync() {
        return this.taskQueue.enqueue(new Continuation<Void, Task<Void>>() {
            public Task<Void> then(Task<Void> toAwait) throws Exception {
                return ParseUser.this.upgradeToRevocableSessionAsync(toAwait);
            }
        });
    }

    private Task<Void> upgradeToRevocableSessionAsync(Task<Void> toAwait) {
        final String sessionToken = getSessionToken();
        return toAwait.continueWithTask(new Continuation<Void, Task<String>>() {
            public Task<String> then(Task<Void> task) throws Exception {
                return ParseSession.upgradeToRevocableSessionAsync(sessionToken);
            }
        }).onSuccessTask(new Continuation<String, Task<Void>>() {
            public Task<Void> then(Task<String> task) throws Exception {
                return ParseUser.this.setSessionTokenInBackground((String) task.getResult());
            }
        });
    }
}
