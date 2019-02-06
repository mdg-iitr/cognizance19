package com.mdgiitr.karthik.cognizance19.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.HomeMenuEventAdapter;
import com.mdgiitr.karthik.cognizance19.adapters.HomeMenuTechtainmentAdapter;
import com.mdgiitr.karthik.cognizance19.adapters.HomeMenuWorkshopAdapter;
import com.mdgiitr.karthik.cognizance19.models.HomeMenuEventModel;
import com.mdgiitr.karthik.cognizance19.models.HomeMenuWorkshopResponse;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class HomeMenuFragment extends Fragment {
    private RecyclerView eventRecyclerView, workshopRecyclerView, techtainmentRecyclerView, guestRecyclerView;
    private CircleImageView smallImageView;
    private List<HomeMenuEventModel> eventList;
    private List<String> techtainmentURL, guestURL;
    private HomeMenuEventAdapter eventAdapter;
    private HomeMenuWorkshopAdapter workshopAdapter;
    private HomeMenuTechtainmentAdapter techtainmentAdapter, guestAdapter;
    private ApiClient apiClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_menu, container, false);

        apiClient = new ApiClient();

        eventRecyclerView = view.findViewById(R.id.home_menu_event_recyclerview);
        workshopRecyclerView = view.findViewById(R.id.home_menu_workshop_recyclerview);
        techtainmentRecyclerView = view.findViewById(R.id.home_menu_techtainment_recyclerview);
        guestRecyclerView = view.findViewById(R.id.home_menu_guest_recyclerview);
        smallImageView = view.findViewById(R.id.small_profile_image);

        eventList = new ArrayList<>();
        techtainmentURL = new ArrayList<>();
        guestURL = new ArrayList<>();

        eventAdapter = new HomeMenuEventAdapter(getContext(), eventList);

        techtainmentAdapter = new HomeMenuTechtainmentAdapter(getContext(), techtainmentURL);
        guestAdapter = new HomeMenuTechtainmentAdapter(getContext(), guestURL);

        HomeMenuEventModel eventModel = new HomeMenuEventModel();
        eventModel.setEvent("Center Stage");
        eventList.add(eventModel);

        HomeMenuEventModel eventModel1 = new HomeMenuEventModel();
        eventModel1.setEvent("Departmental");
        eventList.add(eventModel1);

        eventAdapter.notifyDataSetChanged();

        eventRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        eventRecyclerView.setAdapter(eventAdapter);

        apiClient.fetchWorkshops()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeMenuWorkshopResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeMenuWorkshopResponse homeMenuWorkshopResponse) {
                        Log.d("workshop", homeMenuWorkshopResponse.getMessage());
                        workshopAdapter = new HomeMenuWorkshopAdapter(getContext(), homeMenuWorkshopResponse.getWorkshopsModelList());
                        workshopRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        workshopRecyclerView.setAdapter(workshopAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("workshop", "Error occured while fetching workshop: " + e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });


        techtainmentURL.add("hello");
        techtainmentURL.add("hi");
        techtainmentAdapter.notifyDataSetChanged();

        techtainmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        techtainmentRecyclerView.setAdapter(techtainmentAdapter);

        guestURL.add("hello");
        guestURL.add("hi");
        guestAdapter.notifyDataSetChanged();

        guestRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        guestRecyclerView.setAdapter(guestAdapter);

        smallImageView.setOnClickListener(v -> navController.navigate(R.id.action_homeMenuFragment_to_myProfileFragment));

        return view;
    }
}
