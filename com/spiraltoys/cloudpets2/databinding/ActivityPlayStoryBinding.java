package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.views.ConnectionIndicatorView;

public class ActivityPlayStoryBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ConnectionIndicatorView connectionIndicator;
    public final RelativeLayout container;
    private long mDirtyFlags = -1;
    public final ImageButton nextPageButton;
    public final ImageView pageImage;
    public final TextView pageText;
    public final ImageButton previousPageButton;
    public final Toolbar toolbar;

    static {
        sViewsWithIds.put(R.id.page_image, 1);
        sViewsWithIds.put(R.id.toolbar, 2);
        sViewsWithIds.put(R.id.connection_indicator, 3);
        sViewsWithIds.put(R.id.previous_page_button, 4);
        sViewsWithIds.put(R.id.page_text, 5);
        sViewsWithIds.put(R.id.next_page_button, 6);
    }

    public ActivityPlayStoryBinding(View root) {
        super(root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(root, 7, sIncludes, sViewsWithIds);
        this.connectionIndicator = (ConnectionIndicatorView) bindings[3];
        this.container = (RelativeLayout) bindings[0];
        this.container.setTag(null);
        this.nextPageButton = (ImageButton) bindings[6];
        this.pageImage = (ImageView) bindings[1];
        this.pageText = (TextView) bindings[5];
        this.previousPageButton = (ImageButton) bindings[4];
        this.toolbar = (Toolbar) bindings[2];
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

    public static ActivityPlayStoryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ActivityPlayStoryBinding) DataBindingUtil.inflate(inflater, R.layout.activity_play_story, root, attachToRoot);
    }

    public static ActivityPlayStoryBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.activity_play_story, null, false));
    }

    public static ActivityPlayStoryBinding bind(View view) {
        if ("layout/activity_play_story_0".equals(view.getTag())) {
            return new ActivityPlayStoryBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
