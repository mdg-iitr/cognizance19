package com.mdg.iitr.cognizance19.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.adapters.SpotLightMenuEventAdapter;
import com.mdg.iitr.cognizance19.models.SpotlightEventModel;
import com.mdg.iitr.cognizance19.models.SpotlightMenu;
import com.mdg.iitr.cognizance19.models.SpotlightResponse;
import com.mdg.iitr.cognizance19.models.SpotlightSchedule;
import com.mdg.iitr.cognizance19.network.client.ApiClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SpotlightDay1Fragment extends Fragment {

    private TextView panelDiscussionTextView, techtainmentTextView, guestLectureTextView;
    private RecyclerView panelDiscussionRecyclerView, techtainmentRecyclerView, guestLectureRecyclerView;
    private List<SpotlightEventModel> panelList, techtainmentList, guestLectureList;
    private SpotLightMenuEventAdapter panelAdapter, techtainmentAdapter, guestLectureAdapter;
    private ApiClient apiClient;
    private ProgressDialog progressDialog;
    private boolean isDataFetched = false, isViewCreated = false;
    private SpotlightResponse cachedResponse;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClient = new ApiClient();
        progressDialog = new ProgressDialog(getContext());
        populateViewFromDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spotlight_day1, container, false);

        panelDiscussionTextView = view.findViewById(R.id.home_menu_panel_discussion_textview);
        panelDiscussionRecyclerView = view.findViewById(R.id.home_menu_panel_discussion_recyclerview);
        techtainmentTextView = view.findViewById(R.id.home_menu_techtainment_textview);
        techtainmentRecyclerView = view.findViewById(R.id.home_menu_techtainment_recyclerview);
        guestLectureTextView = view.findViewById(R.id.home_menu_guest_lecture_textview);
        guestLectureRecyclerView = view.findViewById(R.id.home_menu_guest_lecture_recyclerview);


        isViewCreated = true;
        if (isDataFetched) {
            populateView(cachedResponse);
        }


        return view;
    }

    private void populateViewFromDB() {
        progressDialog.setMessage("Fetching spotlights...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        apiClient.fetchSpotlightEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpotlightResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SpotlightResponse response) {
                        isDataFetched = true;
                        cachedResponse = response;
                        if (isViewCreated) {
                            populateView(response);
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("spotlight", "Error occured while fetching spotlights: " + e);
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    private void populateView(SpotlightResponse response) {
        List<SpotlightSchedule> spotlightSchedule = response.getScheduleList();
        for (int i = 0; i < spotlightSchedule.size(); i++) {
            if (spotlightSchedule.get(i).getDay() == 1) {
                SpotlightMenu menu = spotlightSchedule.get(i).getMenu();
                panelList = new ArrayList<>();
                panelAdapter = new SpotLightMenuEventAdapter(getContext(), panelList);
                panelList.addAll(menu.getPanelDiscussion());
                if(panelList.isEmpty()){
                    panelDiscussionTextView.setVisibility(View.GONE);
                    panelDiscussionRecyclerView.setVisibility(View.GONE);
                } else{
                    panelDiscussionTextView.setVisibility(View.VISIBLE);
                    panelDiscussionRecyclerView.setVisibility(View.VISIBLE);
                }
                panelAdapter.notifyDataSetChanged();
                techtainmentList = new ArrayList<>();
                techtainmentAdapter = new SpotLightMenuEventAdapter(getContext(), techtainmentList);
                techtainmentList.addAll(menu.getTechtainment());
                if(techtainmentList.isEmpty()){
                    techtainmentTextView.setVisibility(View.GONE);
                    techtainmentRecyclerView.setVisibility(View.GONE);
                } else{
                    techtainmentTextView.setVisibility(View.VISIBLE);
                    techtainmentRecyclerView.setVisibility(View.VISIBLE);
                }
                techtainmentAdapter.notifyDataSetChanged();
                guestLectureList = new ArrayList<>();
                guestLectureAdapter = new SpotLightMenuEventAdapter(getContext(), guestLectureList);
                guestLectureList.addAll(menu.getGuestLecture());
                if(guestLectureList.isEmpty()){
                    guestLectureTextView.setVisibility(View.GONE);
                    guestLectureRecyclerView.setVisibility(View.GONE);
                } else{
                    guestLectureTextView.setVisibility(View.VISIBLE);
                    guestLectureRecyclerView.setVisibility(View.VISIBLE);
                }
                guestLectureAdapter.notifyDataSetChanged();
            }
        }
        panelDiscussionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        panelDiscussionRecyclerView.setAdapter(panelAdapter);
        techtainmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        techtainmentRecyclerView.setAdapter(techtainmentAdapter);
        guestLectureRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        guestLectureRecyclerView.setAdapter(guestLectureAdapter);
    }

}
