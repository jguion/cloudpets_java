package com.spiraltoys.cloudpets2.toy.event;

import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandIdentifier;

public class ToyEventError {
    private String mMessage;
    private final ToyCommandIdentifier mToyCommandIdentifier;
    private ToyState mToyState;

    public ToyEventError(ToyState toyState, ToyCommandIdentifier toyCommandIdentifier, String message) {
        this.mToyState = toyState;
        this.mToyCommandIdentifier = toyCommandIdentifier;
        this.mMessage = message;
    }

    public ToyState getToyState() {
        return this.mToyState;
    }

    public ToyCommandIdentifier getToyCommandIdentifier() {
        return this.mToyCommandIdentifier;
    }

    public String getMessage() {
        return this.mMessage;
    }
}
