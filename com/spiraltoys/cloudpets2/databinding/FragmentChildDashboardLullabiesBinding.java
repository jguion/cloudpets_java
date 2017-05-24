package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.spiraltoys.cloudpets2.free.R;

public class FragmentChildDashboardLullabiesBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final RecyclerView recyclerView;

    static {
        sViewsWithIds.put(R.id.recycler_view, 1);
    }

    public FragmentChildDashboardLullabiesBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 2, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.recyclerView = (RecyclerView) bindings[1];
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

    public static FragmentChildDashboardLullabiesBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentChildDashboardLullabiesBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_dashboard_lullabies, root, attachToRoot);
    }

    public static FragmentChildDashboardLullabiesBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_child_dashboard_lullabies, null, false));
    }

    public static FragmentChildDashboardLullabiesBinding bind(View view) {
        if ("layout/fragment_child_dashboard_lullabies_0".equals(view.getTag())) {
            return new FragmentChildDashboardLullabiesBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
