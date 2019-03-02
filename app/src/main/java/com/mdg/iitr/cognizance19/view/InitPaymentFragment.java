package com.mdg.iitr.cognizance19.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import de.hdodenhof.circleimageview.CircleImageView;

public class InitPaymentFragment extends Fragment {

    ProgressDialog progressDialog = new ProgressDialog(getContext());
    Button male, female, acco, nonAcco;
    String currentGender = "male";
    public InitPaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_init_payment, container, false);
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


        return view;
    }

    private void doFinalRequest(String type) {

    }

}
