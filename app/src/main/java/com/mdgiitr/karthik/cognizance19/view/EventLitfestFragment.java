package com.mdgiitr.karthik.cognizance19.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.FinfestAdapter;
import com.mdgiitr.karthik.cognizance19.models.EventModel;
import com.mdgiitr.karthik.cognizance19.models.FinfestModel;

import java.util.ArrayList;
import java.util.List;

public class EventLitfestFragment extends Fragment {
    private RecyclerView recyclerView;
    private FinfestAdapter adapter;
    private List<FinfestModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_litfest, container, false);

        recyclerView = view.findViewById(R.id.litfest_recyclerview);
        list = new ArrayList<>();
        adapter = new FinfestAdapter(getContext(),list);

        for (int i=1;i<=3;i++){
            FinfestModel finfestModel = new FinfestModel();
            finfestModel.setCategory("Category " + i);
            List<EventModel> eventModelList = new ArrayList<>();
            for (int j=0;j<5;j++){
                EventModel eventModel = new EventModel();
                eventModel.setEventName("Power Drift");
                eventModel.setEventCategory("#robotics,race-car");
                eventModel.setEventFollowers("100 followers");
                eventModelList.add(eventModel);
            }
            finfestModel.setEventList(eventModelList);
            list.add(finfestModel);
        }

        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
