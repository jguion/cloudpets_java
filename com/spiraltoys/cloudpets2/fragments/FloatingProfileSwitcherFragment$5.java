package com.spiraltoys.cloudpets2.fragments;

import android.animation.AnimatorInflater;
import android.annotation.TargetApi;
import android.graphics.Outline;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.fragments.FloatingProfileSwitcherFragment.OnFloatingProfileSwitcherFragmentInteractionListener;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.LastAccessedProfileComparator;
import java.util.Collections;
import java.util.List;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

class FloatingProfileSwitcherFragment$5 implements FindCallback<Profile> {
    final /* synthetic */ FloatingProfileSwitcherFragment this$0;

    FloatingProfileSwitcherFragment$5(FloatingProfileSwitcherFragment this$0) {
        this.this$0 = this$0;
    }

    public void done(List<Profile> profiles, ParseException e) {
        if (FloatingProfileSwitcherFragment.access$200(this.this$0) != null) {
            OnFloatingProfileSwitcherFragmentInteractionListener access$200 = FloatingProfileSwitcherFragment.access$200(this.this$0);
            boolean z = profiles == null || profiles.isEmpty();
            access$200.onProfileMenuLoaded(z);
        }
        if (e != null || !this.this$0.isAdded() || this.this$0.isRemoving()) {
            return;
        }
        if (profiles == null || profiles.isEmpty()) {
            FloatingProfileSwitcherFragment.access$302(this.this$0, true);
            FloatingProfileSwitcherFragment.access$400(this.this$0);
            return;
        }
        FloatingProfileSwitcherFragment.access$100(this.this$0).floatingMenu.setClipChildren(false);
        FloatingProfileSwitcherFragment.access$100(this.this$0).profileSwitcher.setEnabled(true);
        if (FloatingProfileSwitcherFragment.access$500(this.this$0).getPrivateProfile().isAdult()) {
            FloatingProfileSwitcherFragment.access$100(this.this$0).switchProfileButton.setBackgroundResource(R.drawable.profile_selector);
        } else {
            FloatingProfileSwitcherFragment.access$100(this.this$0).switchProfileButton.setBackgroundResource(R.drawable.profile_selector_child_dashboard);
            FloatingProfileSwitcherFragment.access$100(this.this$0).switchProfileButton.setTextColor(-1);
            FloatingProfileSwitcherFragment.access$100(this.this$0).switchProfileButton.setTypeface(TypefaceUtils.load(this.this$0.getActivity().getAssets(), "fonts/merge_bold.otf"));
        }
        FloatingProfileSwitcherFragment.access$100(this.this$0).switchProfileButtonArrow.setVisibility(0);
        FloatingProfileSwitcherFragment.access$302(this.this$0, false);
        FloatingProfileSwitcherFragment.access$400(this.this$0);
        int menuSectionHeight = 0;
        int availableMenuHeight = FloatingProfileSwitcherFragment.access$100(this.this$0).floatingMenu.getMeasuredHeight();
        Collections.sort(profiles, new LastAccessedProfileComparator(this.this$0.getActivity()));
        int i = 0;
        while (i < Math.min(6, profiles.size())) {
            final Profile profile = (Profile) profiles.get(i);
            OnClickListener clickListener = new OnClickListener() {
                public void onClick(View v) {
                    FloatingProfileSwitcherFragment.access$600(FloatingProfileSwitcherFragment$5.this.this$0, profile);
                }
            };
            LinearLayout clickableArea = new LinearLayout(this.this$0.getActivity());
            clickableArea.setOrientation(1);
            clickableArea.setGravity(1);
            clickableArea.setOnClickListener(clickListener);
            clickableArea.setPadding(FloatingProfileSwitcherFragment.access$700(this.this$0), 0, FloatingProfileSwitcherFragment.access$700(this.this$0), FloatingProfileSwitcherFragment.access$700(this.this$0));
            clickableArea.setClipToPadding(false);
            clickableArea.setClipChildren(false);
            ImageView profileImage = new ImageView(this.this$0.getActivity());
            profileImage.setDuplicateParentStateEnabled(true);
            profileImage.setId(View.generateViewId());
            TextView label = new TextView(this.this$0.getActivity());
            label.setText(profile.getDisplayName());
            label.setPadding(FloatingProfileSwitcherFragment.access$800(this.this$0), FloatingProfileSwitcherFragment.access$900(this.this$0), FloatingProfileSwitcherFragment.access$800(this.this$0), FloatingProfileSwitcherFragment.access$900(this.this$0));
            label.setBackgroundResource(R.drawable.fab_menu_label_bg);
            label.setTextColor(ContextCompat.getColor(this.this$0.getActivity(), R.color.primary_text_default_material_light));
            label.setGravity(17);
            if (VERSION.SDK_INT >= 21) {
                label.setStateListAnimator(AnimatorInflater.loadStateListAnimator(this.this$0.getActivity(), R.drawable.floating_menu_label_state_list_anim));
            }
            View separatorDot = new View(this.this$0.getActivity());
            separatorDot.setBackgroundResource(R.drawable.primary_circle_solid);
            if (VERSION.SDK_INT >= 21) {
                profileImage.setStateListAnimator(AnimatorInflater.loadStateListAnimator(this.this$0.getActivity(), R.drawable.floating_menu_image_state_list_anim));
                profileImage.setOutlineProvider(new ViewOutlineProvider() {
                    @TargetApi(21)
                    public void getOutline(View view, Outline outline) {
                        outline.setOval(0, 0, FloatingProfileSwitcherFragment.access$1000(FloatingProfileSwitcherFragment$5.this.this$0), FloatingProfileSwitcherFragment.access$1000(FloatingProfileSwitcherFragment$5.this.this$0));
                    }
                });
            }
            if (i == 5) {
                FloatingProfileSwitcherFragment.access$1100(this.this$0);
                FloatingProfileSwitcherFragment.access$1200(this.this$0);
                return;
            } else if (availableMenuHeight - (menuSectionHeight * 2) > 0) {
                LayoutParams lp = new LayoutParams(-2, -2);
                lp.addRule(17, profileImage.getId());
                lp.addRule(6, profileImage.getId());
                lp.leftMargin = this.this$0.getResources().getDimensionPixelSize(R.dimen.fab_menu_badge_offset_end);
                lp.rightMargin = this.this$0.getResources().getDimensionPixelSize(R.dimen.fab_menu_badge_offset_end);
                final TextView count = new TextView(this.this$0.getActivity());
                RelativeLayout imageLayout = new RelativeLayout(this.this$0.getActivity());
                count.setBackgroundResource(R.drawable.badge_count);
                count.setTextSize(0, (float) this.this$0.getResources().getDimensionPixelSize(R.dimen.abc_text_size_caption_material));
                count.setTextColor(-1);
                count.setGravity(17);
                count.setTypeface(FloatingProfileSwitcherFragment.access$1300(this.this$0));
                count.setMinimumWidth(this.this$0.getResources().getDimensionPixelSize(R.dimen.count_badge_min_width));
                count.setMinimumHeight(this.this$0.getResources().getDimensionPixelSize(R.dimen.count_badge_min_width));
                count.setSingleLine();
                count.setAlpha(0.0f);
                count.setVisibility(0);
                imageLayout.setClipChildren(false);
                imageLayout.setClipToPadding(false);
                imageLayout.addView(profileImage, FloatingProfileSwitcherFragment.access$1400(this.this$0));
                imageLayout.addView(count, lp);
                imageLayout.setPadding(-this.this$0.getResources().getDimensionPixelSize(R.dimen.fab_menu_badge_offset_end), 0, -this.this$0.getResources().getDimensionPixelSize(R.dimen.fab_menu_badge_offset_end), 0);
                if (VERSION.SDK_INT >= 21) {
                    count.setStateListAnimator(AnimatorInflater.loadStateListAnimator(this.this$0.getActivity(), R.drawable.floating_menu_image_state_list_anim));
                }
                Runnable mMessageCountRunnable = new Runnable() {
                    public void run() {
                        ModelHelper.countLocalNewMessages(profile, false, FloatingProfileSwitcherFragment.access$1500(FloatingProfileSwitcherFragment$5.this.this$0), new CountCallback() {
                            public void done(int i, ParseException e) {
                                if (FloatingProfileSwitcherFragment$5.this.this$0.isAdded() && !FloatingProfileSwitcherFragment$5.this.this$0.isRemoving()) {
                                    if (i == 0) {
                                        count.animate().alpha(0.0f).start();
                                        return;
                                    }
                                    count.setText(String.format("%d", new Object[]{Integer.valueOf(i)}));
                                    count.animate().alpha(1.0f).start();
                                }
                            }
                        });
                    }
                };
                FloatingProfileSwitcherFragment.access$1600(this.this$0).post(mMessageCountRunnable);
                FloatingProfileSwitcherFragment.access$1700(this.this$0).add(mMessageCountRunnable);
                clickableArea.addView(separatorDot, FloatingProfileSwitcherFragment.access$1800(this.this$0));
                clickableArea.addView(imageLayout, -2, FloatingProfileSwitcherFragment.access$1000(this.this$0));
                clickableArea.addView(label, FloatingProfileSwitcherFragment.access$1900(this.this$0));
                FloatingProfileSwitcherFragment.access$100(this.this$0).floatingMenu.addView(clickableArea, FloatingProfileSwitcherFragment.access$2000(this.this$0));
                if (menuSectionHeight == 0) {
                    label.measure(0, 0);
                    menuSectionHeight = (((((((((FloatingProfileSwitcherFragment.access$1800(this.this$0).height + FloatingProfileSwitcherFragment.access$1400(this.this$0).height) + label.getMeasuredHeight()) + clickableArea.getPaddingTop()) + clickableArea.getPaddingBottom()) + FloatingProfileSwitcherFragment.access$1800(this.this$0).topMargin) + FloatingProfileSwitcherFragment.access$1800(this.this$0).bottomMargin) + FloatingProfileSwitcherFragment.access$1400(this.this$0).topMargin) + FloatingProfileSwitcherFragment.access$1400(this.this$0).bottomMargin) + FloatingProfileSwitcherFragment.access$1900(this.this$0).topMargin) + FloatingProfileSwitcherFragment.access$1900(this.this$0).bottomMargin;
                }
                availableMenuHeight -= menuSectionHeight;
                FloatingProfileSwitcherFragment.access$2200(this.this$0, profileImage, profile, false);
                i++;
            } else if (availableMenuHeight < menuSectionHeight) {
                FloatingProfileSwitcherFragment.access$2102(this.this$0, true);
                FloatingProfileSwitcherFragment.access$1200(this.this$0);
                return;
            } else {
                FloatingProfileSwitcherFragment.access$1100(this.this$0);
                FloatingProfileSwitcherFragment.access$1200(this.this$0);
                return;
            }
        }
        FloatingProfileSwitcherFragment.access$2102(this.this$0, false);
        FloatingProfileSwitcherFragment.access$1200(this.this$0);
    }
}
