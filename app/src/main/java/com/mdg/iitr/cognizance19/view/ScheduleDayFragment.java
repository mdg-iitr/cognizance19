package com.mdg.iitr.cognizance19.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.adapters.ScheduleRecyclerAdapter;
import com.mdg.iitr.cognizance19.models.ScheduleEventModel;

import java.util.List;

public class ScheduleDayFragment extends Fragment {

    private List<ScheduleEventModel> scheduleEventModelList;
    private RecyclerView scheduleRecyclerView;
    private ScheduleRecyclerAdapter scheduleRecyclerAdapter;

    public ScheduleDayFragment() {

    }

    public ScheduleDayFragment(List<ScheduleEventModel> scheduleEventModelList) {
        this.scheduleEventModelList = scheduleEventModelList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_day, container, false);

        scheduleRecyclerView = view.findViewById(R.id.schedule_recyclerView);

        scheduleRecyclerAdapter = new ScheduleRecyclerAdapter(scheduleEventModelList);

        scheduleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        scheduleRecyclerView.setItemAnimator(new DefaultItemAnimator());
        scheduleRecyclerView.setAdapter(scheduleRecyclerAdapter);

        return view;
    }

}
