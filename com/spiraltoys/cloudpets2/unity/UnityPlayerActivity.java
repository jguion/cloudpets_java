package com.spiraltoys.cloudpets2.unity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MotionEvent;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.BaseActivity;
import com.spiraltoys.cloudpets2.audio.BackgroundMusicTrack;
import com.spiraltoys.cloudpets2.databinding.ActivityUnityPlayerBinding;
import com.spiraltoys.cloudpets2.events.ProfileSelectedForToyDashboardEvent;
import com.spiraltoys.cloudpets2.events.UnityCloseRequestedEvent;
import com.spiraltoys.cloudpets2.events.UnityLoadedEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.Story;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.toy.ToyConnectionManager;
import com.spiraltoys.cloudpets2.toy.ToyManager;
import com.unity3d.player.UnityPlayer;
import de.greenrobot.event.EventBus;

public class UnityPlayerActivity extends BaseActivity {
    private static final String EXTRA_PROFILE_ID = "extra_profile_id";
    private static final String EXTRA_STORY = "extra_story";
    private static final int SPLASH_HIDE_DELAY_MS = 250;
    private ActivityUnityPlayerBinding mBinding;
    private Profile mChildProfile;
    private ToyConnectionManager mConnectionManager;
    private boolean mShouldStayConnected;
    private Story mStory;
    protected UnityPlayer mUnityPlayer;

    public static void start(BaseActivity context, Story story, Profile childProfile) {
        Intent intent = new Intent(context, UnityPlayerActivity.class);
        intent.putExtra(EXTRA_STORY, story);
        intent.putExtra("extra_profile_id", childProfile.getObjectId());
        context.startActivity(intent);
        context.overridePendingTransition(17432576, 17432577);
    }

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        this.mBinding = (ActivityUnityPlayerBinding) DataBindingUtil.setContentView(this, R.layout.activity_unity_player);
        this.mStory = (Story) getIntent().getSerializableExtra(EXTRA_STORY);
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
        if (!(this.mChildProfile == null || this.mChildProfile.getPlushToy() == null)) {
            String toyConfigId = this.mChildProfile.getPlushToy().getConfigID();
            this.mBinding.connectionIndicator.setConfigId(toyConfigId);
            this.mConnectionManager = new ToyConnectionManager(toyConfigId);
        }
        this.mUnityPlayer = new UnityPlayer(this) {
            protected void kill() {
            }
        };
        UnityBridge.getInstance().onCreate();
        UnityBridge.startScene(this.mStory.getUnitySceneName());
        this.mBinding.unityPlayerContainer.addView(this.mUnityPlayer);
        this.mUnityPlayer.requestFocus();
    }

    protected void onDestroy() {
        this.mUnityPlayer.quit();
        UnityBridge.getInstance().onDestroy();
        super.onDestroy();
    }

    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
        this.mUnityPlayer.pause();
        if (this.mConnectionManager.getLastToyState().isConnected()) {
            ToyManager.setGameModeState(false);
        }
        this.mConnectionManager.onPause(this.mShouldStayConnected);
    }

    protected void onResume() {
        super.onResume();
        this.mConnectionManager.onResume();
        this.mUnityPlayer.resume();
        EventBus.getDefault().registerSticky(this);
    }

    public void onBackPressed() {
        UnityBridge.notifySystemBackPressed();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mUnityPlayer.configurationChanged(newConfig);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.mUnityPlayer.windowFocusChanged(hasFocus);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return this.mUnityPlayer.injectEvent(event);
    }

    public BackgroundMusicTrack getBackgroundMusicTrack() {
        return null;
    }

    private String getProfileId() {
        return getIntent().getExtras().getString("extra_profile_id");
    }

    public void onEventMainThread(UnityCloseRequestedEvent event) {
        exitUnity();
    }

    public void onEventMainThread(UnityLoadedEvent event) {
        this.mBinding.splashOverlay.setAlpha(1.0f);
        this.mBinding.splashOverlay.animate().alpha(0.0f).setStartDelay(250).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                UnityPlayerActivity.this.mBinding.splashOverlay.setVisibility(8);
            }
        }).start();
    }

    private void exitUnity() {
        this.mShouldStayConnected = true;
        finish();
        overridePendingTransition(17432576, 17432577);
    }
}
