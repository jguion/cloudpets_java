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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.views.RippleView;

public class FragmentChildRecordedMessageActionsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final FloatingActionButton deleteButton;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final FrameLayout messageDetailsContainer;
    public final RippleView playbackRippleEffect;
    public final FloatingActionButton selectRecipientsButton;

    static {
        sViewsWithIds.put(R.id.playback_ripple_effect, 1);
        sViewsWithIds.put(R.id.message_details_container, 2);
        sViewsWithIds.put(R.id.select_recipients_button, 3);
        sViewsWithIds.put(R.id.delete_button, 4);
    }

    public FragmentChildRecordedMessageActionsBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 5, sIncludes, sViewsWithIds);
        this.deleteButton = (FloatingActionButton) bindings[4];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.messageDetailsContainer = (FrameLayout) bindings[2];
        this.playbackRippleEffect = (RippleView) bindings[1];
        this.selectRecipientsButton = (FloatingActionButton) bindings[3];
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

    public static FragmentChildRecordedMessageActionsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentChildRecordedMessageActionsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_recorded_message_actions, root, attachToRoot);
    }

    public static FragmentChildRecordedMessageActionsBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_child_recorded_message_actions, null, false));
    }

    public static FragmentChildRecordedMessageActionsBinding bind(View view) {
        if ("layout/fragment_child_recorded_message_actions_0".equals(view.getTag())) {
            return new FragmentChildRecordedMessageActionsBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
