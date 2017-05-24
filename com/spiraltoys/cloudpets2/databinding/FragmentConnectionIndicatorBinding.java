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
import android.widget.ImageView;
import com.spiraltoys.cloudpets2.free.R;

public class FragmentConnectionIndicatorBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView connectionIndicator;
    public final FrameLayout connectionLayout;
    private long mDirtyFlags = -1;

    static {
        sViewsWithIds.put(R.id.connection_indicator, 1);
    }

    public FragmentConnectionIndicatorBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 2, sIncludes, sViewsWithIds);
        this.connectionIndicator = (ImageView) bindings[1];
        this.connectionLayout = (FrameLayout) bindings[0];
        this.connectionLayout.setTag(null);
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

    public static FragmentConnectionIndicatorBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentConnectionIndicatorBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_connection_indicator, root, attachToRoot);
    }

    public static FragmentConnectionIndicatorBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_connection_indicator, null, false));
    }

    public static FragmentConnectionIndicatorBinding bind(View view) {
        if ("layout/fragment_connection_indicator_0".equals(view.getTag())) {
            return new FragmentConnectionIndicatorBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
