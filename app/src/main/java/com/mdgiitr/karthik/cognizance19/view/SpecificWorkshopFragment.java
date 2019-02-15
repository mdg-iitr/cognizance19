package com.mdgiitr.karthik.cognizance19.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.EventRegisterIDsAdapter;
import com.mdgiitr.karthik.cognizance19.models.Contact;
import com.mdgiitr.karthik.cognizance19.models.EventResponse;
import com.mdgiitr.karthik.cognizance19.models.EventSpecificModel;
import com.mdgiitr.karthik.cognizance19.models.GeneralResponse;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;
import com.mdgiitr.karthik.cognizance19.utils.HTMLTagHandler;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

import static com.mdgiitr.karthik.cognizance19.MainActivity.bottomNavigationView;
import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class SpecificWorkshopFragment extends Fragment {
    private ImageView switcher;
    private int teamLimit = 0;
    private TextView introduction, regProcedure, rules, contactDetails, specificEventName, benefits;
    private Button registerButton, cancelButton, registerEventButton;
    private ImageView introductionSplit, regProcedureSplit, rulesSplit, probStatementSplit, contactDetailsSplit, backIcon;
    private View introBrick, regProBrick, rulesBrick, problemBrick, contactBrick;
    private RecyclerView memberRecyclerView;
    private EventRegisterIDsAdapter eventRegisterIDsAdapter;
    private Dialog registerDialog;
    private ProgressDialog progressDialog;
    private String eventId = "";
    private ApiClient apiClient;
    private PreferenceHelper preferenceHelper;
    private CircleImageView smallImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_specific_workshop, container, false);

        preferenceHelper = new PreferenceHelper(getActivity());

        apiClient = new ApiClient();

        introduction = view.findViewById(R.id.specific_event_introduction);
        regProcedure = view.findViewById(R.id.specific_event_registration_procedure);
        rules = view.findViewById(R.id.specific_event_rules);
        benefits = view.findViewById(R.id.specific_event_benefits);
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
        registerButton = view.findViewById(R.id.register_button);
        specificEventName = view.findViewById(R.id.specific_event_name);
        switcher = view.findViewById(R.id.specific_event_image_switcher);
        backIcon = view.findViewById(R.id.back_arrow);
        smallImageView = view.findViewById(R.id.small_profile_image);

        registerButton.setEnabled(false);

        registerDialog = new Dialog(getContext());
        registerDialog.setContentView(R.layout.dialog_event_register);
        registerEventButton = registerDialog.findViewById(R.id.register_event_button);
        cancelButton = registerDialog.findViewById(R.id.cancel_button);
        memberRecyclerView = registerDialog.findViewById(R.id.memberRecyclerView);

        introBrick.setOnClickListener(v -> introSplit());
        regProBrick.setOnClickListener(v -> registerSplit());
        rulesBrick.setOnClickListener(v -> rulesSplit());
        problemBrick.setOnClickListener(v -> problemSplit());
        contactBrick.setOnClickListener(v -> contactSplit());

        eventId = Integer.toString(getArguments().getInt("id"));
        getDetailsfromDb(eventId);

        registerButton.setOnClickListener(v -> registerForEventDialog());

        cancelButton.setOnClickListener(v -> registerDialog.dismiss());

        registerEventButton.setOnClickListener(v -> registerForEvent());

        backIcon.setOnClickListener(v -> navController.navigateUp());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.com_facebook_profile_picture_blank_square)
                .error(R.drawable.com_facebook_profile_picture_blank_square);
        Glide.with(this)
                .load(preferenceHelper.getProfilePicURL())
                .apply(options)
                .into(smallImageView);

        smallImageView.setOnClickListener(v -> navController.navigate(R.id.action_speceficWorkshopFragment_to_myProfileFragment));

        bottomNavigationView.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
        if (benefits.getVisibility() == View.GONE) {
            benefits.setVisibility(View.VISIBLE);
            probStatementSplit.setImageResource(R.drawable.ic_remove_black_24dp);
        } else {
            benefits.setVisibility(View.GONE);
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

    private void registerForEvent() {

        ArrayList<String> members;
        if (teamLimit > 1) {
            members = eventRegisterIDsAdapter.getIds();
        } else {
            members = new ArrayList<String>(Collections.nCopies(1, ""));
        }

        apiClient.registerForEvent(preferenceHelper.getToken(), eventId, members)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        progressDialog.dismiss();
                        registerDialog.dismiss();
                        Toast.makeText(getContext(), generalResponse.message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        registerDialog.dismiss();
                        handleRegisterError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void registerForEventDialog() {

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Registering. Please wait...");
        if (teamLimit > 1) {
            eventRegisterIDsAdapter = new EventRegisterIDsAdapter(teamLimit);
            memberRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            memberRecyclerView.setItemAnimator(new DefaultItemAnimator());
            memberRecyclerView.setAdapter(eventRegisterIDsAdapter);
            registerDialog.show();
        } else {
            registerForEvent();
        }

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

        specificEventName.setText(eventSpecificModel.getName());
        introduction.setText(Html.fromHtml(eventSpecificModel.getDescription()));
        introduction.setMovementMethod(LinkMovementMethod.getInstance());
        introduction.setLinksClickable(true);
        regProcedure.setText(Html.fromHtml(eventSpecificModel.getProcedure()));
        regProcedure.setMovementMethod(LinkMovementMethod.getInstance());
        regProcedure.setLinksClickable(true);
        rules.setText(Html.fromHtml(eventSpecificModel.getRules()));
        rules.setMovementMethod(LinkMovementMethod.getInstance());
        rules.setLinksClickable(true);
        benefits.setText(Html.fromHtml(eventSpecificModel.getPrize(), null, new HTMLTagHandler()));
        benefits.setMovementMethod(LinkMovementMethod.getInstance());
        benefits.setLinksClickable(true);
        List<Contact> contactList = eventSpecificModel.getContact();
        String contacts = "";
        for (Contact contact : contactList) {
            contacts = contacts.concat("<p><strong>Name : </strong>" + contact.getName() + "<br/>");
            contacts = contacts.concat("<strong>Phone No : </strong>" + contact.getPhoneNo() + "</p>");
        }
        contactDetails.setText(Html.fromHtml(contacts));
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.home_menu_gray_card)
                .error(R.drawable.home_menu_gray_card);
        if (eventSpecificModel.getCoverImage() != null)
            Glide.with(getContext())
                    .load(eventSpecificModel.getCoverImage().toString())
                    .apply(options)
                    .into(switcher);
        else
            switcher.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.home_menu_gray_card));

        teamLimit = Integer.parseInt(eventSpecificModel.getTeamLimit());
        registerButton.setEnabled(true);

    }

    private void handleRegisterError(Throwable throwable) {

        try {
            if (((HttpException) throwable).code() == 400) {
                Gson gson = new Gson();
                String msg = ((HttpException) throwable).response().errorBody().string();
                GeneralResponse generalResponse = gson.fromJson(msg, GeneralResponse.class);
                Toast.makeText(getContext(), generalResponse.message, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.d("REGISTER_ERROR", e.toString());
        }

    }


}
