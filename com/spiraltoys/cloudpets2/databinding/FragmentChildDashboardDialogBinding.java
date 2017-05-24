package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;

public class FragmentChildDashboardDialogBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final FrameLayout contentContainer;
    private long mDirtyFlags = -1;
    public final RelativeLayout mainContent;
    private final RelativeLayout mboundView0;
    public final Toolbar toolbar;
    public final ImageView toolbarCloud;
    public final TextView toolbarTitle;
    public final ImageView toolbarTitleIcon;
    public final LinearLayout toolbarWrapper;

    static {
        sViewsWithIds.put(R.id.toolbar_cloud, 1);
        sViewsWithIds.put(R.id.main_content, 2);
        sViewsWithIds.put(R.id.toolbar_wrapper, 3);
        sViewsWithIds.put(R.id.toolbar, 4);
        sViewsWithIds.put(R.id.content_container, 5);
        sViewsWithIds.put(R.id.toolbar_title, 6);
        sViewsWithIds.put(R.id.toolbar_title_icon, 7);
    }

    public FragmentChildDashboardDialogBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 8, sIncludes, sViewsWithIds);
        this.contentContainer = (FrameLayout) bindings[5];
        this.mainContent = (RelativeLayout) bindings[2];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.toolbar = (Toolbar) bindings[4];
        this.toolbarCloud = (ImageView) bindings[1];
        this.toolbarTitle = (TextView) bindings[6];
        this.toolbarTitleIcon = (ImageView) bindings[7];
        this.toolbarWrapper = (LinearLayout) bindings[3];
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

    public static FragmentChildDashboardDialogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentChildDashboardDialogBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_dashboard_dialog, root, attachToRoot);
    }

    public static FragmentChildDashboardDialogBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_child_dashboard_dialog, null, false));
    }

    public static FragmentChildDashboardDialogBinding bind(View view) {
        if ("layout/fragment_child_dashboard_dialog_0".equals(view.getTag())) {
            return new FragmentChildDashboardDialogBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
