package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.AlertDialogWrapper.Builder;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.audio.BackgroundMusicTrack;
import com.spiraltoys.cloudpets2.databinding.ActivityPlayStoryBinding;
import com.spiraltoys.cloudpets2.events.ProfileSelectedForToyDashboardEvent;
import com.spiraltoys.cloudpets2.expansion.ExpansionUtils;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.Story;
import com.spiraltoys.cloudpets2.model.Story.Page;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.toy.ToyConnectionManager;
import com.spiraltoys.cloudpets2.toy.ToyManager;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.event.ToyEventGameModeButtonPress;
import com.spiraltoys.cloudpets2.toy.event.ToyEventGameModeButtonPress.Button;
import com.spiraltoys.cloudpets2.toy.event.ToyEventState;
import com.spiraltoys.cloudpets2.util.FadingMediaPlayer;
import com.spiraltoys.cloudpets2.util.FadingMediaPlayer.FadeTransitionListener;
import de.greenrobot.event.EventBus;
import icepick.State;
import java.io.IOException;

public class PlayStoryActivity extends BaseActivity {
    private static final float DISABLED_BUTTON_ALPHA = 0.2f;
    private static final String EXTRA_PROFILE_ID = "extra_profile_id";
    private static final String EXTRA_STORY = "extra_story";
    private ActivityPlayStoryBinding mBinding;
    private Profile mChildProfile;
    private ToyConnectionManager mConnectionManager;
    @State
    int mCurrentPage;
    private boolean mIsPageCompleted;
    private ToyState mLastToyState;
    private FadingMediaPlayer mMediaPlayer;
    private boolean mShouldStayConnected;
    private Story mStory;

    public static void start(BaseActivity context, Story story, Profile childProfile) {
        Intent intent = new Intent(context, PlayStoryActivity.class);
        intent.putExtra(EXTRA_STORY, story);
        intent.putExtra("extra_profile_id", childProfile.getObjectId());
        context.startActivity(intent);
        context.overridePendingTransition(17432576, 17432577);
    }

    void exitStory() {
        this.mShouldStayConnected = true;
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBinding = (ActivityPlayStoryBinding) DataBindingUtil.setContentView(this, R.layout.activity_play_story);
        ButterKnife.inject((Activity) this);
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
        initToolbar();
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

    protected void onStart() {
        super.onStart();
        startPage(this.mCurrentPage);
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onPause() {
        if (this.mLastToyState.isConnected()) {
            ToyManager.setGameModeState(false);
        }
        pauseStory();
        this.mConnectionManager.onPause(this.mShouldStayConnected);
        EventBus.getDefault().unregister(this);
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.mute(new FadeTransitionListener() {
                public void onFadeComplete() {
                    if (PlayStoryActivity.this.mMediaPlayer != null) {
                        PlayStoryActivity.this.mMediaPlayer.release();
                        PlayStoryActivity.this.mMediaPlayer = null;
                    }
                }
            });
        }
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        unpauseStory();
        this.mConnectionManager.onResume();
        EventBus.getDefault().registerSticky(this);
    }

    public BackgroundMusicTrack getBackgroundMusicTrack() {
        return null;
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    public void onBackPressed() {
        pauseStory();
        new Builder(this).setTitle((int) R.string.title_exit_story).setMessage((int) R.string.message_exit_story_confirm).autoDismiss(false).setPositiveButton((int) R.string.btn_yes, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                PlayStoryActivity.this.exitStory();
            }
        }).setNegativeButton((int) R.string.btn_no, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setOnDismissListener(new OnDismissListener() {
            public void onDismiss(DialogInterface dialog) {
                PlayStoryActivity.this.unpauseStory();
            }
        }).show();
    }

    private void startPage(int pageIndex) {
        int i = 0;
        Page page = this.mStory.getPage(pageIndex);
        this.mIsPageCompleted = false;
        TextView textView = this.mBinding.pageText;
        if (page.getText().trim().isEmpty()) {
            i = 4;
        }
        textView.setVisibility(i);
        this.mBinding.pageText.setText(page.getText());
        this.mBinding.pageImage.setImageDrawable(Drawable.createFromStream(page.getImageInputStream(this), page.getImagePath()));
        try {
            playAudio(ExpansionUtils.getMainExpansionDescriptor().getZipResourceFile(this).getAssetFileDescriptor(page.getAudioPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mCurrentPage = pageIndex;
        updateButtonVisibilities();
        updateButtonStates();
    }

    private void completePage() {
        this.mIsPageCompleted = true;
        updateButtonStates();
    }

    private void pauseStory() {
        if (!(this.mIsPageCompleted || this.mMediaPlayer == null || !this.mMediaPlayer.isPlaying())) {
            this.mMediaPlayer.pause();
        }
        updateButtonStates();
    }

    private void unpauseStory() {
        if (!this.mIsPageCompleted) {
            if (this.mMediaPlayer == null || this.mMediaPlayer.isPlaying()) {
                startPage(this.mCurrentPage);
            } else {
                this.mMediaPlayer.start();
            }
        }
        updateButtonStates();
    }

    private boolean isPlayingAudio() {
        return this.mMediaPlayer != null && this.mMediaPlayer.isPlaying();
    }

    public void onEvent(ToyEventGameModeButtonPress event) {
        if (event.getButton() == Button.RECORD) {
            if (this.mBinding.previousPageButton.isEnabled()) {
                startPage(this.mCurrentPage - 1);
            }
        } else if (this.mBinding.nextPageButton.isEnabled()) {
            startPage(this.mCurrentPage + 1);
        }
    }

    public void onEvent(ToyEventState event) {
        if (event.getState().isConnected() && !isStateReadyForStories(event.getState())) {
            Dialog waitingForToyDialog = showProgress((int) R.string.title_waiting_for_toy, (int) R.string.message_please_wait, (int) R.string.btn_exit_story, true);
            if (waitingForToyDialog != null) {
                waitingForToyDialog.setOnCancelListener(new OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        PlayStoryActivity.this.exitStory();
                    }
                });
            }
            pauseStory();
        }
        if (isStateReadyForStories(event.getState()) && !isStateReadyForStories(this.mLastToyState)) {
            ToyManager.setGameModeAndStopPlaybackLoop(true);
            hideProgress();
            if (!isPlayingAudio()) {
                unpauseStory();
            }
        }
        this.mLastToyState = event.getState();
    }

    @OnClick({2131755213})
    public void onPreviousPageClicked() {
        startPage(this.mCurrentPage - 1);
    }

    @OnClick({2131755215})
    public void onNextPageClicked() {
        startPage(this.mCurrentPage + 1);
    }

    private void updateButtonVisibilities() {
        int previousVisibility;
        int nextVisibility;
        if (this.mCurrentPage < 1) {
            previousVisibility = 4;
        } else {
            previousVisibility = 0;
        }
        if (this.mCurrentPage == this.mStory.getPageCount() - 1) {
            nextVisibility = 4;
        } else {
            nextVisibility = 0;
        }
        this.mBinding.previousPageButton.setVisibility(previousVisibility);
        this.mBinding.nextPageButton.setVisibility(nextVisibility);
    }

    private void updateButtonStates() {
        setButtonState(this.mBinding.previousPageButton, this.mIsPageCompleted);
        setButtonState(this.mBinding.nextPageButton, this.mIsPageCompleted);
    }

    private static void setButtonState(ImageButton button, boolean enabled) {
        button.setClickable(enabled);
        button.setEnabled(enabled);
        button.setAlpha(enabled ? 1.0f : DISABLED_BUTTON_ALPHA);
    }

    private String getProfileId() {
        return getIntent().getExtras().getString("extra_profile_id");
    }

    private boolean playAudio(AssetFileDescriptor afd) {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
        this.mMediaPlayer = new FadingMediaPlayer();
        try {
            this.mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            this.mMediaPlayer.prepare();
            this.mMediaPlayer.start();
            this.mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    PlayStoryActivity.this.mMediaPlayer.release();
                    PlayStoryActivity.this.mMediaPlayer = null;
                    PlayStoryActivity.this.completePage();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean isStateReadyForStories(ToyState state) {
        return state != null && state.isConnected() && (!state.isInUse() || state == ToyState.PLAYING_AUDIO || state == ToyState.WRITING_CHARACTERISTIC);
    }
}
