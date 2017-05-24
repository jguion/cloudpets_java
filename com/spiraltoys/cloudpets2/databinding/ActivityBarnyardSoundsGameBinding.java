package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.views.ConnectionIndicatorView;

public class ActivityBarnyardSoundsGameBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView backgroundImage;
    public final ConnectionIndicatorView connectionIndicator;
    public final FrameLayout gameContainer;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final Toolbar toolbar;
    public final FrameLayout tutorialButtonContainer;
    public final ImageView tutorialCharacter;
    public final LinearLayout tutorialOverlay;
    public final ScrollView tutorialText;

    static {
        sViewsWithIds.put(R.id.game_container, 1);
        sViewsWithIds.put(R.id.background_image, 2);
        sViewsWithIds.put(R.id.connection_indicator, 3);
        sViewsWithIds.put(R.id.toolbar, 4);
        sViewsWithIds.put(R.id.tutorial_overlay, 5);
        sViewsWithIds.put(R.id.tutorial_text, 6);
        sViewsWithIds.put(R.id.tutorial_character, 7);
        sViewsWithIds.put(R.id.tutorial_button_container, 8);
    }

    public ActivityBarnyardSoundsGameBinding(View root) {
        super(root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(root, 9, sIncludes, sViewsWithIds);
        this.backgroundImage = (ImageView) bindings[2];
        this.connectionIndicator = (ConnectionIndicatorView) bindings[3];
        this.gameContainer = (FrameLayout) bindings[1];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.toolbar = (Toolbar) bindings[4];
        this.tutorialButtonContainer = (FrameLayout) bindings[8];
        this.tutorialCharacter = (ImageView) bindings[7];
        this.tutorialOverlay = (LinearLayout) bindings[5];
        this.tutorialText = (ScrollView) bindings[6];
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

    public static ActivityBarnyardSoundsGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ActivityBarnyardSoundsGameBinding) DataBindingUtil.inflate(inflater, R.layout.activity_barnyard_sounds_game, root, attachToRoot);
    }

    public static ActivityBarnyardSoundsGameBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.activity_barnyard_sounds_game, null, false));
    }

    public static ActivityBarnyardSoundsGameBinding bind(View view) {
        if ("layout/activity_barnyard_sounds_game_0".equals(view.getTag())) {
            return new ActivityBarnyardSoundsGameBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
