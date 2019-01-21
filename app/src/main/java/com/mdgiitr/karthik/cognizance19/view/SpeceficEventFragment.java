package com.mdgiitr.karthik.cognizance19.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.SpeceficEventAdapter;

import java.util.ArrayList;
import java.util.List;

public class SpeceficEventFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<String> list;
    private SpeceficEventAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_specefic_event, container, false);

        recyclerView = view.findViewById(R.id.specefic_event_images_recyclerview);
        list = new ArrayList<>();
        adapter = new SpeceficEventAdapter(getContext(),list);

        for (int i=0; i<3; i++){
            list.add("a");
        }
        adapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);

        return view;
    }

}
