package com.mdg.iitr.cognizance19.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdg.iitr.cognizance19.R;

import static com.mdg.iitr.cognizance19.MainActivity.bottomNavigationView;

public class SponsorsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faqs, container, false);



        bottomNavigationView.setVisibility(View.VISIBLE);
        return view;
    }

}
