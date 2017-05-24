package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;

public class OnboardingCreateChildProfileActivity extends CreateOrEditChildProfileActivity {
    public static void startWithProfile(Activity context, Profile profile) {
        Intent intent = new Intent(context, OnboardingCreateChildProfileActivity.class);
        intent.putExtra(CreateOrEditChildProfileActivity.PROFILE_OBJECT_ID, profile.getObjectId());
        context.startActivity(intent);
    }

    public static void startWithProfileForResult(Activity context, Profile profile, int requestCode) {
        Intent intent = new Intent(context, OnboardingCreateChildProfileActivity.class);
        intent.putExtra(CreateOrEditChildProfileActivity.PROFILE_OBJECT_ID, profile.getObjectId());
        context.startActivityForResult(intent, requestCode);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(1280);
        if (VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.status_bar_onboarding));
        }
    }
}
