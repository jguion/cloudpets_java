package com.spiraltoys.cloudpets2.model.events;

import com.parse.ParseObject;
import java.util.HashSet;
import java.util.Set;

public class LocalDatastoreChangedEvent {
    Set<? extends ParseObject> mObjectsDeleted;
    Set<? extends ParseObject> mObjectsInserted;
    Set<? extends ParseObject> mObjectsUpdated;

    public LocalDatastoreChangedEvent(Set<? extends ParseObject> set, Set<? extends ParseObject> set2, Set<? extends ParseObject> set3) {
        Set hashSet;
        Set hashSet2;
        Set hashSet3;
        if (set == null) {
            hashSet = new HashSet();
        }
        this.mObjectsInserted = hashSet;
        if (set2 == null) {
            hashSet2 = new HashSet();
        }
        this.mObjectsUpdated = hashSet2;
        if (set3 == null) {
            hashSet3 = new HashSet();
        }
        this.mObjectsDeleted = hashSet3;
    }

    public Set<? extends ParseObject> getObjectsInserted() {
        return this.mObjectsInserted;
    }

    public Set<? extends ParseObject> getObjectsUpdated() {
        return this.mObjectsUpdated;
    }

    public Set<? extends ParseObject> getObjectsDeleted() {
        return this.mObjectsDeleted;
    }
}
