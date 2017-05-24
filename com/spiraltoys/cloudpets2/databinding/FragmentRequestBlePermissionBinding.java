package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.views.CircleImageView;

public class FragmentRequestBlePermissionBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final CircleImageView connectionIndicator;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final Button nextButton;
    public final LinearLayout titleContainerCompleteConnectionStarted;

    static {
        sViewsWithIds.put(R.id.title_container_complete_connection_started, 1);
        sViewsWithIds.put(R.id.connection_indicator, 2);
        sViewsWithIds.put(R.id.next_button, 3);
    }

    public FragmentRequestBlePermissionBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 4, sIncludes, sViewsWithIds);
        this.connectionIndicator = (CircleImageView) bindings[2];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.nextButton = (Button) bindings[3];
        this.titleContainerCompleteConnectionStarted = (LinearLayout) bindings[1];
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

    public static FragmentRequestBlePermissionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentRequestBlePermissionBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_request_ble_permission, root, attachToRoot);
    }

    public static FragmentRequestBlePermissionBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_request_ble_permission, null, false));
    }

    public static FragmentRequestBlePermissionBinding bind(View view) {
        if ("layout/fragment_request_ble_permission_0".equals(view.getTag())) {
            return new FragmentRequestBlePermissionBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
