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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;

public class ActivityLogInBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button buttonLogIn;
    public final EditText email;
    public final ImageView loginBear;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final EditText password;
    public final Button resetPasswordButton;
    public final ScrollView scrollView;
    public final TextView titleText;
    public final Button tutorialButton;

    static {
        sViewsWithIds.put(R.id.button_log_in, 1);
        sViewsWithIds.put(R.id.scroll_view, 2);
        sViewsWithIds.put(R.id.title_text, 3);
        sViewsWithIds.put(R.id.email, 4);
        sViewsWithIds.put(R.id.password, 5);
        sViewsWithIds.put(R.id.tutorial_button, 6);
        sViewsWithIds.put(R.id.reset_password_button, 7);
        sViewsWithIds.put(R.id.login_bear, 8);
    }

    public ActivityLogInBinding(View root) {
        super(root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(root, 9, sIncludes, sViewsWithIds);
        this.buttonLogIn = (Button) bindings[1];
        this.email = (EditText) bindings[4];
        this.loginBear = (ImageView) bindings[8];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.password = (EditText) bindings[5];
        this.resetPasswordButton = (Button) bindings[7];
        this.scrollView = (ScrollView) bindings[2];
        this.titleText = (TextView) bindings[3];
        this.tutorialButton = (Button) bindings[6];
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
            this.mboundView0.setVisibility(0);
        }
    }

    public static ActivityLogInBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ActivityLogInBinding) DataBindingUtil.inflate(inflater, R.layout.activity_log_in, root, attachToRoot);
    }

    public static ActivityLogInBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.activity_log_in, null, false));
    }

    public static ActivityLogInBinding bind(View view) {
        if ("layout/activity_log_in_0".equals(view.getTag())) {
            return new ActivityLogInBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
