package com.mdg.iitr.cognizance19.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.adapters.RegEventsAdapter;
import com.mdg.iitr.cognizance19.models.RegEventsModel;
import com.mdg.iitr.cognizance19.models.RegEventsResponse;
import com.mdg.iitr.cognizance19.network.client.ApiClient;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegEventsFragment extends Fragment {

    public static RegEventsAdapter adapter;
    private RecyclerView recyclerView;
    private PreferenceHelper preferenceHelper;
    private ApiClient apiClient;
    private List<RegEventsModel> regEventsList;
    private boolean isDataFetched = false, isViewCreated = false;
    private RegEventsResponse cachedResponse;
    private ProgressDialog progressDialog;

    public RegEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelper = new PreferenceHelper(getActivity());
        apiClient = new ApiClient();
        progressDialog = new ProgressDialog(getContext());
        populateViewsFromDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reg_events, container, false);

        recyclerView = view.findViewById(R.id.reg_events_recyclerview);

        isViewCreated = true;
        if(isDataFetched){
            populateViews(cachedResponse);
        }

        return view;
    }

    private void populateViewsFromDB(){
        progressDialog.setMessage("Fetching registered events...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        apiClient.fetchRegisteredEvents(preferenceHelper.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegEventsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegEventsResponse regEventsResponse) {
                        isDataFetched = true;
                        cachedResponse = regEventsResponse;
                        if(isViewCreated){
                            populateViews(regEventsResponse);
                        }

                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "Error in fetching Registered Events.", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void populateViews(RegEventsResponse regEventsResponse){
        int size = regEventsResponse.getEvents().size();
        regEventsList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (!regEventsResponse.getEvents().get(i).getType().equals("workshop")) {
                regEventsList.add(regEventsResponse.getEvents().get(i));
            }
        }
        adapter = new RegEventsAdapter(getActivity(),getContext(), regEventsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }


}
