package com.mdgiitr.karthik.cognizance19.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.WorkshopsAdapter;
import com.mdgiitr.karthik.cognizance19.models.WorkshopsModel;

import java.util.ArrayList;
import java.util.List;

public class WorkshopsFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<WorkshopsModel> list;
    private WorkshopsAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workshops, container, false);

        recyclerView = view.findViewById(R.id.workshops_recyclerview);
        list = new ArrayList<>();
        adapter = new WorkshopsAdapter(getContext(),list);

        for (int i=0; i<5;i++) {
            WorkshopsModel model = new WorkshopsModel();
            model.setWorkshopName("PowerDrift");
            model.setWorkshopCategory("#robotics,race-car");
            model.setWorkshopFollowers("100 followers");
            list.add(model);
        }
        adapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        return view;
    }
}
