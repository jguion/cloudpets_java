package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.spiraltoys.cloudpets2.free.R;

public class ActivityInviteFriendBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final EditText email;
    public final Button inviteFriendButton;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final AppCompatSpinner relationshipSpinner;
    public final FrameLayout selectProfileFragment;

    static {
        sViewsWithIds.put(R.id.email, 1);
        sViewsWithIds.put(R.id.relationship_spinner, 2);
        sViewsWithIds.put(R.id.select_profile_fragment, 3);
        sViewsWithIds.put(R.id.invite_friend_button, 4);
    }

    public ActivityInviteFriendBinding(View root) {
        super(root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(root, 5, sIncludes, sViewsWithIds);
        this.email = (EditText) bindings[1];
        this.inviteFriendButton = (Button) bindings[4];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.relationshipSpinner = (AppCompatSpinner) bindings[2];
        this.selectProfileFragment = (FrameLayout) bindings[3];
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

    public static ActivityInviteFriendBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ActivityInviteFriendBinding) DataBindingUtil.inflate(inflater, R.layout.activity_invite_friend, root, attachToRoot);
    }

    public static ActivityInviteFriendBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.activity_invite_friend, null, false));
    }

    public static ActivityInviteFriendBinding bind(View view) {
        if ("layout/activity_invite_friend_0".equals(view.getTag())) {
            return new ActivityInviteFriendBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
