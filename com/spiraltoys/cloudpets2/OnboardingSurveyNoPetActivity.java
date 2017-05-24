package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.util.SettingsManager;

public class OnboardingSurveyNoPetActivity extends CloudActionBarActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_onboarding_survey_no_pet);
        ButterKnife.inject((Activity) this);
        initToolbar();
    }

    @OnClick({2131755211})
    void onContinueClicked() {
        SettingsManager.setInitialToySetupComplete(this, true);
        startActivityAsNewTask(new Intent(this, AdultDashboardActivity.class));
    }
}
