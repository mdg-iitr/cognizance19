package com.mdgiitr.karthik.cognizance19.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.RegEventsTabFragmentRVAdapter;
import com.mdgiitr.karthik.cognizance19.models.RegEventsModel;

import java.util.ArrayList;
import java.util.List;

public class RegEventsTabFragment extends Fragment {

    private List<RegEventsModel> mdata = new ArrayList<>();
    private RecyclerView recycler_view;
    private RegEventsTabFragmentRVAdapter adapter;

    public RegEventsTabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reg_events_tab_fragment, container, false);

//        Button button = (Button) view.findViewById(R.id.reg_events_tab_RV_button);
//        button.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                PopupMenu popup = new PopupMenu(getActivity(), view);
//                popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) getActivity());
//                popup.inflate(R.menu.my_profile_events_menu);
//
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        switch (menuItem.getItemId()) {
//
//                            case R.id.unregister:
//                                Toast.makeText(getActivity(), "Working Fine!!", Toast.LENGTH_SHORT).show();
//                                return true;
//                            case R.id.manage_team:
//                                Toast.makeText(getActivity(), "Working Fine !!", Toast.LENGTH_SHORT).show();
//                                return true;
//                            default:
//                                return false;
//                        }
//                    }
//
//                });
//
//                popup.show();
//
//            }
//        });
//        recycler_view = (RecyclerView) view.findViewById(R.id.reg_events_tab_RV);
//        recycler_view.setHasFixedSize(true);
//        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//        recycler_view.setLayoutManager(llm);
//
//        adapter = new RegEventsTabFragmentRVAdapter(getActivity(), mdata);
//        recycler_view.setAdapter(adapter);
//        populateRegEventModelList();
        return view;
    }

    private void populateRegEventModelList(){
        RegEventsModel powerdrift = new RegEventsModel("Powerdrift");
        RegEventsModel powerdrift_2 = new RegEventsModel("Powerdrift_2");

        mdata.add(powerdrift);
        mdata.add(powerdrift_2);
        adapter.notifyDataSetChanged();

    }

}
