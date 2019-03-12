package com.mdg.iitr.cognizance19.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mdg.iitr.cognizance19.R;

import androidx.navigation.Navigation;


public class BottonNavigationMenu extends Fragment {
    public static int requiredType = 1;


    public BottonNavigationMenu() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_botton_navigation_menu, container, false);

        LinearLayout spotlightLinearLayout = v.findViewById(R.id.spotlight_linear_layout);
        LinearLayout scheduleLinearLayout = v.findViewById(R.id.schedule_linear_layout);


        LinearLayout faqsLinearLayout = v.findViewById(R.id.faqs_linear_layout);
        LinearLayout contactUsLinearLayout = v.findViewById(R.id.contact_us_linear_layout);
        LinearLayout sponsorsLinearLayout = v.findViewById(R.id.sponsors_linear_layout);

        faqsLinearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                requiredType = 2;
                return false;
            }
        });

        contactUsLinearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                requiredType = 1;
                return false;
            }
        });


        scheduleLinearLayout.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_bottonNavigationMenu_to_scheduleFragment));

        faqsLinearLayout.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_bottonNavigationMenu_to_aboutUsFragment));
        contactUsLinearLayout.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_bottonNavigationMenu_to_aboutUsFragment));
        sponsorsLinearLayout.setOnClickListener(v1 -> {
            String url = "https://www.cognizance.org.in/sponsors18";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });

        return v;
    }

}
