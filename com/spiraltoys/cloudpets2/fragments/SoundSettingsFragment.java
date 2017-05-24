package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.spiraltoys.cloudpets2.free.R;

public class SoundSettingsFragment extends PreferenceFragment {
    private OnSoundSettingsFragmentInteractionListener mListener;

    public interface OnSoundSettingsFragmentInteractionListener {
        void onTitleSet(int i);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_sounds);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnSoundSettingsFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnSoundSettingsFragmentInteractionListener");
        }
    }

    public void onResume() {
        super.onResume();
        this.mListener.onTitleSet(R.string.title_pref_sound_effects_and_music);
    }
}
