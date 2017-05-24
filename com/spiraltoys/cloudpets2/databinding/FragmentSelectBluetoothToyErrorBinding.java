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
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.views.CircleImageView;

public class FragmentSelectBluetoothToyErrorBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button cancelButton;
    public final CircleImageView connectionIndicator;
    public final TextView errorMessage;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final Button retryButton;
    public final LinearLayout titleContainerScanning;

    static {
        sViewsWithIds.put(R.id.title_container_scanning, 1);
        sViewsWithIds.put(R.id.error_message, 2);
        sViewsWithIds.put(R.id.connection_indicator, 3);
        sViewsWithIds.put(R.id.retry_button, 4);
        sViewsWithIds.put(R.id.cancel_button, 5);
    }

    public FragmentSelectBluetoothToyErrorBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 6, sIncludes, sViewsWithIds);
        this.cancelButton = (Button) bindings[5];
        this.connectionIndicator = (CircleImageView) bindings[3];
        this.errorMessage = (TextView) bindings[2];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.retryButton = (Button) bindings[4];
        this.titleContainerScanning = (LinearLayout) bindings[1];
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

    public static FragmentSelectBluetoothToyErrorBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentSelectBluetoothToyErrorBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_select_bluetooth_toy_error, root, attachToRoot);
    }

    public static FragmentSelectBluetoothToyErrorBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_select_bluetooth_toy_error, null, false));
    }

    public static FragmentSelectBluetoothToyErrorBinding bind(View view) {
        if ("layout/fragment_select_bluetooth_toy_error_0".equals(view.getTag())) {
            return new FragmentSelectBluetoothToyErrorBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
