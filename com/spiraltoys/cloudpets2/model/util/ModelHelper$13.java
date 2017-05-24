package com.spiraltoys.cloudpets2.model.util;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.util.Analytics;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

class ModelHelper$13 implements FindCallback<T> {
    final /* synthetic */ FindCallback val$callback;
    final /* synthetic */ String val$pinName;
    final /* synthetic */ ParseQuery val$remoteDatastoreQuery;

    ModelHelper$13(String str, FindCallback findCallback, ParseQuery parseQuery) {
        this.val$pinName = str;
        this.val$callback = findCallback;
        this.val$remoteDatastoreQuery = parseQuery;
    }

    public void done(final List<T> originalObjects, ParseException e) {
        if (e != null) {
            ParseObject.unpinAllInBackground(this.val$pinName);
            Analytics.logLocalDatastoreException(e, this.val$pinName);
            if (this.val$callback != null) {
                this.val$callback.done(originalObjects, e);
                return;
            }
            return;
        }
        final HashMap<ParseObject, Date> originalObjectUpdateMap = new HashMap();
        for (T object : originalObjects) {
            originalObjectUpdateMap.put(object, object.getUpdatedAt());
        }
        this.val$remoteDatastoreQuery.findInBackground(new FindCallback<T>() {
            public void done(final List<T> remotelyFetchedObjects, ParseException e) {
                if (e == null) {
                    List<T> missingObjects = new ArrayList();
                    for (ParseObject originalObject : originalObjects) {
                        if (!remotelyFetchedObjects.contains(originalObject)) {
                            missingObjects.add(originalObject);
                        }
                    }
                    if (missingObjects.size() > 0) {
                        ParseObject.unpinAllInBackground(ModelHelper$13.this.val$pinName, missingObjects);
                    }
                    ParseObject.pinAllInBackground(ModelHelper$13.this.val$pinName, remotelyFetchedObjects, new SaveCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                ModelHelper.access$000(originalObjectUpdateMap, remotelyFetchedObjects);
                                if (ModelHelper$13.this.val$callback != null) {
                                    ModelHelper$13.this.val$callback.done(remotelyFetchedObjects, e);
                                }
                            }
                        }
                    });
                } else if (ModelHelper$13.this.val$callback != null) {
                    ModelHelper$13.this.val$callback.done(remotelyFetchedObjects, e);
                }
            }
        });
    }
}
