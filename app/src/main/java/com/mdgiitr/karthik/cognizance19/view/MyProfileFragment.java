package com.mdgiitr.karthik.cognizance19.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.GeneralResponse;
import com.mdgiitr.karthik.cognizance19.models.UserDetailsSPPResponseModel;
import com.mdgiitr.karthik.cognizance19.models.UserSPPResponseModel;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import androidx.navigation.NavOptions;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

import static com.mdgiitr.karthik.cognizance19.MainActivity.REGISTRATION_TYPE_PARTICIPANT;
import static com.mdgiitr.karthik.cognizance19.MainActivity.REGISTRATION_TYPE_SPP;
import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class MyProfileFragment extends Fragment {

    private PopupMenu popupMenu;
    private ImageView menuImageView, backIcon, userProfilePic;
    private TextView nameView, emailView, cogniIDView, mobileNoView;
    private ApiClient apiClient;
    private PreferenceHelper preferenceHelper;

    public MyProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        setHasOptionsMenu(true);

        preferenceHelper = new PreferenceHelper(getActivity());

        apiClient = new ApiClient();

        menuImageView = view.findViewById(R.id.menu_icon);
        backIcon = view.findViewById(R.id.back_arrow_complete_your_profile);
        userProfilePic = view.findViewById(R.id.user_profile_pic);
        nameView = view.findViewById(R.id.userNameTextView);
        emailView = view.findViewById(R.id.emailIdTextView);
        cogniIDView = view.findViewById(R.id.cogni_id_textView);
        mobileNoView = view.findViewById(R.id.mobileNoTextView);

        popupMenu = new PopupMenu(getActivity(), menuImageView);

        backIcon.setOnClickListener(v -> navController.navigateUp());

        populateViewsFromDB();

        return view;
    }

    private void userLogout() {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Logging out. Please Wait...");
        progressDialog.show();
        apiClient.logout(preferenceHelper.getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        progressDialog.dismiss();
                        handleLogoutResponse(generalResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        handleLogoutError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void populateViewsFromDB() {

        apiClient.getUserDetails(preferenceHelper.getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserSPPResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserSPPResponseModel userResponseModel) {
                        populateViews(userResponseModel.getDetails());
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleUserDetailsErrorResponse(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void populateViews(UserDetailsSPPResponseModel userDetailsSPPResponseModel) {

        if (userDetailsSPPResponseModel.getName() != null) {
            nameView.setText(userDetailsSPPResponseModel.getName().toString());
        } else {
            nameView.setVisibility(View.GONE);
        }
        if (userDetailsSPPResponseModel.getMobile() != null) {
            mobileNoView.setText(userDetailsSPPResponseModel.getMobile());
        } else {
            mobileNoView.setVisibility(View.GONE);
        }
        if (userDetailsSPPResponseModel.getEmail() != null) {
            emailView.setText(userDetailsSPPResponseModel.getEmail());
        } else {
            emailView.setVisibility(View.GONE);
        }
        if (userDetailsSPPResponseModel.getId() != null) {
            cogniIDView.setText(userDetailsSPPResponseModel.getCogniId());
        } else {
            cogniIDView.setVisibility(View.GONE);
        }
        Glide.with(this)
                .load(userDetailsSPPResponseModel.getImageUrl())
                .into(userProfilePic);

        if (userDetailsSPPResponseModel.getRole().equals("cogni_user")) {
            preferenceHelper.setUserType(REGISTRATION_TYPE_PARTICIPANT);
        } else {
            preferenceHelper.setUserType(REGISTRATION_TYPE_SPP);
        }

        if (preferenceHelper.getUserType() == REGISTRATION_TYPE_PARTICIPANT) {
            popupMenu.getMenuInflater().inflate(R.menu.profile_menu, popupMenu.getMenu());
        } else {
            popupMenu.getMenuInflater().inflate(R.menu.spp_profile_menu, popupMenu.getMenu());
        }
        menuImageView.setOnClickListener((View v) -> popupMenu.show());
        popupMenu.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.logout) {
                userLogout();
            } else if (item.getItemId() == R.id.change_password) {
                navController.navigate(R.id.action_myProfileFragment_to_changePasswordFragment);
            } else if (item.getItemId() == R.id.spp_dashboard) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("userDetails", userDetailsSPPResponseModel);
                navController.navigate(R.id.action_myProfileFragment_to_dashboardSPPFragment, bundle);
            }

            return false;
        });

    }

    private void handleLogoutResponse(GeneralResponse generalResponse) {

        Toast.makeText(getContext(), generalResponse.message, Toast.LENGTH_SHORT).show();
        preferenceHelper.clearSharedPrefs();
        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.homeMenuFragment, true)
                .build();
        navController.navigate(R.id.action_myProfileFragment_to_landingFragment2, null, navOptions);

    }

    private void handleLogoutError(Throwable e) {

        e.printStackTrace();

    }

    private void handleUserDetailsErrorResponse(Throwable e) {

        try {
            if (((HttpException) e).code() == 412) {
                Toast.makeText(getContext(), "Please complete your registration by going to the dashboard", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

}
