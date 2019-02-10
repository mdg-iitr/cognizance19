package com.mdgiitr.karthik.cognizance19.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.NavOptions;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class CompleteYourProfileFragment extends Fragment {

    private EditText nameEditText, roomNoEditText, contactNoEditText, branchEditText, pincodeEditText;
    private Spinner yearSpinner, genderSpinner;
    private AutoCompleteTextView stateAutoCompleteTextView, cityAutoCompleteTextView, collegeAutoCompleteTextView;
    private ImageView backIcon;
    private Button continueButton;
    private String name = "",
            state = "",
            city = "",
            college = "",
            address = "",
            mobile = "",
            branch = "",
            year = "",
            pincode = "",
            gender = "";
    private List<String> genderList, yearList;

    private ApiClient apiClient;
    private PreferenceHelper preferenceHelper;

    public CompleteYourProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complete_your_profile, container, false);

        apiClient = new ApiClient();
        preferenceHelper = new PreferenceHelper(getActivity());

        genderList = new ArrayList<>();
        genderList.add("M");
        genderList.add("F");
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, genderList);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        yearList = new ArrayList<>();
        yearList.add("First");
        yearList.add("Second");
        yearList.add("Third");
        yearList.add("Fourth");
        yearList.add("Fifth");
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yearList);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        backIcon = view.findViewById(R.id.back_arrow_complete_your_profile);
        nameEditText = view.findViewById(R.id.full_name_editText);
        roomNoEditText = view.findViewById(R.id.room_no_editText);
        contactNoEditText = view.findViewById(R.id.contact_no_editText);
        branchEditText = view.findViewById(R.id.branch_editText);
        pincodeEditText = view.findViewById(R.id.pincode_editText);
        stateAutoCompleteTextView = view.findViewById(R.id.select_state);
        cityAutoCompleteTextView = view.findViewById(R.id.select_city);
        collegeAutoCompleteTextView = view.findViewById(R.id.select_college);
        yearSpinner = view.findViewById(R.id.spinner_year);
        genderSpinner = view.findViewById(R.id.gender_spinner);
        continueButton = view.findViewById(R.id.cont_Button);

        genderSpinner.setAdapter(genderAdapter);
        yearSpinner.setAdapter(yearAdapter);
        genderSpinner.setSelection(0);
        yearSpinner.setSelection(0);

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item, getActivity().getResources().getStringArray(R.array.Select_State));
        stateAutoCompleteTextView.setThreshold(1);
        stateAutoCompleteTextView.setAdapter(stateAdapter);
        cityAutoCompleteTextView.setThreshold(1);

        stateAutoCompleteTextView.setOnItemClickListener((parent, view1, position, id) -> {
            cityAutoCompleteTextView.setEnabled(true);
            String selection = (String) parent.getItemAtPosition(position);
            String a[] = {""};
            switch (selection) {
                case "Andaman and Nicobar Islands":
                    a = getActivity().getResources().getStringArray(R.array.andaman_city);
                    break;
                case "Arunachal Pradesh":
                    a = getActivity().getResources().getStringArray(R.array.arunachal_pradesh_city);
                    break;
                case "Andhra Pradesh & Telangana":
                    a = getActivity().getResources().getStringArray(R.array.andhra_pradesh_city);
                    break;
                case "Assam":
                    a = getActivity().getResources().getStringArray(R.array.assam_city);
                    break;
                case "Bihar":
                    a = getActivity().getResources().getStringArray(R.array.bihar_city);
                    break;
                case "Chandigarh":
                    a = getActivity().getResources().getStringArray(R.array.chandigarh_city);
                    break;
                case "Chattisgarh":
                    a = getActivity().getResources().getStringArray(R.array.chattisgarh_city);
                    break;
                case "Dadra and Nagar Haveli":
                    a = getActivity().getResources().getStringArray(R.array.dadra_nagar_city);
                    break;
                case "Daman and Diu":
                    a = getActivity().getResources().getStringArray(R.array.daman_diu_city);
                    break;
                case "Delhi":
                    a = getActivity().getResources().getStringArray(R.array.delhi_city);
                    break;
                case "Goa":
                    a = getActivity().getResources().getStringArray(R.array.goa_city);
                    break;
                case "Gujarat":
                    a = getActivity().getResources().getStringArray(R.array.gujarat_city);
                    break;
                case "Haryana":
                    a = getActivity().getResources().getStringArray(R.array.haryana_city);
                    break;
                case "Himachal Pradesh":
                    a = getActivity().getResources().getStringArray(R.array.himachal_city);
                    break;
                case "Jammu and Kashmir":
                    a = getActivity().getResources().getStringArray(R.array.jammu_kashmir_city);
                    break;
                case "Jharkhand":
                    a = getActivity().getResources().getStringArray(R.array.jharkhand_city);
                    break;
                case "Karnataka":
                    a = getActivity().getResources().getStringArray(R.array.karnataka_city);
                    break;
                case "Kerala":
                    a = getActivity().getResources().getStringArray(R.array.kerala_city);
                    break;
                case "Lakshadweep":
                    a = getActivity().getResources().getStringArray(R.array.lakshadweep_city);
                    break;
                case "Madhya Pradesh":
                    a = getActivity().getResources().getStringArray(R.array.madhya_pradesh_city);
                    break;
                case "Maharashtra":
                    a = getActivity().getResources().getStringArray(R.array.maharashtra_city);
                    break;
                case "Manipur":
                    a = getActivity().getResources().getStringArray(R.array.manipur_city);
                    break;
                case "Meghalaya":
                    a = getActivity().getResources().getStringArray(R.array.meghalaya_city);
                    break;
                case "Mizoram":
                    a = getActivity().getResources().getStringArray(R.array.mizoram_city);
                    break;
                case "Nagaland":
                    a = getActivity().getResources().getStringArray(R.array.nagaland_city);
                    break;
                case "Odisha":
                    a = getActivity().getResources().getStringArray(R.array.odisha_city);
                    break;
                case "Puducherry":
                    a = getActivity().getResources().getStringArray(R.array.puducherry_city);
                    break;
                case "Punjab":
                    a = getActivity().getResources().getStringArray(R.array.punjab_city);
                    break;
                case "Rajasthan":
                    a = getActivity().getResources().getStringArray(R.array.rajasthan_city);
                    break;
                case "Sikkim":
                    a = getActivity().getResources().getStringArray(R.array.sikkim_city);
                    break;
                case "Tamil Nadu":
                    a = getActivity().getResources().getStringArray(R.array.tamil_nadu_city);
                    break;
                case "Tripura":
                    a = getActivity().getResources().getStringArray(R.array.tripura_city);
                    break;
                case "Uttar Pradesh":
                    a = getActivity().getResources().getStringArray(R.array.uttar_pradesh_city);
                    break;
                case "Uttarakhand":
                    a = getActivity().getResources().getStringArray(R.array.uttarakhand_city);
                    break;
                case "West Bengal":
                    a = getActivity().getResources().getStringArray(R.array.west_bengal_city);
                    break;

            }
            ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item, a);
            cityAutoCompleteTextView.setAdapter(cityAdapter);
        });

        backIcon.setOnClickListener(v -> navController.navigateUp());

        continueButton.setOnClickListener(v -> updateDetails());

        return view;
    }

    private void updateDetails() {

        if (nameEditText.getText() != null) {
            name = nameEditText.getText().toString().trim();
        }
        if (roomNoEditText.getText() != null) {
            address = roomNoEditText.getText().toString().trim();
        }
        if (pincodeEditText.getText() != null) {
            pincode = pincodeEditText.getText().toString().trim();
        }
        if (stateAutoCompleteTextView.getText() != null) {
            state = stateAutoCompleteTextView.getText().toString();
        }
        if (collegeAutoCompleteTextView.getText() != null) {
            college = collegeAutoCompleteTextView.getText().toString();
        }
        if (cityAutoCompleteTextView.getText() != null) {
            city = cityAutoCompleteTextView.getText().toString();
        }
        if (contactNoEditText.getText() != null) {
            mobile = contactNoEditText.getText().toString().trim();
        }
        if (branchEditText.getText() != null) {
            branch = branchEditText.getText().toString().trim();
        }
        gender = genderSpinner.getSelectedItem().toString();
        year = yearSpinner.getSelectedItem().toString();

        apiClient.updateUserDetails(preferenceHelper.getToken(), state, college, address, city, gender, year, branch, pincode, mobile)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        NavOptions navOptions = new NavOptions.Builder()
                                .setPopUpTo(R.id.landingFragment2, true)
                                .build();
                        preferenceHelper.setLoginStatus(true);
                        navController.navigate(R.id.action_completeYourProfileFragment_to_homeMenuFragment, null, navOptions);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "Unable to complete details.", Toast.LENGTH_SHORT).show();
                        Log.d("COMPLETE_ERROR", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
