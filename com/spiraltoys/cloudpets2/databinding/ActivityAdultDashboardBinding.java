package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.ads.AdView;
import com.spiraltoys.cloudpets2.free.R;

public class ActivityAdultDashboardBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final AdView adView;
    public final GridLayout buttonPanel;
    public final Button buyACloudpetButton;
    public final View floatingMenuUnderlay;
    public final FrameLayout fragmentContainer;
    public final TextView inviteCount;
    private long mDirtyFlags = -1;
    public final FloatingActionButton manageProfilesButton;
    public final FloatingActionButton manageSettingsButton;
    private final RelativeLayout mboundView0;
    public final TextView messageCount;
    public final FrameLayout profilePickerContainer;
    public final FloatingActionButton recordMessageButton;
    public final FloatingActionButton showMessagesButton;
    public final FrameLayout toolbarClouds;
    public final TextView tooltip;
    public final ImageButton tutorialButton;

    static {
        sViewsWithIds.put(R.id.tooltip, 2);
        sViewsWithIds.put(R.id.button_panel, 3);
        sViewsWithIds.put(R.id.record_message_button, 4);
        sViewsWithIds.put(R.id.show_messages_button, 5);
        sViewsWithIds.put(R.id.message_count, 6);
        sViewsWithIds.put(R.id.manage_profiles_button, 7);
        sViewsWithIds.put(R.id.invite_count, 8);
        sViewsWithIds.put(R.id.manage_settings_button, 9);
        sViewsWithIds.put(R.id.buy_a_cloudpet_button, 10);
        sViewsWithIds.put(R.id.ad_view, 11);
        sViewsWithIds.put(R.id.floating_menu_underlay, 12);
        sViewsWithIds.put(R.id.profile_picker_container, 13);
        sViewsWithIds.put(R.id.tutorial_button, 14);
        sViewsWithIds.put(R.id.fragment_container, 15);
    }

    public ActivityAdultDashboardBinding(View root) {
        super(root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(root, 16, sIncludes, sViewsWithIds);
        this.adView = (AdView) bindings[11];
        this.buttonPanel = (GridLayout) bindings[3];
        this.buyACloudpetButton = (Button) bindings[10];
        this.floatingMenuUnderlay = (View) bindings[12];
        this.fragmentContainer = (FrameLayout) bindings[15];
        this.inviteCount = (TextView) bindings[8];
        this.manageProfilesButton = (FloatingActionButton) bindings[7];
        this.manageSettingsButton = (FloatingActionButton) bindings[9];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.messageCount = (TextView) bindings[6];
        this.profilePickerContainer = (FrameLayout) bindings[13];
        this.recordMessageButton = (FloatingActionButton) bindings[4];
        this.showMessagesButton = (FloatingActionButton) bindings[5];
        this.toolbarClouds = (FrameLayout) bindings[1];
        this.toolbarClouds.setTag(null);
        this.tooltip = (TextView) bindings[2];
        this.tutorialButton = (ImageButton) bindings[14];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    private void log(String msg, long i) {
        Log.d("BINDER", msg + ":" + Long.toHexString(i));
    }

    public boolean setVariable(int variableId, Object variable) {
        return false;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    public static ActivityAdultDashboardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ActivityAdultDashboardBinding) DataBindingUtil.inflate(inflater, R.layout.activity_adult_dashboard, root, attachToRoot);
    }

    public static ActivityAdultDashboardBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.activity_adult_dashboard, null, false));
    }

    public static ActivityAdultDashboardBinding bind(View view) {
        if ("layout/activity_adult_dashboard_0".equals(view.getTag())) {
            return new ActivityAdultDashboardBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
