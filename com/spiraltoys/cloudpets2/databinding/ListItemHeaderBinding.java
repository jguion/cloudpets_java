package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;

public class ListItemHeaderBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    public final TextView text;

    public ListItemHeaderBinding(View root) {
        super(root, 0);
        this.text = (TextView) mapBindings(root, 1, sIncludes, sViewsWithIds)[0];
        this.text.setTag(null);
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

    public static ListItemHeaderBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ListItemHeaderBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_header, root, attachToRoot);
    }

    public static ListItemHeaderBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.list_item_header, null, false));
    }

    public static ListItemHeaderBinding bind(View view) {
        if ("layout/list_item_header_0".equals(view.getTag())) {
            return new ListItemHeaderBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
