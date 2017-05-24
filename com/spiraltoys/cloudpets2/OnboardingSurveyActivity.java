package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.AlertDialogWrapper.Builder;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.util.SettingsManager;
import icepick.State;

public class OnboardingSurveyActivity extends CloudActionBarActivity {
    private static final int REQUEST_CODE_ADD_CHILD = 123;
    private Dialog mSetupCompleteDialog;
    @State
    boolean mSetupCompleteDialogShown;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_onboarding_survey);
        ButterKnife.inject((Activity) this);
        initToolbar();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onLogOutSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.mSetupCompleteDialog != null && this.mSetupCompleteDialog.isShowing()) {
            this.mSetupCompleteDialog.dismiss();
        }
    }

    protected void onStart() {
        super.onStart();
        if (this.mSetupCompleteDialogShown) {
            showSetupCompleteDialog();
        }
    }

    protected void onResume() {
        super.onResume();
    }

    @OnClick({2131755209})
    void onHaveCloudPetClicked() {
        OnboardingCreateChildProfileActivity.startWithProfileForResult(this, new Profile(), 123);
    }

    @OnClick({2131755210})
    void onDontHaveCloudPetClicked() {
        startActivity(new Intent(this, OnboardingSurveyNoPetActivity.class));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == -1) {
            showSetupCompleteDialog();
        }
    }

    private void showSetupCompleteDialog() {
        if (this.mSetupCompleteDialog == null || !this.mSetupCompleteDialog.isShowing()) {
            this.mSetupCompleteDialogShown = true;
            SettingsManager.setInitialToySetupComplete(this, true);
            this.mSetupCompleteDialog = new Builder(this).setTitle((int) R.string.title_setup_complete).setMessage((int) R.string.message_setup_another_toy_question).setNegativeButton((int) R.string.btn_no, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    OnboardingSurveyActivity.this.startActivityAsNewTask(new Intent(OnboardingSurveyActivity.this, AdultDashboardActivity.class));
                }
            }).setPositiveButton((int) R.string.btn_yes, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    OnboardingCreateChildProfileActivity.startWithProfileForResult(OnboardingSurveyActivity.this, new Profile(), 123);
                }
            }).setCancelable(false).show();
        }
    }
}
