package com.spiraltoys.cloudpets2;

import android.os.Build.VERSION;
import android.os.Bundle;
import com.spiraltoys.cloudpets2.free.R;

public class CloudActionBarActivity extends BaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(1280);
        if (VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.status_bar_onboarding));
        }
    }
}
