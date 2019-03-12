package com.mdg.iitr.cognizance19.view;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
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

public class SpotLightFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private HashMap<Integer, Fragment> map;
    private ApiClient apiClient;
    private CircleImageView smallImageView;
    private ImageView backArrow;

    public SpotLightFragment() {
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
        View view = inflater.inflate(R.layout.fragment_spotlights, container, false);

        smallImageView = view.findViewById(R.id.small_profile_image);
        smallImageView.setOnClickListener(v -> navController.navigate(R.id.action_scheduleFragment_to_myProfileFragment));

        tabLayout = view.findViewById(R.id.schedule_tabs);
        viewPager = view.findViewById(R.id.schedule_viewPager);

        map = new HashMap<>();
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), map);


        map.put(0, new SpotlightDay1Fragment());
        map.put(1, new SpotlightDay2Fragment());
        map.put(2, new SpotlightDay3Fragment());
        viewPagerAdapter.notifyDataSetChanged();

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        backArrow = view.findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(v -> navController.navigateUp());

        setUpTabs();

        return view;
    }

    private void setUpTabs() {
        tabLayout.getTabAt(0).setText(Html.fromHtml("<b>DAY 1</b><br/><small>15 March 19</small>"));
        tabLayout.getTabAt(1).setText(Html.fromHtml("<b>DAY 2</b><br/><small>16 March 19</small>"));
        tabLayout.getTabAt(2).setText(Html.fromHtml("<b>DAY 3</b><br/><small>17 March 19</small>"));

    }
}
