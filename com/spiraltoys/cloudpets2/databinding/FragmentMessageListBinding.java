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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;

public class FragmentMessageListBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final LinearLayout emptyView;
    public final TextView emptyViewTitle;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final RecyclerView recyclerView;
    public final SwipeRefreshLayout swipeRefreshLayout;

    static {
        sViewsWithIds.put(R.id.empty_view, 1);
        sViewsWithIds.put(R.id.empty_view_title, 2);
        sViewsWithIds.put(R.id.swipe_refresh_layout, 3);
        sViewsWithIds.put(R.id.recycler_view, 4);
    }

    public FragmentMessageListBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 5, sIncludes, sViewsWithIds);
        this.emptyView = (LinearLayout) bindings[1];
        this.emptyViewTitle = (TextView) bindings[2];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.recyclerView = (RecyclerView) bindings[4];
        this.swipeRefreshLayout = (SwipeRefreshLayout) bindings[3];
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

    public static FragmentMessageListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentMessageListBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_message_list, root, attachToRoot);
    }

    public static FragmentMessageListBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_message_list, null, false));
    }

    public static FragmentMessageListBinding bind(View view) {
        if ("layout/fragment_message_list_0".equals(view.getTag())) {
            return new FragmentMessageListBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
