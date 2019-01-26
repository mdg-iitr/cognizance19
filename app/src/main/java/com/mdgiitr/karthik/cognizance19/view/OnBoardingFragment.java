package com.mdgiitr.karthik.cognizance19.view;


import android.os.Bundle;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class OnBoardingFragment extends Fragment {

    private CircleImageView smallImageView;
    private MaterialCardView completeProfileCardView;
    private PreferenceHelper preferenceHelper;
    private Button contButton;

    public OnBoardingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onboarding, container, false);

        preferenceHelper = new PreferenceHelper(getActivity());

        contButton = view.findViewById(R.id.onboarding_continue_button);
        completeProfileCardView = view.findViewById(R.id.complete_you_profile_cardView);

        completeProfileCardView.setOnClickListener((View v) -> {
            navController.navigate(R.id.action_onBoardingFragment_to_completeYourProfileFragment);
        });

        smallImageView = view.findViewById(R.id.small_profile_image);

//        smallImageView.setOnClickListener(v -> navController.navigate(R.id.action_onBoardingFragment_to_myProfileFragment));

        contButton.setOnClickListener(v -> navController.navigate(R.id.action_onBoardingFragment_to_homeMenuFragment));

        return view;
    }

}
