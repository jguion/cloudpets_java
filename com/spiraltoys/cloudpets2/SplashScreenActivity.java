package com.spiraltoys.cloudpets2;

import android.content.Intent;
import android.os.Bundle;
import com.parse.CountCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.spiraltoys.cloudpets2.audio.BackgroundMusicTrack;
import com.spiraltoys.cloudpets2.expansion.ExpansionDownloaderActivity;
import com.spiraltoys.cloudpets2.expansion.ExpansionUtils;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.util.AccountHelper;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.Analytics;
import com.spiraltoys.cloudpets2.util.SettingsManager;

public class SplashScreenActivity extends BaseActivity {
    private static final int REQUEST_CODE_DOWNLOAD_EXPANSION = 42;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ExpansionUtils.expansionFilesDelivered(this)) {
            autoLoginOrLaunchWelcome();
        } else {
            startActivityForResult(new Intent(this, ExpansionDownloaderActivity.class), 42);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 42) {
            return;
        }
        if (ExpansionUtils.expansionFilesDelivered(this)) {
            autoLoginOrLaunchWelcome();
        } else {
            finish();
        }
    }

    public BackgroundMusicTrack getBackgroundMusicTrack() {
        return null;
    }

    private void autoLoginOrLaunchWelcome() {
        if (AccountHelper.isLoggedIn() && AccountHelper.isEmailVerified(ParseUser.getCurrentUser())) {
            setContentView((int) R.layout.activity_splash_screen);
            loadUser();
            return;
        }
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }

    private void loadUser() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null || !currentUser.isAuthenticated()) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
            return;
        }
        performAutoLogin();
    }

    private void startAdultDashboard() {
        startActivityAsNewTask(new Intent(this, AdultDashboardActivity.class));
    }

    private void performAutoLogin() {
        String lastUsedProfileId = SettingsManager.getLastUsedProfileId(this);
        if (lastUsedProfileId != null) {
            ModelHelper.getProfileById(lastUsedProfileId, new GetCallback<Profile>() {
                public void done(Profile profile, ParseException e) {
                    SplashScreenActivity.this.hideProgress();
                    if (!SplashScreenActivity.this.isFinishing() && !SplashScreenActivity.this.isDestroyed()) {
                        if (e == null) {
                            SplashScreenActivity.this.onProfileSelected(profile, true);
                        } else {
                            SplashScreenActivity.this.startAdultDashboard();
                        }
                    }
                }
            });
        } else if (SettingsManager.isInitialToySetupComplete(this)) {
            startAdultDashboard();
        } else {
            ModelHelper.getLocalChildProfilesCount(new CountCallback() {
                public void done(int count, ParseException e) {
                    SplashScreenActivity.this.hideProgress();
                    if (e != null) {
                        Analytics.logLocalDatastoreException(e, ModelHelper.PROFILES_TAG);
                        SplashScreenActivity.this.startAdultDashboard();
                    } else if (count > 0) {
                        SplashScreenActivity.this.startAdultDashboard();
                    } else {
                        SplashScreenActivity.this.startActivityAsNewTask(new Intent(SplashScreenActivity.this, OnboardingSurveyActivity.class));
                    }
                }
            });
        }
    }
}
