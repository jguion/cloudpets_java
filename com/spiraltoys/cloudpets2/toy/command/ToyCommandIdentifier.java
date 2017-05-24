package com.spiraltoys.cloudpets2.toy.command;

import java.util.UUID;

public class ToyCommandIdentifier {
    private Class mClass;
    private UUID mUUID = UUID.randomUUID();

    public ToyCommandIdentifier(Class aClass) {
        this.mClass = aClass;
    }

    public Class getEventClass() {
        return this.mClass;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.mUUID.equals(((ToyCommandIdentifier) o).mUUID);
    }

    public int hashCode() {
        return this.mUUID.hashCode();
    }
}
