package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;

public class FragmentDialogLockScreenBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final GridLayout contentContainer;
    public final Button continueButton;
    public final TextView displayName;
    public final Button logOutButton;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final EditText password;
    public final FrameLayout passwordContainer;
    public final ImageView profilePhoto;
    public final RecyclerView profileRecyclerView;

    static {
        sViewsWithIds.put(R.id.content_container, 1);
        sViewsWithIds.put(R.id.profile_recycler_view, 2);
        sViewsWithIds.put(R.id.profile_photo, 3);
        sViewsWithIds.put(R.id.display_name, 4);
        sViewsWithIds.put(R.id.password_container, 5);
        sViewsWithIds.put(R.id.password, 6);
        sViewsWithIds.put(R.id.log_out_button, 7);
        sViewsWithIds.put(R.id.continue_button, 8);
    }

    public FragmentDialogLockScreenBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 9, sIncludes, sViewsWithIds);
        this.contentContainer = (GridLayout) bindings[1];
        this.continueButton = (Button) bindings[8];
        this.displayName = (TextView) bindings[4];
        this.logOutButton = (Button) bindings[7];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.password = (EditText) bindings[6];
        this.passwordContainer = (FrameLayout) bindings[5];
        this.profilePhoto = (ImageView) bindings[3];
        this.profileRecyclerView = (RecyclerView) bindings[2];
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

    public static FragmentDialogLockScreenBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentDialogLockScreenBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_lock_screen, root, attachToRoot);
    }

    public static FragmentDialogLockScreenBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_dialog_lock_screen, null, false));
    }

    public static FragmentDialogLockScreenBinding bind(View view) {
        if ("layout/fragment_dialog_lock_screen_0".equals(view.getTag())) {
            return new FragmentDialogLockScreenBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
