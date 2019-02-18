package com.mdg.iitr.cognizance19.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.adapters.DepartmentalEventAdapter;
import com.mdg.iitr.cognizance19.models.Event;

import java.util.List;

import static com.mdg.iitr.cognizance19.MainActivity.DEPARTMENT_FLAG;

public class EventDepartmentalSpecificFragment extends Fragment {
    private TextView textView;
    private DepartmentalEventAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_departmental_list, container, false);
        DEPARTMENT_FLAG = 2;

        textView = view.findViewById(R.id.dept_nameTextView);
        recyclerView = view.findViewById(R.id.events_recyclerView);

        Bundle bundle = getArguments();
        textView.setText(bundle.getString("deptName"));

        adapter = new DepartmentalEventAdapter(getContext(), (List<Event>) bundle.getSerializable("eventList"));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    getFragmentManager().popBackStack("departmental", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    DEPARTMENT_FLAG = 1;
                    return true;
                }
                return false;
            }
        });

        return view;
    }

    public FragmentManager getFragManager(){
        return getFragmentManager();
    }

}
