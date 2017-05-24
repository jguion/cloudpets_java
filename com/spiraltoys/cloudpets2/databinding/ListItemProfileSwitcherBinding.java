package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;

public class ListItemProfileSwitcherBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private Profile mProfile;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    public final ImageView profilePhoto;
    public final ImageView selectionIndicator;

    static {
        sViewsWithIds.put(R.id.profile_photo, 2);
        sViewsWithIds.put(R.id.selection_indicator, 3);
    }

    public ListItemProfileSwitcherBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 4, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.profilePhoto = (ImageView) bindings[2];
        this.selectionIndicator = (ImageView) bindings[3];
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
        Profile profile = this.mProfile;
        String displayNameProfile = null;
        if (!((dirtyFlags & 3) == 0 || profile == null)) {
            displayNameProfile = profile.getDisplayName();
        }
        if ((dirtyFlags & 3) != 0) {
            this.mboundView1.setText(displayNameProfile);
        }
    }

    public static ListItemProfileSwitcherBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ListItemProfileSwitcherBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_profile_switcher, root, attachToRoot);
    }

    public static ListItemProfileSwitcherBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.list_item_profile_switcher, null, false));
    }

    public static ListItemProfileSwitcherBinding bind(View view) {
        if ("layout/list_item_profile_switcher_0".equals(view.getTag())) {
            return new ListItemProfileSwitcherBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
