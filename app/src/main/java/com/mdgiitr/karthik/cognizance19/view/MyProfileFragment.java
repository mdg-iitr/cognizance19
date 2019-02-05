package com.mdgiitr.karthik.cognizance19.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Toolbar;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.adapters.TabViewPagerAdapter;
import com.mdgiitr.karthik.cognizance19.models.GeneralResponse;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class MyProfileFragment extends Fragment {

    private PopupMenu popupMenu;
    private ImageView menuImageView, backIcon, userProfilePic;
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

        View view = inflater.inflate(R.layout.my_profile, container, false);
        setHasOptionsMenu(true);

        preferenceHelper = new PreferenceHelper(getActivity());

        apiClient = new ApiClient();

        menuImageView = view.findViewById(R.id.menu_icon);
        backIcon = view.findViewById(R.id.back_arrow_complete_your_profile);
        userProfilePic = view.findViewById(R.id.user_profile_pic);
        popupMenu = new PopupMenu(getActivity(), menuImageView);
        popupMenu.getMenuInflater().inflate(R.menu.profile_menu, popupMenu.getMenu());



        menuImageView.setOnClickListener((View v) -> {

            popupMenu.show();

        });

        popupMenu.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.logout) {
                userLogout();
            } else if (item.getItemId() == R.id.change_password) {
                navController.navigate(R.id.action_myProfileFragment_to_changePasswordFragment);
            }

            return false;
        });

        backIcon.setOnClickListener(v -> navController.navigateUp());
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

    private void handleLogoutResponse(GeneralResponse generalResponse) {

        Toast.makeText(getContext(), generalResponse.message, Toast.LENGTH_SHORT).show();
//                NavOptions navOptions = new NavOptions.Builder()
//                .setPopUpTo(R.id.onBoardingFragment, true)
//                .build();
        navController.navigate(R.id.action_myProfileFragment_to_landingFragment2);

    }

    private void handleLogoutError(Throwable e) {

        e.printStackTrace();

    }

}
