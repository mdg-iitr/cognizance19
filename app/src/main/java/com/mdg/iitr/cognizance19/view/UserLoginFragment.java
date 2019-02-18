package com.mdg.iitr.cognizance19.view;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.adapters.ViewPagerAdapter;

import java.util.HashMap;

import static com.mdg.iitr.cognizance19.MainActivity.bottomNavigationView;

public class UserLoginFragment extends Fragment {

    private static ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private HashMap<Integer, Fragment> map;

    public UserLoginFragment() {
        // Required empty public constructor
    }

    public static void setViewPagerFragment(int i) {
        viewPager.setCurrentItem(i);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);

        tabLayout = view.findViewById(R.id.profile_tabs);
        viewPager = view.findViewById(R.id.view_pager);

        map = new HashMap<>();
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), map);

        map.put(0, new LoginFragment());
        map.put(1, new RegisterFragment());

        viewPagerAdapter.notifyDataSetChanged();


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setUpTabs();
        setTab(getArguments().getInt("fr_open"));
        bottomNavigationView.setVisibility(View.GONE);
        return view;
    }

    private void setUpTabs() {
        tabLayout.getTabAt(0).setText("Log in");
        tabLayout.getTabAt(1).setText("Register");
    }

    private void setTab(int i) {
        viewPager.setCurrentItem(i);
    }

}
