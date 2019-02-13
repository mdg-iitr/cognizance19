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
import com.mdgiitr.karthik.cognizance19.models.CenterstageOrDepartmentalEventsResponse;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EventCenterStageFragment extends Fragment {
    private RecyclerView recyclerView;
    private CenterstageAdapter adapter;
    private ApiClient apiClient;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_center_stage, container, false);

        apiClient = new ApiClient();
        progressDialog = new ProgressDialog(getContext());

        recyclerView = view.findViewById(R.id.center_stage_recyclerview);

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
                        adapter = new CenterstageAdapter(getContext(), response.getCenterstage());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(adapter);
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

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.smoothScrollToPosition(0);
    }
}
