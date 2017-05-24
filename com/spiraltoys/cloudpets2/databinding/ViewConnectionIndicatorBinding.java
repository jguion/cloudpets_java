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

public class ViewConnectionIndicatorBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView connectionIndicator;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.connection_indicator, 1);
    }

    public ViewConnectionIndicatorBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 2, sIncludes, sViewsWithIds);
        this.connectionIndicator = (ImageView) bindings[1];
        this.mboundView0 = (FrameLayout) bindings[0];
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

    public static ViewConnectionIndicatorBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ViewConnectionIndicatorBinding) DataBindingUtil.inflate(inflater, R.layout.view_connection_indicator, root, attachToRoot);
    }

    public static ViewConnectionIndicatorBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.view_connection_indicator, null, false));
    }

    public static ViewConnectionIndicatorBinding bind(View view) {
        if ("layout/view_connection_indicator_0".equals(view.getTag())) {
            return new ViewConnectionIndicatorBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
