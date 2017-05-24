package com.spiraltoys.cloudpets2.views;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.spiraltoys.cloudpets2.databinding.ViewConnectionIndicatorBinding;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.event.ToyEventState;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;

public class ConnectionIndicatorView extends FrameLayout {
    private static final int CONNECTION_INDICATOR_TRANSITION_DURATION_MS = 200;
    private ViewConnectionIndicatorBinding mBinding;
    private ConnectionIndicatorState mCurrentConnectionIndicatorState;
    private String mToyConfigId;

    public enum ConnectionIndicatorState {
        DISCONNECTED,
        SCANNING,
        CONNECTING,
        CONNECTED
    }

    public ConnectionIndicatorView(Context context) {
        this(context, null);
    }

    public ConnectionIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConnectionIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.mBinding = (ViewConnectionIndicatorBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_connection_indicator, this, true);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().registerSticky(this);
    }

    protected void onDetachedFromWindow() {
        EventBus.getDefault().unregister(this);
        super.onDetachedFromWindow();
    }

    public ConnectionIndicatorState getCurrentConnectionIndicatorState() {
        return this.mCurrentConnectionIndicatorState;
    }

    public String getConfigId() {
        return this.mToyConfigId;
    }

    public void setConfigId(String toyConfigId) {
        this.mToyConfigId = toyConfigId;
    }

    public void onEvent(ToyEventState event) {
        updateState(event.getState(), event.getToyIdentifier());
    }

    private void updateState(ToyState toyState, String toyIdentifier) {
        ConnectionIndicatorState newConnectionState;
        if (!(getConfigId() == null || getConfigId().equals(toyIdentifier) || !toyState.isOnline())) {
            toyState = ToyState.READY;
        }
        if (toyState == ToyState.READY) {
            newConnectionState = ConnectionIndicatorState.SCANNING;
        } else if (toyState == ToyState.CONNECTING) {
            newConnectionState = ConnectionIndicatorState.CONNECTING;
        } else if (toyState.isConnected()) {
            newConnectionState = ConnectionIndicatorState.CONNECTED;
        } else {
            newConnectionState = ConnectionIndicatorState.DISCONNECTED;
        }
        boolean isInitialState = this.mCurrentConnectionIndicatorState == null;
        if (this.mCurrentConnectionIndicatorState != newConnectionState) {
            this.mCurrentConnectionIndicatorState = newConnectionState;
            int newConnectionStateIndicator = getDrawableResourceForConnectionIndicatorState(this.mCurrentConnectionIndicatorState);
            if (isInitialState) {
                this.mBinding.connectionIndicator.setImageResource(newConnectionStateIndicator);
                if (this.mBinding.connectionIndicator.getDrawable() instanceof AnimationDrawable) {
                    ((AnimationDrawable) this.mBinding.connectionIndicator.getDrawable()).start();
                    return;
                }
                return;
            }
            Utils.crossFadeImageView(this.mBinding.connectionIndicator, newConnectionStateIndicator, 200);
        }
    }

    private int getDrawableResourceForConnectionIndicatorState(ConnectionIndicatorState state) {
        switch (1.$SwitchMap$com$spiraltoys$cloudpets2$views$ConnectionIndicatorView$ConnectionIndicatorState[state.ordinal()]) {
            case 2:
                return R.drawable.bluetooth_state_scanning;
            case 3:
                return R.drawable.bluetooth_state_scanning_animated;
            case 4:
                return R.drawable.bluetooth_state_connected;
            default:
                return R.drawable.bluetooth_state_disconnected;
        }
    }
}
