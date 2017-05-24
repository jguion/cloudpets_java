package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;

public class FragmentCreateChildProfileBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Spinner birthdayDaySpinner;
    public final Spinner birthdayMonthSpinner;
    public final EditText displayName;
    private long mDirtyFlags = -1;
    private final ScrollView mboundView0;
    public final ImageView profileBear;
    public final ImageView profilePhoto;
    public final FrameLayout profilePhotoWrapper;
    public final TextView titleText;
    public final EditText username;
    public final ImageView usernameHelp;

    static {
        sViewsWithIds.put(R.id.title_text, 1);
        sViewsWithIds.put(R.id.profile_bear, 2);
        sViewsWithIds.put(R.id.profile_photo_wrapper, 3);
        sViewsWithIds.put(R.id.profile_photo, 4);
        sViewsWithIds.put(R.id.display_name, 5);
        sViewsWithIds.put(R.id.username, 6);
        sViewsWithIds.put(R.id.username_help, 7);
        sViewsWithIds.put(R.id.birthday_month_spinner, 8);
        sViewsWithIds.put(R.id.birthday_day_spinner, 9);
    }

    public FragmentCreateChildProfileBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 10, sIncludes, sViewsWithIds);
        this.birthdayDaySpinner = (Spinner) bindings[9];
        this.birthdayMonthSpinner = (Spinner) bindings[8];
        this.displayName = (EditText) bindings[5];
        this.mboundView0 = (ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.profileBear = (ImageView) bindings[2];
        this.profilePhoto = (ImageView) bindings[4];
        this.profilePhotoWrapper = (FrameLayout) bindings[3];
        this.titleText = (TextView) bindings[1];
        this.username = (EditText) bindings[6];
        this.usernameHelp = (ImageView) bindings[7];
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

    public static FragmentCreateChildProfileBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentCreateChildProfileBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_create_child_profile, root, attachToRoot);
    }

    public static FragmentCreateChildProfileBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_create_child_profile, null, false));
    }

    public static FragmentCreateChildProfileBinding bind(View view) {
        if ("layout/fragment_create_child_profile_0".equals(view.getTag())) {
            return new FragmentCreateChildProfileBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
