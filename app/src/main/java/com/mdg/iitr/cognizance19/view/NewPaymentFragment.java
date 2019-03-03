package com.mdg.iitr.cognizance19.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.models.UserDetailsSPPResponseModel;
import com.mdg.iitr.cognizance19.models.UserSPPResponseModel;
import com.mdg.iitr.cognizance19.network.client.ApiClient;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.mdg.iitr.cognizance19.MainActivity.navController;

public class NewPaymentFragment extends Fragment {

    private PreferenceHelper preferenceHelper;
    private ApiClient apiClient;
    private boolean isDataFetched = false, isViewCreated = false;
    private UserDetailsSPPResponseModel cachedResponse;
    private ProgressDialog progressDialog;
    private Button initPaymentButton;
    private TextView paymentDoneTextView;

    public NewPaymentFragment() {
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
        View view = inflater.inflate(R.layout.fragment_new_payment, container, false);

        initPaymentButton = view.findViewById(R.id.initPaymentButton);
        paymentDoneTextView = view.findViewById(R.id.paymentDoneText);

        isViewCreated = true;
        if (isDataFetched) {
            populateViews(cachedResponse);
        }

        return view;
    }

    private void populateViewsFromDB() {
        progressDialog.setMessage("Fetching Payments Status...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        apiClient.getUserDetails(preferenceHelper.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserSPPResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserSPPResponseModel userSPPResponseModel) {
                        isDataFetched = true;
                        cachedResponse = userSPPResponseModel.getDetails();
                        if (isViewCreated) {
                            populateViews(userSPPResponseModel.getDetails());
                        }

                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "Error in fetching status.", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void populateViews(UserDetailsSPPResponseModel userDetailsSPPResponseModel) {

        if (userDetailsSPPResponseModel.getCentralPaymentStatus()) {
            paymentDoneTextView.setVisibility(View.VISIBLE);
            initPaymentButton.setVisibility(View.GONE);
        } else {
            paymentDoneTextView.setVisibility(View.GONE);
            initPaymentButton.setVisibility(View.VISIBLE);
        }

        initPaymentButton.setOnClickListener(v -> {
            navController.navigate(R.id.initPaymentFragment);
        });

    }


}
