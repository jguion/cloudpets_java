package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.AlertDialogWrapper.Builder;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.spiraltoys.cloudpets2.databinding.ActivityLogInBinding;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.util.AccountHelper;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.Utils;

public class LogInActivity extends CloudActionBarActivity {
    public static final int ALLOWED_BEAR_OVERLAP_WITH_FORGOT_PASSWORD_BUTTON_DP = 24;
    ActivityLogInBinding mBinding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBinding = (ActivityLogInBinding) DataBindingUtil.setContentView(this, R.layout.activity_log_in);
        ButterKnife.inject((Activity) this);
        Utils.highlightCloudPetsText(this.mBinding.titleText);
        initToolbar();
        hideLoginBearIfOverlappingText();
    }

    @OnClick({2131755201})
    void onLogInClicked() {
        if (!Utils.isValidEmailAddress(this.mBinding.email.getText())) {
            Utils.showHintDialog((Context) this, (int) R.string.error_please_enter_valid_email);
        } else if (TextUtils.isEmpty(this.mBinding.email.getText()) || TextUtils.isEmpty(this.mBinding.password.getText())) {
            Utils.showHintDialog((Context) this, (int) R.string.error_please_enter_password);
        } else {
            showProgress(R.string.title_logging_in);
            AccountHelper.logOut(this);
            AccountHelper.logIn(this, this.mBinding.email.getText().toString().toLowerCase(), this.mBinding.password.getText().toString(), new LogInCallback() {
                public void done(ParseUser parseUser, ParseException e) {
                    if (e != null) {
                        LogInActivity.this.hideProgress();
                        Utils.showErrorDialog(LogInActivity.this, ErrorMessages.getStringResourceIdForLoginException(LogInActivity.this, e));
                    } else if (AccountHelper.isEmailVerified(parseUser)) {
                        ModelHelper.preFetch(new 1(this));
                    } else {
                        LogInActivity.this.hideProgress();
                        LogInActivity.this.showVerifyEmailDialog();
                    }
                }
            });
        }
    }

    @OnClick({2131755204})
    void onPasswordResetClicked() {
        if (Utils.isValidEmailAddress(this.mBinding.email.getText())) {
            showProgress(R.string.title_resetting_password);
            ParseUser.requestPasswordResetInBackground(this.mBinding.email.getText().toString(), new RequestPasswordResetCallback() {
                public void done(ParseException e) {
                    LogInActivity.this.hideProgress();
                    if (e != null) {
                        Utils.showErrorDialog(LogInActivity.this, String.format(LogInActivity.this.getString(ErrorMessages.getStringResourceIdForErrorResettingPassword(LogInActivity.this, e)), new Object[]{LogInActivity.this.mBinding.email.getText()}));
                        return;
                    }
                    new Builder(LogInActivity.this).setTitle((int) R.string.title_password_reset_sent).setMessage((int) R.string.message_password_reset_sent_instructions).setPositiveButton((int) R.string.btn_okay, null).show();
                }
            });
            return;
        }
        Utils.showHintDialog((Context) this, (int) R.string.error_please_enter_valid_email);
        this.mBinding.email.requestFocus();
    }

    private void hideLoginBearIfOverlappingText() {
        this.mBinding.getRoot().getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int i = 0;
                if (!LogInActivity.this.isFinishing() && !LogInActivity.this.isDestroyed()) {
                    LogInActivity.this.mBinding.getRoot().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    ImageView imageView = LogInActivity.this.mBinding.loginBear;
                    if (Utils.isColliding(LogInActivity.this.mBinding.loginBear, LogInActivity.this.mBinding.resetPasswordButton, Utils.dpToPx(LogInActivity.this, 24.0f), 0)) {
                        i = 8;
                    }
                    imageView.setVisibility(i);
                }
            }
        });
    }
}
