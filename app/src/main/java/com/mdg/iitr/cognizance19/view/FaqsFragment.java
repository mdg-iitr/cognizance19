package com.mdg.iitr.cognizance19.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.adapters.SpotLightMenuEventAdapter;
import com.mdg.iitr.cognizance19.models.HomeMenuEventModel;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import static com.mdg.iitr.cognizance19.MainActivity.bottomNavigationView;

public class FaqsFragment extends Fragment {

    private TextView introduction, regProcedure, rules, contactDetails, probStatement;
    private View introBrick, regProBrick, rulesBrick, problemBrick, contactBrick;
    private ImageView introductionSplit, regProcedureSplit, rulesSplit, probStatementSplit, contactDetailsSplit, backIcon;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faqs, container, false);
        introduction = view.findViewById(R.id.specific_event_introduction);
        regProcedure = view.findViewById(R.id.specific_event_registration_procedure);
        rules = view.findViewById(R.id.specific_event_rules);
        probStatement = view.findViewById(R.id.specific_eventa_rules);
        contactDetails = view.findViewById(R.id.specific_event_contact_details);

        introductionSplit = view.findViewById(R.id.specific_event_introduction_split);
        regProcedureSplit = view.findViewById(R.id.specific_event_registration_procedure_split);
        rulesSplit = view.findViewById(R.id.specific_event_rules_split);
        probStatementSplit = view.findViewById(R.id.specific_event_problem_statement_split);
        contactDetailsSplit = view.findViewById(R.id.specific_event_contact_details_split);

        introBrick = view.findViewById(R.id.specific_evnt_intro_brick);
        regProBrick = view.findViewById(R.id.specific_evnt_reg_pro_brick);
        rulesBrick = view.findViewById(R.id.specific_evnt_rules_brick);
        problemBrick = view.findViewById(R.id.specific_evnt_prob_brick);
        contactBrick = view.findViewById(R.id.specific_evnt_contact_brick);

        introBrick.setOnClickListener(v -> introSplit());
        regProBrick.setOnClickListener(v -> registerSplit());
        rulesBrick.setOnClickListener(v -> rulesSplit());
        problemBrick.setOnClickListener(v -> problemSplit());
        contactBrick.setOnClickListener(v -> contactSplit());


        bottomNavigationView.setVisibility(View.VISIBLE);
        return view;
    }


    private void registerSplit() {
        if (regProcedure.getVisibility() == View.GONE) {
            regProcedure.setVisibility(View.VISIBLE);
            regProcedureSplit.setImageResource(R.drawable.ic_remove_black_24dp);
        } else {
            regProcedure.setVisibility(View.GONE);
            regProcedureSplit.setImageResource(R.drawable.ic_add_black_24dp);
        }
    }

    private void introSplit() {
        if (introduction.getVisibility() == View.GONE) {
            introduction.setVisibility(View.VISIBLE);
            introductionSplit.setImageResource(R.drawable.ic_remove_black_24dp);
        } else {
            introduction.setVisibility(View.GONE);
            introductionSplit.setImageResource(R.drawable.ic_add_black_24dp);
        }
    }

    private void rulesSplit() {
        if (rules.getVisibility() == View.GONE) {
            rules.setVisibility(View.VISIBLE);
            rulesSplit.setImageResource(R.drawable.ic_remove_black_24dp);
        } else {
            rules.setVisibility(View.GONE);
            rulesSplit.setImageResource(R.drawable.ic_add_black_24dp);
        }
    }

    private void problemSplit() {
        if (probStatement.getVisibility() == View.GONE) {
            probStatement.setVisibility(View.VISIBLE);
            probStatementSplit.setImageResource(R.drawable.ic_remove_black_24dp);
        } else {
            probStatement.setVisibility(View.GONE);
            probStatementSplit.setImageResource(R.drawable.ic_add_black_24dp);
        }
    }

    private void contactSplit() {
        if (contactDetails.getVisibility() == View.GONE) {
            contactDetails.setVisibility(View.VISIBLE);
            contactDetailsSplit.setImageResource(R.drawable.ic_remove_black_24dp);
        } else {
            contactDetails.setVisibility(View.GONE);
            contactDetailsSplit.setImageResource(R.drawable.ic_add_black_24dp);
        }
    }

}
