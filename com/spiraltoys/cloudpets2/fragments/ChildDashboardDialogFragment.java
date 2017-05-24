package com.spiraltoys.cloudpets2.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardDialogBinding;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Lullaby;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.Story;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import icepick.Icepick;
import icepick.State;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ChildDashboardDialogFragment extends DialogFragment implements OnBackStackChangedListener {
    private static final String ARG_IS_DOWNLOADING_AUDIO = "arg_is_downloading_audio";
    private static final String ARG_IS_HELP_INSTANCE = "arg_is_help_instance";
    private static final String ARG_IS_LULLABY_INSTANCE = "arg_is_lullaby_instance";
    private static final String ARG_IS_STORIES_INSTANCE = "arg_is_stories_instance";
    private static final String ARG_PROFILE_ID = "arg_profile_id";
    private static final String ARG_RECORDED_AUDIO_URI = "arg_recorded_audio_uri";
    private FragmentChildDashboardDialogBinding mBinding;
    private boolean mIsDownloadingAudio;
    private boolean mIsHelpInstance;
    private boolean mIsLullabyInstance;
    private boolean mIsStoriesInstance;
    private String mProfileId;
    private Uri mRecordedAudioUri;
    @State
    ArrayList<Integer> mTitleIconStack;
    @State
    ArrayList<String> mTitleStack;

    public static ChildDashboardDialogFragment newInstance(Uri recordedAudioUri) {
        ChildDashboardDialogFragment fragment = new ChildDashboardDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_RECORDED_AUDIO_URI, recordedAudioUri);
        fragment.setArguments(args);
        return fragment;
    }

    public static ChildDashboardDialogFragment newInstance(boolean isDownloadingAudio) {
        ChildDashboardDialogFragment fragment = new ChildDashboardDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_IS_DOWNLOADING_AUDIO, isDownloadingAudio);
        fragment.setArguments(args);
        return fragment;
    }

    public static ChildDashboardDialogFragment newMessagesInstance(String profileId) {
        ChildDashboardDialogFragment fragment = new ChildDashboardDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PROFILE_ID, profileId);
        fragment.setArguments(args);
        return fragment;
    }

    public static ChildDashboardDialogFragment newLullabyInstance() {
        ChildDashboardDialogFragment fragment = new ChildDashboardDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_IS_LULLABY_INSTANCE, true);
        fragment.setArguments(args);
        return fragment;
    }

    public static ChildDashboardDialogFragment newStoriesInstance() {
        ChildDashboardDialogFragment fragment = new ChildDashboardDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_IS_STORIES_INSTANCE, true);
        fragment.setArguments(args);
        return fragment;
    }

    public static ChildDashboardDialogFragment newHelpInstance() {
        ChildDashboardDialogFragment fragment = new ChildDashboardDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_IS_HELP_INSTANCE, true);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(2, R.style.AppTheme.AppCompatDialogStyle);
        Icepick.restoreInstanceState((Object) this, savedInstanceState);
        if (savedInstanceState == null) {
            this.mTitleStack = new ArrayList();
            this.mTitleIconStack = new ArrayList();
        }
        if (getArguments() != null) {
            this.mRecordedAudioUri = (Uri) getArguments().getParcelable(ARG_RECORDED_AUDIO_URI);
            this.mIsDownloadingAudio = getArguments().getBoolean(ARG_IS_DOWNLOADING_AUDIO, false);
            this.mIsLullabyInstance = getArguments().getBoolean(ARG_IS_LULLABY_INSTANCE, false);
            this.mIsStoriesInstance = getArguments().getBoolean(ARG_IS_STORIES_INSTANCE, false);
            this.mIsHelpInstance = getArguments().getBoolean(ARG_IS_HELP_INSTANCE, false);
            this.mProfileId = getArguments().getString(ARG_PROFILE_ID);
        }
        getChildFragmentManager().addOnBackStackChangedListener(this);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentChildDashboardDialogBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_dashboard_dialog, container, false);
        if (savedInstanceState == null) {
            if (this.mIsHelpInstance) {
                showHelpFragment();
            } else if (this.mIsLullabyInstance) {
                showLullabiesFragment();
            } else if (this.mIsStoriesInstance) {
                showStoriesFragment();
            } else if (this.mIsDownloadingAudio) {
                showIncomingRecordingFragment();
            } else if (this.mRecordedAudioUri == null) {
                showMessagesFragment();
            } else {
                showRecordingFragment();
            }
        }
        this.mBinding.toolbar.setNavigationOnClickListener(new 1(this));
        this.mBinding.toolbarTitle.setText((CharSequence) peek(this.mTitleStack));
        this.mBinding.toolbarTitleIcon.setImageResource(((Integer) peek(this.mTitleIconStack)).intValue());
        updateToolbarIcon();
        return this.mBinding.getRoot();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AppCompatDialog dialog = new 2(this, getActivity(), getTheme());
        dialog.supportRequestWindowFeature(1);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return dialog;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState((Object) this, outState);
    }

    public void onBackStackChanged() {
        updateToolbarIcon();
    }

    private void setTitle(int titleStringResource) {
        String title = getResources().getString(titleStringResource);
        push(this.mTitleStack, title);
        this.mBinding.toolbarTitle.setText(title);
    }

    private void setTitleIcon(int titleIconResource) {
        push(this.mTitleIconStack, Integer.valueOf(titleIconResource));
        this.mBinding.toolbarTitleIcon.setImageResource(titleIconResource);
    }

    public void showRecording(Uri recordedAudioUri) {
        getArguments().putParcelable(ARG_RECORDED_AUDIO_URI, recordedAudioUri);
        if (isAdded() && !isRemoving()) {
            showRecordingFragment();
        }
    }

    private void showHelpFragment() {
        getChildFragmentManager().beginTransaction().replace(R.id.content_container, new ChildDashboardHelpFragment()).addToBackStack(null).commit();
        this.mIsDownloadingAudio = false;
        setTitle(R.string.title_child_dashboard_help);
        setTitleIcon(R.drawable.transparent_rect);
    }

    private void showMessagesFragment() {
        getChildFragmentManager().beginTransaction().replace(R.id.content_container, ChildVoiceMessagesFragment.newInstance(this.mProfileId)).addToBackStack(null).commit();
        this.mIsDownloadingAudio = false;
        setTitle(R.string.title_my_messages);
        setTitleIcon(R.drawable.message_icon);
    }

    private void showLullabiesFragment() {
        getChildFragmentManager().beginTransaction().replace(R.id.content_container, new ChildLullabiesFragment()).addToBackStack(null).commit();
        this.mIsDownloadingAudio = false;
        setTitle(R.string.title_lullabies);
        setTitleIcon(R.drawable.lullaby_icon);
    }

    public void showLullabyFragment(Lullaby lullaby) {
        getChildFragmentManager().beginTransaction().replace(R.id.content_container, ChildLullabyDetailsFragment.newInstance(lullaby)).addToBackStack(null).commit();
        this.mIsDownloadingAudio = false;
        setTitle(R.string.title_lullabies);
        setTitleIcon(R.drawable.lullaby_icon);
    }

    private void showStoriesFragment() {
        getChildFragmentManager().beginTransaction().replace(R.id.content_container, new ChildStoriesFragment()).addToBackStack(null).commit();
        this.mIsDownloadingAudio = false;
        setTitle(R.string.title_stories);
        setTitleIcon(R.drawable.stories_icon);
    }

    public void showStoryFragment(Story story) {
        getChildFragmentManager().beginTransaction().replace(R.id.content_container, ChildStoryDetailsFragment.newInstance(story)).addToBackStack(null).commit();
        this.mIsDownloadingAudio = false;
        setTitle(R.string.title_stories);
        setTitleIcon(R.drawable.stories_icon);
    }

    public void showMessageFragment(VoiceMessage voiceMessage) {
        getChildFragmentManager().beginTransaction().replace(R.id.content_container, ChildVoiceMessageDetailsFragment.newInstance(voiceMessage)).addToBackStack(null).commit();
        this.mIsDownloadingAudio = false;
        setTitle(R.string.title_message);
        setTitleIcon(R.drawable.message_icon);
    }

    public void showRecipientSelectorFragment(Profile sender) {
        getChildFragmentManager().beginTransaction().replace(R.id.content_container, ChildDashboardSelectProfileFragment.newInstance(sender)).addToBackStack(null).commit();
        this.mIsDownloadingAudio = false;
        setTitle(R.string.title_send_to);
        setTitleIcon(R.drawable.message_icon);
    }

    public void showIncomingRecordingFragment() {
        if (!(getChildFragmentManager().findFragmentById(R.id.content_container) instanceof ChildDashboardDownloadingFragment)) {
            getChildFragmentManager().beginTransaction().replace(R.id.content_container, new ChildDashboardDownloadingFragment()).addToBackStack(null).commit();
            this.mIsDownloadingAudio = true;
            setTitle(R.string.title_downloading_toy_message);
            setTitleIcon(R.drawable.message_icon);
        }
    }

    private void showRecordingFragment() {
        if (this.mIsDownloadingAudio) {
            getChildFragmentManager().popBackStack();
            pop(this.mTitleStack);
            pop(this.mTitleIconStack);
        }
        getChildFragmentManager().beginTransaction().replace(R.id.content_container, ChildRecordedMessageActionsFragment.newInstance((Uri) getArguments().getParcelable(ARG_RECORDED_AUDIO_URI))).addToBackStack(null).commit();
        this.mIsDownloadingAudio = false;
        setTitle(R.string.title_listen);
        setTitleIcon(R.drawable.message_icon);
    }

    public void hideIncomingRecordingFragment() {
        if (this.mIsDownloadingAudio && getDialog() != null) {
            getDialog().onBackPressed();
        }
        this.mIsDownloadingAudio = false;
    }

    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        try {
            Field mDialog = DialogFragment.class.getDeclaredField("mDialog");
            mDialog.setAccessible(true);
            mDialog.set(this, onCreateDialog(savedInstanceState));
            return (LayoutInflater) ((Dialog) mDialog.get(this)).getContext().getSystemService("layout_inflater");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateToolbarIcon() {
        if (getChildFragmentManager().getBackStackEntryCount() == 1) {
            this.mBinding.toolbar.setNavigationIcon((int) R.drawable.ic_close_white_24dp);
        } else {
            this.mBinding.toolbar.setNavigationIcon((int) R.drawable.ic_arrow_back_white_24dp);
        }
    }

    private <T> T peek(ArrayList<T> list) {
        return list.get(list.size() - 1);
    }

    private <T> T pop(ArrayList<T> list) {
        return list.remove(list.size() - 1);
    }

    private <T> void push(ArrayList<T> list, T obj) {
        list.add(obj);
    }
}
