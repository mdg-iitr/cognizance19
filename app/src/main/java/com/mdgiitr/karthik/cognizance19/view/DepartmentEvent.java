package com.mdgiitr.karthik.cognizance19.view;

import android.app.ProgressDialog;
import android.os.Bundle;
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

public class DepartmentEvent extends Fragment {
    private final int NO_OF_COLUMNS = 2;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private ApiClient apiClient;
    private DepartmentalAdapter departmentalAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_departmental_fragment, container, false);

        return view;
    }

    private void test(){
        Fragment departmentEvent = new Fragment();

    }

}
