package com.mdgiitr.karthik.cognizance19.view;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.mdgiitr.karthik.cognizance19.models.SignupResponse;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

import static com.mdgiitr.karthik.cognizance19.MainActivity.REGISTRATION_TYPE_PARTICIPANT;
import static com.mdgiitr.karthik.cognizance19.MainActivity.REGISTRATION_TYPE_SPP;
import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class RegisterFragment extends Fragment {

    public static int REGISTRATION_TYPE = -1;
    EditText emailEditText, passwordEditText;
    private Button cancelAction, nextAction, contButton;
    private TextView signupParticipant, signupSPP;
    private Dialog typeDialog;
    private ProgressDialog progressDialog;
    private boolean isVisible = false, emailValid = false, passwordValid = false;
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
        contButton = view.findViewById(R.id.cont_Button);

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

        typeDialog = new Dialog(getContext());
        typeDialog.setContentView(R.layout.registration_type_dialog);
        typeDialog.setCancelable(false);
        typeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        signupParticipant = typeDialog.findViewById(R.id.signup_as_participant);
        signupSPP = typeDialog.findViewById(R.id.signup_as_spp);

        cancelAction = typeDialog.findViewById(R.id.cancel_action);
        nextAction = typeDialog.findViewById(R.id.next_action);

        signupSPP.setOnClickListener((View v) -> {
            signupSPP.setTextColor(Color.BLUE);
            signupParticipant.setTextColor(getActivity().getResources().getColor(R.color.userType_gray));
            REGISTRATION_TYPE = REGISTRATION_TYPE_SPP;
            nextAction.setEnabled(true);
        });

        signupParticipant.setOnClickListener((View v) -> {
            signupParticipant.setTextColor(Color.BLUE);
            signupSPP.setTextColor(getActivity().getResources().getColor(R.color.userType_gray));
            REGISTRATION_TYPE = REGISTRATION_TYPE_PARTICIPANT;
            nextAction.setEnabled(true);
        });

        cancelAction.setOnClickListener((View v) -> {
            typeDialog.dismiss();
            navController.navigateUp();
        });

        nextAction.setOnClickListener((View v) -> typeDialog.dismiss());

        if (isVisible)
            typeDialog.show();

        contButton.setOnClickListener((View v) -> {

            progressDialog.show();

            if (REGISTRATION_TYPE == REGISTRATION_TYPE_PARTICIPANT) {
                apiClient.signUpRemote(emailEditText.getText().toString(), "cogni_user", passwordEditText.getText().toString())
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
                                Log.d("TAGTAGTAG", preferenceHelper.getToken());
                                Toast.makeText(getContext(), signupResponse.message, Toast.LENGTH_SHORT).show();
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
            } else {
                apiClient.signUpRemote(emailEditText.getText().toString(), "spp", passwordEditText.getText().toString())
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
                                Toast.makeText(getContext(), signupResponse.message, Toast.LENGTH_SHORT).show();
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
            }

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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser)
            isVisible = isVisibleToUser;
        if (isVisibleToUser && typeDialog != null)
            typeDialog.show();
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
