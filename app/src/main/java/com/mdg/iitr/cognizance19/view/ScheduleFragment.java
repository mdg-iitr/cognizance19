package com.mdg.iitr.cognizance19.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.adapters.ViewPagerAdapter;
import com.mdg.iitr.cognizance19.models.Centerstage;
import com.mdg.iitr.cognizance19.models.CenterstageOrDepartmentalEventsResponse;
import com.mdg.iitr.cognizance19.models.Departmental;
import com.mdg.iitr.cognizance19.models.Event;
import com.mdg.iitr.cognizance19.models.Schedule;
import com.mdg.iitr.cognizance19.models.ScheduleEventModel;
import com.mdg.iitr.cognizance19.network.client.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static com.mdg.iitr.cognizance19.MainActivity.navController;

public class ScheduleFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private HashMap<Integer, Fragment> map;
    private ApiClient apiClient;
    private CircleImageView smallImageView;
    private ImageView backArrow;

    public ScheduleFragment() {
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
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

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

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        apiClient.fetchEvents()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CenterstageOrDepartmentalEventsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CenterstageOrDepartmentalEventsResponse centerstageOrDepartmentalEventsResponse) {
                        progressDialog.dismiss();
                        List<ScheduleEventModel> dayOne = new ArrayList<>();
                        List<ScheduleEventModel> dayTwo = new ArrayList<>();
                        List<ScheduleEventModel> dayThree = new ArrayList<>();
                        List<Centerstage> centerstageList = centerstageOrDepartmentalEventsResponse.getCenterstage();
                        List<Departmental> departmentalList = centerstageOrDepartmentalEventsResponse.getDepartmental();
                        for (Centerstage centerstage : centerstageList) {
                            List<Event> centerStageEvents = centerstage.getEvents();
                            if (centerStageEvents != null)
                                for (Event event : centerStageEvents) {
                                    List<Schedule> scheduleList = event.getSchedule();
                                    if (scheduleList != null)
                                        for (Schedule schedule : scheduleList) {
                                            if (schedule != null)
                                                switch (schedule.getDay()) {
                                                    case 1:
                                                        dayOne.add(new ScheduleEventModel(event.getName(), schedule.getTime()));
                                                        break;
                                                    case 2:
                                                        dayTwo.add(new ScheduleEventModel(event.getName(), schedule.getTime()));
                                                        break;
                                                    case 3:
                                                        dayThree.add(new ScheduleEventModel(event.getName(), schedule.getTime()));
                                                        break;
                                                }
                                        }
                                }
                        }
                        for (Departmental departmental : departmentalList) {
                            List<Event> departmentalEventList = departmental.getEvents();
                            if (departmentalEventList != null)
                                for (Event event : departmentalEventList) {
                                    List<Schedule> scheduleList = event.getSchedule();
                                    if (scheduleList != null)
                                        for (Schedule schedule : scheduleList) {
                                            if (schedule != null)
                                                switch (schedule.getDay()) {
                                                    case 1:
                                                        dayOne.add(new ScheduleEventModel(event.getName(), schedule.getTime()));
                                                        break;
                                                    case 2:
                                                        dayTwo.add(new ScheduleEventModel(event.getName(), schedule.getTime()));
                                                        break;
                                                    case 3:
                                                        dayThree.add(new ScheduleEventModel(event.getName(), schedule.getTime()));
                                                        break;
                                                }
                                        }
                                }
                        }
                        map = new HashMap<>();
                        map.put(0, new ScheduleDayFragment(dayOne));
                        map.put(1, new ScheduleDayFragment(dayTwo));
                        map.put(2, new ScheduleDayFragment(dayThree));
                        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), map);
                        viewPagerAdapter.notifyDataSetChanged();

                        viewPager.setAdapter(viewPagerAdapter);

                        tabLayout.setupWithViewPager(viewPager);

                        tabLayout.getTabAt(0).setText(Html.fromHtml("<b>DAY 1</b><br/><small>15 March 2019</small>"));
                        tabLayout.getTabAt(1).setText(Html.fromHtml("<b>DAY 2</b><br/><small>16 March 2019</small>"));
                        tabLayout.getTabAt(2).setText(Html.fromHtml("<b>DAY 3</b><br/><small>17 March 2019</small>"));

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Some error occured.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
