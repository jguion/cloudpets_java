package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.afollestad.materialdialogs.AlertDialogWrapper.Builder;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PlushToy;
import com.spiraltoys.cloudpets2.model.PlushToy.Character;
import com.spiraltoys.cloudpets2.toy.ToyLedState;
import com.spiraltoys.cloudpets2.toy.ToyManager;
import com.spiraltoys.cloudpets2.toy.ToyService;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.event.ToyEventDiscovered;
import com.spiraltoys.cloudpets2.toy.event.ToyEventError;
import com.spiraltoys.cloudpets2.toy.event.ToyEventState;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;
import icepick.Icepick;
import java.util.ArrayList;
import java.util.Timer;

public class SelectBluetoothToyFragment extends Fragment {
    private static final String ARG_PLUSH_TOY_CHARACTER_TYPE = "plush_toy_character_type";
    public static final int HEART_BLINK_INTERVAL_MILLISECONDS = 500;
    private static final int SCAN_TIMEOUT_MILLISECONDS = 10000;
    private Dialog mBluetoothOffDialog;
    private Character mCharacterType;
    @InjectView(2131755149)
    ImageView mConfiguredPetAvatar;
    @InjectView(2131755301)
    View mConnectedTitleContainer;
    @InjectView(2131755302)
    TextView mConnectionText;
    private ToyState mCurrentState;
    private String mCurrentToyConfigIdentifier;
    private int mCurrentlySelectedIndex;
    private Dialog mErrorDialog;
    private boolean mIsWritingConfigIdentifier;
    private OnToySelectedListener mListener;
    @InjectView(2131755304)
    Button mNextToyButton;
    private int mOriginalConnectionTextColor;
    private ToyState mPreviousState;
    @InjectView(2131755365)
    View mProgress2;
    @InjectView(2131755366)
    View mProgress3;
    private Timer mScanTimeoutTimer;
    @InjectView(2131755300)
    View mScanningTitleContainer;
    private int mShortAnimationDuration;
    private ArrayList<String> mToyAddresses;
    @InjectView(2131755305)
    Button mUseToyButton;

    public interface OnToySelectedListener {
        void onToyConnectionTimeout();

        void onToyScanTimeout(int i);

        void onToySelected(String str);
    }

    public static SelectBluetoothToyFragment newInstance(Character character) {
        SelectBluetoothToyFragment fragment = new SelectBluetoothToyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PLUSH_TOY_CHARACTER_TYPE, character.toString());
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState((Object) this, savedInstanceState);
        if (getArguments() != null) {
            this.mCharacterType = Character.getCharacter(getArguments().getString(ARG_PLUSH_TOY_CHARACTER_TYPE));
        }
        this.mShortAnimationDuration = getResources().getInteger(17694720);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_bluetooth_toy, container, false);
        ButterKnife.inject((Object) this, view);
        this.mOriginalConnectionTextColor = this.mConnectionText.getCurrentTextColor();
        this.mProgress2.setEnabled(true);
        this.mConfiguredPetAvatar.setImageResource(PlushToy.getAvatarResourceForCharacter(this.mCharacterType));
        return view;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnToySelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnToySelectedListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onPause() {
        super.onPause();
        ToyManager.stopScan();
        if (this.mCurrentState == ToyState.CONNECTED) {
            ToyManager.setLedState(ToyLedState.OFF, 0);
        }
    }

    public void onResume() {
        super.onResume();
        this.mCurrentlySelectedIndex = 0;
        this.mPreviousState = null;
        this.mCurrentState = null;
        this.mNextToyButton.setEnabled(false);
        this.mUseToyButton.setEnabled(false);
        this.mToyAddresses = new ArrayList();
        getActivity().startService(new Intent(getActivity(), ToyService.class));
        if (ToyManager.getState().isConnected()) {
            ToyManager.disconnectFromToyEventually();
        } else {
            startScan();
        }
        if (!Utils.isBluetoothEnabled()) {
            showBluetoothOffDialog();
        }
    }

    @OnClick({2131755304})
    void onNextToyButtonClicked(View view) {
        connectToToyAtIndex(this.mCurrentlySelectedIndex + 1);
    }

    @OnClick({2131755305})
    void onUseAsSharedToyClicked(View view) {
        if (!PlushToy.isValidCharacterId(this.mCurrentToyConfigIdentifier)) {
            writeConfigIdentifierAndDismiss();
        } else if (this.mListener != null) {
            this.mListener.onToySelected(this.mCurrentToyConfigIdentifier);
        }
    }

    public void onEvent(ToyEventDiscovered toyDiscoveredEvent) {
        if (!this.mToyAddresses.contains(toyDiscoveredEvent.getDeviceAddress())) {
            this.mToyAddresses.add(toyDiscoveredEvent.getDeviceAddress());
            if (this.mToyAddresses.size() == 1) {
                connectToToyAtIndex(0);
            }
        }
    }

    public void onEvent(ToyEventState toyEventState) {
        this.mPreviousState = this.mCurrentState;
        this.mCurrentState = toyEventState.getState();
        switch (4.$SwitchMap$com$spiraltoys$cloudpets2$toy$ToyState[this.mCurrentState.ordinal()]) {
            case 1:
                startScan();
                break;
            case 2:
                if (!this.mIsWritingConfigIdentifier) {
                    if (this.mPreviousState != ToyState.WRITING_CHARACTERISTIC) {
                        if (this.mPreviousState == ToyState.CONNECTING) {
                            ToyManager.stopScan();
                            ToyManager.setLedState(ToyLedState.BLINKING, HEART_BLINK_INTERVAL_MILLISECONDS);
                            break;
                        }
                    }
                    String currentToyAddress;
                    if (this.mToyAddresses.size() > this.mCurrentlySelectedIndex) {
                        currentToyAddress = (String) this.mToyAddresses.get(this.mCurrentlySelectedIndex);
                    } else {
                        currentToyAddress = null;
                    }
                    if (!toyEventState.getDeviceAddress().equals(currentToyAddress)) {
                        ToyManager.stopScan();
                        ToyManager.connectToToy(null, currentToyAddress, false);
                        break;
                    }
                    cancelFallAsleepTimer();
                    this.mUseToyButton.setEnabled(true);
                    this.mNextToyButton.setEnabled(true);
                    this.mCurrentToyConfigIdentifier = toyEventState.getToyIdentifier();
                    ToyManager.startScan();
                    break;
                } else if (this.mListener != null) {
                    this.mListener.onToySelected(toyEventState.getToyIdentifier());
                    break;
                }
                break;
            case 3:
            case 4:
                break;
            default:
                this.mUseToyButton.setEnabled(false);
                break;
        }
        updateStateIndicators(toyEventState.getState());
    }

    public void onEvent(ToyEventError errorEvent) {
        EventBus.getDefault().removeStickyEvent((Object) errorEvent);
        if (this.mErrorDialog == null && errorEvent.getToyState() == ToyState.NOT_SUPPORTED) {
            this.mErrorDialog = new Builder(getActivity()).setTitle((int) R.string.title_error).setMessage(errorEvent.getMessage()).setPositiveButton((int) R.string.btn_continue, null).create();
            this.mErrorDialog.setOnDismissListener(new 1(this));
            this.mErrorDialog.show();
        }
    }

    private void startScan() {
        if (Utils.isBluetoothEnabled()) {
            if (this.mToyAddresses.isEmpty()) {
                resetTimeoutTimer();
                ToyManager.stopScan();
            }
            ToyManager.startScan();
        }
    }

    private synchronized void resetTimeoutTimer() {
        cancelFallAsleepTimer();
        this.mScanTimeoutTimer = new Timer();
        this.mScanTimeoutTimer.schedule(new TimeoutTimerTask(this, null), 10000);
    }

    private void cancelFallAsleepTimer() {
        if (this.mScanTimeoutTimer != null) {
            this.mScanTimeoutTimer.cancel();
            this.mScanTimeoutTimer.purge();
        }
    }

    private void writeConfigIdentifierAndDismiss() {
        this.mNextToyButton.setEnabled(false);
        this.mIsWritingConfigIdentifier = true;
        ToyManager.setIdentifier(PlushToy.generateCharacterId(this.mCharacterType));
    }

    private void connectToToyAtIndex(int index) {
        if (index >= 0) {
            this.mProgress3.setEnabled(true);
            this.mUseToyButton.setEnabled(false);
            this.mNextToyButton.setEnabled(false);
            updateStateIndicators(ToyState.CONNECTING);
            resetTimeoutTimer();
            boolean hasPreviousToy;
            if (index > 0) {
                hasPreviousToy = true;
            } else {
                hasPreviousToy = false;
            }
            boolean hasNextToy;
            if (index + 1 < this.mToyAddresses.size()) {
                hasNextToy = true;
            } else {
                hasNextToy = false;
            }
            if (this.mToyAddresses.size() > index) {
                this.mCurrentlySelectedIndex = index;
                String nextAddress = (String) this.mToyAddresses.get(index);
                if (this.mCurrentState == ToyState.CONNECTED) {
                    ToyManager.setLedState(ToyLedState.OFF, 0);
                } else {
                    ToyManager.connectToToy(null, nextAddress, false);
                }
            } else if (this.mListener != null) {
                this.mCurrentlySelectedIndex = this.mToyAddresses.size();
                updateStateIndicators(ToyState.READY);
                startScan();
            }
        }
    }

    private void updateStateIndicators(ToyState toyState) {
        float f = 0.0f;
        if (!Utils.isBluetoothEnabled()) {
            this.mConnectionText.setText(R.string.label_bluetooth_off);
            this.mConnectionText.setTextColor(ContextCompat.getColor(getActivity(), R.color.md_edittext_error));
            showBluetoothOffDialog();
        } else if (toyState == ToyState.READY || toyState == ToyState.UNKNOWN) {
            this.mConnectionText.setText(R.string.label_scanning);
            this.mConnectionText.setTextColor(this.mOriginalConnectionTextColor);
            hideBluetoothOffDialog();
        } else {
            this.mConnectionText.setText(toyState.getLocalizedStringId());
            this.mConnectionText.setTextColor(this.mOriginalConnectionTextColor);
            hideBluetoothOffDialog();
        }
        if (this.mPreviousState == null || this.mPreviousState.isConnected() != toyState.isConnected()) {
            float f2;
            ViewPropertyAnimator animate = this.mConnectedTitleContainer.animate();
            if (toyState.isConnected()) {
                f2 = 1.0f;
            } else {
                f2 = 0.0f;
            }
            animate.alpha(f2).setDuration((long) this.mShortAnimationDuration).setListener(null);
            ViewPropertyAnimator animate2 = this.mScanningTitleContainer.animate();
            if (!toyState.isConnected()) {
                f = 1.0f;
            }
            animate2.alpha(f).setDuration((long) this.mShortAnimationDuration).setListener(null);
        }
    }

    private void showBluetoothOffDialog() {
        if (this.mBluetoothOffDialog == null || !this.mBluetoothOffDialog.isShowing()) {
            this.mBluetoothOffDialog = new Builder(getActivity()).setTitle((int) R.string.title_bluetooth_off).setMessage((int) R.string.error_bluetooth_off_setup).setPositiveButton((int) R.string.btn_turn_bluetooth_on, new 3(this)).setNegativeButton((int) R.string.btn_cancel, new 2(this)).setCancelable(false).show();
        }
    }

    private void hideBluetoothOffDialog() {
        if (this.mBluetoothOffDialog != null && this.mBluetoothOffDialog.isShowing()) {
            this.mBluetoothOffDialog.dismiss();
        }
    }
}
