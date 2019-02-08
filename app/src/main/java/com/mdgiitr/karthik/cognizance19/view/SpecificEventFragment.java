package com.mdgiitr.karthik.cognizance19.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.ContactModel;
import com.mdgiitr.karthik.cognizance19.models.EventResponse;
import com.mdgiitr.karthik.cognizance19.models.EventSpecificModel;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class SpecificEventFragment extends Fragment {
    private static final int MEGABYTE = 1024 * 1024;
    private ImageSwitcher switcher;
    private Timer timer = null;
    private int[] gallery = {R.drawable.dark_blue_bg, R.drawable.ic_launcher_background, R.drawable.blue_button_bg, R.drawable.gray_round_card};
    private int position = 0;
    private TextView introduction, regProcedure, rules, contactDetails;
    private Button probStatement;
    private ImageView introductionSplit, regProcedureSplit, rulesSplit, probStatementSplit, contactDetailsSplit;
    private String eventId = "";
    private ApiClient apiClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_specific_event, container, false);

        apiClient = new ApiClient();

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

        introductionSplit.setOnClickListener(v -> {
            if (introduction.getVisibility() == View.GONE) {
                introduction.setVisibility(View.VISIBLE);
                introductionSplit.setImageResource(R.drawable.ic_remove_black_24dp);
            } else {
                introduction.setVisibility(View.GONE);
                introductionSplit.setImageResource(R.drawable.ic_add_black_24dp);
            }
        });

        regProcedureSplit.setOnClickListener(v -> {
            if (regProcedure.getVisibility() == View.GONE) {
                regProcedure.setVisibility(View.VISIBLE);
                regProcedureSplit.setImageResource(R.drawable.ic_remove_black_24dp);
            } else {
                regProcedure.setVisibility(View.GONE);
                regProcedureSplit.setImageResource(R.drawable.ic_add_black_24dp);
            }
        });

        rulesSplit.setOnClickListener(v -> {
            if (rules.getVisibility() == View.GONE) {
                rules.setVisibility(View.VISIBLE);
                rulesSplit.setImageResource(R.drawable.ic_remove_black_24dp);
            } else {
                rules.setVisibility(View.GONE);
                rulesSplit.setImageResource(R.drawable.ic_add_black_24dp);
            }
        });

        probStatementSplit.setOnClickListener(v -> {
            if (probStatement.getVisibility() == View.GONE) {
                probStatement.setVisibility(View.VISIBLE);
                probStatementSplit.setImageResource(R.drawable.ic_remove_black_24dp);
            } else {
                probStatement.setVisibility(View.GONE);
                probStatementSplit.setImageResource(R.drawable.ic_add_black_24dp);
            }
        });

        contactDetailsSplit.setOnClickListener(v -> {
            if (contactDetails.getVisibility() == View.GONE) {
                contactDetails.setVisibility(View.VISIBLE);
                contactDetailsSplit.setImageResource(R.drawable.ic_remove_black_24dp);
            } else {
                contactDetails.setVisibility(View.GONE);
                contactDetailsSplit.setImageResource(R.drawable.ic_add_black_24dp);
            }
        });

        switcher = view.findViewById(R.id.specific_event_image_switcher);
        switcher.setFactory(() -> {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewGroup.LayoutParams params = new ImageSwitcher.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            return imageView;
        });

        Animation fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        fadeIn.setDuration(2000);
        Animation fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_left);
        fadeOut.setDuration(2000);
        switcher.setInAnimation(fadeIn);
        switcher.setOutAnimation(fadeOut);

        start();
        eventId = "174";
        getDetailsfromDb(eventId);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stop();
    }

    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                // avoid exception: "Only the original thread that created a view hierarchy can touch its views"
                getActivity().runOnUiThread(() -> {
                    switcher.setImageResource(gallery[position]);
                    position++;
                    if (position == 4) {
                        position = 0;
                    }
                });
            }

        }, 0, 6000);

    }

    public void stop() {
        timer.cancel();
    }

    private void getDetailsfromDb(String id) {

        apiClient.fetchSpecificEventDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EventResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EventResponse eventResponse) {
                        populateViews(eventResponse.getEvent());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void populateViews(EventSpecificModel eventSpecificModel) {

        introduction.setText(Html.fromHtml(eventSpecificModel.getDescription()));
        regProcedure.setText(Html.fromHtml(eventSpecificModel.getProcedure()));
        rules.setText(Html.fromHtml(eventSpecificModel.getRules()));
        List<ContactModel> contactList = eventSpecificModel.getContact();
        String contacts = "";
        for (ContactModel contactModel : contactList) {
            contacts = contacts.concat("<p><strong>Name : </strong>" + contactModel.getName() + "<br/>");
            contacts = contacts.concat("<strong>Phone No : </strong>" + contactModel.getPhoneNo() + "</p>");
        }
        contactDetails.setText(Html.fromHtml(contacts));

        probStatement.setOnClickListener(v -> {
            String url = eventSpecificModel.getProblemStatement();
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(getContext().getResources().getColor(R.color.fragment_bg));
            builder.setStartAnimations(getContext(), R.anim.slide_in_right, R.anim.slide_out_left);
            builder.setExitAnimations(getContext(), R.anim.slide_in_left, R.anim.slide_out_right);
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(getContext(), Uri.parse(url));
        });

    }


}
