package com.parse;

import bolts.Continuation;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.parse.http.ParseHttpRequest.Method;
import com.parse.http.ParseHttpResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ParseRESTObjectBatchCommand extends ParseRESTCommand {
    public static final int COMMAND_OBJECT_BATCH_MAX_SIZE = 50;
    private static final String KEY_RESULTS = "results";

    public static List<Task<JSONObject>> executeBatch(ParseHttpClient client, List<ParseRESTObjectCommand> commands, String sessionToken) {
        final int batchSize = commands.size();
        List<Task<JSONObject>> tasks = new ArrayList(batchSize);
        if (batchSize == 1) {
            tasks.add(((ParseRESTObjectCommand) commands.get(0)).executeAsync(client));
        } else if (batchSize > 50) {
            List<List<ParseRESTObjectCommand>> batches = Lists.partition(commands, 50);
            int size = batches.size();
            for (i = 0; i < size; i++) {
                tasks.addAll(executeBatch(client, (List) batches.get(i), sessionToken));
            }
        } else {
            List<TaskCompletionSource<JSONObject>> arrayList = new ArrayList(batchSize);
            for (i = 0; i < batchSize; i++) {
                TaskCompletionSource<JSONObject> tcs = new TaskCompletionSource();
                arrayList.add(tcs);
                tasks.add(tcs.getTask());
            }
            JSONObject parameters = new JSONObject();
            JSONArray requests = new JSONArray();
            try {
                for (ParseRESTObjectCommand command : commands) {
                    JSONObject requestParameters = new JSONObject();
                    requestParameters.put("method", command.method.toString());
                    requestParameters.put("path", new URL(server, command.httpPath).getPath());
                    JSONObject body = command.jsonParameters;
                    if (body != null) {
                        requestParameters.put("body", body);
                    }
                    requests.put(requestParameters);
                }
                parameters.put("requests", requests);
                final List<TaskCompletionSource<JSONObject>> list = arrayList;
                new ParseRESTObjectBatchCommand("batch", Method.POST, parameters, sessionToken).executeAsync(client).continueWith(new Continuation<JSONObject, Void>() {
                    public Void then(Task<JSONObject> task) throws Exception {
                        int i;
                        TaskCompletionSource<JSONObject> tcs;
                        if (task.isFaulted() || task.isCancelled()) {
                            for (i = 0; i < batchSize; i++) {
                                tcs = (TaskCompletionSource) list.get(i);
                                if (task.isFaulted()) {
                                    tcs.setError(task.getError());
                                } else {
                                    tcs.setCancelled();
                                }
                            }
                        }
                        JSONArray results = ((JSONObject) task.getResult()).getJSONArray(ParseRESTObjectBatchCommand.KEY_RESULTS);
                        int resultLength = results.length();
                        if (resultLength != batchSize) {
                            for (i = 0; i < batchSize; i++) {
                                ((TaskCompletionSource) list.get(i)).setError(new IllegalStateException("Batch command result count expected: " + batchSize + " but was: " + resultLength));
                            }
                        }
                        for (i = 0; i < batchSize; i++) {
                            JSONObject result = results.getJSONObject(i);
                            tcs = (TaskCompletionSource) list.get(i);
                            if (result.has("success")) {
                                tcs.setResult(result.getJSONObject("success"));
                            } else if (result.has("error")) {
                                JSONObject error = result.getJSONObject("error");
                                tcs.setError(new ParseException(error.getInt("code"), error.getString("error")));
                            }
                        }
                        return null;
                    }
                });
            } catch (JSONException e) {
                throw new RuntimeException(e);
            } catch (MalformedURLException e2) {
                throw new RuntimeException(e2);
            }
        }
        return tasks;
    }

    private ParseRESTObjectBatchCommand(String httpPath, Method httpMethod, JSONObject parameters, String sessionToken) {
        super(httpPath, httpMethod, parameters, sessionToken);
    }

    protected Task<JSONObject> onResponseAsync(ParseHttpResponse response, ProgressCallback downloadProgressCallback) {
        InputStream responseStream = null;
        Task<JSONObject> toByteArray;
        try {
            responseStream = response.getContent();
            toByteArray = ParseIOUtils.toByteArray(responseStream);
            String content = new String(toByteArray);
            String str;
            try {
                JSONArray results = new JSONArray(content);
                JSONObject json = new JSONObject();
                json.put(KEY_RESULTS, results);
                str = content;
                return Task.forResult(json);
            } catch (JSONException e) {
                str = content;
                return Task.forError(newTemporaryException("bad json response", (Throwable) e));
            }
        } catch (IOException e2) {
            toByteArray = Task.forError(e2);
            return toByteArray;
        } finally {
            ParseIOUtils.closeQuietly(responseStream);
        }
    }
}
