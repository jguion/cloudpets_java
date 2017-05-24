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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.spiraltoys.cloudpets2.free.R;

public class FragmentFloatingProfileSwitcherBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final LinearLayout floatingMenu;
    public final View floatingMenuUnderlay;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final ImageView profilePhoto;
    public final FrameLayout profileSwitcher;
    public final Button switchProfileButton;
    public final ImageView switchProfileButtonArrow;
    public final FrameLayout switchProfileSpinner;

    static {
        sViewsWithIds.put(R.id.floating_menu_underlay, 1);
        sViewsWithIds.put(R.id.profile_switcher, 2);
        sViewsWithIds.put(R.id.profile_photo, 3);
        sViewsWithIds.put(R.id.switch_profile_spinner, 4);
        sViewsWithIds.put(R.id.switch_profile_button, 5);
        sViewsWithIds.put(R.id.switch_profile_button_arrow, 6);
        sViewsWithIds.put(R.id.floating_menu, 7);
    }

    public FragmentFloatingProfileSwitcherBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 8, sIncludes, sViewsWithIds);
        this.floatingMenu = (LinearLayout) bindings[7];
        this.floatingMenuUnderlay = (View) bindings[1];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.profilePhoto = (ImageView) bindings[3];
        this.profileSwitcher = (FrameLayout) bindings[2];
        this.switchProfileButton = (Button) bindings[5];
        this.switchProfileButtonArrow = (ImageView) bindings[6];
        this.switchProfileSpinner = (FrameLayout) bindings[4];
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

    public static FragmentFloatingProfileSwitcherBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentFloatingProfileSwitcherBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_floating_profile_switcher, root, attachToRoot);
    }

    public static FragmentFloatingProfileSwitcherBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_floating_profile_switcher, null, false));
    }

    public static FragmentFloatingProfileSwitcherBinding bind(View view) {
        if ("layout/fragment_floating_profile_switcher_0".equals(view.getTag())) {
            return new FragmentFloatingProfileSwitcherBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
