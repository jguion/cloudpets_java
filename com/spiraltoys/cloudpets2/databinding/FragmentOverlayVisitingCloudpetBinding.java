package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PlushToy;
import com.spiraltoys.cloudpets2.model.Profile;

public class FragmentOverlayVisitingCloudpetBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    private Profile mProfile;
    private final TextView mboundView1;
    private final TextView mboundView2;
    public final FrameLayout visitingCloudpetOverlay;

    public FragmentOverlayVisitingCloudpetBinding(View root) {
        super(root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(root, 3, sIncludes, sViewsWithIds);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.visitingCloudpetOverlay = (FrameLayout) bindings[0];
        this.visitingCloudpetOverlay.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        switch (variableId) {
            case 4:
                setProfile((Profile) variable);
                return true;
            default:
                return false;
        }
    }

    public void setProfile(Profile profile) {
        this.mProfile = profile;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        super.requestRebind();
    }

    public Profile getProfile() {
        return this.mProfile;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        PlushToy plushToyProfile = null;
        Profile profile = this.mProfile;
        String AndroidStringMessageChildDashboardTransitionNickNamePlushToyProfile = null;
        String nickNamePlushToyProfile = null;
        String AndroidStringTitleChildDashboardTransitionDisplayNameProfile = null;
        String displayNameProfile = null;
        if ((3 & dirtyFlags) != 0) {
            if (profile != null) {
                plushToyProfile = profile.getPlushToy();
            }
            if (plushToyProfile != null) {
                nickNamePlushToyProfile = plushToyProfile.getNickName();
            }
            AndroidStringMessageChildDashboardTransitionNickNamePlushToyProfile = getRoot().getResources().getString(R.string.message_child_dashboard_transition, new Object[]{nickNamePlushToyProfile});
            if (profile != null) {
                displayNameProfile = profile.getDisplayName();
            }
            AndroidStringTitleChildDashboardTransitionDisplayNameProfile = getRoot().getResources().getString(R.string.title_child_dashboard_transition, new Object[]{displayNameProfile});
        }
        if ((3 & dirtyFlags) != 0) {
            this.mboundView1.setText(AndroidStringTitleChildDashboardTransitionDisplayNameProfile);
        }
        if ((3 & dirtyFlags) != 0) {
            this.mboundView2.setText(AndroidStringMessageChildDashboardTransitionNickNamePlushToyProfile);
        }
    }

    public static FragmentOverlayVisitingCloudpetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentOverlayVisitingCloudpetBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_overlay_visiting_cloudpet, root, attachToRoot);
    }

    public static FragmentOverlayVisitingCloudpetBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_overlay_visiting_cloudpet, null, false));
    }

    public static FragmentOverlayVisitingCloudpetBinding bind(View view) {
        if ("layout/fragment_overlay_visiting_cloudpet_0".equals(view.getTag())) {
            return new FragmentOverlayVisitingCloudpetBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
