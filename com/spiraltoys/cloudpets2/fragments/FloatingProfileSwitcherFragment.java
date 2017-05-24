package com.spiraltoys.cloudpets2.fragments;

import android.animation.AnimatorInflater;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.databinding.FragmentFloatingProfileSwitcherBinding;
import com.spiraltoys.cloudpets2.events.VoiceMessageSavedToLocalDatastoreEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.util.SettingsManager;
import com.spiraltoys.cloudpets2.views.ProfilePlaceholderDrawable;
import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;
import java.util.ArrayList;
import java.util.Iterator;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class FloatingProfileSwitcherFragment extends Fragment {
    private static final String ARG_PROFILE_ID = "arg_profile_id";
    private static final int MAX_MENU_ITEMS = 5;
    private static final int MENU_ARROW_TRANSITION_DURATION_MS = 200;
    private static final int MENU_ITEM_OFFSET_DELAY_MS = 20;
    private static final int MENU_UNDERLAY_TRANSITION_DURATION_MS = 200;
    private static final int NUMBER_OF_ANIMATEABLE_COMPONENTS_PER_MENU_ITEM = 3;
    private FragmentFloatingProfileSwitcherBinding mBinding;
    private Typeface mCounterTypeface;
    private boolean mFloatingMenuDisabled;
    private int mFloatingMenuImageElevation;
    private LayoutParams mFloatingMenuImageLayoutParams;
    private int mFloatingMenuImageSize;
    private LinearLayout.LayoutParams mFloatingMenuItemWrapperLayoutParams;
    private int mFloatingMenuLabelElevation;
    private int mFloatingMenuLabelHorizontalPadding;
    private LinearLayout.LayoutParams mFloatingMenuLabelLayoutParams;
    private int mFloatingMenuLabelMargin;
    private int mFloatingMenuLabelVerticalPadding;
    private LinearLayout.LayoutParams mFloatingMenuSeparatorDotLayoutParams;
    private int mFloatingMenuSeparatorMargin;
    private int mFloatingMenuSeparatorSize;
    private Handler mHandler;
    private boolean mHasNoProfiles;
    private boolean mIsParentalControlEnabled;
    private boolean mIsPasswordProtectAdultDashboardEnabled;
    private OnFloatingProfileSwitcherFragmentInteractionListener mListener;
    private Profile mProfile;
    private ArrayList<Runnable> mProfileMessageCountTasks = new ArrayList();
    private TimeInterpolator mRotationInterpolator;
    private Animation[] mShowViewAnimations;
    private FloatingProfilePickerUnderlayProvider mUnderlayProvider;

    public interface OnFloatingProfileSwitcherFragmentInteractionListener {
        void onProfileFlyoutMenuDismissed();

        void onProfileFlyoutMenuShown();

        void onProfileMenuLoaded(boolean z);

        void onProfileSelected(Profile profile);
    }

    public interface FloatingProfilePickerUnderlayProvider {
        View provideUnderlayView();
    }

    public static FloatingProfileSwitcherFragment newInstance(Profile profile) {
        FloatingProfileSwitcherFragment fragment = new FloatingProfileSwitcherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PROFILE_ID, profile.getObjectId());
        fragment.setArguments(args);
        return fragment;
    }

    @DebugLog
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mHandler = new Handler();
        this.mIsPasswordProtectAdultDashboardEnabled = SettingsManager.isUseAdminPasswordEnabled(getActivity());
        this.mIsParentalControlEnabled = SettingsManager.isParentalControlEnabled(getActivity());
        if (getArguments() != null) {
            try {
                this.mProfile = ModelHelper.getProfileFromLocalDatastore(getArguments().getString(ARG_PROFILE_ID));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        this.mRotationInterpolator = new AccelerateDecelerateInterpolator();
        this.mFloatingMenuImageSize = getResources().getDimensionPixelSize(R.dimen.fab_menu_image_size);
        this.mFloatingMenuSeparatorSize = getResources().getDimensionPixelSize(R.dimen.fab_menu_separator_size);
        this.mFloatingMenuSeparatorMargin = getResources().getDimensionPixelSize(R.dimen.fab_menu_separator_margin);
        this.mFloatingMenuLabelMargin = getResources().getDimensionPixelSize(R.dimen.fab_menu_label_margin);
        this.mFloatingMenuLabelElevation = getResources().getDimensionPixelSize(R.dimen.fab_menu_label_elevation);
        this.mFloatingMenuImageElevation = getResources().getDimensionPixelSize(R.dimen.fab_menu_image_elevation);
        this.mFloatingMenuLabelHorizontalPadding = getResources().getDimensionPixelSize(R.dimen.fab_menu_label_padding_horizontal);
        this.mFloatingMenuLabelVerticalPadding = getResources().getDimensionPixelSize(R.dimen.fab_menu_label_padding_vertical);
        this.mFloatingMenuImageLayoutParams = new LayoutParams(this.mFloatingMenuImageSize, this.mFloatingMenuImageSize);
        this.mFloatingMenuLabelLayoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.mFloatingMenuSeparatorDotLayoutParams = new LinearLayout.LayoutParams(this.mFloatingMenuSeparatorSize, this.mFloatingMenuSeparatorSize);
        this.mFloatingMenuItemWrapperLayoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.mFloatingMenuLabelLayoutParams.setMargins(this.mFloatingMenuLabelMargin, this.mFloatingMenuLabelMargin, this.mFloatingMenuLabelMargin, this.mFloatingMenuLabelElevation / 2);
        this.mFloatingMenuSeparatorDotLayoutParams.setMargins(this.mFloatingMenuSeparatorMargin, 0, this.mFloatingMenuSeparatorMargin, this.mFloatingMenuSeparatorMargin);
        this.mFloatingMenuImageLayoutParams.addRule(13);
    }

    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        this.mIsPasswordProtectAdultDashboardEnabled = SettingsManager.isUseAdminPasswordEnabled(getActivity());
        this.mIsParentalControlEnabled = SettingsManager.isParentalControlEnabled(getActivity());
        updateNewMessageCounters();
    }

    public void onEvent(VoiceMessageSavedToLocalDatastoreEvent event) {
        updateNewMessageCounters();
    }

    @OnClick({2131755287})
    void onSwitchProfileClicked() {
        toggleProfilePicker();
    }

    void onMenuUnderlayClicked() {
        hideProfilePicker();
    }

    @DebugLog
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentFloatingProfileSwitcherBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_floating_profile_switcher, container, false);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        this.mCounterTypeface = TypefaceUtils.load(getActivity().getAssets(), "fonts/merge_regular.otf");
        this.mBinding.switchProfileButton.setText(this.mProfile.getDisplayName().trim());
        this.mBinding.profileSwitcher.setEnabled(false);
        setupProfilePhoto(this.mBinding.profilePhoto, this.mProfile, true);
        setupProfilePicker();
        getUnderlayView().setOnClickListener(new 1(this));
        return this.mBinding.getRoot();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnFloatingProfileSwitcherFragmentInteractionListener) activity;
            if (activity instanceof FloatingProfilePickerUnderlayProvider) {
                this.mUnderlayProvider = (FloatingProfilePickerUnderlayProvider) activity;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFloatingProfileSwitcherFragmentInteractionListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    private void onProfileSelected(Profile profile) {
        if (isProfilePasswordLocked(profile)) {
            ProfileSwitcherDialogFragment.newInstance(profile, true).show(getFragmentManager(), null);
        } else if (this.mListener != null) {
            this.mListener.onProfileSelected(profile);
        }
    }

    private boolean isProfilePasswordLocked(Profile profile) {
        return profile.getPrivateProfile().isAdult() && this.mIsPasswordProtectAdultDashboardEnabled;
    }

    private void updateNewMessageCounters() {
        Iterator it = this.mProfileMessageCountTasks.iterator();
        while (it.hasNext()) {
            this.mHandler.post((Runnable) it.next());
        }
    }

    private void toggleProfilePicker() {
        if (!this.mHasNoProfiles) {
            if (this.mFloatingMenuDisabled) {
                showMoreProfilesPicker();
            } else if (this.mBinding.floatingMenu.getVisibility() == 0) {
                hideProfilePicker();
            } else {
                showProfilePicker();
            }
        }
    }

    private synchronized void hideProfilePicker() {
        this.mBinding.switchProfileButtonArrow.animate().setInterpolator(this.mRotationInterpolator).setDuration(200).rotation(0.0f).start();
        getUnderlayView().setVisibility(0);
        getUnderlayView().animate().alpha(0.0f).setDuration(200).setListener(new 2(this)).start();
        this.mBinding.floatingMenu.setVisibility(0);
        this.mBinding.floatingMenu.setAlpha(1.0f);
        this.mBinding.floatingMenu.animate().alpha(0.0f).setDuration(200).setListener(new 3(this)).start();
    }

    private synchronized void showProfilePicker() {
        if (this.mListener != null) {
            this.mListener.onProfileFlyoutMenuShown();
        }
        this.mBinding.switchProfileButtonArrow.animate().setInterpolator(this.mRotationInterpolator).setDuration(200).rotation(180.0f).start();
        this.mBinding.floatingMenu.setVisibility(0);
        this.mBinding.floatingMenu.clearAnimation();
        this.mBinding.floatingMenu.setAlpha(1.0f);
        getUnderlayView().setVisibility(0);
        getUnderlayView().animate().alpha(1.0f).setDuration(200).setListener(new 4(this)).start();
        for (int i = 0; i < this.mBinding.floatingMenu.getChildCount(); i++) {
            LinearLayout wrapper = (LinearLayout) this.mBinding.floatingMenu.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                View childView = wrapper.getChildAt(j);
                if (childView.getAnimation() != null) {
                    childView.getAnimation().reset();
                    childView.getAnimation().start();
                } else {
                    childView.startAnimation(this.mShowViewAnimations[(i * 3) + j]);
                }
            }
        }
    }

    private void showMoreProfilesPicker() {
        if (!getFragmentManager().isDestroyed()) {
            ProfileSwitcherDialogFragment.newInstance(this.mProfile, false).show(getFragmentManager(), null);
        }
    }

    private void showProfilePickerButton() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_menu_item_scale_up);
        this.mBinding.switchProfileSpinner.setVisibility(0);
        this.mBinding.switchProfileSpinner.startAnimation(animation);
    }

    @DebugLog
    private void setupProfilePicker() {
        this.mHasNoProfiles = true;
        this.mBinding.floatingMenu.removeAllViews();
        ModelHelper.getOwnedProfilesLocalDatastoreQuery(this.mProfile).findInBackground(new 5(this));
    }

    private void setupAnimations() {
        this.mShowViewAnimations = new Animation[(this.mBinding.floatingMenu.getChildCount() * 3)];
        for (int i = 0; i < this.mShowViewAnimations.length; i++) {
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_menu_item_scale_up);
            animation.setStartOffset((long) (i * 20));
            this.mShowViewAnimations[i] = animation;
        }
    }

    private void setupProfilePickerMoreButton() {
        OnClickListener clickListener = new 6(this);
        LinearLayout clickableArea = new LinearLayout(getActivity());
        clickableArea.setOrientation(1);
        clickableArea.setGravity(1);
        clickableArea.setOnClickListener(clickListener);
        clickableArea.setPadding(this.mFloatingMenuSeparatorMargin, 0, this.mFloatingMenuSeparatorMargin, this.mFloatingMenuSeparatorMargin);
        clickableArea.setClipToPadding(false);
        ImageView profileImage = new ImageView(getActivity());
        profileImage.setDuplicateParentStateEnabled(true);
        TextView label = new TextView(getActivity());
        label.setText(R.string.btn_more);
        label.setDuplicateParentStateEnabled(true);
        label.setPadding(this.mFloatingMenuLabelHorizontalPadding, this.mFloatingMenuLabelVerticalPadding, this.mFloatingMenuLabelHorizontalPadding, this.mFloatingMenuLabelVerticalPadding);
        label.setBackgroundResource(R.drawable.fab_menu_label_bg);
        label.setTextColor(ContextCompat.getColor(getActivity(), R.color.primary_text_default_material_light));
        label.setClickable(false);
        if (VERSION.SDK_INT >= 21) {
            label.setStateListAnimator(AnimatorInflater.loadStateListAnimator(getActivity(), R.drawable.floating_menu_label_state_list_anim));
        }
        View separatorDot = new View(getActivity());
        separatorDot.setBackgroundResource(R.drawable.primary_circle_solid);
        if (VERSION.SDK_INT >= 21) {
            profileImage.setStateListAnimator(AnimatorInflater.loadStateListAnimator(getActivity(), R.drawable.floating_menu_image_state_list_anim));
            profileImage.setOutlineProvider(new 7(this));
        }
        clickableArea.addView(separatorDot, this.mFloatingMenuSeparatorDotLayoutParams);
        clickableArea.addView(profileImage, this.mFloatingMenuImageLayoutParams);
        clickableArea.addView(label, this.mFloatingMenuLabelLayoutParams);
        this.mBinding.floatingMenu.addView(clickableArea, this.mFloatingMenuItemWrapperLayoutParams);
        BitmapPool pool = Glide.get(getActivity()).getBitmapPool();
        Glide.with(getActivity()).load(Integer.valueOf(R.drawable.fab_more_bg)).bitmapTransform(new DoubleBorderCropCircleTransformation(getActivity(), pool, getResources().getColor(R.color.primary), -1)).into(profileImage);
    }

    private void setupProfilePhoto(ImageView imageView, Profile profile, boolean isCurrent) {
        String str;
        String displayName = profile.getDisplayName();
        String placeholderText = displayName.isEmpty() ? " " : Character.toString(displayName.charAt(0)).toUpperCase();
        ProfilePortrait profilePortrait = profile.getPortrait();
        BitmapPool pool = Glide.get(getActivity()).getBitmapPool();
        RequestManager with = Glide.with(getActivity());
        if (profilePortrait == null) {
            str = null;
        } else {
            str = profilePortrait.getFile().getUrl();
        }
        with.load(str).bitmapTransform(new DoubleBorderCropCircleTransformation(getActivity(), pool, getResources().getColor(R.color.primary), -1)).placeholder(isCurrent ? ProfilePlaceholderDrawable.getFloatingProfileSwitcherLargeInstance(getActivity(), placeholderText) : ProfilePlaceholderDrawable.getFloatingProfileSwitcherInstance(getActivity(), placeholderText)).crossFade().into(imageView);
    }

    private View getUnderlayView() {
        return this.mUnderlayProvider == null ? this.mBinding.floatingMenuUnderlay : this.mUnderlayProvider.provideUnderlayView();
    }
}
