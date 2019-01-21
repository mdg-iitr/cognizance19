package com.mdgiitr.karthik.cognizance19.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mdgiitr.karthik.cognizance19.view.BookmarkTabFragment;
import com.mdgiitr.karthik.cognizance19.view.RegEventsTabFragment;
import com.mdgiitr.karthik.cognizance19.view.RegWorkshopsTabFragment;

public class TabViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TabViewPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                RegEventsTabFragment tab1 = new RegEventsTabFragment();
                return tab1;
            case 1:
                RegWorkshopsTabFragment tab2 = new RegWorkshopsTabFragment();
                return tab2;
            case 3:
                BookmarkTabFragment tab3 = new BookmarkTabFragment();
                return tab3;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
