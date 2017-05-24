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
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Lullaby;
import com.spiraltoys.cloudpets2.views.FixedAspectLayout;

public class ListItemLullabyBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView lullabyImage;
    private long mDirtyFlags = -1;
    private Lullaby mLullaby;
    private final FrameLayout mboundView0;
    private final TextView mboundView1;
    public final FixedAspectLayout tile;

    static {
        sViewsWithIds.put(R.id.tile, 2);
        sViewsWithIds.put(R.id.lullaby_image, 3);
    }

    public ListItemLullabyBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 4, sIncludes, sViewsWithIds);
        this.lullabyImage = (ImageView) bindings[3];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.tile = (FixedAspectLayout) bindings[2];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        switch (variableId) {
            case 3:
                setLullaby((Lullaby) variable);
                return true;
            default:
                return false;
        }
    }

    public void setLullaby(Lullaby lullaby) {
        this.mLullaby = lullaby;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        super.requestRebind();
    }

    public Lullaby getLullaby() {
        return this.mLullaby;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        int nameResourceIdLullaby = 0;
        Lullaby lullaby = this.mLullaby;
        if (!((dirtyFlags & 3) == 0 || lullaby == null)) {
            nameResourceIdLullaby = lullaby.getNameResourceId();
        }
        if ((dirtyFlags & 3) != 0) {
            this.mboundView1.setText(nameResourceIdLullaby);
        }
    }

    public static ListItemLullabyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ListItemLullabyBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_lullaby, root, attachToRoot);
    }

    public static ListItemLullabyBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.list_item_lullaby, null, false));
    }

    public static ListItemLullabyBinding bind(View view) {
        if ("layout/list_item_lullaby_0".equals(view.getTag())) {
            return new ListItemLullabyBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
