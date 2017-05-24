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

public class FragmentChildDashboardHelpSendBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;

    public FragmentChildDashboardHelpSendBinding(View root) {
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

    public static FragmentChildDashboardHelpSendBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentChildDashboardHelpSendBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_dashboard_help_send, root, attachToRoot);
    }

    public static FragmentChildDashboardHelpSendBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_child_dashboard_help_send, null, false));
    }

    public static FragmentChildDashboardHelpSendBinding bind(View view) {
        if ("layout/fragment_child_dashboard_help_send_0".equals(view.getTag())) {
            return new FragmentChildDashboardHelpSendBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
