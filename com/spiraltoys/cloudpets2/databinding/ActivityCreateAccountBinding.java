package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
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

public class ActivityCreateAccountBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final EditText displayName;
    public final EditText email;
    public final Button logInButton;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    private final FrameLayout mboundView1;
    public final EditText password;
    public final EditText passwordRepeat;
    public final ImageView profilePhoto;
    public final TextView subHeadingText;

    static {
        sViewsWithIds.put(R.id.sub_heading_text, 2);
        sViewsWithIds.put(R.id.profile_photo, 3);
        sViewsWithIds.put(R.id.display_name, 4);
        sViewsWithIds.put(R.id.email, 5);
        sViewsWithIds.put(R.id.password, 6);
        sViewsWithIds.put(R.id.password_repeat, 7);
        sViewsWithIds.put(R.id.log_in_button, 8);
    }

    public ActivityCreateAccountBinding(View root) {
        super(root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(root, 9, sIncludes, sViewsWithIds);
        this.displayName = (EditText) bindings[4];
        this.email = (EditText) bindings[5];
        this.logInButton = (Button) bindings[8];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (FrameLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.password = (EditText) bindings[6];
        this.passwordRepeat = (EditText) bindings[7];
        this.profilePhoto = (ImageView) bindings[3];
        this.subHeadingText = (TextView) bindings[2];
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
        if ((1 & dirtyFlags) != 0) {
            this.mboundView1.setVisibility(0);
        }
    }

    public static ActivityCreateAccountBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ActivityCreateAccountBinding) DataBindingUtil.inflate(inflater, R.layout.activity_create_account, root, attachToRoot);
    }

    public static ActivityCreateAccountBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.activity_create_account, null, false));
    }

    public static ActivityCreateAccountBinding bind(View view) {
        if ("layout/activity_create_account_0".equals(view.getTag())) {
            return new ActivityCreateAccountBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
