package com.bwf.p_1land.ui.welcome.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bwf.p_1land.ui.welcome.fragment.GuideFragment;

/**
 * Created by Administrator on 2016/11/29.
 */

public class GuideAdapter extends FragmentPagerAdapter{
    public GuideAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        GuideFragment fragment=GuideFragment.getFragment(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
