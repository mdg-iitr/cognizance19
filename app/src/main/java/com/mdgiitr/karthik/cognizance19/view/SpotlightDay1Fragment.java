package com.mdgiitr.karthik.cognizance19.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.SpotlightAdapter;
import com.mdgiitr.karthik.cognizance19.models.SpotlightGuestModel;
import com.mdgiitr.karthik.cognizance19.models.SpotlightModel;

import java.util.ArrayList;
import java.util.List;

public class SpotlightDay1Fragment extends Fragment {
    private RecyclerView recyclerView;
    private List<SpotlightModel> list;
    private SpotlightAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spotlight_day1, container, false);

        recyclerView = view.findViewById(R.id.spotlight_day1_recyclerview);
        list = new ArrayList<>();
        adapter = new SpotlightAdapter(getContext(),list);

        SpotlightModel day1model1  = new SpotlightModel();
        day1model1.setCategory("Guest Lectures");
        List<SpotlightGuestModel> guestList = new ArrayList<>();
        for (int i=0; i<5;i++){
            SpotlightGuestModel model = new SpotlightGuestModel();
            model.setGuestName("Manohar Parrikar");
            model.setGuestDesignation("Raksha Mantri");
            guestList.add(model);
        }
        day1model1.setGuestList(guestList);
        list.add(day1model1);


        SpotlightModel day1model2  = new SpotlightModel();
        day1model2.setCategory("Panel Discussion");
        List<SpotlightGuestModel> guestlist = new ArrayList<>();
        for (int i=0; i<5;i++){
            SpotlightGuestModel model = new SpotlightGuestModel();
            model.setGuestName("Mayur Naik");
            guestlist.add(model);
        }
        day1model2.setGuestList(guestlist);
        list.add(day1model2);

        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
