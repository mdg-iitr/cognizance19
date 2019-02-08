package com.mdgiitr.karthik.cognizance19.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mdgiitr.karthik.cognizance19.EmailPasswordValidator;
import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.LoginResponse;
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
import static com.mdgiitr.karthik.cognizance19.view.RegisterFragment.REGISTRATION_TYPE;

public class LoginFragment extends Fragment {

    private Button contButton;
    private EditText emailEditText, passwordEditText;
    private boolean emailValid = false, passwordValid = false;
    private ApiClient apiClient;
    private PreferenceHelper preferenceHelper;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        apiClient = new ApiClient();
        preferenceHelper = new PreferenceHelper(getActivity());

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Logging in, please wait.");

        emailEditText = view.findViewById(R.id.email_editText);
        passwordEditText = view.findViewById(R.id.password_editText);
        contButton = view.findViewById(R.id.cont_Button);
        forgotPassword = view.findViewById(R.id.forgot_password_textView);

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (EmailPasswordValidator.isEmailValid(s.toString())) {
                    emailValid = true;
                } else {
                    emailValid = false;
                }
                isEmailPwValid();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (EmailPasswordValidator.isValidPassword(s.toString())) {
                    passwordValid = true;
                } else {
                    passwordValid = false;
                }
                isEmailPwValid();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        forgotPassword.setOnClickListener((View v) -> {
            navController.navigate(R.id.action_userLoginFragment_to_forgotPasswordFragment);
        });

        contButton.setOnClickListener((View v) -> {
            progressDialog.show();
            apiClient.userLogin(emailEditText.getText().toString(), passwordEditText.getText().toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginResponse loginResponse) {
                            handleLoginResponse(loginResponse);
                        }

                        @Override
                        public void onError(Throwable e) {
                            progressDialog.dismiss();
                            handleLoginError(e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        });

        return view;
    }

    private void isEmailPwValid() {
        if (emailValid && passwordValid) {
            contButton.setEnabled(true);
        } else {
            contButton.setEnabled(false);
        }
    }

    private void handleLoginResponse(LoginResponse loginResponse) {

        preferenceHelper.setToken(loginResponse.token);
        if (loginResponse.role.equals("cogni_user")) {
            REGISTRATION_TYPE = REGISTRATION_TYPE_PARTICIPANT;
        } else if (loginResponse.role.equals("spp")) {
            REGISTRATION_TYPE = REGISTRATION_TYPE_SPP;
        }

        checkUserDetailsComplete();

    }

    private void checkUserDetailsComplete() {

        apiClient.getUserDetails(preferenceHelper.getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserSPPResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserSPPResponseModel userSPPResponseModel) {
                        progressDialog.dismiss();
                            NavOptions navOptions = new NavOptions.Builder()
                                    .setPopUpTo(R.id.landingFragment2, true)
                                    .build();
                        if (userSPPResponseModel.getDetails().getIsCompleted()) {
                            Toast.makeText(getContext(), "Successfully logged in", Toast.LENGTH_SHORT).show();
                            preferenceHelper.setLoginStatus(true);
                            navController.navigate(R.id.action_userLoginFragment_to_homeMenuFragment, null, navOptions);
                        } else {
                            navController.navigate(R.id.action_userLoginFragment_to_completeYourProfileFragment);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        try {
                            if ((((HttpException) e).code() == 412)) {
                                navController.navigate(R.id.action_userLoginFragment_to_completeYourProfileFragment);
                            }
                        } catch (Exception e1) {
                            Log.d("ERROR", e1.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void handleLoginError(Throwable throwable) {

        try {
            if (((HttpException) throwable).code() == 404) {
                Toast.makeText(getContext(), "User not found", Toast.LENGTH_SHORT).show();
            } else if ((((HttpException) throwable).code() == 412)) {
                Toast.makeText(getContext(), "Please verify your email.", Toast.LENGTH_SHORT).show();
            } else if (((HttpException) throwable).code() == 400) {
                Toast.makeText(getContext(), "Please enter correct email-id and password.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.d("TAGTAGTAG", throwable.toString());
            Toast.makeText(getContext(), "No internet!", Toast.LENGTH_SHORT).show();
        }

    }

}
