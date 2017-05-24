package com.spiraltoys.cloudpets2.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardHelpBinding;
import com.spiraltoys.cloudpets2.free.R;

public class ChildDashboardHelpFragment extends Fragment {
    private static final int[] TAB_LABELS = new int[]{R.string.title_listen, R.string.title_send};
    private static final int[] TAB_LAYOUTS = new int[]{R.layout.fragment_child_dashboard_help_listen, R.layout.fragment_child_dashboard_help_send};
    private FragmentChildDashboardHelpBinding mBinding;

    private class HelpPagerAdapter extends FragmentPagerAdapter {
        public HelpPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public int getCount() {
            return ChildDashboardHelpFragment.TAB_LABELS.length;
        }

        public Fragment getItem(int position) {
            return LayoutFragment.newInstance(ChildDashboardHelpFragment.TAB_LAYOUTS[position]);
        }

        public CharSequence getPageTitle(int position) {
            return ChildDashboardHelpFragment.this.getString(ChildDashboardHelpFragment.TAB_LABELS[position]);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentChildDashboardHelpBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_dashboard_help, container, false);
        this.mBinding.pager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(this.mBinding.tabs));
        this.mBinding.pager.setAdapter(new HelpPagerAdapter(getChildFragmentManager()));
        this.mBinding.tabs.setupWithViewPager(this.mBinding.pager);
        return this.mBinding.getRoot();
    }
}
