package com.mdg.iitr.cognizance19.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private HashMap<Integer, Fragment> map;

    public ViewPagerAdapter(FragmentManager fm, HashMap<Integer, Fragment> map) {
        super(fm);
        this.map = map;
    }

    @Override
    public Fragment getItem(int i) {
        return map.get(i);
    }

    @Override
    public int getCount() {
        return map.size();
    }
}
