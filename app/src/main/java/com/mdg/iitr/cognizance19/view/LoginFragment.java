package com.mdg.iitr.cognizance19.view;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.mdg.iitr.cognizance19.AsyncResponse;
import com.mdg.iitr.cognizance19.R;
import com.mdg.iitr.cognizance19.models.FbGoogleLoginModel;
import com.mdg.iitr.cognizance19.models.LoginResponse;
import com.mdg.iitr.cognizance19.models.SignupResponse;
import com.mdg.iitr.cognizance19.models.UserSPPResponseModel;
import com.mdg.iitr.cognizance19.network.client.ApiClient;
import com.mdg.iitr.cognizance19.utils.EmailPasswordValidator;
import com.mdg.iitr.cognizance19.utils.PreferenceHelper;

import org.json.JSONException;

import java.util.Arrays;

import androidx.navigation.NavOptions;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

import static com.mdg.iitr.cognizance19.MainActivity.REGISTRATION_TYPE_PARTICIPANT;
import static com.mdg.iitr.cognizance19.MainActivity.REGISTRATION_TYPE_SPP;
import static com.mdg.iitr.cognizance19.MainActivity.navController;
import static com.mdg.iitr.cognizance19.view.RegisterFragment.RC_SIGN_IN;
import static com.mdg.iitr.cognizance19.view.RegisterFragment.REGISTRATION_TYPE;

public class LoginFragment extends Fragment implements AsyncResponse {

    public RetrieveTokenTask retrieveTokenTask;
    private Button contButton;
    private EditText emailEditText, passwordEditText;
    private boolean emailValid = false, passwordValid = false;
    private ApiClient apiClient;
    private PreferenceHelper preferenceHelper;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;
    private CallbackManager callbackManager;
    private View signInGoogle, signInFacebook;
    private GoogleSignInOptions gso;
    private GoogleSignInClient googleSignInClient;
    private String name, email, imageUrl, accessToken;

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

        retrieveTokenTask = new LoginFragment.RetrieveTokenTask();
        retrieveTokenTask.asyncResponse = this;

        apiClient = new ApiClient();
        preferenceHelper = new PreferenceHelper(getActivity());

        signInGoogle = view.findViewById(R.id.google_login_Button);
        signInFacebook = view.findViewById(R.id.fb_login_Button);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        callbackManager = CallbackManager.Factory.create();

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
        if (emailValid && passwordValid) {
            contButton.setEnabled(true);
        } else {
            contButton.setEnabled(false);
        }
    }

    private void googleSignIn() {

        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        progressDialog.show();

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

    private void googleLogin(FbGoogleLoginModel googleLoginModel) {
        apiClient.fbGoogleLogin(googleLoginModel)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignupResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SignupResponse signupResponse) {
                        progressDialog.dismiss();
                        handleLoginResponse(signupResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Unable to login", Toast.LENGTH_SHORT).show();
                        Log.d("GOOGLE_LOGIN_ERROR", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void handleGoogleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            if (account.getPhotoUrl() != null) {
                imageUrl = account.getPhotoUrl().toString();
            }
            name = account.getDisplayName();
            email = account.getEmail();
            retrieveTokenTask.execute(email);
        } catch (ApiException e) {
            progressDialog.dismiss();
            Log.d("GOOGLE_SIGNIN_FAILED", "signInResult:failed code=" + e.getStatusCode());
        }
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

    @Override
    public void processFinish(String output) {

        accessToken = output;
        FbGoogleLoginModel googleLoginModel = new FbGoogleLoginModel();
        googleLoginModel.setAccessToken(accessToken);
        googleLoginModel.setName(name);
        googleLoginModel.setImageUrl(imageUrl);
        googleLoginModel.setEmail(email);
        googleLoginModel.setType("google");
        googleLogin(googleLoginModel);

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

    private void handleLoginResponse(SignupResponse signupResponse) {
        preferenceHelper.setLoginStatus(true);
        preferenceHelper.setToken(signupResponse.token);
        Toast.makeText(getContext(), "Successfully logged in!", Toast.LENGTH_SHORT).show();
        navController.navigate(R.id.action_userLoginFragment_to_completeYourProfileFragment);
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

    private class RetrieveTokenTask extends AsyncTask<String, Void, String> {
        public AsyncResponse asyncResponse = null;

        @Override
        protected String doInBackground(String... params) {
            String acctName = params[0];
            String scopes = "oauth2:profile email";
            String token = null;
            try {
                token = GoogleAuthUtil.getToken(getActivity(), acctName, scopes);
            } catch (Exception e) {
                progressDialog.dismiss();
                Log.d("GOOGLE_TOKEN_ERROR", e.toString());
            }
            return token;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            asyncResponse.processFinish(s);
        }
    }

}
