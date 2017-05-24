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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Story;

public class FragmentChildDashboardStoryDetailsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private Story mStory;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    public final FloatingActionButton playButton;
    public final ImageView storyImage;

    static {
        sViewsWithIds.put(R.id.story_image, 2);
        sViewsWithIds.put(R.id.play_button, 3);
    }

    public FragmentChildDashboardStoryDetailsBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 4, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.playButton = (FloatingActionButton) bindings[3];
        this.storyImage = (ImageView) bindings[2];
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
            case 5:
                setStory((Story) variable);
                return true;
            default:
                return false;
        }
    }

    public void setStory(Story story) {
        this.mStory = story;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        super.requestRebind();
    }

    public Story getStory() {
        return this.mStory;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String titleStory = null;
        Story story = this.mStory;
        if (!((dirtyFlags & 3) == 0 || story == null)) {
            titleStory = story.getTitle();
        }
        if ((dirtyFlags & 3) != 0) {
            this.mboundView1.setText(titleStory);
        }
    }

    public static FragmentChildDashboardStoryDetailsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentChildDashboardStoryDetailsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_dashboard_story_details, root, attachToRoot);
    }

    public static FragmentChildDashboardStoryDetailsBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_child_dashboard_story_details, null, false));
    }

    public static FragmentChildDashboardStoryDetailsBinding bind(View view) {
        if ("layout/fragment_child_dashboard_story_details_0".equals(view.getTag())) {
            return new FragmentChildDashboardStoryDetailsBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
