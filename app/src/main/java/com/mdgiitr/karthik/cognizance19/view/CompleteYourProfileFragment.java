package com.mdgiitr.karthik.cognizance19.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.mdgiitr.karthik.cognizance19.R;

public class CompleteYourProfileFragment extends Fragment {

    private EditText nameEditText, roomNoEditText, contactNoEditText;
    private AutoCompleteTextView stateAutoCompleteTextView, cityAutoCompleteTextView, collegeAutoCompleteTextView;

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

        nameEditText = view.findViewById(R.id.full_name_editText);
        roomNoEditText = view.findViewById(R.id.room_no_editText);
        contactNoEditText = view.findViewById(R.id.contact_no_editText);
        stateAutoCompleteTextView = view.findViewById(R.id.select_state);
        cityAutoCompleteTextView = view.findViewById(R.id.select_city);
        collegeAutoCompleteTextView = view.findViewById(R.id.select_college);

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item, getActivity().getResources().getStringArray(R.array.Select_State));
        stateAutoCompleteTextView.setThreshold(1);
        stateAutoCompleteTextView.setAdapter(stateAdapter);
        cityAutoCompleteTextView.setThreshold(1);

        stateAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
            }
        });

        return view;
    }

}
