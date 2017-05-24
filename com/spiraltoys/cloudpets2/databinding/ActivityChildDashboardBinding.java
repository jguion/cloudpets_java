package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.AdView;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.views.ConnectionIndicatorView;
import com.spiraltoys.cloudpets2.views.PetVideoSurfaceView;

public class ActivityChildDashboardBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(18);
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final AdView adView;
    public final ImageView childDashboardHelp;
    public final ConnectionIndicatorView connectionIndicator;
    public final FrameLayout container;
    public final View floatingMenuUnderlay;
    private long mDirtyFlags = -1;
    private Profile mProfile;
    public final TextView messageCount;
    public final PetVideoSurfaceView petVideo;
    public final FrameLayout profilePickerContainer;
    public final FloatingActionButton showGamesButton;
    public final TextView showGamesButtonLabel;
    public final FloatingActionButton showLullabiesButton;
    public final TextView showLullabiesButtonLabel;
    public final FloatingActionButton showMessagesButton;
    public final TextView showMessagesButtonLabel;
    public final FloatingActionButton showStoriesButton;
    public final TextView showStoriesButtonLabel;
    public final ImageView videoPlaceholder;
    public final FragmentOverlayVisitingCloudpetBinding visitingCloudpetOverlay;

    static {
        sIncludes.setIncludes(0, new String[]{"fragment_overlay_visiting_cloudpet"}, new int[]{1}, new int[]{R.layout.fragment_overlay_visiting_cloudpet});
        sViewsWithIds.put(R.id.pet_video, 2);
        sViewsWithIds.put(R.id.video_placeholder, 3);
        sViewsWithIds.put(R.id.ad_view, 4);
        sViewsWithIds.put(R.id.connection_indicator, 5);
        sViewsWithIds.put(R.id.show_games_button, 6);
        sViewsWithIds.put(R.id.show_games_button_label, 7);
        sViewsWithIds.put(R.id.show_messages_button, 8);
        sViewsWithIds.put(R.id.message_count, 9);
        sViewsWithIds.put(R.id.show_messages_button_label, 10);
        sViewsWithIds.put(R.id.show_stories_button, 11);
        sViewsWithIds.put(R.id.show_stories_button_label, 12);
        sViewsWithIds.put(R.id.show_lullabies_button, 13);
        sViewsWithIds.put(R.id.show_lullabies_button_label, 14);
        sViewsWithIds.put(R.id.floating_menu_underlay, 15);
        sViewsWithIds.put(R.id.profile_picker_container, 16);
        sViewsWithIds.put(R.id.child_dashboard_help, 17);
    }

    public ActivityChildDashboardBinding(View root) {
        super(root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(root, 18, sIncludes, sViewsWithIds);
        this.adView = (AdView) bindings[4];
        this.childDashboardHelp = (ImageView) bindings[17];
        this.connectionIndicator = (ConnectionIndicatorView) bindings[5];
        this.container = (FrameLayout) bindings[0];
        this.container.setTag(null);
        this.floatingMenuUnderlay = (View) bindings[15];
        this.messageCount = (TextView) bindings[9];
        this.petVideo = (PetVideoSurfaceView) bindings[2];
        this.profilePickerContainer = (FrameLayout) bindings[16];
        this.showGamesButton = (FloatingActionButton) bindings[6];
        this.showGamesButtonLabel = (TextView) bindings[7];
        this.showLullabiesButton = (FloatingActionButton) bindings[13];
        this.showLullabiesButtonLabel = (TextView) bindings[14];
        this.showMessagesButton = (FloatingActionButton) bindings[8];
        this.showMessagesButtonLabel = (TextView) bindings[10];
        this.showStoriesButton = (FloatingActionButton) bindings[11];
        this.showStoriesButtonLabel = (TextView) bindings[12];
        this.videoPlaceholder = (ImageView) bindings[3];
        this.visitingCloudpetOverlay = (FragmentOverlayVisitingCloudpetBinding) bindings[1];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.visitingCloudpetOverlay.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
        r6 = this;
        r0 = 1;
        monitor-enter(r6);
        r2 = r6.mDirtyFlags;	 Catch:{ all -> 0x0017 }
        r4 = 0;
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 == 0) goto L_0x000c;
    L_0x000a:
        monitor-exit(r6);	 Catch:{ all -> 0x0017 }
    L_0x000b:
        return r0;
    L_0x000c:
        monitor-exit(r6);	 Catch:{ all -> 0x0017 }
        r1 = r6.visitingCloudpetOverlay;
        r1 = r1.hasPendingBindings();
        if (r1 != 0) goto L_0x000b;
    L_0x0015:
        r0 = 0;
        goto L_0x000b;
    L_0x0017:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0017 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spiraltoys.cloudpets2.databinding.ActivityChildDashboardBinding.hasPendingBindings():boolean");
    }

    private void log(String msg, long i) {
        Log.d("BINDER", msg + ":" + Long.toHexString(i));
    }

    public boolean setVariable(int variableId, Object variable) {
        switch (variableId) {
            case 4:
                setProfile((Profile) variable);
                return true;
            default:
                return false;
        }
    }

    public void setProfile(Profile profile) {
        this.mProfile = profile;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        super.requestRebind();
    }

    public Profile getProfile() {
        return this.mProfile;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Profile profile = this.mProfile;
        if ((dirtyFlags & 3) != 0) {
        }
        if ((dirtyFlags & 3) != 0) {
            this.visitingCloudpetOverlay.setProfile(profile);
        }
        this.visitingCloudpetOverlay.executePendingBindings();
    }

    public static ActivityChildDashboardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ActivityChildDashboardBinding) DataBindingUtil.inflate(inflater, R.layout.activity_child_dashboard, root, attachToRoot);
    }

    public static ActivityChildDashboardBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.activity_child_dashboard, null, false));
    }

    public static ActivityChildDashboardBinding bind(View view) {
        if ("layout/activity_child_dashboard_0".equals(view.getTag())) {
            return new ActivityChildDashboardBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
