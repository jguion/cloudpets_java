package com.spiraltoys.cloudpets2.toy.event;

import com.spiraltoys.cloudpets2.toy.command.ToyCommandIdentifier;

public class ToyEventCommandSucceeded {
    private ToyCommandIdentifier mCommandIdentifier;

    public ToyEventCommandSucceeded(ToyCommandIdentifier toyCommandIdentifier) {
        this.mCommandIdentifier = toyCommandIdentifier;
    }

    public ToyCommandIdentifier getCommandIdentifier() {
        return this.mCommandIdentifier;
    }
}
