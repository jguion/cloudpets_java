package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.spiraltoys.cloudpets2.free.R;

public class FragmentProfilesListBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button addProfileButton;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final RecyclerView recyclerView;
    public final SwipeRefreshLayout swipeRefreshLayout;

    static {
        sViewsWithIds.put(R.id.swipe_refresh_layout, 1);
        sViewsWithIds.put(R.id.recycler_view, 2);
        sViewsWithIds.put(R.id.add_profile_button, 3);
    }

    public FragmentProfilesListBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 4, sIncludes, sViewsWithIds);
        this.addProfileButton = (Button) bindings[3];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.recyclerView = (RecyclerView) bindings[2];
        this.swipeRefreshLayout = (SwipeRefreshLayout) bindings[1];
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

    public static FragmentProfilesListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentProfilesListBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_profiles_list, root, attachToRoot);
    }

    public static FragmentProfilesListBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_profiles_list, null, false));
    }

    public static FragmentProfilesListBinding bind(View view) {
        if ("layout/fragment_profiles_list_0".equals(view.getTag())) {
            return new FragmentProfilesListBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
