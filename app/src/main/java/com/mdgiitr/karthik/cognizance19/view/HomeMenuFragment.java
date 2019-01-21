package com.mdgiitr.karthik.cognizance19.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.HomeMenuEventAdapter;
import com.mdgiitr.karthik.cognizance19.adapters.HomeMenuTechtainmentAdapter;
import com.mdgiitr.karthik.cognizance19.adapters.HomeMenuWorkshopAdapter;
import com.mdgiitr.karthik.cognizance19.models.HomeMenuEventModel;

import java.util.ArrayList;
import java.util.List;

public class HomeMenuFragment extends Fragment {
    private RecyclerView eventRecyclerView, workshopRecyclerView, techtainmentRecyclerView, guestRecyclerView;
    private List<HomeMenuEventModel> eventList, workshopList;
    private List<String> techtainmentURL, guestURL;
    private HomeMenuEventAdapter eventAdapter;
    private HomeMenuWorkshopAdapter workshopAdapter;
    private HomeMenuTechtainmentAdapter techtainmentAdapter, guestAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_menu, container, false);

        eventRecyclerView = view.findViewById(R.id.home_menu_event_recyclerview);
        workshopRecyclerView = view.findViewById(R.id.home_menu_workshop_recyclerview);
        techtainmentRecyclerView = view.findViewById(R.id.home_menu_techtainment_recyclerview);
        guestRecyclerView = view.findViewById(R.id.home_menu_guest_recyclerview);

        eventList = new ArrayList<>();
        workshopList = new ArrayList<>();
        techtainmentURL = new ArrayList<>();
        guestURL = new ArrayList<>();

        eventAdapter = new HomeMenuEventAdapter(getContext(),eventList);
        workshopAdapter = new HomeMenuWorkshopAdapter(getContext(), workshopList);
        techtainmentAdapter = new HomeMenuTechtainmentAdapter(getContext(),techtainmentURL);
        guestAdapter = new HomeMenuTechtainmentAdapter(getContext(), guestURL);

        HomeMenuEventModel eventModel = new HomeMenuEventModel();
        eventModel.setEventName("Armageddon");
        eventModel.setEventCategory("#robotics, bot-wars");
        eventModel.setEventFollowers("100 followers");
        eventList.add(eventModel);

        HomeMenuEventModel eventModel1 = new HomeMenuEventModel();
        eventModel1.setEventName("Powerdrift");
        eventModel1.setEventCategory("#robotics, race-car");
        eventModel1.setEventFollowers("70 followers");
        eventList.add(eventModel1);

        eventAdapter.notifyDataSetChanged();

        eventRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        eventRecyclerView.setAdapter(eventAdapter);

        workshopList.add(eventModel);
        workshopList.add(eventModel1);
        workshopAdapter.notifyDataSetChanged();

        workshopRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        workshopRecyclerView.setAdapter(workshopAdapter);

        techtainmentURL.add("hello");
        techtainmentURL.add("hi");
        techtainmentAdapter.notifyDataSetChanged();

        techtainmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        techtainmentRecyclerView.setAdapter(techtainmentAdapter);

        guestURL.add("hello");
        guestURL.add("hi");
        guestAdapter.notifyDataSetChanged();

        guestRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        guestRecyclerView.setAdapter(guestAdapter);

        return view;
    }
}
