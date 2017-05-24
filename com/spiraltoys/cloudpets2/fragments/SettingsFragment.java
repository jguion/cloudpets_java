package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceFragment;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.util.SettingsManager;

public class SettingsFragment extends PreferenceFragment {
    private OnSettingsFragmentInteractionListener mListener;

    public interface OnSettingsFragmentInteractionListener {
        void onPreferenceFragmentRequested(PreferenceFragment preferenceFragment);

        void onResetPasswordRequested();

        void onTitleSet(int i);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getActivity()));
        getPreferenceManager().setSharedPreferencesName(SettingsManager.PREFS_NAME);
        getPreferenceManager().setSharedPreferencesMode(0);
        CheckBoxPreference adminPasswordPreference = new CheckBoxPreference(getActivity());
        adminPasswordPreference.setKey(SettingsManager.getUsername(getActivity()) + SettingsManager.KEY_USE_ADMIN_PASSWORD);
        adminPasswordPreference.setTitle(R.string.title_pref_password_protect_adult_dashboard);
        adminPasswordPreference.setSummary(R.string.description_setting_password_protect_adult_dashboard);
        adminPasswordPreference.setDefaultValue(Boolean.valueOf(false));
        CheckBoxPreference childPinsPreference = new CheckBoxPreference(getActivity());
        childPinsPreference.setKey(SettingsManager.getUsername(getActivity()) + SettingsManager.KEY_USE_CHILD_PINS);
        childPinsPreference.setTitle(R.string.title_pref_use_child_pins);
        childPinsPreference.setSummary(R.string.description_setting_use_child_pins);
        childPinsPreference.setDefaultValue(Boolean.valueOf(false));
        CheckBoxPreference parentalControlPreference = new CheckBoxPreference(getActivity());
        parentalControlPreference.setKey(SettingsManager.getUsername(getActivity()) + SettingsManager.KEY_PARENTAL_CONTROL);
        parentalControlPreference.setTitle(R.string.title_pref_require_message_approval);
        parentalControlPreference.setSummary(R.string.description_setting_require_message_approval);
        parentalControlPreference.setDefaultValue(Boolean.valueOf(false));
        getPreferenceScreen().addPreference(adminPasswordPreference);
        getPreferenceScreen().addPreference(parentalControlPreference);
        addPreferencesFromResource(R.xml.preferences);
        findPreference("pref_sounds").setOnPreferenceClickListener(new 1(this));
        findPreference("pref_reset_password").setOnPreferenceClickListener(new 2(this));
        findPreference("pref_terms_of_use").setOnPreferenceClickListener(new 3(this));
        findPreference("pref_log_out").setOnPreferenceClickListener(new 4(this));
    }

    public void onResume() {
        super.onResume();
        this.mListener.onTitleSet(R.string.title_activity_settings);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnSettingsFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnSettingsFragmentInteractionListener");
        }
    }
}
