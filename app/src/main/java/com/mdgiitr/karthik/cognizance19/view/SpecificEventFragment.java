package com.mdgiitr.karthik.cognizance19.view;

import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.mdgiitr.karthik.cognizance19.R;

import java.util.Timer;
import java.util.TimerTask;

public class SpecificEventFragment extends Fragment {
    private ImageSwitcher switcher;
    private Timer timer = null;
    private int[] gallery = {R.drawable.dark_blue_bg, R.drawable.ic_launcher_background, R.drawable.blue_button_bg, R.drawable.gray_round_card};
    private int position = 0;
    private TextView introduction, regProcedure, rules, probStatement, contactDetails;
    private ImageView introductionSplit, regProcedureSplit, rulesSplit, probStatementSplit, contactDetailsSplit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_specific_event, container, false);

        introduction = view.findViewById(R.id.specific_event_introduction);
        regProcedure = view.findViewById(R.id.specific_event_registration_procedure);
        rules = view.findViewById(R.id.specific_event_rules);
        probStatement = view.findViewById(R.id.specific_event_problem_statement);
        contactDetails = view.findViewById(R.id.specific_event_contact_details);
        introductionSplit = view.findViewById(R.id.specific_event_introduction_split);
        regProcedureSplit = view.findViewById(R.id.specific_event_registration_procedure_split);
        rulesSplit = view.findViewById(R.id.specific_event_rules_split);
        probStatementSplit = view.findViewById(R.id.specific_event_problem_statement_split);
        contactDetailsSplit = view.findViewById(R.id.specific_event_contact_details_split);

        introductionSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(introduction.getVisibility() == View.GONE){
                    introduction.setVisibility(View.VISIBLE);
                    introductionSplit.setImageResource(R.drawable.ic_remove_black_24dp);
                } else {
                    introduction.setVisibility(View.GONE);
                    introductionSplit.setImageResource(R.drawable.ic_add_black_24dp);
                }
            }
        });

        regProcedureSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(regProcedure.getVisibility() == View.GONE){
                    regProcedure.setVisibility(View.VISIBLE);
                    regProcedureSplit.setImageResource(R.drawable.ic_remove_black_24dp);
                } else {
                    regProcedure.setVisibility(View.GONE);
                    regProcedureSplit.setImageResource(R.drawable.ic_add_black_24dp);
                }
            }
        });

        rulesSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rules.getVisibility() == View.GONE){
                    rules.setVisibility(View.VISIBLE);
                    rulesSplit.setImageResource(R.drawable.ic_remove_black_24dp);
                } else {
                    rules.setVisibility(View.GONE);
                    rulesSplit.setImageResource(R.drawable.ic_add_black_24dp);
                }
            }
        });

        probStatementSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(probStatement.getVisibility() == View.GONE){
                    probStatement.setVisibility(View.VISIBLE);
                    probStatementSplit.setImageResource(R.drawable.ic_remove_black_24dp);
                } else {
                    probStatement.setVisibility(View.GONE);
                    probStatementSplit.setImageResource(R.drawable.ic_add_black_24dp);
                }
            }
        });

        contactDetailsSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contactDetails.getVisibility() == View.GONE){
                    contactDetails.setVisibility(View.VISIBLE);
                    contactDetailsSplit.setImageResource(R.drawable.ic_remove_black_24dp);
                } else {
                    contactDetails.setVisibility(View.GONE);
                    contactDetailsSplit.setImageResource(R.drawable.ic_add_black_24dp);
                }
            }
        });

        switcher = view.findViewById(R.id.specific_event_image_switcher);
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                ViewGroup.LayoutParams params = new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(params);
                return imageView;
            }
        });

        Animation fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        fadeIn.setDuration(2000);
        Animation fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_left);
        fadeOut.setDuration(2000);
        switcher.setInAnimation(fadeIn);
        switcher.setOutAnimation(fadeOut);

        start();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stop();
    }

    public void start()
    {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                // avoid exception: "Only the original thread that created a view hierarchy can touch its views"
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        switcher.setImageResource(gallery[position]);
                        position++;
                        if (position == 4)
                        {
                            position = 0;
                        }
                    }
                });
            }

        }, 0, 6000);

    }

    public void stop()
    {
        timer.cancel();
    }

}
