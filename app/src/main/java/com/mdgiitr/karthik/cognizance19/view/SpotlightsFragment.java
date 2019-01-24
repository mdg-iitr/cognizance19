package com.mdgiitr.karthik.cognizance19.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.SpotlightsVerticalRVAdapter;
import com.mdgiitr.karthik.cognizance19.models.VerticalItemsModel;

import java.util.ArrayList;
import java.util.List;

public class SpotlightsFragment extends Fragment {

    private SpotlightsVerticalRVAdapter adapter;
    private RecyclerView verticalRV;
    private List<VerticalItemsModel> mItemsList;

    public SpotlightsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.spotlights_fragment, container, false);
        verticalRV = (RecyclerView)view.findViewById(R.id.vertical_spotlight_RV);
        mItemsList = new ArrayList<>();

        verticalRV.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        verticalRV.setLayoutManager(llm);

        adapter = new SpotlightsVerticalRVAdapter(getActivity(), mItemsList);
        verticalRV.setAdapter(adapter);
        return view;
    }

}
