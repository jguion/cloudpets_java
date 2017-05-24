package com.spiraltoys.cloudpets2.fragments;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.adapters.ChildVoiceMessageAdapter;
import com.spiraltoys.cloudpets2.databinding.FragmentChildVoiceMessagesBinding;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.DividerItemDecoration;
import com.spiraltoys.cloudpets2.util.SettingsManager;
import com.spiraltoys.cloudpets2.util.TopSpaceItemDecoration;
import java.util.List;

public class ChildVoiceMessagesFragment extends Fragment implements OnRefreshListener {
    private static final String ARG_PROFILE_ID = "arg_profile_id";
    private static final int MESSAGES_BEAR_FADE_ANIMATION_DURATION = 400;
    private FragmentChildVoiceMessagesBinding mBinding;
    private int mMaxMessageCountToShowBear;
    private Profile mProfile;
    private ChildVoiceMessageAdapter mVoiceMessageAdapter;

    public static ChildVoiceMessagesFragment newInstance(String profileId) {
        ChildVoiceMessagesFragment fragment = new ChildVoiceMessagesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PROFILE_ID, profileId);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            try {
                this.mProfile = ModelHelper.getProfileFromLocalDatastore(getArguments().getString(ARG_PROFILE_ID));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        this.mMaxMessageCountToShowBear = getResources().getInteger(R.integer.max_message_count_to_show_child_messages_bear);
        ModelHelper.fetchAllVoiceMessagesToLocalDatastore(null);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentChildVoiceMessagesBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_voice_messages, container, false);
        this.mBinding.swipeRefreshLayout.setOnRefreshListener(this);
        this.mBinding.swipeRefreshLayout.post(new Runnable() {
            public void run() {
                ChildVoiceMessagesFragment.this.mBinding.swipeRefreshLayout.setRefreshing(true);
            }
        });
        this.mVoiceMessageAdapter = new ChildVoiceMessageAdapter(ModelHelper.getVoiceMessagesLocalDatastoreQuery(this.mProfile, false, SettingsManager.isParentalControlEnabled(getActivity())));
        this.mVoiceMessageAdapter.registerAdapterDataObserver(new AdapterDataObserver() {
            public void onChanged() {
                super.onChanged();
                ChildVoiceMessagesFragment.this.mBinding.swipeRefreshLayout.setRefreshing(false);
            }
        });
        RecyclerView recyclerView = this.mBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new TopSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.abc_dialog_list_padding_vertical_material)));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        recyclerView.setAdapter(this.mVoiceMessageAdapter);
        recyclerView.getAdapter().registerAdapterDataObserver(new AdapterDataObserver() {
            public void onChanged() {
                if (ChildVoiceMessagesFragment.this.mVoiceMessageAdapter != null) {
                    if (ChildVoiceMessagesFragment.this.mVoiceMessageAdapter.getItemCount() == 0 || ChildVoiceMessagesFragment.this.mVoiceMessageAdapter.getItemCount() > ChildVoiceMessagesFragment.this.mMaxMessageCountToShowBear) {
                        ChildVoiceMessagesFragment.this.mBinding.childMessagesListCharacter.animate().alpha(0.0f).setDuration(400).setListener(new AnimatorListener() {
                            public void onAnimationStart(Animator animation) {
                            }

                            public void onAnimationEnd(Animator animation) {
                                ChildVoiceMessagesFragment.this.mBinding.childMessagesListCharacter.setVisibility(8);
                            }

                            public void onAnimationCancel(Animator animation) {
                            }

                            public void onAnimationRepeat(Animator animation) {
                            }
                        }).start();
                    } else {
                        ChildVoiceMessagesFragment.this.mBinding.childMessagesListCharacter.setVisibility(0);
                        ChildVoiceMessagesFragment.this.mBinding.childMessagesListCharacter.animate().alpha(1.0f).setDuration(400).setListener(new AnimatorListener() {
                            public void onAnimationStart(Animator animation) {
                            }

                            public void onAnimationEnd(Animator animation) {
                                ChildVoiceMessagesFragment.this.mBinding.childMessagesListCharacter.setVisibility(0);
                            }

                            public void onAnimationCancel(Animator animation) {
                            }

                            public void onAnimationRepeat(Animator animation) {
                            }
                        }).start();
                    }
                }
                ChildVoiceMessagesFragment.this.updateEmptyView();
            }
        });
        return this.mBinding.getRoot();
    }

    public void onDestroyView() {
        if (this.mVoiceMessageAdapter != null) {
            this.mVoiceMessageAdapter.release();
        }
        super.onDestroyView();
    }

    public void onPause() {
        super.onPause();
        if (this.mBinding.swipeRefreshLayout != null) {
            this.mBinding.swipeRefreshLayout.setRefreshing(false);
            this.mBinding.swipeRefreshLayout.destroyDrawingCache();
            this.mBinding.swipeRefreshLayout.clearAnimation();
        }
    }

    public void onRefresh() {
        ModelHelper.fetchAllVoiceMessagesToLocalDatastore(new FindCallback<VoiceMessage>() {
            public void done(List<VoiceMessage> list, ParseException e) {
                ChildVoiceMessagesFragment.this.mBinding.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void updateEmptyView() {
        if (this.mBinding.recyclerView.getAdapter().getItemCount() == 0) {
            this.mBinding.emptyView.setVisibility(0);
        } else {
            this.mBinding.emptyView.setVisibility(8);
        }
    }
}
