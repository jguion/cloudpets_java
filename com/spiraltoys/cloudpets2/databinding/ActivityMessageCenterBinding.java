package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.spiraltoys.cloudpets2.free.R;

public class ActivityMessageCenterBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final ViewPager pager;
    public final TabLayout tabs;
    public final Toolbar toolbar;

    static {
        sViewsWithIds.put(R.id.toolbar, 1);
        sViewsWithIds.put(R.id.tabs, 2);
        sViewsWithIds.put(R.id.pager, 3);
    }

    public ActivityMessageCenterBinding(View root) {
        super(root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(root, 4, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.pager = (ViewPager) bindings[3];
        this.tabs = (TabLayout) bindings[2];
        this.toolbar = (Toolbar) bindings[1];
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

    public static ActivityMessageCenterBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ActivityMessageCenterBinding) DataBindingUtil.inflate(inflater, R.layout.activity_message_center, root, attachToRoot);
    }

    public static ActivityMessageCenterBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.activity_message_center, null, false));
    }

    public static ActivityMessageCenterBinding bind(View view) {
        if ("layout/activity_message_center_0".equals(view.getTag())) {
            return new ActivityMessageCenterBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
