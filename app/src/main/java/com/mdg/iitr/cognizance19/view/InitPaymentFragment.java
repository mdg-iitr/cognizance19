package com.mdg.iitr.cognizance19.view;


import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.models.GeneralResponse;
import com.mdg.iitr.cognizance19.models.PaymentEvent;
import com.mdg.iitr.cognizance19.models.PaymentRequestModel;
import com.mdg.iitr.cognizance19.models.UserSPPResponseModel;
import com.mdg.iitr.cognizance19.models.VerifyDetailsRequestModel;
import com.mdg.iitr.cognizance19.network.client.ApiClient;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static com.mdg.iitr.cognizance19.MainActivity.navController;

public class InitPaymentFragment extends Fragment {

    ProgressDialog progressDialog;
    Button male, female, acco, nonAcco;
    String currentGender = "male";
    String mobile = "", referalCode = "";
    EditText mobileEditText, referalEditText;
    ApiClient apiClient;
    PreferenceHelper preferenceHelper;

    public InitPaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClient = new ApiClient();
        preferenceHelper = new PreferenceHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_init_payment, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);

        mobileEditText = view.findViewById(R.id.phoneNoEditText);
        referalEditText = view.findViewById(R.id.referralCodeEditText);

        mobileEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mobile = s.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        referalEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                referalCode = s.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);
        acco = view.findViewById(R.id.acco);
        nonAcco = view.findViewById(R.id.nonAcco);
        male.setBackground(getResources().getDrawable(R.drawable.blue_button_bg));
        female.setBackground(getResources().getDrawable(R.drawable.gray_button_bg));
        female.setOnClickListener(v -> {
            female.setBackground(getResources().getDrawable(R.drawable.blue_button_bg));
            male.setBackground(getResources().getDrawable(R.drawable.gray_button_bg));
            currentGender = "female";
            acco.setVisibility(View.VISIBLE);
            nonAcco.setVisibility(View.INVISIBLE);
        });
        male.setOnClickListener(v -> {
            female.setBackground(getResources().getDrawable(R.drawable.gray_button_bg));
            male.setBackground(getResources().getDrawable(R.drawable.blue_button_bg));
            currentGender = "male";
            acco.setVisibility(View.VISIBLE);
            nonAcco.setVisibility(View.VISIBLE);
        });


        acco.setOnClickListener(v -> doFinalRequest("acco"));
        nonAcco.setOnClickListener(v -> doFinalRequest("nonAcco"));

        return view;
    }

    private void doFinalRequest(String type) {

        PaymentEvent paymentEvent = new PaymentEvent();
        if (type.equals("acco")) {
            paymentEvent.setType("acco");
            paymentEvent.setAmount(2100);
            paymentEvent.setName("Registration with Accomodation");
        } else {
            paymentEvent.setType("non_acco");
            paymentEvent.setAmount(1400);
            paymentEvent.setName("Registration Without Accommodation - For Male Only");
        }
        PaymentRequestModel paymentRequestModel = new PaymentRequestModel();
        VerifyDetailsRequestModel verifyDetailsRequestModel = new VerifyDetailsRequestModel();
        if (currentGender.equals("male")) {
            paymentRequestModel.setGender("M");
            verifyDetailsRequestModel.setGender("M");
        } else {
            paymentRequestModel.setGender("F");
            verifyDetailsRequestModel.setGender("F");
        }
        paymentRequestModel.setType("central");
        paymentRequestModel.setReferalCode(referalCode);
        List<PaymentEvent> paymentEventsList = new ArrayList<PaymentEvent>();
        paymentEventsList.add(paymentEvent);
        paymentRequestModel.setEvents(paymentEventsList);
        verifyDetailsRequestModel.setMobile(mobile);

        progressDialog.show();
        apiClient.verifyDetails(preferenceHelper.getToken(), verifyDetailsRequestModel.getGender(), verifyDetailsRequestModel.getMobile())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        apiClient.getPaymentUrl(preferenceHelper.getToken(), paymentRequestModel)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<GeneralResponse>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(GeneralResponse responseBody) {
                                        progressDialog.dismiss();
                                        openPaymentCustomTab(responseBody.message);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Toast.makeText(getContext(), "Some error ocurred", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "Some error ocurred", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void openPaymentCustomTab(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(getContext(), Uri.parse(url));
    }

    @Override
    public void onResume() {
        super.onResume();
        progressDialog.show();
        apiClient.getUserDetails(preferenceHelper.getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserSPPResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserSPPResponseModel userSPPResponseModel) {
                        progressDialog.dismiss();
                        if (userSPPResponseModel.getDetails().getCentralPaymentStatus()) {
                            navController.navigateUp();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Some error ocurred", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
