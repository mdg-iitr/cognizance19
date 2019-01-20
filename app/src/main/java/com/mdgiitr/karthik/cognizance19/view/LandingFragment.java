package com.mdgiitr.karthik.cognizance19.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mdgiitr.karthik.cognizance19.R;

import androidx.navigation.Navigation;


public class LandingFragment extends Fragment {

    private Button loginButton, registerButton;

    public LandingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landing, container, false);

        loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener((View v) -> {
            Bundle bundle = new Bundle();
            bundle.putInt("tabToOpen", 0);
            Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment).navigate(R.id.action_landingFragment2_to_userLoginFragment, bundle);
        });

        registerButton = view.findViewById(R.id.register_button);
        registerButton.setOnClickListener((View v) -> {
            Bundle bundle = new Bundle();
            bundle.putInt("tabToOpen", 1);
            Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment).navigate(R.id.action_landingFragment2_to_userLoginFragment, bundle);
        });

        return view;
    }

}
