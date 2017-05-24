package com.spiraltoys.cloudpets2.fragments;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import com.spiraltoys.cloudpets2.TermsOfUseActivity;

class SettingsFragment$3 implements OnPreferenceClickListener {
    final /* synthetic */ SettingsFragment this$0;

    SettingsFragment$3(SettingsFragment this$0) {
        this.this$0 = this$0;
    }

    public boolean onPreferenceClick(Preference preference) {
        TermsOfUseActivity.start(this.this$0.getActivity(), true);
        return true;
    }
}
