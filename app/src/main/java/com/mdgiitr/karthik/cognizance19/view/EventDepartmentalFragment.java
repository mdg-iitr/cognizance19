package com.mdgiitr.karthik.cognizance19.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.DepartmentalAdapter;
import com.mdgiitr.karthik.cognizance19.models.CenterstageOrDepartmentalEventsResponse;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.mdgiitr.karthik.cognizance19.MainActivity.SCREEN_WIDTH;

public class EventDepartmentalFragment extends Fragment {
    private final int NO_OF_COLUMNS = 2;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private ApiClient apiClient;
    private DepartmentalAdapter departmentalAdapter;
    private boolean isDataFetched = false, isViewCreated = false;
    private CenterstageOrDepartmentalEventsResponse cachedResponse;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(getContext());
        apiClient = new ApiClient();
        populateViewsFromDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_departmental, container, false);

        recyclerView = view.findViewById(R.id.departmental_recyclerview);

        isViewCreated = true;

        if (isDataFetched) {
            populateViews(cachedResponse);
        }

        return view;
    }

    public void populateViews(CenterstageOrDepartmentalEventsResponse response){
        departmentalAdapter = new DepartmentalAdapter(getContext(), response.getDepartmental(), SCREEN_WIDTH, getFragmentManager());
        recyclerView.setAdapter(departmentalAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), NO_OF_COLUMNS));
    }

    public void populateViewsFromDB(){
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
                        cachedResponse = response;
                        isDataFetched = true;
                        if(isViewCreated){
                            populateViews(response);
                        }
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



    @Override
    public void onResume() {
        super.onResume();
        recyclerView.smoothScrollToPosition(0);
    }
}
