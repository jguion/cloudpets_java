package com.spiraltoys.cloudpets2;

import android.net.Uri;
import android.os.Bundle;
import icepick.Injector.Helper;
import icepick.Injector.Object;

public class RecordAndSendMessageActivity$$Icepick<T extends RecordAndSendMessageActivity> extends Object<T> {
    private static final Helper H = new Helper("com.spiraltoys.cloudpets2.RecordAndSendMessageActivity$$Icepick.");

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mUri = (Uri) H.getParcelable(state, "mUri");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        H.putParcelable(state, "mUri", target.mUri);
    }
}
