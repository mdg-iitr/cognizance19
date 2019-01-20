package com.mdgiitr.karthik.cognizance19.view;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mdgiitr.karthik.cognizance19.R;

import static com.mdgiitr.karthik.cognizance19.MainActivity.REGISTRATION_TYPE_PARTICIPANT;
import static com.mdgiitr.karthik.cognizance19.MainActivity.REGISTRATION_TYPE_SPP;
import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class RegisterFragment extends Fragment {

    public static int REGISTRATION_TYPE = -1;
    private Button cancelAction, nextAction;
    private TextView signupParticipant, signupSPP;
    private Dialog typeDialog;
    private boolean isVisible = false;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        typeDialog = new Dialog(getContext());
        typeDialog.setContentView(R.layout.registration_type_dialog);
        typeDialog.setCanceledOnTouchOutside(false);

        signupParticipant = typeDialog.findViewById(R.id.signup_as_participant);
        signupSPP = typeDialog.findViewById(R.id.signup_as_spp);

        cancelAction = typeDialog.findViewById(R.id.cancel_action);
        nextAction = typeDialog.findViewById(R.id.next_action);

        signupSPP.setOnClickListener((View v) -> {
            signupSPP.setTextColor(Color.BLUE);
            signupParticipant.setTextColor(getActivity().getResources().getColor(R.color.userType_gray));
            REGISTRATION_TYPE = REGISTRATION_TYPE_SPP;
            nextAction.setEnabled(true);
        });

        signupParticipant.setOnClickListener((View v) -> {
            signupParticipant.setTextColor(Color.BLUE);
            signupSPP.setTextColor(getActivity().getResources().getColor(R.color.userType_gray));
            REGISTRATION_TYPE = REGISTRATION_TYPE_PARTICIPANT;
            nextAction.setEnabled(true);
        });

        cancelAction.setOnClickListener((View v) -> {
            typeDialog.dismiss();
            navController.navigateUp();
        });

        nextAction.setOnClickListener((View v) -> typeDialog.dismiss());

        if(isVisible)
            typeDialog.show();

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        if(isVisibleToUser)
            isVisible = isVisibleToUser;
        if(isVisibleToUser && typeDialog!=null)
            typeDialog.show();
    }

}
