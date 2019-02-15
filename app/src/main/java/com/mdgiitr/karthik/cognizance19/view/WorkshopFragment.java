package com.mdgiitr.karthik.cognizance19.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.ViewPagerAdapter;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.mdgiitr.karthik.cognizance19.MainActivity.bottomNavigationView;
import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;


public class WorkshopFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private HashMap<Integer, Fragment> map;
    private ImageView backIcon;
    private CircleImageView smallImageView;
    private PreferenceHelper preferenceHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workshop, container, false);

        tabLayout = view.findViewById(R.id.workshop_tabs);
        viewPager = view.findViewById(R.id.workshop_view_pager);

        backIcon = view.findViewById(R.id.back_arrow);
        smallImageView = view.findViewById(R.id.small_profile_image);

        preferenceHelper = new PreferenceHelper(getActivity());

        map = new HashMap<>();
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), map);

        map.put(0, new WorkshopSlot1Fragment());
        map.put(1, new WorkshopSlot2Fragment());
        viewPagerAdapter.notifyDataSetChanged();

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        setUpTabs();

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });

        bottomNavigationView.setVisibility(View.VISIBLE);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.com_facebook_profile_picture_blank_square)
                .error(R.drawable.com_facebook_profile_picture_blank_square);
        Glide.with(this)
                .load(preferenceHelper.getProfilePicURL())
                .apply(options)
                .into(smallImageView);

        smallImageView.setOnClickListener(v -> navController.navigate(R.id.action_workshopFragment_to_myProfileFragment));

        return view;
    }

    private void setUpTabs() {
        tabLayout.getTabAt(0).setText("Day 1 - Day 2");
        tabLayout.getTabAt(1).setText("Day 2 - Day 3");

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setAllCaps(false);
                    ((TextView) tabViewChild).setTextSize(15);
                }
            }
        }
    }
}
