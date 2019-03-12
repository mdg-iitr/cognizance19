package com.mdg.iitr.cognizance19.view;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.adapters.ViewPagerAdapter;
import com.mdg.iitr.cognizance19.models.GeneralResponse;
import com.mdg.iitr.cognizance19.models.UserDetailsSPPResponseModel;
import com.mdg.iitr.cognizance19.models.UserSPPResponseModel;
import com.mdg.iitr.cognizance19.network.client.ApiClient;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import androidx.navigation.NavOptions;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;
import static com.mdg.iitr.cognizance19.MainActivity.REGISTRATION_TYPE_PARTICIPANT;
import static com.mdg.iitr.cognizance19.MainActivity.REGISTRATION_TYPE_SPP;
import static com.mdg.iitr.cognizance19.MainActivity.navController;
import static com.mdg.iitr.cognizance19.MainActivity.bottomNavigationView;

public class MyProfileFragment extends Fragment {

    private PopupMenu popupMenu;
    private ImageView menuImageView, backIcon, userProfilePic, editProfileIcon;
    private TextView nameView, emailView, cogniIDView, mobileNoView;
    private ApiClient apiClient;
    private PreferenceHelper preferenceHelper;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private HashMap<Integer, Fragment> map;
    private boolean updateVisible = false;
    private LinearLayout updateProfile;
    private int PROFILE_PIC_REQUEST = 100;
    private File profilePicFile;
    private ProgressDialog progressDialog;

    private boolean dataFetched = false;
    private UserDetailsSPPResponseModel cachedResponse;
    private boolean isViewCreated = false;

    public MyProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceHelper = new PreferenceHelper(getActivity());
        progressDialog = new ProgressDialog(getContext());
        apiClient = new ApiClient();
        populateViewsFromDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        setHasOptionsMenu(true);

        menuImageView = view.findViewById(R.id.menu_icon);
        backIcon = view.findViewById(R.id.back_arrow_complete_your_profile);
        userProfilePic = view.findViewById(R.id.user_profile_pic);
        nameView = view.findViewById(R.id.userNameTextView);
        emailView = view.findViewById(R.id.emailIdTextView);
        cogniIDView = view.findViewById(R.id.cogni_id_textView);
        mobileNoView = view.findViewById(R.id.mobileNoTextView);
        tabLayout = view.findViewById(R.id.my_profile_tabs);
        viewPager = view.findViewById(R.id.my_profile_view_pager);
        updateProfile = view.findViewById(R.id.updateProfilePicLinearLayout);
        editProfileIcon = view.findViewById(R.id.edit_profile_icon);

        updateProfile.setVisibility(View.GONE);

        map = new HashMap<>();
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), map);


        map.put(0, new RegEventsFragment());
        map.put(1, new RegWorkshopsFragment());
        map.put(2, new NewPaymentFragment());
        viewPagerAdapter.notifyDataSetChanged();

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        setUpTabs(tabLayout.getSelectedTabPosition());

        popupMenu = new PopupMenu(getActivity(), menuImageView);

        userProfilePic.setOnClickListener(v -> {
            if (updateVisible) {
                updateVisible = false;
                updateProfile.setVisibility(View.GONE);
            } else {
                updateProfile.setVisibility(View.VISIBLE);
                updateVisible = true;
            }
        });

        updateProfile.setOnClickListener(v -> {
            if (updateVisible) {
                //startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI), PROFILE_PIC_REQUEST);
            }
        });

        backIcon.setOnClickListener(v -> navController.navigateUp());
        bottomNavigationView.setVisibility(View.VISIBLE);

        isViewCreated = true;

        if (dataFetched) {
            populateViews(cachedResponse);
        }


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PROFILE_PIC_REQUEST && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), selectedImage);
                if (bitmap != null) {
//                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
//                    Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//                    cursor.moveToFirst();
//                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                    String picturePath = cursor.getString(columnIndex);
//                    cursor.close();
//                    Log.d("Image", picturePath);
//                    profilePicFile = new File(picturePath);
                    uploadImage(selectedImage, bitmap);

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            updateVisible = false;
            updateProfile.setVisibility(View.GONE);
        }
    }

    @SuppressLint("CheckResult")
    private void uploadImage(Uri uri, Bitmap bitmap) {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Uploading. Please Wait...");
        progressDialog.show();
        Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(ObservableEmitter<File> emitter) throws Exception {
                emitter.onNext(getResizedImageFile(bitmap, uri));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(file -> {
                    if (file != null) {
                        apiClient.updateUserImage(preferenceHelper.getToken(), file)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<GeneralResponse>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(GeneralResponse responseBody) {
                                        Toast.makeText(getContext(), responseBody.message, Toast.LENGTH_LONG).show();
                                        userProfilePic.setImageBitmap(bitmap);
                                        progressDialog.dismiss();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        progressDialog.dismiss();
                                        Log.d("TAGTAGTAG", e.toString());
                                        if (e.toString().trim().equals("retrofit2.adapter.rxjava2.HttpException: HTTP 413"))
                                            Toast.makeText(getContext(), "File size exceeded 50KB", Toast.LENGTH_LONG).show();
                                        else if (e.toString().trim().equals("retrofit2.adapter.rxjava2.HttpException: HTTP 400"))
                                            Toast.makeText(getContext(), "Unexpected file", Toast.LENGTH_LONG).show();
                                        else
                                            Toast.makeText(getContext(), "Couldn't update. Please try again!", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });

                    } else {
                        progressDialog.dismiss();
                    }
                } );



    }

    public File getResizedImageFile(Bitmap bmp, Uri uri) {
        if (bmp == null) {
            return null;
        }
        Bitmap bitmap;
        bitmap = Bitmap.createScaledBitmap(bmp, bmp.getWidth() / 2, bmp.getHeight() / 2, false);
        File file = null;
        try {
            file = createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bitmap != null && file != null) {
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
                return file;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        deleteFile(uri);
        return null;
    }

    public void deleteFile(Uri fileUri) {
        File file = new File(fileUri.toString());
        file.delete();
        if (file.exists()) {
            try {
                file.getCanonicalFile().delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (file.exists()) {
                getApplicationContext().deleteFile(file.getName());
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = String.valueOf(new Date().getTime());
        String imageFileName = "photo-" + timeStamp;
        File storageDir = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }


    private void setUpTabs(int selectedTabPosition) {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_exhibition);
        tabLayout.getTabAt(0).setText("Reg Events");

        tabLayout.getTabAt(1).setIcon(R.drawable.ic_workshop);
        tabLayout.getTabAt(1).setText("Reg Workshops");

        tabLayout.getTabAt(2).setIcon(R.drawable.ic_payment);
        tabLayout.getTabAt(2).setText("Payments");

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setAllCaps(false);
                    ((TextView) tabViewChild).setTextSize(15);
                    ((TextView) tabViewChild).setTypeface(null, Typeface.NORMAL);
                }
            }
        }

        ViewGroup vgTab = (ViewGroup) vg.getChildAt(selectedTabPosition);
        int tabChildsCount = vgTab.getChildCount();
        for (int i = 0; i < tabChildsCount; i++) {
            View tabViewChild = vgTab.getChildAt(i);
            if (tabViewChild instanceof TextView) {
                ((TextView) tabViewChild).setTypeface(null, Typeface.BOLD);
            }
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
                ViewGroup vgTab = (ViewGroup) vg.getChildAt(tab.getPosition());
                int tabChildsCount = vgTab.getChildCount();
                for (int i = 0; i < tabChildsCount; i++) {
                    View tabViewChild = vgTab.getChildAt(i);
                    if (tabViewChild instanceof TextView) {
                        ((TextView) tabViewChild).setTypeface(null, Typeface.BOLD);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
                ViewGroup vgTab = (ViewGroup) vg.getChildAt(tab.getPosition());
                int tabChildsCount = vgTab.getChildCount();
                for (int i = 0; i < tabChildsCount; i++) {
                    View tabViewChild = vgTab.getChildAt(i);
                    if (tabViewChild instanceof TextView) {
                        ((TextView) tabViewChild).setTypeface(null, Typeface.NORMAL);
                    }
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
        progressDialog.setMessage("Fetching details...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        apiClient.getUserDetails(preferenceHelper.getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserSPPResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserSPPResponseModel userResponseModel) {
                        dataFetched = true;
                        cachedResponse = userResponseModel.getDetails();
                        if (isViewCreated)
                            populateViews(userResponseModel.getDetails());
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleUserDetailsErrorResponse(e);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void populateViews(UserDetailsSPPResponseModel userDetailsSPPResponseModel) {
        preferenceHelper.setCogniId(userDetailsSPPResponseModel.getCogniId());

        editProfileIcon.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("profile", userDetailsSPPResponseModel);
            navController.navigate(R.id.action_myProfileFragment_to_editProfileFragment, bundle);
        });

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
            cogniIDView.setText("Cogni ID : " + userDetailsSPPResponseModel.getCogniId());
        } else {
            cogniIDView.setVisibility(View.GONE);
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.com_facebook_profile_picture_blank_square)
                .error(R.drawable.com_facebook_profile_picture_blank_square);
        Glide.with(this)
                .load("https://bucket.cognizance.org.in/bucket/" + userDetailsSPPResponseModel.getImageUrl())
                .apply(options)
                .into(userProfilePic);

        preferenceHelper.setProfilePicURL("https://bucket.cognizance.org.in/bucket/" + userDetailsSPPResponseModel.getImageUrl());

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
