package com.mdgiitr.karthik.cognizance19.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.WorkshopAdapter;
import com.mdgiitr.karthik.cognizance19.models.Workshop;
import com.mdgiitr.karthik.cognizance19.models.WorkshopResponse;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WorkshopSlot1Fragment extends Fragment {
    private RecyclerView recyclerView;
    private WorkshopAdapter adapter;
    private ApiClient apiClient;
    private ProgressDialog progressDialog;
    private List<Workshop> workshopList;
    private boolean isDataFetched = false, isViewCreated = false;
    private WorkshopResponse cachedResponse;

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
        View view = inflater.inflate(R.layout.fragment_workshop_slot1, container, false);

        recyclerView = view.findViewById(R.id.workshop_slot1_recyclerview);

        isViewCreated = true;
        if(isDataFetched){
            populateView(cachedResponse);
        }

        return view;
    }

    private void populateViewFromDB(){
        progressDialog.setMessage("Fetching workshops...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        apiClient.fetchWorkshops()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WorkshopResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WorkshopResponse response) {
                        isDataFetched = true;
                        cachedResponse = response;
                        if(isViewCreated){
                            populateView(response);
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("workshop", "Error occured while fetching workshop: " + e);
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    private void populateView(WorkshopResponse response){
        workshopList = new ArrayList<>();
        adapter = new WorkshopAdapter(getContext(),workshopList);
        List<Workshop> allWorkshopList = response.getWorkshops();
        for (int i=0; i<allWorkshopList.size(); i++){
            Workshop workshop = allWorkshopList.get(i);
            if(workshop.getTags().equals("1")){
                workshopList.add(workshop);
                adapter.notifyDataSetChanged();
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onResume() {
        super.onResume();
        recyclerView.smoothScrollToPosition(0);
    }
}
