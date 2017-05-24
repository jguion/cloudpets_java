package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import butterknife.ButterKnife;
import com.afollestad.materialdialogs.AlertDialogWrapper.Builder;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.spiraltoys.cloudpets2.fragments.SettingsFragment;
import com.spiraltoys.cloudpets2.fragments.SettingsFragment.OnSettingsFragmentInteractionListener;
import com.spiraltoys.cloudpets2.fragments.SoundSettingsFragment.OnSoundSettingsFragmentInteractionListener;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.Utils;

public class SettingsActivity extends BaseActivity implements OnSettingsFragmentInteractionListener, OnSoundSettingsFragmentInteractionListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_settings);
        ButterKnife.inject((Activity) this);
        if (savedInstanceState == null) {
            onPreferenceFragmentRequested(new SettingsFragment());
        }
        initToolbar();
        showToolbarTitle();
    }

    public void onPreferenceFragmentRequested(PreferenceFragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.settings_container, fragment).addToBackStack(null).commit();
    }

    public void onTitleSet(int titleResourceId) {
        showToolbarTitle(getString(titleResourceId));
    }

    public void onResetPasswordRequested() {
        showProgress(R.string.title_resetting_password);
        ParseUser.requestPasswordResetInBackground(ParseUser.getCurrentUser().getEmail(), new RequestPasswordResetCallback() {
            public void done(ParseException e) {
                SettingsActivity.this.hideProgress();
                if (e != null) {
                    Utils.showErrorDialog(SettingsActivity.this, String.format(SettingsActivity.this.getString(ErrorMessages.getStringResourceIdForErrorResettingPassword(SettingsActivity.this, e)), new Object[]{ParseUser.getCurrentUser().getEmail()}));
                    return;
                }
                new Builder(SettingsActivity.this).setTitle((int) R.string.title_password_reset_sent).setMessage((int) R.string.message_password_reset_sent_instructions).setPositiveButton((int) R.string.btn_okay, null).show();
            }
        });
    }
}
