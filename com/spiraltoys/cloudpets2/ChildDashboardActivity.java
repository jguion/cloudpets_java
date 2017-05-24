package com.spiraltoys.cloudpets2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.ButtonCallback;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.google.common.io.Files;
import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.audio.BackgroundMusicTrack;
import com.spiraltoys.cloudpets2.databinding.ActivityChildDashboardBinding;
import com.spiraltoys.cloudpets2.events.ProfileSelectedForToyDashboardEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessageClickedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessageDeletedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessageMarkedAsViewedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessagePushNotificationReceivedEvent;
import com.spiraltoys.cloudpets2.fragments.ChildDashboardDialogFragment;
import com.spiraltoys.cloudpets2.fragments.ChildDashboardSelectProfileFragment.OnSelectProfileFragmentInteractionListener;
import com.spiraltoys.cloudpets2.fragments.ChildLullabiesFragment;
import com.spiraltoys.cloudpets2.fragments.ChildLullabiesFragment.OnChildDashboardLullabiesInteractionListener;
import com.spiraltoys.cloudpets2.fragments.ChildLullabyDetailsFragment.OnChildDashboardLullabyInteractionListener;
import com.spiraltoys.cloudpets2.fragments.ChildRecordedMessageActionsFragment.OnChildRecordedMessageActionsFragmentInteractionListener;
import com.spiraltoys.cloudpets2.fragments.ChildStoriesFragment.OnChildDashboardStoriesInteractionListener;
import com.spiraltoys.cloudpets2.fragments.ChildStoryDetailsFragment.OnChildDashboardStoryInteractionListener;
import com.spiraltoys.cloudpets2.fragments.ChildVoiceMessageDetailsFragment.OnChildVoiceMessageDetailsFragmentInteractionListener;
import com.spiraltoys.cloudpets2.fragments.FloatingProfileSwitcherFragment;
import com.spiraltoys.cloudpets2.fragments.FloatingProfileSwitcherFragment.FloatingProfilePickerUnderlayProvider;
import com.spiraltoys.cloudpets2.fragments.FloatingProfileSwitcherFragment.OnFloatingProfileSwitcherFragmentInteractionListener;
import com.spiraltoys.cloudpets2.fragments.PremiumDialogFragment;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Lullaby;
import com.spiraltoys.cloudpets2.model.PlushToy;
import com.spiraltoys.cloudpets2.model.PrivateProfile.ProfileType;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.Story;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.toy.ToyAudioSlot;
import com.spiraltoys.cloudpets2.toy.ToyConnectionManager;
import com.spiraltoys.cloudpets2.toy.ToyManager;
import com.spiraltoys.cloudpets2.toy.ToyService;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandIdentifier;
import com.spiraltoys.cloudpets2.toy.event.ToyEventCommandSucceeded;
import com.spiraltoys.cloudpets2.toy.event.ToyEventError;
import com.spiraltoys.cloudpets2.toy.event.ToyEventGameModeButtonPress;
import com.spiraltoys.cloudpets2.toy.event.ToyEventReceivedAudio;
import com.spiraltoys.cloudpets2.toy.event.ToyEventRequiresUpdate;
import com.spiraltoys.cloudpets2.toy.event.ToyEventState;
import com.spiraltoys.cloudpets2.toy.event.ToyEventUpdateProgress;
import com.spiraltoys.cloudpets2.unity.UnityPlayerActivity;
import com.spiraltoys.cloudpets2.util.CacheUtil;
import com.spiraltoys.cloudpets2.util.CacheUtil.Callback;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.SettingsManager;
import com.spiraltoys.cloudpets2.util.Utils;
import com.spiraltoys.cloudpets2.views.ConnectionIndicatorView.ConnectionIndicatorState;
import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;
import icepick.State;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChildDashboardActivity extends InteractiveToyActivity implements OnChildVoiceMessageDetailsFragmentInteractionListener, OnChildRecordedMessageActionsFragmentInteractionListener, OnFloatingProfileSwitcherFragmentInteractionListener, FloatingProfilePickerUnderlayProvider, OnSelectProfileFragmentInteractionListener, OnBackStackChangedListener, OnChildDashboardLullabiesInteractionListener, OnChildDashboardLullabyInteractionListener, OnChildDashboardStoriesInteractionListener, OnChildDashboardStoryInteractionListener {
    private static final String EXTRA_PROFILE_ID = "extra_profile_id";
    private static final String EXTRA_SKIP_SPLASH_SCREEN = "extra_skip_splash_screen";
    private static final String TAG_DIALOG_FRAGMENT = "tag_dialog_fragment";
    private static final int VISITING_CLOUDPET_DISPLAY_DURATION_MS = 2750;
    private static final int VISITING_CLOUDPET_FADE_TRANSITION_DURATION_MS = 500;
    private ActivityChildDashboardBinding mBinding;
    private Dialog mBluetoothOffDialog;
    private ChildDashboardDialogFragment mChildDashboardDialogFragment;
    private Profile mChildProfile;
    private ToyConnectionManager mConnectionManager;
    private Handler mHandler;
    private Runnable mHideVisitingCloudPetOverlayRunnable = new Runnable() {
        public void run() {
            ChildDashboardActivity.this.mBinding.visitingCloudpetOverlay.getRoot().animate().alpha(0.0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    ChildDashboardActivity.this.mBinding.visitingCloudpetOverlay.getRoot().setVisibility(8);
                    ChildDashboardActivity.this.checkIfiInitialChildHelpDisplayed();
                    ChildDashboardActivity.this.showBluetoothOffDialogIfNeeded();
                }

                public void onAnimationCancel(Animator animation) {
                    ChildDashboardActivity.this.mBinding.visitingCloudpetOverlay.getRoot().setVisibility(8);
                }
            });
        }
    };
    private boolean mIsSendingAudioToToy;
    @State
    boolean mIsSendingLullabyToToy;
    private boolean mIsSendingMessageToServer;
    private ToyCommandIdentifier mLastLullabyStepCommandIdentifier;
    private ToyCommandIdentifier mLastSendAudioCommandIdentifier;
    @State
    Uri mLullabyAudioUri;
    @State
    long mLullabyPlaybackDuration;
    @State
    double mLullabyVolume;
    private boolean mShouldStayConnected;
    @State
    Uri mTempMessageUri;

    public static void start(BaseActivity context, Profile childProfile, boolean skipSplashScreen) {
        EventBus.getDefault().postSticky(new ProfileSelectedForToyDashboardEvent(childProfile));
        Intent intent = new Intent(context, ChildDashboardActivity.class);
        intent.putExtra(EXTRA_SKIP_SPLASH_SCREEN, skipSplashScreen);
        intent.putExtra("extra_profile_id", childProfile.getObjectId());
        context.startActivityAsNewTask(intent);
    }

    @DebugLog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mHandler = new Handler();
        ProfileSelectedForToyDashboardEvent profileSelectedForToyDashboardEvent = (ProfileSelectedForToyDashboardEvent) EventBus.getDefault().getStickyEvent(ProfileSelectedForToyDashboardEvent.class);
        if (profileSelectedForToyDashboardEvent != null) {
            this.mChildProfile = profileSelectedForToyDashboardEvent.getSelectedProfile();
        }
        if (this.mChildProfile == null) {
            try {
                this.mChildProfile = ModelHelper.getProfileFromLocalDatastore(getProfileId());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        setTheme(PlushToy.getAppThemeResourceForCharacter(this.mChildProfile.getPlushToy().getCharacter()));
        this.mBinding = (ActivityChildDashboardBinding) DataBindingUtil.setContentView(this, R.layout.activity_child_dashboard);
        this.mBinding.setProfile(this.mChildProfile);
        ButterKnife.inject((Activity) this);
        this.mBinding.connectionIndicator.setConfigId(getConfigID());
        this.mBinding.showGamesButtonLabel.setSelected(true);
        this.mBinding.showMessagesButtonLabel.setSelected(true);
        this.mBinding.showStoriesButtonLabel.setSelected(true);
        this.mBinding.showLullabiesButtonLabel.setSelected(true);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.profile_picker_container, FloatingProfileSwitcherFragment.newInstance(this.mChildProfile)).commit();
            if (isSplashScreenSkipped()) {
                checkIfiInitialChildHelpDisplayed();
            } else {
                EventBus.getDefault().removeStickyEvent(ToyEventReceivedAudio.class);
                this.mChildProfile.setJustAccessed(this);
                this.mBinding.visitingCloudpetOverlay.getRoot().setVisibility(0);
                this.mHandler.postDelayed(this.mHideVisitingCloudPetOverlayRunnable, 2750);
            }
        } else {
            this.mChildDashboardDialogFragment = (ChildDashboardDialogFragment) getFragmentManager().findFragmentByTag(TAG_DIALOG_FRAGMENT);
        }
        getFragmentManager().addOnBackStackChangedListener(this);
        startService(new Intent(this, ToyService.class));
        this.mConnectionManager = new ToyConnectionManager(getConfigID());
        setupAds();
    }

    private void setupAds() {
        this.mBinding.adView.setAdListener(new AdListener() {
            public void onAdLoaded() {
                MarginLayoutParams lp = (MarginLayoutParams) ChildDashboardActivity.this.mBinding.connectionIndicator.getLayoutParams();
                int offset = ChildDashboardActivity.this.mBinding.adView.getHeight();
                lp.topMargin = offset;
                ChildDashboardActivity.this.mBinding.connectionIndicator.setLayoutParams(lp);
                lp = (MarginLayoutParams) ChildDashboardActivity.this.mBinding.childDashboardHelp.getLayoutParams();
                lp.topMargin = offset;
                ChildDashboardActivity.this.mBinding.childDashboardHelp.setLayoutParams(lp);
                ChildDashboardActivity.this.mBinding.profilePickerContainer.d(0, offset, 0, 0);
            }
        });
        this.mBinding.adView.loadAd(new Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("02c666be09378a75").build());
        final InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (!ChildDashboardActivity.this.isPaused()) {
                    interstitialAd.show();
                }
            }
        });
        interstitialAd.loadAd(new Builder().addTestDevice("02c666be09378a75").build());
    }

    protected void onPause() {
        super.onPause();
        this.mConnectionManager.onPause(this.mShouldStayConnected);
        this.mShouldStayConnected = false;
        this.mBinding.adView.pause();
    }

    protected void onResume() {
        ToyEventReceivedAudio receivedAudioEvent = (ToyEventReceivedAudio) EventBus.getDefault().getStickyEvent(ToyEventReceivedAudio.class);
        if (receivedAudioEvent != null) {
            onEvent(receivedAudioEvent);
        }
        this.mBinding.adView.resume();
        super.onResume();
        if (VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.mConnectionManager.onResume();
            updateNewMessageCount();
            return;
        }
        startActivityAsNewTask(new Intent(this, AdultDashboardActivity.class));
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            updateNewMessageCount();
        }
        super.onWindowFocusChanged(hasFocus);
    }

    protected void onDestroy() {
        this.mHandler.removeCallbacks(this.mHideVisitingCloudPetOverlayRunnable);
        hideBluetoothOffDialog();
        super.onDestroy();
        this.mBinding.adView.destroy();
    }

    public BackgroundMusicTrack getBackgroundMusicTrack() {
        if (this.mChildProfile == null) {
            return null;
        }
        if (this.mChildProfile.getPlushToy() == null) {
            return BackgroundMusicTrack.BENTLEY_AMBIENT;
        }
        return PlushToy.getBackgroundMusicTrackForCharacter(this.mChildProfile.getPlushToy().getCharacter());
    }

    @OnClick({2131755134})
    void onShowMessagesClicked() {}
        ToyManager.startLoopPlayback(1, 100);
        if (this.mChildDashboardDialogFragment == null || !this.mChildDashboardDialogFragment.isAdded()) {
            showVoiceMessagesDialog();
        }
    }

    @OnClick({2131755163})
    void onShowLullabiesClicked() {
        if (this.mChildDashboardDialogFragment == null || !this.mChildDashboardDialogFragment.isAdded()) {
            showLullabiesDialog();
        }
    }

    @OnClick({2131755158})
    void onShowGamesClicked() {
        PremiumDialogFragment.newInstance().show(getDialogFragmentTransaction(), null);
    }

    @OnClick({2131755161})
    public void onShowStoriesClicked() {
        if (this.mChildDashboardDialogFragment == null || !this.mChildDashboardDialogFragment.isAdded()) {
            showStoriesDialog();
        }
    }

    @OnClick({2131755165})
    void onHelpClicked() {
        showHelpDialog();
    }

    public void onPushToToyClicked(VoiceMessage voiceMessage) {
        if (this.mBinding.connectionIndicator.getCurrentConnectionIndicatorState() != ConnectionIndicatorState.CONNECTED) {
            Utils.showErrorDialogWithTitle((Context) this, (int) R.string.error_toy_not_connected_for_send_to_cloudpet, (int) R.string.error_toy_not_connected_title);
            return;
        }
        showProgress((int) R.string.title_sending_message_to_toy, false);
        CacheUtil.fetchToTemporaryFileAndCache(this, Uri.parse(voiceMessage.getFile().getUrl()), new Callback() {
            public void done(Uri uri, Exception e) {
                if (e != null) {
                    ChildDashboardActivity.this.hideProgress();
                    Utils.showErrorDialog(ChildDashboardActivity.this, ErrorMessages.getStringResourceIdForErrorFetchingVoiceMessage(ChildDashboardActivity.this, e));
                    return;
                }
                ChildDashboardActivity.this.mIsSendingAudioToToy = true;
                ChildDashboardActivity.this.mLastSendAudioCommandIdentifier = ToyManager.sendAudioToToyAndBlinkLed(uri, ToyAudioSlot.UPLOAD_1);
            }
        });
        markVoiceMessageAsViewed(voiceMessage);
    }

    public void onDeleteVoiceMessageClicked(final VoiceMessage voiceMessage) {
        new AlertDialogWrapper.Builder(this).setTitle((int) R.string.title_dialog_delete_message).setMessage((int) R.string.message_delete_voice_message_confirm).setPositiveButton((int) R.string.btn_delete, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                voiceMessage.deleteEventually();
                ChildDashboardActivity.this.mChildDashboardDialogFragment.getChildFragmentManager().popBackStack();
                EventBus.getDefault().post(new VoiceMessageDeletedEvent());
            }
        }).setNegativeButton((int) R.string.btn_cancel, null).show();
    }

    public void onSelectRecipientsClicked(Uri recordingToSendUri) {
        this.mTempMessageUri = recordingToSendUri;
        this.mChildDashboardDialogFragment.showRecipientSelectorFragment(this.mChildProfile);
    }

    public void onDeleteRecordedMessageClicked(final Uri recordingToDeleteUri) {
        if (this.mChildDashboardDialogFragment.getChildFragmentManager().getBackStackEntryCount() > 1) {
            this.mChildDashboardDialogFragment.getChildFragmentManager().popBackStack();
        } else {
            this.mChildDashboardDialogFragment.dismiss();
        }
        new Thread(new Runnable() {
            public void run() {
                new File(recordingToDeleteUri.getPath()).delete();
            }
        }).run();
    }

    public void onBackStackChanged() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            unmute();
        }
        if (getFragmentManager().getBackStackEntryCount() == 1) {
            mute();
        }
    }

    private void markVoiceMessageAsViewed(VoiceMessage voiceMessage) {
        if (!voiceMessage.isViewed()) {
            voiceMessage.setViewed(true);
            voiceMessage.saveEventually();
            EventBus.getDefault().post(new VoiceMessageMarkedAsViewedEvent());
        }
    }

    private FragmentTransaction getDialogFragmentTransaction() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(TAG_DIALOG_FRAGMENT);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        return ft;
    }

    private void showDownloadingAudioDialog() {
        this.mChildDashboardDialogFragment = ChildDashboardDialogFragment.newInstance(true);
        this.mChildDashboardDialogFragment.show(getDialogFragmentTransaction(), TAG_DIALOG_FRAGMENT);
    }

    private void showVoiceMessagesDialog() {
        this.mChildDashboardDialogFragment = ChildDashboardDialogFragment.newMessagesInstance(getProfileId());
        this.mChildDashboardDialogFragment.show(getDialogFragmentTransaction(), TAG_DIALOG_FRAGMENT);
    }

    private void showRecordedToyAudioDialog(Uri recordedAudioUri) {
        this.mChildDashboardDialogFragment = ChildDashboardDialogFragment.newInstance(recordedAudioUri);
        this.mChildDashboardDialogFragment.show(getDialogFragmentTransaction(), TAG_DIALOG_FRAGMENT);
    }

    private void showLullabiesDialog() {
        PremiumDialogFragment.newInstance().show(getDialogFragmentTransaction(), null);
        this.mChildDashboardDialogFragment = ChildLullabiesFragment.
    }

    private void showStoriesDialog() {
        PremiumDialogFragment.newInstance().show(getDialogFragmentTransaction(), null);
    }

    private void showHelpDialog() {
        this.mChildDashboardDialogFragment = ChildDashboardDialogFragment.newHelpInstance();
        this.mChildDashboardDialogFragment.show(getDialogFragmentTransaction(), TAG_DIALOG_FRAGMENT);
    }

    public void onEvent(ToyEventReceivedAudio event) {
        if (!isUpdatingFirmware() && !isFinishing() && event.getToyIdentifier() != null && getConfigID() != null && event.getToyIdentifier().equals(getConfigID())) {
            mute();
            if (this.mChildDashboardDialogFragment == null || !this.mChildDashboardDialogFragment.isAdded() || this.mChildDashboardDialogFragment.isRemoving()) {
                showRecordedToyAudioDialog(event.getLocalUri());
            } else {
                this.mChildDashboardDialogFragment.showRecording(event.getLocalUri());
            }
            this.mTempMessageUri = event.getLocalUri();
            EventBus.getDefault().removeStickyEvent((Object) event);
        }
    }

    public void onEvent(ToyEventError event) {
        super.onEvent(event);
        EventBus.getDefault().removeStickyEvent((Object) event);
        if (this.mIsSendingAudioToToy || this.mIsSendingLullabyToToy) {
            this.mIsSendingAudioToToy = false;
            this.mIsSendingLullabyToToy = false;
            hideProgress();
            Utils.showErrorDialog((Context) this, event.getMessage());
        } else if (event.getToyState() == ToyState.RECEIVING_AUDIO) {
            this.mChildDashboardDialogFragment.hideIncomingRecordingFragment();
            Utils.showErrorDialog((Context) this, event.getMessage());
        } else if (event.getToyState() == ToyState.NOT_SUPPORTED) {
            Utils.showErrorDialog((Context) this, event.getMessage());
        }
    }

    public void onEvent(VoiceMessageClickedEvent event) {
        this.mChildDashboardDialogFragment.showMessageFragment(event.getVoiceMessage());
    }

    public void onEvent(ToyEventGameModeButtonPress event) {
        if (!this.mIsSendingAudioToToy && !this.mIsSendingLullabyToToy) {
            ToyManager.exitGameModeAndStopPlaybackLoop();
        }
    }

    public void onEvent(ToyEventState event) {
        super.onEvent(event);
        updateState(event.getState(), event.getToyIdentifier());
    }

    public void onEvent(ToyEventRequiresUpdate eventRequiresUpdate) {
        if (eventRequiresUpdate.getToyIdentifier() != null && getConfigID() != null && eventRequiresUpdate.getToyIdentifier().equals(getConfigID())) {
            super.onEvent(eventRequiresUpdate);
        }
    }

    public void onEvent(ToyEventUpdateProgress event) {
        updateProgress(event.getPercentage());
    }

    public void onEvent(VoiceMessagePushNotificationReceivedEvent event) {
        ModelHelper.fetchAllVoiceMessagesToLocalDatastore(new FindCallback<VoiceMessage>() {
            public void done(List<VoiceMessage> list, ParseException e) {
                ChildDashboardActivity.this.updateNewMessageCount();
            }
        });
    }

    private String getConfigID() {
        if (this.mChildProfile == null || ProfileType.CHILD != this.mChildProfile.getPrivateProfile().getProfileType() || this.mChildProfile.getPlushToy() == null) {
            return null;
        }
        return this.mChildProfile.getPlushToy().getConfigID();
    }

    private void showBluetoothOffDialogIfNeeded() {
        if (Utils.isBluetoothEnabled() || this.mBinding.visitingCloudpetOverlay.getRoot().getVisibility() != 8) {
            hideBluetoothOffDialog();
        } else if (this.mBluetoothOffDialog == null || !this.mBluetoothOffDialog.isShowing()) {
            this.mBluetoothOffDialog = new MaterialDialog.Builder(this).title((int) R.string.title_bluetooth_off).content((int) R.string.error_bluetooth_off_child_dashboard).cancelable(false).autoDismiss(false).positiveText((int) R.string.btn_turn_bluetooth_on).callback(new ButtonCallback() {
                public void onPositive(MaterialDialog dialog) {
                    BluetoothAdapter.getDefaultAdapter().enable();
                }
            }).backgroundColor(-1).show();
            this.mBluetoothOffDialog.getWindow().clearFlags(2);
            this.mBluetoothOffDialog.getWindow().addFlags(32);
        }
    }

    private void hideBluetoothOffDialog() {
        if (this.mBluetoothOffDialog != null && this.mBluetoothOffDialog.isShowing()) {
            this.mBluetoothOffDialog.dismiss();
        }
    }

    private void updateState(ToyState toyState, String toyIdentifier) {
        showBluetoothOffDialogIfNeeded();
        if (!(getConfigID() == null || getConfigID().equals(toyIdentifier) || !toyState.isOnline())) {
            toyState = ToyState.READY;
        }
        if (toyState == ToyState.RECEIVING_AUDIO) {
            mute();
            if (this.mChildDashboardDialogFragment == null || !this.mChildDashboardDialogFragment.isAdded() || this.mChildDashboardDialogFragment.isRemoving()) {
                showDownloadingAudioDialog();
            } else {
                this.mChildDashboardDialogFragment.showIncomingRecordingFragment();
            }
        }
    }

    synchronized void mute() {
        super.mute();
        this.mPetVideoSurfaceView.mute();
    }

    synchronized void unmute() {
        super.unmute();
        this.mPetVideoSurfaceView.unmute();
    }

    public void onRefreshProfileList(final SwipeRefreshLayout swipeRefreshLayout) {
        ModelHelper.fetchAllProfilesToLocalDatastore(new FindCallback<Profile>() {
            public void done(List<Profile> list, ParseException e) {
                if (!ChildDashboardActivity.this.isDestroyed() && !ChildDashboardActivity.this.isFinishing()) {
                    ModelHelper.fetchAllFriendsToLocalDatastore(new 1(this));
                }
            }
        });
    }

    public void onProfilesSelected(List<Profile> selectedProfiles) {
        if (this.mTempMessageUri != null && !this.mIsSendingMessageToServer) {
            this.mIsSendingMessageToServer = true;
            showProgress(R.string.title_sending_message_to_toy_cloud);
            try {
                byte[] data = Files.toByteArray(new File(this.mTempMessageUri.getPath()));
                ParseFile file = new ParseFile(UUID.randomUUID().toString() + ".wav", data);
                List<VoiceMessage> messages = new ArrayList();
                for (Profile profile : selectedProfiles) {
                    VoiceMessage message = new VoiceMessage();
                    message.setFile(file);
                    message.setLocalFilename(file.getName());
                    message.setSender(this.mChildProfile);
                    message.setRecipient(profile);
                    message.setType(this.mChildProfile.getPrivateProfile().getProfileType());
                    messages.add(message);
                }
                ModelHelper.saveVoiceMessages(messages, new SaveCallback() {
                    public void done(ParseException e) {
                        ChildDashboardActivity.this.mIsSendingMessageToServer = false;
                        ChildDashboardActivity.this.hideProgress();
                        if (e != null) {
                            e.printStackTrace();
                            Utils.showErrorDialog(ChildDashboardActivity.this, ErrorMessages.getStringResourceIdForErrorSendingVoiceMessage(ChildDashboardActivity.this, e));
                            return;
                        }
                        Toast.makeText(ChildDashboardActivity.this, R.string.title_message_sent, 1).show();
                        ChildDashboardActivity.this.popChildDashboardDialogFragmentBackStack();
                        ChildDashboardActivity.this.popChildDashboardDialogFragmentBackStack();
                    }
                });
            } catch (IOException e) {
                this.mIsSendingMessageToServer = false;
                Utils.showErrorDialog((Context) this, ErrorMessages.getStringResourceIdForIOException(e));
                e.printStackTrace();
            }
        }
    }

    public void onProfileFlyoutMenuShown() {
        hideBluetoothOffDialog();
    }

    public void onProfileFlyoutMenuDismissed() {
        showBluetoothOffDialogIfNeeded();
    }

    public void onProfileSelected(Profile profile) {
        if (profile != this.mChildProfile) {
            Utils.deleteAllCachedToyRecordingsAsync(this);
            super.onProfileSelected(profile);
        }
    }

    public void onProfileMenuLoaded(boolean isEmpty) {
    }

    public void onLullabySelected(Lullaby lullaby) {
        this.mChildDashboardDialogFragment.showLullabyFragment(lullaby);
    }

    public void onSendLullabyToToySelected(Lullaby lullaby, double volume, long playbackDurationMs) {
        if (this.mBinding.connectionIndicator.getCurrentConnectionIndicatorState() != ConnectionIndicatorState.CONNECTED) {
            Utils.showErrorDialogWithTitle((Context) this, (int) R.string.error_toy_not_connected_for_lullaby_to_cloudpet, (int) R.string.error_toy_not_connected_title);
            return;
        }
        showProgress((int) R.string.title_sending_lullaby_to_toy, false);
        this.mLullabyPlaybackDuration = playbackDurationMs;
        this.mLullabyVolume = volume;
        this.mLullabyAudioUri = Uri.parse("android.resource://" + getPackageName() + "/" + lullaby.getAudioResourceId());
        try {
            this.mIsSendingLullabyToToy = true;
            this.mLastLullabyStepCommandIdentifier = ToyManager.startLullaby(this, this.mLullabyAudioUri, this.mLullabyVolume, this.mLullabyPlaybackDuration);
        } catch (IOException e) {
            e.printStackTrace();
            hideProgress();
        }
    }

    public void onStorySelected(Story story) {
        this.mChildDashboardDialogFragment.showStoryFragment(story);
    }

    public void onPlayStory(Story story) {
        if (this.mBinding.connectionIndicator.getCurrentConnectionIndicatorState() != ConnectionIndicatorState.CONNECTED) {
            Utils.showErrorDialog((Context) this, (int) R.string.error_toy_not_connected);
            return;
        }
        this.mShouldStayConnected = true;
        if (story.getUnitySceneName() != null) {
            UnityPlayerActivity.start(this, story, this.mChildProfile);
        } else {
            PlayStoryActivity.start(this, story, this.mChildProfile);
        }
    }

    private void popChildDashboardDialogFragmentBackStack() {
        if (this.mChildDashboardDialogFragment != null && this.mChildDashboardDialogFragment.isAdded() && !this.mChildDashboardDialogFragment.isRemoving()) {
            this.mChildDashboardDialogFragment.getDialog().onBackPressed();
        }
    }

    private void onMessageSentToCloudPet() {
        Toast.makeText(this, R.string.message_child_post_send_message_to_toy_instructions, 1).show();
        this.mIsSendingAudioToToy = false;
        this.mLastSendAudioCommandIdentifier = null;
        hideProgress();
        popChildDashboardDialogFragmentBackStack();
    }

    private void onLullabySentToCloudPet() {
        this.mIsSendingLullabyToToy = false;
        this.mLastLullabyStepCommandIdentifier = null;
        hideProgress();
        popChildDashboardDialogFragmentBackStack();
    }

    @DebugLog
    public void onEvent(ToyEventCommandSucceeded event) {
        if (this.mIsSendingLullabyToToy && event.getCommandIdentifier() != null && event.getCommandIdentifier().equals(this.mLastLullabyStepCommandIdentifier)) {
            onLullabySentToCloudPet();
        }
        if (this.mIsSendingAudioToToy && event.getCommandIdentifier() != null && event.getCommandIdentifier().equals(this.mLastSendAudioCommandIdentifier)) {
            onMessageSentToCloudPet();
        }
    }

    private boolean isSplashScreenSkipped() {
        return getIntent().getBooleanExtra(EXTRA_SKIP_SPLASH_SCREEN, false);
    }

    private String getProfileId() {
        return getIntent().getExtras().getString("extra_profile_id");
    }

    private void checkIfiInitialChildHelpDisplayed() {
        if (!SettingsManager.isInitialChildHelpComplete(this, this.mChildProfile.getUsername()) && !isPaused()) {
            showHelpDialog();
            SettingsManager.setInitialChildHelpComplete(this, this.mChildProfile.getUsername(), true);
        }
    }

    private void updateNewMessageCount() {
        ModelHelper.countLocalNewMessages(this.mChildProfile, false, SettingsManager.isParentalControlEnabled(this), new CountCallback() {
            public void done(int count, ParseException e) {
                if (e == null) {
                    Utils.animateBadgeWithCount(ChildDashboardActivity.this.mBinding.messageCount, count);
                }
            }
        });
    }

    public View provideUnderlayView() {
        return this.mBinding.floatingMenuUnderlay;
    }
}
