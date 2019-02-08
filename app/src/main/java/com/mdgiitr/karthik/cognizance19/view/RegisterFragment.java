package com.mdgiitr.karthik.cognizance19.view;

import android.app.ProgressDialog;
import android.content.Intent;
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

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.mdgiitr.karthik.cognizance19.EmailPasswordValidator;
import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.FbGoogleLoginModel;
import com.mdgiitr.karthik.cognizance19.models.SignupResponse;
import com.mdgiitr.karthik.cognizance19.network.client.ApiClient;
import com.mdgiitr.karthik.cognizance19.utils.PreferenceHelper;

import org.json.JSONException;

import java.util.Arrays;

import androidx.navigation.NavOptions;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

import static com.mdgiitr.karthik.cognizance19.EmailPasswordValidator.isPhoneValid;
import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;
import static com.mdgiitr.karthik.cognizance19.view.UserLoginFragment.setViewPagerFragment;

public class RegisterFragment extends Fragment {

    private static final String EMAIL = "email";
    public static int REGISTRATION_TYPE = -1, RC_SIGN_IN = 100;
    private EditText emailEditText, passwordEditText, nameEditText, mobileEditText;
    private Button contButton;
    private CallbackManager callbackManager;
    private ProgressDialog progressDialog;
    private boolean isVisible = false, emailValid = false, passwordValid = false, nameFilled = false, phoneFilled = false;
    private PreferenceHelper preferenceHelper;
    private ApiClient apiClient;
    private View signInGoogle, signInFacebook;
    private GoogleSignInOptions gso;
    private GoogleSignInClient googleSignInClient;

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

        signInGoogle = view.findViewById(R.id.google_login_Button);
        signInFacebook = view.findViewById(R.id.fb_login_Button);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        callbackManager = CallbackManager.Factory.create();

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
//                                navController.navigate(R.id.action_userLoginFragment_to_onBoardingFragment);
                            Toast.makeText(getContext(), signupResponse.message + "\nPlease verify your email.", Toast.LENGTH_SHORT).show();
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

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("TAGTAGTAG", "SUCCESS");
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), (object, response) -> {
                    try {
                        String email = object.getString("email");
                        String id = object.getString("id");
                        String name = object.getString("name");
                        String accessToken = loginResult.getAccessToken().toString();
                        String imgUrl = "https://graph.facebook.com/" + id + "/picture?type=small";
                        facebookLogin(email, name, imgUrl, accessToken);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        signInGoogle.setOnClickListener(v -> googleSignIn());

        signInFacebook.setOnClickListener(v -> LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("email", "public_profile")));

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

    private void handleSignupResponse(SignupResponse signupResponse) {

        preferenceHelper.setToken(signupResponse.token);
        Toast.makeText(getContext(), signupResponse.message, Toast.LENGTH_SHORT).show();
        navController.navigateUp();

    }

    private void googleSignIn() {

        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    private void facebookLogin(String email, String name, String imgUrl, String accessToken) {

        FbGoogleLoginModel loginModel = new FbGoogleLoginModel();
        loginModel.setEmail(email);
        loginModel.setAccessToken(accessToken);
        loginModel.setImageUrl(imgUrl);
        loginModel.setName(name);
        loginModel.setType("facebook");
        apiClient.fbGoogleLogin(loginModel)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignupResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SignupResponse signupResponse) {
                        handleLoginResponse(signupResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "Unable to login", Toast.LENGTH_SHORT).show();
                        Log.d("FACEBOOK_LOGIN_ERROR", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleSignInResult(task);
        }
    }

    private void handleGoogleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
        } catch (ApiException e) {
            Log.d("GOOGLE_SIGNIN_FAILED", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void handleLoginResponse(SignupResponse signupResponse) {
        preferenceHelper.setLoginStatus(true);
        preferenceHelper.setToken(signupResponse.token);
        Toast.makeText(getContext(), "Successfully logged in!", Toast.LENGTH_SHORT).show();
        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.landingFragment2, true)
                .build();
        navController.navigate(R.id.action_userLoginFragment_to_homeMenuFragment, null, navOptions);
    }

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
    }
}
