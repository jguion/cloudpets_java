package com.spiraltoys.cloudpets2.fragments;

import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import com.spiraltoys.cloudpets2.util.Utils;

class SettingsFragment$4 implements OnPreferenceClickListener {
    final /* synthetic */ SettingsFragment this$0;

    SettingsFragment$4(SettingsFragment this$0) {
        this.this$0 = this$0;
    }

    public boolean onPreferenceClick(Preference preference) {
        Utils.showLogoutConfirmationDialog(this.this$0.getActivity());
        return true;
    }
}
