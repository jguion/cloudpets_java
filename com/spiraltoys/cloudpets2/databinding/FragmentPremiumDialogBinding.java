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

public class FragmentPremiumDialogBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final FrameLayout bgGetPremium;
    public final ImageView getPremiumButton;
    private long mDirtyFlags = -1;

    static {
        sViewsWithIds.put(R.id.get_premium_button, 1);
    }

    public FragmentPremiumDialogBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 2, sIncludes, sViewsWithIds);
        this.bgGetPremium = (FrameLayout) bindings[0];
        this.bgGetPremium.setTag(null);
        this.getPremiumButton = (ImageView) bindings[1];
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

    public static FragmentPremiumDialogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentPremiumDialogBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_premium_dialog, root, attachToRoot);
    }

    public static FragmentPremiumDialogBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_premium_dialog, null, false));
    }

    public static FragmentPremiumDialogBinding bind(View view) {
        if ("layout/fragment_premium_dialog_0".equals(view.getTag())) {
            return new FragmentPremiumDialogBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
