package com.spiraltoys.cloudpets2.fragments;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;

class SettingsFragment$1 implements OnPreferenceClickListener {
    final /* synthetic */ SettingsFragment this$0;

    SettingsFragment$1(SettingsFragment this$0) {
        this.this$0 = this$0;
    }

    public boolean onPreferenceClick(Preference preference) {
        SettingsFragment.access$000(this.this$0).onPreferenceFragmentRequested(new SoundSettingsFragment());
        return true;
    }
}
