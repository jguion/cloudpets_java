package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.spiraltoys.cloudpets2.free.R;

public class ViewRippleBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    public final View ripple1;
    public final View ripple2;
    public final View ripple3;

    public ViewRippleBinding(View[] root) {
        super(root[0], 0);
        Object[] bindings = mapBindings(root, 3, sIncludes, sViewsWithIds);
        this.ripple1 = (View) bindings[0];
        this.ripple1.setTag(null);
        this.ripple2 = (View) bindings[1];
        this.ripple2.setTag(null);
        this.ripple3 = (View) bindings[2];
        this.ripple3.setTag(null);
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

    public static ViewRippleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ViewRippleBinding) DataBindingUtil.inflate(inflater, R.layout.view_ripple, root, attachToRoot);
    }
}
