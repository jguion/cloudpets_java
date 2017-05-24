package com.spiraltoys.cloudpets2.toy.event;

import android.net.Uri;

public class ToyEventReceivedAudio {
    private Uri mLocalUri;
    private String mToyIdentifier;

    public ToyEventReceivedAudio(Uri localUri, String toyIdentifier) {
        this.mLocalUri = localUri;
        this.mToyIdentifier = toyIdentifier;
    }

    public Uri getLocalUri() {
        return this.mLocalUri;
    }

    public String getToyIdentifier() {
        return this.mToyIdentifier;
    }
}
