package com.spiraltoys.cloudpets2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.AlertDialogWrapper.Builder;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.audio.BackgroundMusicTrack;
import com.spiraltoys.cloudpets2.databinding.ActivityBarnyardSoundsGameBinding;
import com.spiraltoys.cloudpets2.events.ProfileSelectedForToyDashboardEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PlushToy;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.toy.ToyAudioSlot;
import com.spiraltoys.cloudpets2.toy.ToyConnectionManager;
import com.spiraltoys.cloudpets2.toy.ToyManager;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandIdentifier;
import com.spiraltoys.cloudpets2.toy.event.ToyEventCommandSucceeded;
import com.spiraltoys.cloudpets2.toy.event.ToyEventError;
import com.spiraltoys.cloudpets2.toy.event.ToyEventState;
import com.spiraltoys.cloudpets2.toy.event.ToyEventUpdateProgress;
import com.spiraltoys.cloudpets2.util.FadingMediaPlayer;
import com.spiraltoys.cloudpets2.util.FadingMediaPlayer.FadeTransitionListener;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BarnyardSoundsGameActivity extends BaseActivity {
    private static final int[] ANIMAL_AUDIO_RESOURCE_IDS = new int[]{R.raw.barnyard_chicken, R.raw.barnyard_cow, R.raw.barnyard_horse, R.raw.barnyard_pig};
    private static final ToyAudioSlot[] ANIMAL_AUDIO_SLOTS = new ToyAudioSlot[]{ToyAudioSlot.UPLOAD_1, ToyAudioSlot.UPLOAD_2, ToyAudioSlot.UPLOAD_3, ToyAudioSlot.UPLOAD_4};
    private static final int[] ANIMAL_SEQUENCE_INDICATOR_RESOURCE_IDS = new int[]{R.drawable.barnyard_portrait_chicken, R.drawable.barnyard_portrait_cow, R.drawable.barnyard_portrait_horse, R.drawable.barnyard_portrait_pig};
    private static final long CHECK_INPUT_SEQUENCE_DELAY_MS = 2000;
    private static final int CHICKEN_INDEX = 0;
    private static final double CHICKEN_OFFSET_X = 0.128d;
    private static final double CHICKEN_OFFSET_Y = -0.655d;
    private static final int COW_INDEX = 1;
    private static final double COW_OFFSET_X = 1.0d;
    private static final double COW_OFFSET_Y = 0.95d;
    private static final long DELAY_BETWEEN_ANIMAL_SOUNDS = 1000;
    private static final String EXTRA_PROFILE_ID = "extra_profile_id";
    private static final long GAME_TEXT_FADE_IN_DURATION_MS = 1000;
    private static final long GAME_TEXT_FADE_OUT_DURATION_MS = 1000;
    private static final double GET_READY_OFFSET_X = 0.0d;
    private static final double GET_READY_OFFSET_Y = 0.0d;
    private static final long GLOW_FADE_IN_DURATION_MS = 500;
    private static final long GLOW_FADE_OUT_DURATION_MS = 500;
    private static final int HORSE_INDEX = 2;
    private static final double HORSE_OFFSET_X = -0.02d;
    private static final double HORSE_OFFSET_Y = -0.128d;
    private static final long INTRO_MUSIC_START_DELAY = 1000;
    private static final double LOGO_OFFSET_X = 0.0d;
    private static final double LOGO_OFFSET_Y = -0.5d;
    private static final long MAX_DISCONNECTED_TIME_WITHOUT_REUPLOADING_AUDIO_MS = 60000;
    private static final int PIG_INDEX = 3;
    private static final double PIG_OFFSET_X = -1.0d;
    private static final double PIG_OFFSET_Y = 0.8d;
    private static final int[] SUCCESS_SOUND_RESOURCE_IDS = new int[]{R.raw.barnyard_success_01, R.raw.barnyard_success_02, R.raw.barnyard_success_03};
    private static final int[] SUCCESS_STRING_RESOURCE_IDS = new int[]{R.drawable.barnyard_great, R.drawable.barnyard_excellent};
    private static final double SUCCESS_TEXT_OFFSET_X = 0.0d;
    private static final double SUCCESS_TEXT_OFFSET_Y = 0.0d;
    private static final long SUCCESS_TEXT_UPTIME_DELAY_MS = 1250;
    private static final float TAP_THE_ANIMALS_ANIMATION_INTERACTION_DELAY_FRACTION = 0.5f;
    private static final double TAP_THE_ANIMALS_OFFSET_X = 0.0d;
    private static final double TAP_THE_ANIMALS_OFFSET_Y = 0.0d;
    private static final long TAP_TO_START_FADE_OUT_DURATION_MS = 1000;
    private static final double TAP_TO_START_OFFSET_X = 0.0d;
    private static final double TAP_TO_START_OFFSET_Y = 0.9d;
    private static final double TRY_AGAIN_BUTTON_NO_OFFSET_X = 0.25d;
    private static final double TRY_AGAIN_BUTTON_NO_OFFSET_Y = 0.1d;
    private static final double TRY_AGAIN_BUTTON_YES_OFFSET_X = -0.25d;
    private static final double TRY_AGAIN_BUTTON_YES_OFFSET_Y = 0.1d;
    private static final long TRY_AGAIN_FADE_IN_DURATION_MS = 1000;
    private static final long TRY_AGAIN_FADE_OUT_DURATION_MS = 500;
    private static final double TRY_AGAIN_FRAME_OFFSET_X = 0.0d;
    private static final double TRY_AGAIN_FRAME_OFFSET_Y = 0.0d;
    private static final long TRY_AGAIN_SHOW_DELAY_MS = 3000;
    private static final double TRY_AGAIN_TEXT_OFFSET_X = 0.0d;
    private static final double TRY_AGAIN_TEXT_OFFSET_Y = -0.15d;
    private static final long TUTORIAL_FADE_IN_DURATION_MS = 1000;
    private static final long TUTORIAL_FADE_OUT_DURATION_MS = 1000;
    private MediaPlayer mAnimalMediaPlayer;
    private LinearLayout mAnimalSequenceIndicatorBar;
    private int mBackgroundImageHeight;
    private int mBackgroundImageWidth;
    private ActivityBarnyardSoundsGameBinding mBinding;
    private Dialog mBluetoothOffDialog;
    private ImageView mChickenGlowImageView;
    private ImageView mChickenImageView;
    private Profile mChildProfile;
    private ToyConnectionManager mConnectionManager;
    private ImageView mCowGlowImageView;
    private ImageView mCowImageView;
    private int mCurrentTrySequenceIndex;
    private int mCurrentlyPlayingAudioTrackIndex;
    private int mCurrentlySendingAudioTrackIndex;
    private Dialog mDisconnectedDialog;
    private Dialog mErrorDialog;
    private BarnyardSoundsGame mGame;
    private int mGameContainerHeight;
    private int mGameContainerWidth;
    private ImageView mGetReadyText;
    private Handler mHandler;
    private ImageView mHorseGlowImageView;
    private ImageView mHorseImageView;
    private boolean mIsGameOver;
    private boolean mIsPlayingAudioOnToy;
    private boolean mIsSendingAudioToToy;
    private boolean mIsSequenceInputMode;
    private boolean mIsTapToStartPressed;
    private long mLastConnectionTime;
    private ToyCommandIdentifier mLastPlayAudioCommandIdentifier;
    private ToyCommandIdentifier mLastSendAudioCommandIdentifier;
    private ToyState mLastToyState;
    private ImageView mLogo;
    private FadingMediaPlayer mMusicMediaPlayer;
    private ImageView mPigGlowImageView;
    private ImageView mPigImageView;
    private double mScale;
    private Dialog mSendingAudioDialog;
    private boolean mShouldStayConnected;
    private ImageView[] mSuccessTexts;
    private boolean mSuccessfullySentAudioToToy;
    private ImageView mTapTheAnimalsText;
    private FrameLayout mTapToStartOverlay;
    private ImageView mTapToStartText;
    private FrameLayout mTryAgainLayout;
    private ImageView mTryAgainNoButton;
    private ImageView mTryAgainYesButton;
    private int[] mTrySequence;
    private ImageView mTutorialTapToStartText;
    private int mWorldClippingX;
    private int mWorldClippingY;

    private class BarnyardSoundsGame {
        private static final int NUM_SOUNDS_AT_MAX_DIFFICULTY = 6;
        private static final int NUM_SOUNDS_AT_MIN_DIFFICULTY = 2;
        private int mCurrentDifficultyNumSounds = 1;
        private int[] mCurrentSequence;
        private final int mNumberOfCharacters;
        private Random mRandom = new Random();

        public BarnyardSoundsGame(int numberOfCharacters) {
            this.mNumberOfCharacters = numberOfCharacters;
        }

        public synchronized int[] getCurrentSequence() {
            return this.mCurrentSequence == null ? nextSequence() : this.mCurrentSequence;
        }

        public synchronized int[] nextSequence() {
            this.mCurrentDifficultyNumSounds = Math.min(this.mCurrentDifficultyNumSounds + 1, 6);
            this.mCurrentSequence = new int[this.mCurrentDifficultyNumSounds];
            for (int i = 0; i < this.mCurrentSequence.length; i++) {
                this.mCurrentSequence[i] = this.mRandom.nextInt(this.mNumberOfCharacters);
            }
            return this.mCurrentSequence;
        }
    }

    public static void start(BaseActivity context, Profile childProfile) {
        Intent intent = new Intent(context, BarnyardSoundsGameActivity.class);
        intent.putExtra("extra_profile_id", childProfile.getObjectId());
        context.startActivity(intent);
        context.overridePendingTransition(17432576, 17432577);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBinding = (ActivityBarnyardSoundsGameBinding) DataBindingUtil.setContentView(this, R.layout.activity_barnyard_sounds_game);
        ButterKnife.inject((Activity) this);
        this.mHandler = new Handler();
        this.mBinding.gameContainer.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                BarnyardSoundsGameActivity.this.mBinding.gameContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                BarnyardSoundsGameActivity.this.calculateWorldMetrics();
                BarnyardSoundsGameActivity.this.addChicken();
                BarnyardSoundsGameActivity.this.addHorse();
                BarnyardSoundsGameActivity.this.addPig();
                BarnyardSoundsGameActivity.this.addCow();
                BarnyardSoundsGameActivity.this.addLogo();
                BarnyardSoundsGameActivity.this.addTapToStartText();
                BarnyardSoundsGameActivity.this.addTapToStartOverlay();
                BarnyardSoundsGameActivity.this.addGetReadyText();
                BarnyardSoundsGameActivity.this.addTapTheAnimalsText();
                BarnyardSoundsGameActivity.this.addTryAgain();
                BarnyardSoundsGameActivity.this.addSuccessTexts();
                BarnyardSoundsGameActivity.this.addAnimalSequenceIndicatorBar();
            }
        });
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
            this.mBinding.tutorialCharacter.setImageResource(PlushToy.getGameAvatarResourceForCharacter(this.mChildProfile.getPlushToy().getCharacter()));
        }
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (!BarnyardSoundsGameActivity.this.isFinishing() && !BarnyardSoundsGameActivity.this.isDestroyed()) {
                    BarnyardSoundsGameActivity.this.playMusicTrack(R.raw.barnyard_theme);
                }
            }
        }, 1000);
        setVolumeControlStream(3);
        initToolbar();
    }

    protected void onDestroy() {
        unloadChildImageViews(this.mBinding.getRoot());
        super.onDestroy();
    }

    private void unloadChildImageViews(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                unloadChildImageViews(viewGroup.getChildAt(i));
            }
        } else if (view instanceof ImageView) {
            ((ImageView) view).setImageBitmap(null);
        }
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
        EventBus.getDefault().registerSticky(this);
    }

    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    protected void onPause() {
        super.onPause();
        if (this.mLastToyState.isConnected()) {
            ToyManager.setGameModeState(false);
        }
        this.mConnectionManager.onPause(this.mShouldStayConnected);
        if (this.mMusicMediaPlayer != null) {
            this.mMusicMediaPlayer.mute(new FadeTransitionListener() {
                public void onFadeComplete() {
                    if (BarnyardSoundsGameActivity.this.mMusicMediaPlayer != null) {
                        BarnyardSoundsGameActivity.this.mMusicMediaPlayer.release();
                        BarnyardSoundsGameActivity.this.mMusicMediaPlayer = null;
                    }
                }
            });
        }
    }

    protected void onResume() {
        super.onResume();
        this.mConnectionManager.onResume();
    }

    public void onBackPressed() {
        new Builder(this).setTitle((int) R.string.title_leave_barnyard_sounds).setMessage((int) R.string.message_leave_barnyard_sounds_confirm).setPositiveButton((int) R.string.btn_yes, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                BarnyardSoundsGameActivity.this.exitGame();
            }
        }).setNegativeButton((int) R.string.btn_no, null).show();
    }

    public BackgroundMusicTrack getBackgroundMusicTrack() {
        return null;
    }

    private void exitGame() {
        this.mShouldStayConnected = true;
        super.onBackPressed();
        overridePendingTransition(17432576, 17432577);
    }

    private void calculateWorldMetrics() {
        this.mBackgroundImageWidth = this.mBinding.backgroundImage.getDrawable().getIntrinsicWidth();
        this.mBackgroundImageHeight = this.mBinding.backgroundImage.getDrawable().getIntrinsicHeight();
        this.mGameContainerWidth = this.mBinding.gameContainer.getWidth();
        this.mGameContainerHeight = this.mBinding.gameContainer.getHeight();
        if (((double) this.mBackgroundImageHeight) / ((double) this.mBackgroundImageWidth) <= ((double) this.mGameContainerHeight) / ((double) this.mGameContainerWidth)) {
            this.mScale = ((double) this.mGameContainerHeight) / ((double) this.mBackgroundImageHeight);
        } else {
            this.mScale = ((double) this.mGameContainerWidth) / ((double) this.mBackgroundImageWidth);
        }
        this.mWorldClippingX = (int) (((((double) this.mBackgroundImageWidth) * this.mScale) - ((double) this.mGameContainerWidth)) / 2.0d);
        this.mWorldClippingY = (int) (((((double) this.mBackgroundImageHeight) * this.mScale) - ((double) this.mGameContainerHeight)) / 2.0d);
    }

    private String getProfileId() {
        return getIntent().getExtras().getString("extra_profile_id");
    }

    private boolean playAnimalTrackIfNotCurrentlyPlaying(int trackResourceId) {
        if (this.mAnimalMediaPlayer != null && this.mAnimalMediaPlayer.isPlaying()) {
            return false;
        }
        if (this.mAnimalMediaPlayer != null) {
            this.mAnimalMediaPlayer.release();
            this.mAnimalMediaPlayer = null;
        }
        this.mAnimalMediaPlayer = MediaPlayer.create(this, trackResourceId);
        this.mAnimalMediaPlayer.start();
        this.mAnimalMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                BarnyardSoundsGameActivity.this.mAnimalMediaPlayer.release();
                BarnyardSoundsGameActivity.this.mAnimalMediaPlayer = null;
            }
        });
        return true;
    }

    private boolean playMusicTrack(int trackResourceId) {
        if (this.mMusicMediaPlayer != null) {
            this.mMusicMediaPlayer.release();
            this.mMusicMediaPlayer = null;
        }
        this.mMusicMediaPlayer = new FadingMediaPlayer();
        AssetFileDescriptor afd = getResources().openRawResourceFd(trackResourceId);
        try {
            this.mMusicMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            this.mMusicMediaPlayer.prepare();
            this.mMusicMediaPlayer.start();
            this.mMusicMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    BarnyardSoundsGameActivity.this.mMusicMediaPlayer.release();
                    BarnyardSoundsGameActivity.this.mMusicMediaPlayer = null;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @OnClick({2131755151})
    void onTutorialOverlayClicked() {
        if (this.mBinding.tutorialOverlay.getAlpha() >= 1.0f) {
            hideTutorial();
        }
    }

    void onChickenClicked() {
        if (this.mIsSequenceInputMode && playAnimalTrackIfNotCurrentlyPlaying(R.raw.barnyard_chicken)) {
            animateChickenClicked();
            addAnimalToTrySequence(0);
        }
    }

    void onCowClicked() {
        if (this.mIsSequenceInputMode && playAnimalTrackIfNotCurrentlyPlaying(R.raw.barnyard_cow)) {
            animateCowClicked();
            addAnimalToTrySequence(1);
        }
    }

    void onHorseClicked() {
        if (this.mIsSequenceInputMode && playAnimalTrackIfNotCurrentlyPlaying(R.raw.barnyard_horse)) {
            animateHorseClicked();
            addAnimalToTrySequence(2);
        }
    }

    void onPigClicked() {
        if (this.mIsSequenceInputMode && playAnimalTrackIfNotCurrentlyPlaying(R.raw.barnyard_pig)) {
            animatePigClicked();
            addAnimalToTrySequence(3);
        }
    }

    void onTapToStartOverlayClicked() {
        if (!this.mIsTapToStartPressed) {
            this.mIsTapToStartPressed = true;
            ToyEventState lastToyStateEvent = (ToyEventState) EventBus.getDefault().getStickyEvent(ToyEventState.class);
            if (lastToyStateEvent != null) {
                this.mLastToyState = lastToyStateEvent.getState();
                if (this.mChildProfile.getPlushToy().getConfigID().equals(lastToyStateEvent.getToyIdentifier()) && this.mLastToyState.isConnected() && (!this.mLastToyState.isInUse() || this.mLastToyState == ToyState.PLAYING_AUDIO)) {
                    uploadAnimalAudioToToyIfGameIsStarted();
                } else {
                    Dialog waitingForToyDialog = showProgress((int) R.string.title_waiting_for_toy, (int) R.string.message_please_wait, (int) R.string.btn_exit_game, true);
                    if (waitingForToyDialog != null) {
                        waitingForToyDialog.setOnCancelListener(new OnCancelListener() {
                            public void onCancel(DialogInterface dialog) {
                                BarnyardSoundsGameActivity.this.exitGame();
                            }
                        });
                    }
                }
            }
            hideTapToStart();
        }
    }

    void onTryAgainClicked() {
        if (this.mTryAgainLayout.getAlpha() >= 1.0f) {
            this.mAnimalSequenceIndicatorBar.removeAllViews();
            hideTryAgain();
            onReadyToStartGame();
        }
    }

    void onDontTryAgainClicked() {
        if (this.mTryAgainLayout.getAlpha() >= 1.0f) {
            playMusicTrack(R.raw.barnyard_game_over);
            onBackPressed();
        }
    }

    void onAudioUploadComplete() {
        if (!hideLogo() && this.mBinding.tutorialOverlay.getVisibility() == 8) {
            startLevel();
        }
    }

    void onCurrentSequencePlaybackOnToyComplete() {
        this.mTapTheAnimalsText.setAlpha(0.0f);
        this.mTapTheAnimalsText.setVisibility(0);
        this.mTapTheAnimalsText.animate().alpha(1.0f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                BarnyardSoundsGameActivity.this.mTapTheAnimalsText.animate().alpha(0.0f).setDuration(1000).setListener(null).start();
            }
        }).setUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animation.getAnimatedFraction() > BarnyardSoundsGameActivity.TAP_THE_ANIMALS_ANIMATION_INTERACTION_DELAY_FRACTION && !BarnyardSoundsGameActivity.this.mIsSequenceInputMode) {
                    BarnyardSoundsGameActivity.this.startSequenceInputMode();
                }
            }
        }).start();
    }

    void onReadyToStartGame() {
        this.mGame = new BarnyardSoundsGame(ANIMAL_AUDIO_RESOURCE_IDS.length);
        startNextLevel();
    }

    void onGameOver() {
        this.mIsGameOver = true;
        playMusicTrack(R.raw.barnyard_lose);
        for (int i = 0; i < this.mGame.getCurrentSequence().length; i++) {
            final int correctAnswer = this.mGame.getCurrentSequence()[i];
            if (this.mTrySequence[i] != correctAnswer) {
                final ImageView indicator = (ImageView) this.mAnimalSequenceIndicatorBar.getChildAt(i);
                Animation dropOutAnimation = AnimationUtils.loadAnimation(this, R.anim.barnyard_drop_out);
                dropOutAnimation.setAnimationListener(new AnimationListener() {
                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        indicator.setImageResource(BarnyardSoundsGameActivity.ANIMAL_SEQUENCE_INDICATOR_RESOURCE_IDS[correctAnswer]);
                        Animation popInAnimation = AnimationUtils.loadAnimation(BarnyardSoundsGameActivity.this, R.anim.barnyard_pop_in);
                        popInAnimation.setAnimationListener(new 1(this));
                        indicator.startAnimation(popInAnimation);
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                indicator.startAnimation(dropOutAnimation);
            }
        }
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (!BarnyardSoundsGameActivity.this.isFinishing() && !BarnyardSoundsGameActivity.this.isDestroyed()) {
                    BarnyardSoundsGameActivity.this.showTryAgain();
                }
            }
        }, TRY_AGAIN_SHOW_DELAY_MS);
    }

    void onToyDisconnected() {
        if (!(this.mGame == null || this.mIsGameOver)) {
            cancelSequenceInputMode();
        }
        if (Utils.isBluetoothEnabled()) {
            hideBluetoothOffDialog();
            showDisconnectedDialog();
        }
    }

    void onToyReconnected() {
        hideDisconnectedDialog();
        if (this.mGame != null && !this.mIsGameOver && !isToyAudioExpired()) {
            startLevel();
        }
    }

    private void startNextLevel() {
        this.mGame.nextSequence();
        startLevel();
    }

    private void startLevel() {
        this.mIsGameOver = false;
        this.mGetReadyText.setAlpha(0.0f);
        this.mGetReadyText.setVisibility(0);
        this.mGetReadyText.animate().alpha(1.0f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                BarnyardSoundsGameActivity.this.fadeOutGetReadyTextAndStart();
            }
        }).start();
    }

    private void fadeOutGetReadyTextAndStart() {
        this.mGetReadyText.animate().alpha(0.0f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                BarnyardSoundsGameActivity.this.playCurrentSequenceOnToy();
            }
        }).start();
    }

    private void startSequenceInputMode() {
        this.mIsSequenceInputMode = true;
        this.mTrySequence = new int[this.mGame.getCurrentSequence().length];
        this.mCurrentTrySequenceIndex = 0;
    }

    private void finishSequenceInputMode() {
        this.mIsSequenceInputMode = false;
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (!BarnyardSoundsGameActivity.this.isFinishing() && !BarnyardSoundsGameActivity.this.isDestroyed()) {
                    BarnyardSoundsGameActivity.this.checkInputSequenceForPassFail();
                }
            }
        }, CHECK_INPUT_SEQUENCE_DELAY_MS);
    }

    private void cancelSequenceInputMode() {
        this.mIsSequenceInputMode = false;
        this.mAnimalSequenceIndicatorBar.removeAllViews();
    }

    private void checkInputSequenceForPassFail() {
        if (Arrays.equals(this.mTrySequence, this.mGame.getCurrentSequence())) {
            final ImageView successText = this.mSuccessTexts[new Random().nextInt(this.mSuccessTexts.length)];
            successText.setAlpha(0.0f);
            successText.setVisibility(0);
            successText.animate().alpha(1.0f).setStartDelay(0).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    successText.animate().alpha(0.0f).setStartDelay(BarnyardSoundsGameActivity.SUCCESS_TEXT_UPTIME_DELAY_MS).setDuration(1000).setListener(new 1(this));
                }
            }).start();
            playRandomSuccessTrack();
            return;
        }
        onGameOver();
    }

    private void playRandomSuccessTrack() {
        playMusicTrack(SUCCESS_SOUND_RESOURCE_IDS[new Random().nextInt(SUCCESS_SOUND_RESOURCE_IDS.length)]);
    }

    private void addAnimalToTrySequence(int animalIndex) {
        int[] iArr = this.mTrySequence;
        int i = this.mCurrentTrySequenceIndex;
        this.mCurrentTrySequenceIndex = i + 1;
        iArr[i] = animalIndex;
        addWorldScaledResource(ANIMAL_SEQUENCE_INDICATOR_RESOURCE_IDS[animalIndex], 0.0d, 0.0d, this.mAnimalSequenceIndicatorBar);
        if (isTrySequenceFull()) {
            finishSequenceInputMode();
        }
    }

    private boolean isTrySequenceFull() {
        return this.mCurrentTrySequenceIndex >= this.mTrySequence.length;
    }

    private void playCurrentSequenceOnToy() {
        this.mCurrentlyPlayingAudioTrackIndex = 0;
        this.mIsPlayingAudioOnToy = true;
        playCurrentSoundOnToy();
    }

    private void playNextSoundOnToy() {
        this.mCurrentlyPlayingAudioTrackIndex++;
        playCurrentSoundOnToy();
    }

    private void playCurrentSoundOnToy() {
        this.mLastPlayAudioCommandIdentifier = ToyManager.triggerSlotPlayback(getToySlotForCurrentGameSequenceIndex(this.mCurrentlyPlayingAudioTrackIndex), Utils.getRelativeToyVolume(this));
        highlightCurrentlyPlayingAnimal();
    }

    private void highlightCurrentlyPlayingAnimal() {
        switch (this.mGame.getCurrentSequence()[this.mCurrentlyPlayingAudioTrackIndex]) {
            case 0:
                animateChickenClicked();
                return;
            case 1:
                animateCowClicked();
                return;
            case 2:
                animateHorseClicked();
                return;
            case 3:
                animatePigClicked();
                return;
            default:
                return;
        }
    }

    private ToyAudioSlot getToySlotForCurrentGameSequenceIndex(int gameSequenceIndex) {
        return ToyAudioSlot.values()[this.mGame.getCurrentSequence()[gameSequenceIndex] + 1];
    }

    private boolean hideLogo() {
        if (this.mLogo.getVisibility() != 0) {
            return false;
        }
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.barnyard_slide_up);
        slideUp.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                BarnyardSoundsGameActivity.this.mLogo.setVisibility(8);
                BarnyardSoundsGameActivity.this.mTapToStartOverlay.setVisibility(8);
                BarnyardSoundsGameActivity.this.showTutorial();
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.mLogo.startAnimation(slideUp);
        return true;
    }

    private void hideTapToStart() {
        this.mTapToStartText.animate().alpha(0.0f).setDuration(1000).start();
    }

    private void showTutorial() {
        this.mBinding.tutorialOverlay.setAlpha(0.0f);
        this.mBinding.tutorialOverlay.setVisibility(0);
        this.mBinding.tutorialOverlay.animate().alpha(1.0f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                BarnyardSoundsGameActivity.this.mTutorialTapToStartText.startAnimation(AnimationUtils.loadAnimation(BarnyardSoundsGameActivity.this, R.anim.barnyard_sounds_pulse_infinite));
            }
        }).start();
    }

    private void hideTutorial() {
        this.mBinding.tutorialOverlay.animate().alpha(0.0f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                BarnyardSoundsGameActivity.this.mBinding.tutorialOverlay.setVisibility(8);
                BarnyardSoundsGameActivity.this.onReadyToStartGame();
            }
        }).start();
    }

    private void showTryAgain() {
        this.mTryAgainLayout.setAlpha(0.0f);
        this.mTryAgainLayout.setVisibility(0);
        this.mTryAgainLayout.animate().alpha(1.0f).setDuration(1000).start();
    }

    private void hideTryAgain() {
        this.mTryAgainLayout.animate().alpha(0.0f).setDuration(500).start();
    }

    private void animateChickenClicked() {
        this.mChickenGlowImageView.animate().alpha(1.0f).setDuration(500).setInterpolator(new DecelerateInterpolator()).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                BarnyardSoundsGameActivity.this.mChickenGlowImageView.animate().alpha(0.0f).setInterpolator(new AccelerateInterpolator()).setDuration(500).setListener(null).start();
            }
        }).start();
    }

    private void animateCowClicked() {
        this.mCowGlowImageView.animate().alpha(1.0f).setDuration(500).setInterpolator(new DecelerateInterpolator()).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                BarnyardSoundsGameActivity.this.mCowGlowImageView.animate().alpha(0.0f).setInterpolator(new AccelerateInterpolator()).setDuration(500).setListener(null).start();
            }
        }).start();
    }

    private void animateHorseClicked() {
        this.mHorseGlowImageView.animate().alpha(1.0f).setDuration(500).setInterpolator(new DecelerateInterpolator()).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                BarnyardSoundsGameActivity.this.mHorseGlowImageView.animate().alpha(0.0f).setInterpolator(new AccelerateInterpolator()).setDuration(500).setListener(null).start();
            }
        }).start();
    }

    private void animatePigClicked() {
        this.mPigGlowImageView.animate().alpha(1.0f).setDuration(500).setInterpolator(new DecelerateInterpolator()).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                BarnyardSoundsGameActivity.this.mPigGlowImageView.animate().alpha(0.0f).setInterpolator(new AccelerateInterpolator()).setDuration(500).setListener(null).start();
            }
        }).start();
    }

    private void addHorse() {
        this.mHorseImageView = addWorldScaledResource(R.drawable.barnyard_horse, HORSE_OFFSET_X, HORSE_OFFSET_Y);
        this.mHorseGlowImageView = addWorldScaledResource(R.drawable.barnyard_horse_glow, HORSE_OFFSET_X, HORSE_OFFSET_Y);
        this.mHorseGlowImageView.setAlpha(0.0f);
        this.mHorseGlowImageView.setClickable(false);
        this.mHorseImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BarnyardSoundsGameActivity.this.onHorseClicked();
            }
        });
    }

    private void addChicken() {
        this.mChickenImageView = addWorldScaledResource(R.drawable.barnyard_chicken, CHICKEN_OFFSET_X, CHICKEN_OFFSET_Y);
        this.mChickenGlowImageView = addWorldScaledResource(R.drawable.barnyard_chicken_glow, CHICKEN_OFFSET_X, CHICKEN_OFFSET_Y);
        this.mChickenGlowImageView.setAlpha(0.0f);
        this.mChickenGlowImageView.setClickable(false);
        this.mChickenImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BarnyardSoundsGameActivity.this.onChickenClicked();
            }
        });
    }

    private void addPig() {
        this.mPigImageView = addWorldScaledResource(R.drawable.barnyard_pig, PIG_OFFSET_X, PIG_OFFSET_Y);
        this.mPigGlowImageView = addWorldScaledResource(R.drawable.barnyard_pig_glow, PIG_OFFSET_X, PIG_OFFSET_Y);
        this.mPigGlowImageView.setAlpha(0.0f);
        this.mPigGlowImageView.setClickable(false);
        this.mPigImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BarnyardSoundsGameActivity.this.onPigClicked();
            }
        });
    }

    private void addCow() {
        this.mCowImageView = addWorldScaledResource(R.drawable.barnyard_cow, COW_OFFSET_X, COW_OFFSET_Y);
        this.mCowGlowImageView = addWorldScaledResource(R.drawable.barnyard_cow_glow, COW_OFFSET_X, COW_OFFSET_Y);
        this.mCowGlowImageView.setAlpha(0.0f);
        this.mCowGlowImageView.setClickable(false);
        this.mCowImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BarnyardSoundsGameActivity.this.onCowClicked();
            }
        });
    }

    private void addLogo() {
        this.mLogo = addWorldScaledResource(R.drawable.barnyard_logo, 0.0d, LOGO_OFFSET_Y);
        this.mLogo.setAnimation(AnimationUtils.loadAnimation(this, R.anim.barnyard_slide_down));
    }

    private void addTapToStartText() {
        this.mTapToStartText = addWorldScaledResource(R.drawable.barnyard_text_tap_to_start, 0.0d, TAP_TO_START_OFFSET_Y);
        this.mTapToStartText.setAnimation(AnimationUtils.loadAnimation(this, R.anim.barnyard_pulse_infinite_fade_in));
        this.mTutorialTapToStartText = addWorldScaledResource(R.drawable.barnyard_text_tap_to_start, this.mBinding.tutorialButtonContainer);
    }

    private void addGetReadyText() {
        this.mGetReadyText = addWorldScaledResource(R.drawable.barnyard_get_ready, 0.0d, 0.0d);
        this.mGetReadyText.setVisibility(8);
    }

    private void addTapToStartOverlay() {
        this.mTapToStartOverlay = new FrameLayout(this);
        LayoutParams lp = new LayoutParams(-1, -1);
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(16843499, typedValue, true);
        lp.topMargin = obtainStyledAttributes(typedValue.resourceId, new int[]{16843499}).getDimensionPixelSize(0, 0);
        this.mBinding.gameContainer.addView(this.mTapToStartOverlay, lp);
        this.mTapToStartOverlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BarnyardSoundsGameActivity.this.onTapToStartOverlayClicked();
            }
        });
    }

    private void addTapTheAnimalsText() {
        this.mTapTheAnimalsText = addWorldScaledResource(R.drawable.barnyard_text_tap_the_animals, 0.0d, 0.0d);
        this.mTapTheAnimalsText.setVisibility(8);
    }

    private void addTryAgain() {
        this.mTryAgainLayout = new FrameLayout(this);
        this.mBinding.gameContainer.addView(this.mTryAgainLayout, new LayoutParams(-1, -1));
        this.mTryAgainLayout.setVisibility(8);
        addWorldScaledResource(R.drawable.barnyard_try_again_frame, 0.0d, 0.0d, this.mTryAgainLayout);
        addWorldScaledResource(R.drawable.barnyard_text_try_again, 0.0d, TRY_AGAIN_TEXT_OFFSET_Y, this.mTryAgainLayout);
        this.mTryAgainYesButton = addWorldScaledResource(R.drawable.barnyard_button_yes, TRY_AGAIN_BUTTON_YES_OFFSET_X, 0.1d, this.mTryAgainLayout);
        this.mTryAgainNoButton = addWorldScaledResource(R.drawable.barnyard_button_no, TRY_AGAIN_BUTTON_NO_OFFSET_X, 0.1d, this.mTryAgainLayout);
        this.mTryAgainYesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BarnyardSoundsGameActivity.this.onTryAgainClicked();
            }
        });
        this.mTryAgainNoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BarnyardSoundsGameActivity.this.onDontTryAgainClicked();
            }
        });
    }

    private void addSuccessTexts() {
        this.mSuccessTexts = new ImageView[SUCCESS_STRING_RESOURCE_IDS.length];
        for (int i = 0; i < SUCCESS_STRING_RESOURCE_IDS.length; i++) {
            this.mSuccessTexts[i] = addWorldScaledResource(SUCCESS_STRING_RESOURCE_IDS[i], 0.0d, 0.0d);
            this.mSuccessTexts[i].setVisibility(8);
        }
    }

    private void addAnimalSequenceIndicatorBar() {
        this.mAnimalSequenceIndicatorBar = new LinearLayout(this);
        this.mAnimalSequenceIndicatorBar.setLayoutTransition(new LayoutTransition());
        this.mAnimalSequenceIndicatorBar.setGravity(1);
        this.mAnimalSequenceIndicatorBar.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.barnyard_sequence_indicator_divider));
        this.mAnimalSequenceIndicatorBar.setShowDividers(2);
        LayoutParams lp = new LayoutParams(-1, -2);
        lp.topMargin = getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin);
        this.mBinding.gameContainer.addView(this.mAnimalSequenceIndicatorBar, lp);
    }

    private Bitmap getWorldScaledBitmap(int resourceId) {
        return Utils.decodeSampledBitmapFromResource(getResources(), resourceId, (int) Math.ceil(this.mScale));
    }

    private ImageView addWorldScaledResource(int resourceId, double offsetX, double offsetY, ViewGroup root) {
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(getWorldScaledBitmap(resourceId));
        LayoutParams lp = new LayoutParams((int) (((double) imageView.getDrawable().getIntrinsicWidth()) * this.mScale), (int) (((double) imageView.getDrawable().getIntrinsicHeight()) * this.mScale), 51);
        lp.leftMargin = (int) ((((double) (((this.mGameContainerWidth - lp.width) / 2) + this.mWorldClippingX)) * (COW_OFFSET_X + offsetX)) - ((double) this.mWorldClippingX));
        lp.topMargin = (int) ((((double) (((this.mGameContainerHeight - lp.height) / 2) + this.mWorldClippingY)) * (COW_OFFSET_X + offsetY)) - ((double) this.mWorldClippingY));
        root.addView(imageView, lp);
        return imageView;
    }

    private ImageView addWorldScaledResource(int resourceId, ViewGroup root) {
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(getWorldScaledBitmap(resourceId));
        root.addView(imageView, new LayoutParams((int) (((double) imageView.getDrawable().getIntrinsicWidth()) * this.mScale), (int) (((double) imageView.getDrawable().getIntrinsicHeight()) * this.mScale), 17));
        return imageView;
    }

    private ImageView addWorldScaledResource(int resourceId, double offsetX, double offsetY) {
        return addWorldScaledResource(resourceId, offsetX, offsetY, this.mBinding.gameContainer);
    }

    private void showBluetoothOffDialog() {
        if (this.mBluetoothOffDialog == null || !this.mBluetoothOffDialog.isShowing()) {
            this.mBluetoothOffDialog = new Builder(this).setTitle((int) R.string.title_bluetooth_off).setMessage((int) R.string.error_bluetooth_off_setup).setPositiveButton((int) R.string.btn_turn_bluetooth_on, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    BluetoothAdapter.getDefaultAdapter().enable();
                }
            }).setNegativeButton((int) R.string.btn_exit_game, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    BarnyardSoundsGameActivity.this.exitGame();
                }
            }).autoDismiss(false).setCancelable(false).show();
            hideProgress();
            hideErrorDialog();
            hideDisconnectedDialog();
        }
    }

    private void hideBluetoothOffDialog() {
        if (this.mBluetoothOffDialog != null && this.mBluetoothOffDialog.isShowing()) {
            hideProgress();
            hideErrorDialog();
            this.mBluetoothOffDialog.dismiss();
            if (!this.mLastToyState.isConnected()) {
                showDisconnectedDialog();
            }
        }
    }

    private void showOrHideBluetoothOffDialog() {
        if (Utils.isBluetoothEnabled()) {
            hideBluetoothOffDialog();
        } else {
            showBluetoothOffDialog();
        }
    }

    private void showDisconnectedDialog() {
        if (!isFinishing() && !isDestroyed() && this.mDisconnectedDialog == null) {
            hideProgress();
            hideErrorDialog();
            this.mDisconnectedDialog = showProgress((int) R.string.title_game_cloudpet_disconnected, (int) R.string.message_game_cloudpet_disconnected, (int) R.string.btn_exit_game, true);
            if (this.mDisconnectedDialog != null) {
                this.mDisconnectedDialog.setOnCancelListener(new OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        BarnyardSoundsGameActivity.this.exitGame();
                    }
                });
            }
        }
    }

    private void hideDisconnectedDialog() {
        if (this.mDisconnectedDialog != null) {
            hideProgress();
            this.mDisconnectedDialog = null;
        }
    }

    private void showErrorDialog(String message) {
        this.mErrorDialog = Utils.showErrorDialog((Context) this, message);
    }

    private void hideErrorDialog() {
        if (this.mErrorDialog != null && this.mErrorDialog.isShowing()) {
            this.mErrorDialog.hide();
        }
    }

    public void onEvent(ToyEventState event) {
        super.onEvent(event);
        if (event.getToyIdentifier() == null || this.mChildProfile.getPlushToy().getConfigID().equals(event.getToyIdentifier())) {
            if (!(this.mLastToyState == null || this.mLastToyState.isConnected() == event.getState().isConnected())) {
                if (event.getState().isConnected()) {
                    onToyReconnected();
                } else {
                    onToyDisconnected();
                }
            }
            if (!(this.mLastToyState == null || !this.mLastToyState.isConnected() || event.getState().isConnected())) {
                this.mLastConnectionTime = SystemClock.elapsedRealtime();
            }
            if (event.getState().isConnected() && (this.mLastToyState == null || !this.mLastToyState.isConnected())) {
                if (!this.mSuccessfullySentAudioToToy || isToyAudioExpired()) {
                    uploadAnimalAudioToToyIfGameIsStarted();
                } else if (this.mIsTapToStartPressed) {
                    ToyManager.setGameModeState(true);
                }
            }
            if (!this.mSuccessfullySentAudioToToy && !this.mIsSendingAudioToToy && event.getState().isConnected() && (!event.getState().isInUse() || event.getState() == ToyState.PLAYING_AUDIO)) {
                uploadAnimalAudioToToyIfGameIsStarted();
            }
            if (this.mSuccessfullySentAudioToToy && this.mIsPlayingAudioOnToy && event.getState() == ToyState.CONNECTED && this.mLastToyState == ToyState.PLAYING_AUDIO) {
                if (this.mCurrentlyPlayingAudioTrackIndex >= this.mGame.getCurrentSequence().length - 1) {
                    this.mIsPlayingAudioOnToy = false;
                    onCurrentSequencePlaybackOnToyComplete();
                } else {
                    this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            BarnyardSoundsGameActivity.this.playNextSoundOnToy();
                        }
                    }, 1000);
                }
            }
            this.mLastToyState = event.getState();
            showOrHideBluetoothOffDialog();
        }
    }

    private boolean isToyAudioExpired() {
        return SystemClock.elapsedRealtime() - this.mLastConnectionTime > 60000;
    }

    public void onEvent(ToyEventError event) {
        super.onEvent(event);
        EventBus.getDefault().removeStickyEvent((Object) event);
        if (event.getToyState().isConnected() && (this.mIsSendingAudioToToy || this.mIsPlayingAudioOnToy)) {
            this.mIsSendingAudioToToy = false;
            this.mIsPlayingAudioOnToy = false;
            if (this.mDisconnectedDialog == null) {
                hideProgress();
            }
            hideErrorDialog();
            showErrorDialog(event.getMessage());
        } else if (event.getToyState() == ToyState.NOT_SUPPORTED) {
            hideErrorDialog();
            showErrorDialog(event.getMessage());
        }
    }

    public void onEvent(ToyEventCommandSucceeded event) {
        if (this.mLastSendAudioCommandIdentifier != null && this.mLastSendAudioCommandIdentifier.equals(event.getCommandIdentifier())) {
            if (this.mCurrentlySendingAudioTrackIndex == ANIMAL_AUDIO_RESOURCE_IDS.length - 1) {
                this.mIsSendingAudioToToy = false;
                this.mSuccessfullySentAudioToToy = true;
                this.mSendingAudioDialog = null;
                onAudioUploadComplete();
                hideErrorDialog();
                hideProgress();
                return;
            }
            this.mCurrentlySendingAudioTrackIndex++;
            this.mLastSendAudioCommandIdentifier = ToyManager.sendGameAudioToToy(getUriForRawResource(ANIMAL_AUDIO_RESOURCE_IDS[this.mCurrentlySendingAudioTrackIndex]), ANIMAL_AUDIO_SLOTS[this.mCurrentlySendingAudioTrackIndex]);
            if (this.mSendingAudioDialog != null && this.mSendingAudioDialog.isShowing()) {
                this.mSendingAudioDialog.setTitle(getString(R.string.title_sending_to_toy, new Object[]{getResources().getStringArray(R.array.barnyard_animals)[this.mCurrentlySendingAudioTrackIndex]}));
                updateProgress(0.0f);
            }
        }
    }

    public void onEvent(ToyEventUpdateProgress event) {
        updateProgress(event.getPercentage());
    }

    private void uploadAnimalAudioToToyIfGameIsStarted() {
        if (this.mIsTapToStartPressed) {
            hideProgress();
            this.mCurrentlySendingAudioTrackIndex = 0;
            this.mIsSendingAudioToToy = true;
            this.mSendingAudioDialog = showProgress(getString(R.string.title_sending_to_toy, new Object[]{getResources().getStringArray(R.array.barnyard_animals)[this.mCurrentlySendingAudioTrackIndex]}), false);
            this.mLastSendAudioCommandIdentifier = ToyManager.sendGameAudioToToy(getUriForRawResource(ANIMAL_AUDIO_RESOURCE_IDS[this.mCurrentlySendingAudioTrackIndex]), ANIMAL_AUDIO_SLOTS[this.mCurrentlySendingAudioTrackIndex]);
        }
    }

    private Uri getUriForRawResource(int resourceId) {
        return Uri.parse("android.resource://" + getPackageName() + "/" + resourceId);
    }
}
