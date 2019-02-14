package com.mdgiitr.karthik.cognizance19.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.GeneralResponse;
import com.mdgiitr.karthik.cognizance19.models.UserDetailsSPPResponseModel;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class EditProfileFragment extends Fragment {

    private EditText name, address, city, pincode, contact;
    private Button updateButton;
    private ImageView backIcon;
    private String nameStr = "", mobileStr = "", pincodeStr = "", addressStr = "", cityStr = "";
    private PreferenceHelper preferenceHelper;
    private ApiClient apiClient;

    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        preferenceHelper = new PreferenceHelper(getActivity());
        apiClient = new ApiClient();

        name = view.findViewById(R.id.full_name_editText);
        address = view.findViewById(R.id.room_no_editText);
        city = view.findViewById(R.id.select_city);
        pincode = view.findViewById(R.id.pincode_editText);
        contact = view.findViewById(R.id.contact_no_editText);
        updateButton = view.findViewById(R.id.update_Button);
        backIcon = view.findViewById(R.id.back_arrow_complete_your_profile);

        UserDetailsSPPResponseModel userDetailsSPPResponseModel = getArguments().getParcelable("profile");
        populateViews(userDetailsSPPResponseModel);

        backIcon.setOnClickListener(v -> navController.navigateUp());

        return view;
    }

    private void populateViews(UserDetailsSPPResponseModel userDetailsSPPResponseModel) {

        if (userDetailsSPPResponseModel.getName() != null) {
            nameStr = userDetailsSPPResponseModel.getName().toString();
            name.setText(nameStr);
        }
        if (userDetailsSPPResponseModel.getAddress() != null) {
            addressStr = userDetailsSPPResponseModel.getAddress();
            address.setText(addressStr);
        }
        if (userDetailsSPPResponseModel.getCity() != null) {
            cityStr = userDetailsSPPResponseModel.getCity();
            city.setText(cityStr);
        }
        if (userDetailsSPPResponseModel.getPincode() != null) {
            pincodeStr = userDetailsSPPResponseModel.getPincode();
            pincode.setText(pincodeStr);
        }
        if (userDetailsSPPResponseModel.getMobile() != null) {
            mobileStr = userDetailsSPPResponseModel.getMobile();
            contact.setText(mobileStr);
        }
        updateButton.setOnClickListener(v -> updateDetails());

    }

    private void updateDetails() {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Updating. Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (name.getText() != null && !name.getText().toString().trim().isEmpty())
            nameStr = name.getText().toString().trim();
        if (contact.getText() != null && !contact.getText().toString().trim().isEmpty())
            mobileStr = contact.getText().toString().trim();
        if (pincode.getText() != null && !pincode.getText().toString().trim().isEmpty())
            pincodeStr = pincode.getText().toString().trim();
        if (address.getText() != null && !address.getText().toString().trim().isEmpty())
            addressStr = address.getText().toString().trim();
        if (city.getText() != null && !city.getText().toString().trim().isEmpty())
            cityStr = city.getText().toString().trim();

        apiClient.updateDetails(preferenceHelper.getToken(), nameStr, cityStr, addressStr, pincodeStr, mobileStr)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), generalResponse.message, Toast.LENGTH_SHORT).show();
                        navController.navigateUp();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        handleUpdateError(e);
                        navController.navigateUp();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void handleUpdateError(Throwable throwable) {

        try {

            Log.d("UPDATE_ERROR", throwable.toString());

        } catch (Exception e) {
            Log.d("UPDATE_ERROR", e.toString());
        }

    }

}
