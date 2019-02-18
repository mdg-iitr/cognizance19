package com.mdg.iitr.cognizance19.view;

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
import android.widget.Toast;

import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.models.SignupResponse;
import com.mdg.iitr.cognizance19.network.client.ApiClient;
import com.mdg.iitr.cognizance19.utils.EmailPasswordValidator;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

import static com.mdg.iitr.cognizance19.MainActivity.navController;
import static com.mdg.iitr.cognizance19.utils.EmailPasswordValidator.isPhoneValid;
import static com.mdg.iitr.cognizance19.view.UserLoginFragment.setViewPagerFragment;

public class RegisterFragment extends Fragment {

    private static final String EMAIL = "email";
    public static int REGISTRATION_TYPE = -1, RC_SIGN_IN = 100;
    private EditText emailEditText, passwordEditText, nameEditText, mobileEditText;
    private Button contButton;
    private ProgressDialog progressDialog;
    private boolean isVisible = false, emailValid = false, passwordValid = false, nameFilled = false, phoneFilled = false;
    private PreferenceHelper preferenceHelper;
    private ApiClient apiClient;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        preferenceHelper = new PreferenceHelper(getActivity());

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Registering, please wait.");

        apiClient = new ApiClient();

        emailEditText = view.findViewById(R.id.email_editText);
        passwordEditText = view.findViewById(R.id.password_editText);
        nameEditText = view.findViewById(R.id.name_editText);
        mobileEditText = view.findViewById(R.id.number_editText);
        contButton = view.findViewById(R.id.cont_Button);

        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    nameFilled = true;
                } else {
                    nameFilled = false;
                }
                isEmailPwValid();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mobileEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isPhoneValid(s.toString())) {
                    phoneFilled = true;
                } else {
                    phoneFilled = false;
                }
                isEmailPwValid();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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

        contButton.setOnClickListener((View v) -> {

            progressDialog.show();

            apiClient.signUpRemote(emailEditText.getText().toString(), "cogni_user", passwordEditText.getText().toString(), nameEditText.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SignupResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(SignupResponse signupResponse) {
                            progressDialog.dismiss();
                            preferenceHelper.setToken(signupResponse.token);
                            setViewPagerFragment(0);
                            navController.navigate(R.id.action_userLoginFragment_to_completeYourProfileFragment);
//                            Toast.makeText(getContext(), signupResponse.message + "\nPlease verify your email.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {
                            progressDialog.dismiss();
                            handleSignupError(e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });


        });


        return view;
    }

    private void isEmailPwValid() {
        if (emailValid && passwordValid && phoneFilled && nameFilled) {
            contButton.setEnabled(true);
        } else {
            contButton.setEnabled(false);
        }
    }

    private void handleSignupError(Throwable throwable) {

        try {
            if (((HttpException) throwable).code() == 400) {
                Toast.makeText(getContext(), "User Already Registered", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.d("TAGTAGTAG", throwable.toString());
            Toast.makeText(getContext(), "No internet!", Toast.LENGTH_SHORT).show();
        }

    }


}
