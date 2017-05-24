package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PlushToy;
import com.spiraltoys.cloudpets2.model.PlushToy.Character;
import com.spiraltoys.cloudpets2.toy.ToyManager;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.event.ToyEventGameModeButtonPress;
import com.spiraltoys.cloudpets2.toy.event.ToyEventState;
import de.greenrobot.event.EventBus;
import java.util.Timer;

public class SelectBluetoothToyCompleteConnectionFragment extends Fragment {
    private static final String ARG_PLUSH_TOY_CHARACTER_TYPE = "plush_toy_character_type";
    private static final String ARG_TOY_CONFIG_IDENTIFIER = "toy_config_identifier";
    private static final int CONNECTION_TIMEOUT_MILLISECONDS = 10000;
    private Character mCharacterType;
    @InjectView(2131755307)
    Button mCompleteConnectionButton;
    @InjectView(2131755149)
    ImageView mConfiguredPetAvatar;
    @InjectView(2131755302)
    TextView mConnectionText;
    private Timer mConnectionTimeoutTimer;
    @InjectView(2131755306)
    View mFinishedTitleContainer;
    private boolean mHasSetGameMode;
    private OnConnectionCompletedListener mListener;
    @InjectView(2131755365)
    Button mProgress2;
    @InjectView(2131755366)
    Button mProgress3;
    @InjectView(2131755367)
    Button mProgress4;
    @InjectView(2131755368)
    Button mProgress5;
    private int mShortAnimationDuration;
    @InjectView(2131755298)
    View mStartedTitleContainer;
    private String mToyConfigIdentifier;

    public interface OnConnectionCompletedListener {
        void onToyConnectionTimeout();

        void onToySelectionConfirmed();
    }

    public static SelectBluetoothToyCompleteConnectionFragment newInstance(Character character, String configIdentifier) {
        SelectBluetoothToyCompleteConnectionFragment fragment = new SelectBluetoothToyCompleteConnectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PLUSH_TOY_CHARACTER_TYPE, character.toString());
        args.putString(ARG_TOY_CONFIG_IDENTIFIER, configIdentifier);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mCharacterType = Character.getCharacter(getArguments().getString(ARG_PLUSH_TOY_CHARACTER_TYPE));
            this.mToyConfigIdentifier = getArguments().getString(ARG_TOY_CONFIG_IDENTIFIER);
        }
        this.mShortAnimationDuration = getResources().getInteger(17694720);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_bluetooth_toy_complete_connection, container, false);
        ButterKnife.inject((Object) this, view);
        this.mProgress2.setEnabled(true);
        this.mProgress3.setEnabled(true);
        this.mProgress4.setEnabled(true);
        this.mConfiguredPetAvatar.setImageResource(PlushToy.getAvatarResourceForCharacter(this.mCharacterType));
        this.mConnectionText.setText(R.string.label_connected);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnConnectionCompletedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onPause() {
        super.onPause();
        ToyManager.setGameModeState(false);
        this.mHasSetGameMode = false;
    }

    public void onResume() {
        super.onResume();
        this.mHasSetGameMode = false;
        resetTimeoutTimer();
        ToyManager.connectToToy(this.mToyConfigIdentifier, null, false);
    }

    @OnClick({2131755307})
    void onCompleteConnectionButtonClicked(View view) {
        if (this.mListener != null) {
            this.mListener.onToySelectionConfirmed();
        }
    }

    public void onEvent(ToyEventGameModeButtonPress eventGameModeButtonPress) {
        this.mCompleteConnectionButton.setEnabled(true);
        this.mStartedTitleContainer.animate().alpha(0.0f).setDuration((long) this.mShortAnimationDuration).setListener(null);
        this.mFinishedTitleContainer.animate().alpha(1.0f).setDuration((long) this.mShortAnimationDuration).setListener(new 1(this));
    }

    public void onEvent(ToyEventState toyEventState) {
        if (toyEventState.getToyIdentifier() != null && toyEventState.getToyIdentifier().equals(this.mToyConfigIdentifier) && toyEventState.getState() == ToyState.CONNECTED && !this.mHasSetGameMode) {
            ToyManager.setGameModeState(true);
            this.mHasSetGameMode = true;
            cancelFallAsleepTimer();
        }
        switch (2.$SwitchMap$com$spiraltoys$cloudpets2$toy$ToyState[toyEventState.getState().ordinal()]) {
            case 1:
            case 2:
            case 3:
                this.mHasSetGameMode = false;
                return;
            default:
                return;
        }
    }

    private synchronized void resetTimeoutTimer() {
        cancelFallAsleepTimer();
        this.mConnectionTimeoutTimer = new Timer();
        this.mConnectionTimeoutTimer.schedule(new TimeoutTimerTask(this, null), 10000);
    }

    private synchronized void cancelFallAsleepTimer() {
        if (this.mConnectionTimeoutTimer != null) {
            this.mConnectionTimeoutTimer.cancel();
            this.mConnectionTimeoutTimer.purge();
        }
    }
}
