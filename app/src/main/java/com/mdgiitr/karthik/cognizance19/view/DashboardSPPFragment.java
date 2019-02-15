package com.mdgiitr.karthik.cognizance19.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.GeneralResponse;
import com.mdgiitr.karthik.cognizance19.models.UserDetailsSPPResponseModel;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

import static android.app.Activity.RESULT_OK;
import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class DashboardSPPFragment extends Fragment {
    private ProgressBar referalProgressBar;
    private ImageButton splitExcelButton, imageButton;
    private Button uploadButton;
    private ImageView backIcon;
    private CircleImageView smallImageView;
    private LinearLayout excelCard, imageCard;
    private TextView referalCodeTextView, usersReferredTextView, scoreTextView, progressText;
    private EditText imageEditText;
    private File imageFile;
    private PreferenceHelper preferenceHelper;
    private ApiClient apiClient;
    private boolean isExcelVisible = false, isImageSetionVisible = false;
    private int RESULT_LOAD_IMAGE = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard_spp, container, false);

        preferenceHelper = new PreferenceHelper(getActivity());
        apiClient = new ApiClient();

        referalProgressBar = view.findViewById(R.id.referal_progress_bar);
        referalProgressBar.setProgress(0);

        splitExcelButton = view.findViewById(R.id.split_excel_card_button);
        excelCard = view.findViewById(R.id.excel_card);

        imageButton = view.findViewById(R.id.imageSplit);
        imageCard = view.findViewById(R.id.image_card);
        imageEditText = view.findViewById(R.id.imageEditText);
        uploadButton = view.findViewById(R.id.uploadImageButton);
        referalCodeTextView = view.findViewById(R.id.referal_codeView);
        usersReferredTextView = view.findViewById(R.id.usersReferredView);
        scoreTextView = view.findViewById(R.id.referalScoreView);
        progressText = view.findViewById(R.id.progressText);
        backIcon = view.findViewById(R.id.back_arrow);
        smallImageView = view.findViewById(R.id.small_profile_image);

        splitExcelButton.setOnClickListener(v -> {
            if (isExcelVisible) {
                excelCard.setVisibility(View.GONE);
                isExcelVisible = false;
                splitExcelButton.setImageResource(R.drawable.ic_add_black_24dp);
            } else {
                excelCard.setVisibility(View.VISIBLE);
                isExcelVisible = true;
                splitExcelButton.setImageResource(R.drawable.ic_remove_black_24dp);
            }
        });

        imageButton.setOnClickListener(v -> {
            if (isImageSetionVisible) {
                imageCard.setVisibility(View.GONE);
                isImageSetionVisible = false;
                imageButton.setImageResource(R.drawable.ic_add_black_24dp);
            } else {
                imageCard.setVisibility(View.VISIBLE);
                isImageSetionVisible = true;
                imageButton.setImageResource(R.drawable.ic_remove_black_24dp);
            }
        });

        imageEditText.setOnClickListener(v -> getImage());

        uploadButton.setOnClickListener(v -> uploadImage(imageFile));

        backIcon.setOnClickListener(v -> navController.navigateUp());

        populateViews(getArguments().getParcelable("userDetails"));

        smallImageView.setOnClickListener(v -> navController.navigateUp());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.com_facebook_profile_picture_blank_square);
        Glide.with(this)
                .load(preferenceHelper.getProfilePicURL())
                .apply(options)
                .into(smallImageView);

        return view;
    }

    private void getImage() {

        if (!preferenceHelper.getStoragePerm()) {
            askStoragePermission();
        } else {
            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }

    }

    public void askStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                preferenceHelper.setStoragePerm(true);
                getImage();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            preferenceHelper.setStoragePerm(true);
            getImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            preferenceHelper.setStoragePerm(true);
            getImage();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imageEditText.setText(picturePath);
            imageFile = new File(picturePath);
        }
    }

    private void uploadImage(File file) {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Uploading. Please Wait...");
        if (file != null) {
            progressDialog.show();
            apiClient.updateUserImage(preferenceHelper.getToken(), file)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<GeneralResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(GeneralResponse responseBody) {
                            Log.d("TAGTAGTAG", responseBody.message);
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {
                            progressDialog.dismiss();
                            Log.d("TAGTAGTAG", e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }

    }

    private void populateViews(UserDetailsSPPResponseModel details) {

        referalCodeTextView.setText(details.getReferalCode());
        usersReferredTextView.setText(details.getUsersReferred());
        scoreTextView.setText(details.getScore());
        referalProgressBar.setProgress(Integer.parseInt(details.getUsersReferred()));
        progressText.setText(details.getUsersReferred() + "/100 Students    ");
    }

    private void handleUploadErrorResponse(Throwable e) {

        try {
            if (((HttpException) e).code() == 412) {
                Toast.makeText(getContext(), "File too large to be uploaded.", Toast.LENGTH_SHORT).show();
            } else if (((HttpException) e).code() == 412) {
                Toast.makeText(getContext(), "Please complete your registration by going to the dashboard", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


}
