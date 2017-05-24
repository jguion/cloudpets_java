package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.spiraltoys.cloudpets2.free.R;

public class TermsOfUseActivity extends BaseActivity {
    private static final String ARG_HIDE_BUTTON_BAR = "hide_button_bar";
    @InjectView(2131755219)
    ViewGroup mButtonBar;

    public static void start(Activity context, boolean withoutButtonBar) {
        Intent intent = new Intent(context, TermsOfUseActivity.class);
        intent.putExtra(ARG_HIDE_BUTTON_BAR, withoutButtonBar);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_terms_of_use);
        ButterKnife.inject((Activity) this);
        initToolbar();
        showToolbarTitle();
        this.mButtonBar.setVisibility(isButtonBarHidden() ? 8 : 0);
        if (isButtonBarHidden()) {
            this.mStatusBarSpace.setVisibility(8);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
    }

    @OnClick({2131755221})
    void agreeClicked() {
        startActivity(new Intent(this, CreateAccountActivity.class));
        finish();
    }

    @OnClick({2131755220})
    void disagreeClicked() {
        onBackPressed();
    }

    private boolean isButtonBarHidden() {
        return getIntent().getBooleanExtra(ARG_HIDE_BUTTON_BAR, false);
    }
}
