package com.mdgiitr.karthik.cognizance19.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.CenterstageAdapter;
import com.mdgiitr.karthik.cognizance19.models.Centerstage;
import com.mdgiitr.karthik.cognizance19.models.CenterstageOrDepartmentalEventsResponse;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EventCenterStageFragment extends Fragment {
    private RecyclerView recyclerView;
    private CenterstageAdapter adapter;
    private ApiClient apiClient;
    private ProgressDialog progressDialog;
    private List<Centerstage> list;
    private boolean dataFetched = false;
    private boolean isViewCreated = false;
    private CenterstageOrDepartmentalEventsResponse cachedResponse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(getContext());
        apiClient = new ApiClient();
        populateViewsFromDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_center_stage, container, false);
        recyclerView = view.findViewById(R.id.center_stage_recyclerview);

            isViewCreated = true;

            if (dataFetched) {
                populateViews(cachedResponse);
            }

        return view;
    }

    private void populateViewsFromDB(){
        progressDialog.setMessage("Fetching events...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        apiClient.fetchEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CenterstageOrDepartmentalEventsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CenterstageOrDepartmentalEventsResponse response) {
                        dataFetched = true;
                        cachedResponse = response;
                        if (isViewCreated)
                            populateViews(response);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void populateViews(CenterstageOrDepartmentalEventsResponse response){
        list = new ArrayList<>();
        adapter = new CenterstageAdapter(getContext(),list);
        List<Centerstage> allEventList = response.getCenterstage();
        for (int i=0; i<allEventList.size();i++){
            Centerstage centerstage = allEventList.get(i);
            if(!centerstage.getName().equals("Fin Fest") && !centerstage.getName().equals("LIT.A.F.")){
                list.add(centerstage);
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
