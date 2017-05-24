package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.views.ConnectionIndicatorView;

public class ActivityUnityPlayerBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ConnectionIndicatorView connectionIndicator;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final View splashOverlay;
    public final FrameLayout unityPlayerContainer;

    static {
        sViewsWithIds.put(R.id.unity_player_container, 1);
        sViewsWithIds.put(R.id.connection_indicator, 2);
        sViewsWithIds.put(R.id.splash_overlay, 3);
    }

    public ActivityUnityPlayerBinding(View root) {
        super(root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(root, 4, sIncludes, sViewsWithIds);
        this.connectionIndicator = (ConnectionIndicatorView) bindings[2];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.splashOverlay = (View) bindings[3];
        this.unityPlayerContainer = (FrameLayout) bindings[1];
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

    public static ActivityUnityPlayerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ActivityUnityPlayerBinding) DataBindingUtil.inflate(inflater, R.layout.activity_unity_player, root, attachToRoot);
    }

    public static ActivityUnityPlayerBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.activity_unity_player, null, false));
    }

    public static ActivityUnityPlayerBinding bind(View view) {
        if ("layout/activity_unity_player_0".equals(view.getTag())) {
            return new ActivityUnityPlayerBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
