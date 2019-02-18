package com.mdg.iitr.cognizance19.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.models.GeneralResponse;
import com.mdg.iitr.cognizance19.network.client.ApiClient;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

import static com.mdg.iitr.cognizance19.MainActivity.navController;

public class ChangePasswordFragment extends Fragment {

    private ImageView backIcon;
    private EditText currentPassword, newPassword1, newPassword2;
    private Button changePasswordButton;
    private PreferenceHelper preferenceHelper;
    private ApiClient apiClient;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);

        preferenceHelper = new PreferenceHelper(getActivity());
        apiClient = new ApiClient();

        currentPassword = view.findViewById(R.id.currPass);
        newPassword1 = view.findViewById(R.id.newPass1);
        newPassword2 = view.findViewById(R.id.newPass2);
        changePasswordButton = view.findViewById(R.id.changePasswordButton);
        newPassword1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (newPassword1.getText() != null && newPassword2.getText() != null && !newPassword2.getText().toString().trim().isEmpty() && !newPassword1.getText().toString().trim().isEmpty() && newPassword1.getText().toString().equals(newPassword2.getText().toString())) {
                    changePasswordButton.setEnabled(true);
                } else {
                    changePasswordButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        newPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (newPassword1.getText() != null && newPassword2.getText() != null && !newPassword2.getText().toString().trim().isEmpty() && !newPassword1.getText().toString().trim().isEmpty() && newPassword1.getText().toString().equals(newPassword2.getText().toString())) {
                    changePasswordButton.setEnabled(true);
                } else {
                    changePasswordButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        backIcon = view.findViewById(R.id.back_arrow);
        backIcon.setOnClickListener(v -> {
            navController.navigateUp();
        });

        changePasswordButton.setOnClickListener(v -> {
            if (currentPassword.getText() != null && !currentPassword.getText().toString().trim().isEmpty()) {

                changeUserPassword();

            }
        });

        return view;
    }

    private void changeUserPassword() {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Updating Password. Please Wait...");
        progressDialog.show();
        apiClient.updatePassword(preferenceHelper.getToken(), currentPassword.getText().toString().trim(), newPassword1.getText().toString().trim(), newPassword2.getText().toString().trim())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GeneralResponse generalResponse) {
                        progressDialog.dismiss();
                        handleChangePasswordResponse(generalResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        handleChangePasswordError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void handleChangePasswordResponse(GeneralResponse generalResponse) {

        Toast.makeText(getContext(), generalResponse.message, Toast.LENGTH_SHORT).show();
        navController.navigateUp();

    }

    private void handleChangePasswordError(Throwable e) {

        try {

            if (((HttpException) e).code() == 401) {
                Toast.makeText(getContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                currentPassword.requestFocus();
                currentPassword.setText("");
                currentPassword.setError("Wrong Password");
            }

        } catch (Exception exception) {

        }

    }

}
