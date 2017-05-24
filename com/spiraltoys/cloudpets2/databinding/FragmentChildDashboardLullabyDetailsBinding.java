package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Lullaby;

public class FragmentChildDashboardLullabyDetailsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView durationLabel;
    public final ImageButton increaseDurationButton;
    public final ImageButton increaseVolumeButton;
    public final ImageView lullabyImage;
    private long mDirtyFlags = -1;
    private Lullaby mLullaby;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    public final ImageButton reduceDurationButton;
    public final ImageButton reduceVolumeButton;
    public final FloatingActionButton sendButton;
    public final TextView volumeLabel;

    static {
        sViewsWithIds.put(R.id.lullaby_image, 2);
        sViewsWithIds.put(R.id.reduce_duration_button, 3);
        sViewsWithIds.put(R.id.duration_label, 4);
        sViewsWithIds.put(R.id.increase_duration_button, 5);
        sViewsWithIds.put(R.id.reduce_volume_button, 6);
        sViewsWithIds.put(R.id.volume_label, 7);
        sViewsWithIds.put(R.id.increase_volume_button, 8);
        sViewsWithIds.put(R.id.send_button, 9);
    }

    public FragmentChildDashboardLullabyDetailsBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 10, sIncludes, sViewsWithIds);
        this.durationLabel = (TextView) bindings[4];
        this.increaseDurationButton = (ImageButton) bindings[5];
        this.increaseVolumeButton = (ImageButton) bindings[8];
        this.lullabyImage = (ImageView) bindings[2];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.reduceDurationButton = (ImageButton) bindings[3];
        this.reduceVolumeButton = (ImageButton) bindings[6];
        this.sendButton = (FloatingActionButton) bindings[9];
        this.volumeLabel = (TextView) bindings[7];
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

    public static FragmentChildDashboardLullabyDetailsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentChildDashboardLullabyDetailsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_dashboard_lullaby_details, root, attachToRoot);
    }

    public static FragmentChildDashboardLullabyDetailsBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_child_dashboard_lullaby_details, null, false));
    }

    public static FragmentChildDashboardLullabyDetailsBinding bind(View view) {
        if ("layout/fragment_child_dashboard_lullaby_details_0".equals(view.getTag())) {
            return new FragmentChildDashboardLullabyDetailsBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
