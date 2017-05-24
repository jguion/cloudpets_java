package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;
import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.Builder;
import com.afollestad.materialdialogs.MaterialDialog.ButtonCallback;
import com.spiraltoys.cloudpets2.audio.BackgroundMusicService;
import com.spiraltoys.cloudpets2.audio.BackgroundMusicTrack;
import com.spiraltoys.cloudpets2.fragments.ProfileSwitcherDialogFragment.OnProfilePickerInteractionListener;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PrivateProfile.ProfileType;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.util.AccountHelper;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.toy.ToyManager;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.event.ToyEventError;
import com.spiraltoys.cloudpets2.toy.event.ToyEventRequiresUpdate;
import com.spiraltoys.cloudpets2.toy.event.ToyEventState;
import com.spiraltoys.cloudpets2.toy.event.ToyEventUpdateProgress;
import com.spiraltoys.cloudpets2.util.SettingsManager;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;
import icepick.Icepick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AppCompatActivity implements OnProfilePickerInteractionListener {
    private static final int HIDE_PROGRESS_DELAY_MS = 50;
    private static final int MAX_PROGRESS_STEPS = 1000;
    private Dialog mFirmwareErrorDialog;
    private Dialog mFirmwareSuccessDialog;
    private Dialog mFirmwareUpdateRequiredDialog;
    private Handler mHandler;
    private boolean mIsActivityPaused = true;
    private boolean mIsFirmwareUpdating;
    private boolean mIsMuted;
    private boolean mKeepMusicPlaying;
    private MaterialDialog mProgressDialog;
    private boolean mShouldPauseMusic;
    @InjectView(2131755218)
    @Optional
    View mStatusBarSpace;
    @InjectView(2131755150)
    @Optional
    Toolbar mToolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState((Object) this, savedInstanceState);
        this.mShouldPauseMusic = true;
        this.mHandler = new Handler();
    }

    protected void onPause() {
        this.mIsActivityPaused = true;
        super.onPause();
        if (this.mShouldPauseMusic) {
            BackgroundMusicService.stopMe(this);
        }
        this.mShouldPauseMusic = true;
    }

    protected void onResume() {
        super.onResume();
        this.mIsActivityPaused = false;
        BackgroundMusicTrack track = getBackgroundMusicTrack();
        if (track != null) {
            BackgroundMusicService.startMe(this, track, isMuted());
        } else {
            BackgroundMusicService.stopMe(this);
        }
        setKeepMusicPlaying(true);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState((Object) this, outState);
    }

    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }

    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        this.mShouldPauseMusic = !this.mKeepMusicPlaying;
        super.startActivityForResult(intent, requestCode, options);
    }

    public void finish() {
        this.mShouldPauseMusic = isTaskRoot();
        super.finish();
    }

    public void finishAffinity() {
        this.mShouldPauseMusic = isTaskRoot();
        super.finishAffinity();
    }

    public void finishActivity(int requestCode) {
        this.mShouldPauseMusic = isTaskRoot();
        super.finishActivity(requestCode);
    }

    public void finishActivityFromChild(Activity child, int requestCode) {
        this.mShouldPauseMusic = isTaskRoot();
        super.finishActivityFromChild(child, requestCode);
    }

    public void finishAfterTransition() {
        this.mShouldPauseMusic = isTaskRoot();
        super.finishAfterTransition();
    }

    public void finishAndRemoveTask() {
        this.mShouldPauseMusic = isTaskRoot();
        super.finishAndRemoveTask();
    }

    public void finishFromChild(Activity child) {
        this.mShouldPauseMusic = isTaskRoot();
        super.finishFromChild(child);
    }

    public boolean onSupportNavigateUp() {
        this.mShouldPauseMusic = isTaskRoot();
        return super.onSupportNavigateUp();
    }

    @OnClick({2131755129})
    @Optional
    void onBuyNowClicked() {
        setKeepMusicPlaying(false);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.spiraltoys.cloudpets2.premium"));
        startActivity(intent);
    }

    @OnClick({2131755142})
    @Optional
    void onTutorialClicked() {
        setKeepMusicPlaying(false);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://mycloudpets.com/tour/"));
        startActivity(intent);
    }

    public BackgroundMusicTrack getBackgroundMusicTrack() {
        return BackgroundMusicTrack.FRONT_END;
    }

    synchronized void mute() {
        sendBroadcast(new Intent(BackgroundMusicService.INTENT_PAUSE_MUSIC));
        this.mIsMuted = true;
    }

    synchronized void unmute() {
        sendBroadcast(new Intent(BackgroundMusicService.INTENT_RESUME_MUSIC));
        this.mIsMuted = false;
    }

    public boolean isMuted() {
        return this.mIsMuted;
    }

    @Nullable
    public Dialog showProgress(int tileStringResource) {
        return showProgress(tileStringResource, (int) R.string.message_please_wait);
    }

    @Nullable
    public Dialog showProgress(int tileStringResource, boolean isIndeterminate) {
        return showProgress(tileStringResource, R.string.message_please_wait, isIndeterminate);
    }

    @Nullable
    public Dialog showProgress(String title, boolean isIndeterminate) {
        return showProgress(title, getString(R.string.message_please_wait), null, isIndeterminate);
    }

    @Nullable
    public Dialog showProgress(int tileStringResource, int contentStringResource) {
        return showProgress(tileStringResource, contentStringResource, true);
    }

    @Nullable
    public synchronized Dialog showProgress(int tileStringResource, int contentStringResource, boolean isIndeterminate) {
        return showProgress(getString(tileStringResource), getString(contentStringResource), null, isIndeterminate);
    }

    @Nullable
    public synchronized Dialog showProgress(String title, String content, String dismissButtonText, boolean isIndeterminate) {
        Dialog dialog;
        if (this.mProgressDialog != null) {
            dialog = null;
        } else {
            Builder builder = new Builder(this).title((CharSequence) title).content((CharSequence) content).progress(isIndeterminate, 1000).cancelable(false);
            if (dismissButtonText != null) {
                builder.negativeText((CharSequence) dismissButtonText);
                builder.callback(new ButtonCallback() {
                    public void onAny(MaterialDialog dialog) {
                        dialog.cancel();
                    }
                });
            }
            dialog = builder.show();
            this.mProgressDialog = dialog;
        }
        return dialog;
    }

    @Nullable
    public synchronized Dialog showProgress(int tileStringResource, int contentStringResource, int dismissButtonTextResource, boolean isIndeterminate) {
        Dialog dialog;
        if (this.mProgressDialog != null) {
            dialog = null;
        } else {
            Builder builder = new Builder(this).title(tileStringResource).content(contentStringResource).progress(isIndeterminate, 1000).cancelable(false);
            if (dismissButtonTextResource > 0) {
                builder.negativeText(dismissButtonTextResource);
                builder.callback(new ButtonCallback() {
                    public void onAny(MaterialDialog dialog) {
                        dialog.cancel();
                    }
                });
            }
            dialog = builder.show();
            this.mProgressDialog = dialog;
        }
        return dialog;
    }

    public void updateProgress(float progress) {
        if (!isFinishing() && !isDestroyed()) {
            if (progress > 1.0f || progress < 0.0f) {
                throw new IllegalArgumentException("progress must be between 0 and 1 inclusively");
            } else if (this.mProgressDialog != null) {
                this.mProgressDialog.setProgress((int) (1000.0f * progress));
            }
        }
    }

    public synchronized void hideProgress() {
        if (!(isFinishing() || isDestroyed())) {
            if (this.mProgressDialog != null) {
                this.mProgressDialog.dismiss();
                this.mProgressDialog = null;
            }
        }
    }

    public synchronized void hideProgressDelayed() {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                BaseActivity.this.hideProgress();
            }
        }, 50);
    }

    public void setKeepMusicPlaying(boolean keepMusicPlayingAfterPause) {
        this.mKeepMusicPlaying = keepMusicPlayingAfterPause;
    }

    void initToolbar() {
        if (this.mToolbar != null) {
            setSupportActionBar(this.mToolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setHomeButtonEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
        if (this.mStatusBarSpace != null) {
            this.mStatusBarSpace.setLayoutParams(new LayoutParams(-1, Utils.getStatusBarHeight(this)));
        }
    }

    void showToolbarTitle() {
        showToolbarTitle(null);
    }

    void showToolbarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (title != null) {
                actionBar.setTitle((CharSequence) title);
            }
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }

    public void startActivityAsNewTask(Intent intent) {
        startActivity(intent.setFlags(268468224));
    }

    public boolean isUpdatingFirmware() {
        return this.mIsFirmwareUpdating;
    }

    public boolean isPaused() {
        return this.mIsActivityPaused;
    }

    void showVerifyEmailDialog() {
        new AlertDialogWrapper.Builder(this).setTitle((int) R.string.title_verify_email).setMessage((int) R.string.message_email_not_verified).setCancelable(false).autoDismiss(false).setNegativeButton((int) R.string.btn_cancel, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setNeutralButton((int) R.string.btn_resend, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                BaseActivity.this.showProgress(R.string.title_resending_verification);
                ModelHelper.resendVerificationEmail(new 1(this));
            }
        }).setPositiveButton((int) R.string.btn_continue, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                BaseActivity.this.showProgress(R.string.title_checking_verification_email);
                AccountHelper.refreshUser(new 1(this));
            }
        }).show();
    }

    public void onProfileSelected(Profile profile) {
        onProfileSelected(profile, false);
    }

    public void onProfileSelected(Profile profile, boolean preventSplash) {
        if (profile.getPrivateProfile().getProfileType() == ProfileType.ADULT) {
            startActivityAsNewTask(new Intent(this, AdultDashboardActivity.class));
        } else {
            ChildDashboardActivity.start(this, profile, preventSplash);
        }
        SettingsManager.setLastUsedProfileId(this, profile.getObjectId());
    }

    public void onLogOutSelected() {
        Utils.showLogoutConfirmationDialog(this);
    }

    public void startFirmwareUpdate() {
        ToyManager.updateFirmware();
        this.mIsFirmwareUpdating = true;
        showProgress((int) R.string.firmware_updating_title, false);
    }

    public void onEvent(ToyEventRequiresUpdate eventRequiresUpdate) {
        if (!this.mIsFirmwareUpdating && !isFinishing()) {
            if (this.mFirmwareUpdateRequiredDialog == null || !this.mFirmwareUpdateRequiredDialog.isShowing()) {
                this.mFirmwareUpdateRequiredDialog = new AlertDialogWrapper.Builder(this).setTitle((int) R.string.firmware_update_title).setMessage((int) R.string.firmware_update_prompt).setCancelable(false).setPositiveButton((int) R.string.btn_continue, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        BaseActivity.this.startFirmwareUpdate();
                    }
                }).show();
            }
        }
    }

    public void onEvent(ToyEventUpdateProgress toyEventUpdateProgress) {
        if (this.mIsFirmwareUpdating) {
            updateProgress(toyEventUpdateProgress.getPercentage());
        }
    }

    public void onEvent(ToyEventState toyEventState) {
        if (this.mIsFirmwareUpdating && toyEventState.getState() != ToyState.UPDATING_FIRMWARE && toyEventState.getState().isOnline()) {
            hideProgress();
            onFirmwareUpdateSuccess();
        }
    }

    public void onEvent(ToyEventError errorEvent) {
        if (this.mIsFirmwareUpdating) {
            hideProgress();
            onFirmwareUpdateFailure();
            EventBus.getDefault().removeStickyEvent((Object) errorEvent);
        }
    }

    private void onFirmwareUpdateSuccess() {
        this.mIsFirmwareUpdating = false;
        EventBus.getDefault().removeStickyEvent(ToyEventRequiresUpdate.class);
        if (!isFinishing() && !isDestroyed()) {
            if (this.mFirmwareSuccessDialog == null || !this.mFirmwareSuccessDialog.isShowing()) {
                this.mFirmwareSuccessDialog = new AlertDialogWrapper.Builder(this).setTitle((int) R.string.firmware_update_completed_title).setMessage((int) R.string.firmware_update_completed_message).setPositiveButton((int) R.string.btn_continue, null).setCancelable(false).setOnDismissListener(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialog) {
                        BaseActivity.this.mFirmwareSuccessDialog = null;
                    }
                }).show();
            }
        }
    }

    private void onFirmwareUpdateFailure() {
        this.mIsFirmwareUpdating = false;
        if (!isFinishing() && !isDestroyed()) {
            if (this.mFirmwareErrorDialog == null || !this.mFirmwareErrorDialog.isShowing()) {
                this.mFirmwareErrorDialog = new AlertDialogWrapper.Builder(this).setTitle((int) R.string.title_error).setMessage((int) R.string.firmware_update_failed_message).setPositiveButton((int) R.string.btn_continue, null).setCancelable(false).setOnDismissListener(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                        BaseActivity.this.mFirmwareErrorDialog = null;
                    }
                }).show();
            }
        }
    }
}
