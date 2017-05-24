package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.spiraltoys.cloudpets2.free.R;

public class FragmentChildDashboardHelpListenBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;

    public FragmentChildDashboardHelpListenBinding(View root) {
        super(root, 0);
        this.mboundView0 = (FrameLayout) mapBindings(root, 1, sIncludes, sViewsWithIds)[0];
        this.mboundView0.setTag(null);
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

    public static FragmentChildDashboardHelpListenBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentChildDashboardHelpListenBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_dashboard_help_listen, root, attachToRoot);
    }

    public static FragmentChildDashboardHelpListenBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_child_dashboard_help_listen, null, false));
    }

    public static FragmentChildDashboardHelpListenBinding bind(View view) {
        if ("layout/fragment_child_dashboard_help_listen_0".equals(view.getTag())) {
            return new FragmentChildDashboardHelpListenBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
