package com.spiraltoys.cloudpets2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.databinding.ActivityAdultDashboardBinding;
import com.spiraltoys.cloudpets2.fragments.FloatingProfileSwitcherFragment;
import com.spiraltoys.cloudpets2.fragments.FloatingProfileSwitcherFragment.FloatingProfilePickerUnderlayProvider;
import com.spiraltoys.cloudpets2.fragments.FloatingProfileSwitcherFragment.OnFloatingProfileSwitcherFragmentInteractionListener;
import com.spiraltoys.cloudpets2.fragments.RequestBLEPermissionFragment.OnRequestBLEPermissionFragmentInteractionListener;
import com.spiraltoys.cloudpets2.fragments.RequestBLEPermissionFragmentWithOnboardingToolbar;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.FriendAcceptanceNotification;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.SettingsManager;
import com.spiraltoys.cloudpets2.util.Utils;
import hugo.weaving.DebugLog;
import java.util.List;

public class AdultDashboardActivity extends BaseActivity implements OnFloatingProfileSwitcherFragmentInteractionListener, FloatingProfilePickerUnderlayProvider, OnRequestBLEPermissionFragmentInteractionListener {
    private static final int PERMISSIONS_REQUEST_COARSE_LOCATION_ACCESS = 43;
    private static final int PERMISSIONS_REQUEST_RECORD_AUDIO = 42;
    private static final String TAG = AdultDashboardActivity.class.getSimpleName();
    private static final String TAG_LOCATION_PERMISSION_REQUEST_FRAGMENT = "TAG_LOCATION_PERMISSION_REQUEST_FRAGMENT";
    private static final String TAG_PROFILE_PICKER_FRAGMENT = "TAG_PROFILE_PICKER_FRAGMENT";
    private static final int TOOLTIP_ANIMATION_INTERVAL_MS = 2000;
    private static final int TOOLTIP_ANIMATION_START_DELAY_MS = 1500;
    private Profile mAdultProfile;
    private ActivityAdultDashboardBinding mBinding;
    private boolean mFriendsLoaded;
    private boolean mHasLocalChildProfiles;
    private boolean mHasLocalFriends;
    private boolean mIsInitialAdultTooltipComplete;
    private boolean mLocalChildProfilesLoaded;
    private boolean mLocalFriendsLoaded;
    private boolean mMessagesLoaded;
    private Profile mPendingPickedProfile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBinding = (ActivityAdultDashboardBinding) DataBindingUtil.setContentView(this, R.layout.activity_adult_dashboard);
        ButterKnife.inject((Activity) this);
        getWindow().getDecorView().setSystemUiVisibility(1280);
        if (VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_onboarding));
        }
        if (savedInstanceState == null && getFragmentManager().findFragmentByTag(TAG_PROFILE_PICKER_FRAGMENT) == null) {
            setupProfilePicker();
        }
        setupAds();
    }

    private void setupAds() {
        this.mBinding.adView.setAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                AdultDashboardActivity.this.mBinding.buyACloudpetButton.setVisibility(0);
            }

            public void onAdLoaded() {
                AdultDashboardActivity.this.mBinding.buyACloudpetButton.setVisibility(4);
            }
        });
        this.mBinding.adView.loadAd(new Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("02c666be09378a75").build());
        final InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (!AdultDashboardActivity.this.isPaused()) {
                    interstitialAd.show();
                }
            }
        });
        interstitialAd.loadAd(new Builder().addTestDevice("02c666be09378a75").build());
    }

    protected void onResume() {
        super.onResume();
        updateNewMessageCount();
        updateNewInviteCount();
        updateTooltip();
        this.mBinding.adView.resume();
    }

    protected void onPause() {
        super.onPause();
        this.mBinding.adView.pause();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mBinding.adView.destroy();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick({2131755133})
    void onRecordMessageClicked() {
        if (VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.RECORD_AUDIO") == 0) {
            startRecordMessageActivityOrError();
            return;
        }
        requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 42);
    }

    private void startRecordMessageActivityOrError() {
        if (this.mHasLocalChildProfiles || this.mHasLocalFriends) {
            if (!this.mIsInitialAdultTooltipComplete) {
                SettingsManager.setInitialAdultTooltipComplete(this, true);
            }
            startActivity(new Intent(this, RecordAndSendMessageActivity.class));
        } else if (this.mLocalChildProfilesLoaded && this.mLocalFriendsLoaded) {
            Utils.showHintDialog((Context) this, (int) R.string.hint_recording_requires_children_or_friends);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 42:
                if (grantResults[0] == 0) {
                    startRecordMessageActivityOrError();
                    return;
                } else {
                    Snackbar.make(this.mBinding.getRoot(), (int) R.string.hint_audio_permission_denied, 0).show();
                    return;
                }
            case 43:
                if (grantResults[0] != 0 || this.mPendingPickedProfile == null) {
                    Snackbar.make(this.mBinding.getRoot(), (int) R.string.hint_location_permission_denied, 0).show();
                    return;
                } else {
                    super.onProfileSelected(this.mPendingPickedProfile);
                    return;
                }
            default:
                return;
        }
    }

    @OnClick({2131755134})
    void onShowMessagesClicked() {
        startActivity(new Intent(this, MessageCenterActivity.class));
    }

    @OnClick({2131755136})
    void onManageProfilesClicked() {
        startActivity(new Intent(this, ManageProfilesActivity.class));
    }

    @OnClick({2131755138})
    void onManageSettingsClicked() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @DebugLog
    private void setupProfilePicker() {
        try {
            getFragmentManager().beginTransaction().replace(R.id.profile_picker_container, FloatingProfileSwitcherFragment.newInstance(getAdultProfile()), TAG_PROFILE_PICKER_FRAGMENT).commit();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @DebugLog
    private Profile getAdultProfile() throws ParseException {
        if (this.mAdultProfile == null) {
            this.mAdultProfile = ModelHelper.getLocalAdultProfile();
        }
        return this.mAdultProfile;
    }

    public void onProfileFlyoutMenuShown() {
    }

    public void onProfileFlyoutMenuDismissed() {
    }

    public void onProfileSelected(Profile profile) {
        try {
            if (profile == getAdultProfile()) {
                return;
            }
            if (VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.RECORD_AUDIO") == 0 || profile.getPrivateProfile().isAdult()) {
                super.onProfileSelected(profile);
                return;
            }
            this.mPendingPickedProfile = profile;
            this.mBinding.fragmentContainer.setVisibility(0);
            getFragmentManager().beginTransaction().addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.fragment_container, RequestBLEPermissionFragmentWithOnboardingToolbar.newInstance(profile.getPlushToy().getCharacter()), TAG_LOCATION_PERMISSION_REQUEST_FRAGMENT).commit();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void onProfileMenuLoaded(boolean isEmpty) {
        boolean z = true;
        this.mLocalChildProfilesLoaded = true;
        if (isEmpty) {
            z = false;
        }
        this.mHasLocalChildProfiles = z;
        try {
            ModelHelper.getFriendsLocalDatastoreQuery(true, false, false, getAdultProfile()).setLimit(1).countInBackground(new CountCallback() {
                public void done(int i, ParseException e) {
                    boolean z = true;
                    AdultDashboardActivity.this.mLocalFriendsLoaded = true;
                    AdultDashboardActivity adultDashboardActivity = AdultDashboardActivity.this;
                    if (i <= 0) {
                        z = false;
                    }
                    adultDashboardActivity.mHasLocalFriends = z;
                    AdultDashboardActivity.this.startRemoteDataSync();
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public View provideUnderlayView() {
        return this.mBinding.floatingMenuUnderlay;
    }

    @TargetApi(23)
    public void onRequestBlePermissionClicked() {
        requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION"}, 43);
    }

    private void updateNewMessageCount() {
        ModelHelper.countLocalNewMessages(this.mAdultProfile, false, false, new CountCallback() {
            public void done(int count, ParseException e) {
                if (e == null) {
                    Utils.animateBadgeWithCount(AdultDashboardActivity.this.mBinding.messageCount, count);
                }
            }
        });
    }

    private void updateNewInviteCount() {
        try {
            ModelHelper.countPendingIncomingFriendRequests(getAdultProfile(), new CountCallback() {
                public void done(int count, ParseException e) {
                    if (e == null) {
                        Utils.animateBadgeWithCount(AdultDashboardActivity.this.mBinding.inviteCount, count);
                    }
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void displayFriendRequestAcceptedDialog(int requests) {
        AlertDialogWrapper.Builder builder = new AlertDialogWrapper.Builder(this);
        builder.setTitle((int) R.string.title_friend_request);
        builder.setMessage(requests > 1 ? R.string.message_friends_invite_accepted : R.string.message_friend_invite_accepted);
        builder.setPositiveButton((int) R.string.btn_continue, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton((int) R.string.btn_view_permissions, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(AdultDashboardActivity.this, ManageProfilesActivity.class);
                intent.addFlags(1);
                AdultDashboardActivity.this.startActivity(intent);
            }
        });
        builder.show();
    }

    @DebugLog
    private void startRemoteDataSync() {
        ModelHelper.getPendingFriendRequestAcceptanceNotifications(new FindCallback<FriendAcceptanceNotification>() {
            public void done(List<FriendAcceptanceNotification> list, ParseException e) {
                if (e == null && list.size() > 0) {
                    AdultDashboardActivity.this.displayFriendRequestAcceptedDialog(list.size());
                    ModelHelper.clearAllFriendRequestAcceptanceNotifications(null);
                }
            }
        });
        ModelHelper.fetchAllFriendsToLocalDatastore(new FindCallback<FriendRecord>() {
            public void done(List<FriendRecord> list, ParseException e) {
                AdultDashboardActivity.this.updateNewInviteCount();
                AdultDashboardActivity.this.mFriendsLoaded = true;
            }
        });
        ModelHelper.fetchAllVoiceMessagesToLocalDatastore(new FindCallback<VoiceMessage>() {
            public void done(List<VoiceMessage> list, ParseException e) {
                AdultDashboardActivity.this.updateNewMessageCount();
                AdultDashboardActivity.this.mMessagesLoaded = true;
            }
        });
    }

    public void onBackPressed() {
        if (getFragmentManager().findFragmentByTag(TAG_LOCATION_PERMISSION_REQUEST_FRAGMENT) != null) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void updateTooltip() {
        this.mIsInitialAdultTooltipComplete = SettingsManager.isInitialAdultTooltipComplete(this);
        this.mBinding.tooltip.clearAnimation();
        this.mBinding.tooltip.setVisibility(this.mIsInitialAdultTooltipComplete ? 8 : 0);
        if (!this.mIsInitialAdultTooltipComplete) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.tooltip_wiggle);
            animation.setStartTime(AnimationUtils.currentAnimationTimeMillis() + 1500);
            animation.setAnimationListener(new AnimationListener() {
                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    animation.reset();
                    animation.setStartTime(AnimationUtils.currentAnimationTimeMillis() + 2000);
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
            this.mBinding.tooltip.setAnimation(animation);
        }
    }
}
