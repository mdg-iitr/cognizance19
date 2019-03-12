package com.mdg.iitr.cognizance19.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.adapters.HomeMenuEventAdapter;
import com.mdg.iitr.cognizance19.adapters.HomeMenuTechtainmentAdapter;
import com.mdg.iitr.cognizance19.adapters.SpotLightMenuEventAdapter;
import com.mdg.iitr.cognizance19.models.HomeMenuEventModel;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.mdg.iitr.cognizance19.MainActivity.bottomNavigationView;
import static com.mdg.iitr.cognizance19.MainActivity.navController;

public class SpotLightMenuFragment extends Fragment {
    private RecyclerView panelDiscussionRecyclerView, techtainmentRecyclerView, guestLectureRecyclerView;
    private List<HomeMenuEventModel> panelList, techtainmentList, guestLectureList;
    private SpotLightMenuEventAdapter panelAdapter, techtainmentAdapter, guestLectureAdapter;
    private PreferenceHelper preferenceHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spotlight_menu, container, false);


        panelDiscussionRecyclerView = view.findViewById(R.id.home_menu_panel_discussion_recyclerview);
        techtainmentRecyclerView = view.findViewById(R.id.home_menu_techtainment_recyclerview);
        guestLectureRecyclerView = view.findViewById(R.id.home_menu_guest_lecture_recyclerview);


        preferenceHelper = new PreferenceHelper(getActivity());

        panelList = new ArrayList<>();
        techtainmentList = new ArrayList<>();
        guestLectureList = new ArrayList<>();

        panelAdapter = new SpotLightMenuEventAdapter(getContext(), panelList, 0);
        techtainmentAdapter = new SpotLightMenuEventAdapter(getContext(), techtainmentList, 0);
        guestLectureAdapter = new SpotLightMenuEventAdapter(getContext(), guestLectureList, 0);

        populatePanelDiscussionList();
        populateTechtainmentList();
        populateGuestLecture();

        bottomNavigationView.setVisibility(View.VISIBLE);
        return view;
    }

    private void populatePanelDiscussionList() {
/*        HomeMenuEventModel eventModel = new HomeMenuEventModel();
        eventModel.setEvent("Panel discussion");
        eventModel.setImgDrawable(getResources().getDrawable(R.drawable.fin_tech_f));
        panelList.add(eventModel);

        HomeMenuEventModel eventModel1 = new HomeMenuEventModel();
        eventModel1.setEvent("Techtainment");
        eventModel1.setImgDrawable(getResources().getDrawable(R.drawable.lit_af));
        panelList.add(eventModel1);

        HomeMenuEventModel eventModel2 = new HomeMenuEventModel();
        eventModel2.setEvent("Guest Lecture");
        eventModel2.setImgDrawable(getResources().getDrawable(R.drawable.lit_af));
        panelList.add(eventModel2);

        spotlightAdapter.notifyDataSetChanged();

        spotlightRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        spotlightRecyclerView.setAdapter(spotlightAdapter);*/
    }

    private void populateTechtainmentList() {
    }

    private void populateGuestLecture() {
    }

}
