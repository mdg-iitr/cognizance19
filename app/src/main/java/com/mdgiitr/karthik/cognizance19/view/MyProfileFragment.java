package com.mdgiitr.karthik.cognizance19.view;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toolbar;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.TabViewPagerAdapter;

import androidx.navigation.NavOptions;

import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class MyProfileFragment extends Fragment {

    private PopupMenu popupMenu;
    private ImageView menuImageView;

    public MyProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.my_profile, container, false);
        setHasOptionsMenu(true);

        menuImageView = view.findViewById(R.id.menu_icon);

        popupMenu = new PopupMenu(getActivity(), menuImageView);
        popupMenu.getMenuInflater().inflate(R.menu.profile_menu, popupMenu.getMenu());

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        final ViewPager viewPager = view.findViewById(R.id.view_pager_tab);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        assert viewPager != null;
        tabLayout.setupWithViewPager(viewPager);

        final TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        menuImageView.setOnClickListener((View v) -> {

            popupMenu.show();

        });

        popupMenu.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.logout) {
                userLogout();
            } else if (item.getItemId() == R.id.change_password) {

            }

            return false;
        });
        return view;
    }

    private void userLogout() {
        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.landingFragment2, true)
                .build();
        navController.navigate(R.id.action_myProfileFragment_to_landingFragment2, null, navOptions);

    }

}
