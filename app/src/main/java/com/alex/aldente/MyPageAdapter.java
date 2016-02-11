package com.alex.aldente;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class MyPageAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragments;

    public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return this.fragments.get(position);
    }

    @Override
    public int getCount() {

        return this.fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Don't destroy the Fragment, so this is empty.

    }


}


