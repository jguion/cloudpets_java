package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;

public class ListItemSelectableProfileBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView checkboxImage;
    public final TextView displayName;
    private long mDirtyFlags = -1;
    private final GridLayout mboundView0;
    public final ImageView profilePhoto;

    static {
        sViewsWithIds.put(R.id.checkbox_image, 1);
        sViewsWithIds.put(R.id.profile_photo, 2);
        sViewsWithIds.put(R.id.display_name, 3);
    }

    public ListItemSelectableProfileBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 4, sIncludes, sViewsWithIds);
        this.checkboxImage = (ImageView) bindings[1];
        this.displayName = (TextView) bindings[3];
        this.mboundView0 = (GridLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.profilePhoto = (ImageView) bindings[2];
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

    public static ListItemSelectableProfileBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ListItemSelectableProfileBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_selectable_profile, root, attachToRoot);
    }

    public static ListItemSelectableProfileBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.list_item_selectable_profile, null, false));
    }

    public static ListItemSelectableProfileBinding bind(View view) {
        if ("layout/list_item_selectable_profile_0".equals(view.getTag())) {
            return new ListItemSelectableProfileBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
