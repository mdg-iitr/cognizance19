package com.mdg.iitr.cognizance19.view;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.adapters.ViewPagerAdapter;
import com.mdg.iitr.cognizance19.models.SpotlightSchedule;
import com.mdg.iitr.cognizance19.network.client.ApiClient;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.mdg.iitr.cognizance19.MainActivity.navController;

public class AboutUsFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private HashMap<Integer, Fragment> map;
    private ApiClient apiClient;
    private CircleImageView smallImageView;
    private ImageView backArrow;

    public AboutUsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClient = new ApiClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        smallImageView = view.findViewById(R.id.small_profile_image);
        smallImageView.setOnClickListener(v -> navController.navigate(R.id.action_scheduleFragment_to_myProfileFragment));

        tabLayout = view.findViewById(R.id.schedule_tabs);
        viewPager = view.findViewById(R.id.schedule_viewPager);

        backArrow = view.findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(v -> navController.navigateUp());

        setUpTabs();

        return view;
    }

    private void setUpTabs() {


        map = new HashMap<>();
        map.put(0, new ContactUsFragment());
        map.put(1, new FaqsFragment());
       // map.put(2, new SponsorsFragment());

        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), map);
        viewPagerAdapter.notifyDataSetChanged();

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Contact Us");
        tabLayout.getTabAt(1).setText("FAQs");

        if (BottonNavigationMenu.requiredType == 1) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(1);
        }

     //   tabLayout.getTabAt(2).setText("Sponsors");

    }

}
