package com.mdgiitr.karthik.cognizance19.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mdgiitr.karthik.cognizance19.view.LoginFragment;
import com.mdgiitr.karthik.cognizance19.view.RegisterFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new LoginFragment();
        } else if (position == 1) {
            fragment = new RegisterFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "LOG IN";
        } else if (position == 1) {
            title = "REGISTER";
        }
        return title;
    }
}