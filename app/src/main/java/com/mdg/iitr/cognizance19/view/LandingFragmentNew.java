package com.mdg.iitr.cognizance19.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import static com.mdg.iitr.cognizance19.MainActivity.bottomNavigationView;
import static com.mdg.iitr.cognizance19.MainActivity.navController;


public class LandingFragmentNew extends Fragment {

    private Button loginButton, registerButton, register2Button;
    private PreferenceHelper preferenceHelper;
    public static int whatPressed = 0;

    public LandingFragmentNew() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landing_new, container, false);

        preferenceHelper = new PreferenceHelper(getActivity());

        loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_landingFragmentNew_to_landingFragment2));
        loginButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                whatPressed = 1;
                return false;
            }
        });

        registerButton = view.findViewById(R.id.register_button);
        registerButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_landingFragmentNew_to_scheduleFragment));

        register2Button = view.findViewById(R.id.register2_button);
        register2Button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_landingFragmentNew_to_spotLightFragment));

        checkUserLoggedIn();

        bottomNavigationView.setVisibility(View.GONE);
        return view;
    }

    private void checkUserLoggedIn() {

        if (preferenceHelper.getLoginStatus()) {
            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.landingFragment2, true)
                    .build();
            navController.navigate(R.id.action_landingFragment2_to_homeMenuFragment2, null, navOptions);
        }

    }

}
