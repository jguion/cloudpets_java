package com.spiraltoys.cloudpets2;

import android.os.Bundle;
import icepick.Injector.Helper;
import icepick.Injector.Object;

public class OnboardingSurveyActivity$$Icepick<T extends OnboardingSurveyActivity> extends Object<T> {
    private static final Helper H = new Helper("com.spiraltoys.cloudpets2.OnboardingSurveyActivity$$Icepick.");

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mSetupCompleteDialogShown = H.getBoolean(state, "mSetupCompleteDialogShown");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        H.putBoolean(state, "mSetupCompleteDialogShown", target.mSetupCompleteDialogShown);
    }
}
