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
import com.mdg.iitr.cognizance19.models.HomeMenuEventModel;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.mdg.iitr.cognizance19.MainActivity.bottomNavigationView;
import static com.mdg.iitr.cognizance19.MainActivity.navController;

public class HomeMenuFragment extends Fragment {
    private RecyclerView eventRecyclerView, attractionRecyclerView, whatsNewRecyclerView, panelRecyclerView, techtainmentRecyclerView, guestRecyclerView;
    private CircleImageView smallImageView;
    private List<HomeMenuEventModel> eventList, attractionList, whatsNewList, panelList;
    private List<String> techtainmentURL, guestURL;
    private HomeMenuEventAdapter eventAdapter, attractionAdapter, whatsNewAdapter, panelAdapter;
    private HomeMenuTechtainmentAdapter techtainmentAdapter, guestAdapter;
    private PreferenceHelper preferenceHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_menu, container, false);


        eventRecyclerView = view.findViewById(R.id.home_menu_event_recyclerview);
        attractionRecyclerView = view.findViewById(R.id.home_menu_attraction_recyclerview);
        whatsNewRecyclerView = view.findViewById(R.id.home_menu_whats_new_recyclerview);
        panelRecyclerView = view.findViewById(R.id.home_menu_panel_discussion_recyclerview);
        techtainmentRecyclerView = view.findViewById(R.id.home_menu_techtainment_recyclerview);
        guestRecyclerView = view.findViewById(R.id.home_menu_guest_recyclerview);
        smallImageView = view.findViewById(R.id.small_profile_image);

        preferenceHelper = new PreferenceHelper(getActivity());

        eventList = new ArrayList<>();
        attractionList = new ArrayList<>();
        whatsNewList = new ArrayList<>();
        panelList = new ArrayList<>();
        techtainmentURL = new ArrayList<>();
        guestURL = new ArrayList<>();

        eventAdapter = new HomeMenuEventAdapter(getContext(), eventList,0);
        attractionAdapter = new HomeMenuEventAdapter(getContext(), attractionList,1);
        whatsNewAdapter = new HomeMenuEventAdapter(getContext(), whatsNewList,0);
        panelAdapter = new HomeMenuEventAdapter(getContext(),panelList,0);

        techtainmentAdapter = new HomeMenuTechtainmentAdapter(getContext(), techtainmentURL);
        guestAdapter = new HomeMenuTechtainmentAdapter(getContext(), guestURL);

        populateEventList();
        populateAttractionList();
        populateWhatsNewList();
        populatePanelDiscussionList();

        techtainmentURL.add("a");
        techtainmentURL.add("b");
        techtainmentURL.add("c");
        techtainmentAdapter.notifyDataSetChanged();

        techtainmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        techtainmentRecyclerView.setAdapter(techtainmentAdapter);

        guestURL.add("a");
        guestURL.add("b");
        guestURL.add("c");
        guestAdapter.notifyDataSetChanged();

        guestRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        guestRecyclerView.setAdapter(guestAdapter);

        smallImageView.setOnClickListener(v -> navController.navigate(R.id.action_homeMenuFragment_to_myProfileFragment));
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.com_facebook_profile_picture_blank_square)
                .error(R.drawable.com_facebook_profile_picture_blank_square);
        Glide.with(this)
                .load(preferenceHelper.getProfilePicURL())
                .apply(options)
                .into(smallImageView);

        bottomNavigationView.setVisibility(View.VISIBLE);
        return view;
    }

    private void populateEventList() {
        HomeMenuEventModel eventModel = new HomeMenuEventModel();
        eventModel.setEvent("Centerstage");
        eventModel.setImgDrawable(getResources().getDrawable(R.drawable.centerstage_f));
        eventList.add(eventModel);

        HomeMenuEventModel eventModel1 = new HomeMenuEventModel();
        eventModel1.setEvent("Departmental");
        eventModel1.setImgDrawable(getResources().getDrawable(R.drawable.departmental));
        eventList.add(eventModel1);

        HomeMenuEventModel eventModel2 = new HomeMenuEventModel();
        eventModel2.setEvent("Online");
        eventList.add(eventModel2);

        eventAdapter.notifyDataSetChanged();

        eventRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        eventRecyclerView.setAdapter(eventAdapter);
    }

    private void populateAttractionList() {
        HomeMenuEventModel eventModel = new HomeMenuEventModel();
        eventModel.setEvent("Workshops");
        eventModel.setImgDrawable(getResources().getDrawable(R.drawable.workshop));
        attractionList.add(eventModel);

        HomeMenuEventModel eventModel1 = new HomeMenuEventModel();
        eventModel1.setEvent("Exhibitions");
        eventModel1.setImgDrawable(getResources().getDrawable(R.drawable.exhibition));
        attractionList.add(eventModel1);

        attractionAdapter.notifyDataSetChanged();

        attractionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        attractionRecyclerView.setAdapter(attractionAdapter);
    }

    private void populateWhatsNewList(){
        HomeMenuEventModel eventModel = new HomeMenuEventModel();
        eventModel.setEvent("FinFest");
        eventModel.setImgDrawable(getResources().getDrawable(R.drawable.fin_tech_f));
        whatsNewList.add(eventModel);

        HomeMenuEventModel eventModel1 = new HomeMenuEventModel();
        eventModel1.setEvent("Lit.A.F");
        eventModel1.setImgDrawable(getResources().getDrawable(R.drawable.lit_af));
        whatsNewList.add(eventModel1);

        whatsNewAdapter.notifyDataSetChanged();

        whatsNewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        whatsNewRecyclerView.setAdapter(whatsNewAdapter);
    }

    private void populatePanelDiscussionList(){
        for(int i=0; i<5; i++){
            HomeMenuEventModel eventModel = new HomeMenuEventModel();
            eventModel.setEvent("Lorem Ipsum");
            panelList.add(eventModel);
            panelAdapter.notifyDataSetChanged();
        }

        panelRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        panelRecyclerView.setAdapter(panelAdapter);
    }
}
