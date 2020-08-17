package com.example.bpcrsadmin.screen.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.auth0.android.jwt.JWT;
import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.model.Account;
import com.example.bpcrsadmin.screen.home.HomeActivity;
import com.example.bpcrsadmin.utils.SharedPreferenceUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    private Button btLogin;
    private ProgressBar progressBar;
    private Task<GoogleSignInAccount> mGoogleSignInClient;
    private GoogleSignInClient mClient;
    private int RC_SIGN_IN = 200;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btLogin.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id)).requestEmail().build();
        mClient = GoogleSignIn.getClient(this, gso);
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
//            .silentSignIn().addOnCompleteListener(
//                this,
//                new OnCompleteListener<GoogleSignInAccount>() {
//                    @Override
//                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
//                        handleSignInResult(task);
//                    }
//                });;
    }

    public void init() {
        btLogin = findViewById(R.id.bt_loginGmail);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        btLogin.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        Intent signInIntent = mClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        // This task is always completed immediately, there is no need to attach an
// asynchronous listener.

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult( Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String idToken = account.getIdToken();
            Log.d("TOKEN", "token: " + idToken);
            // Signed in successfully, show authenticated UI.
            loginPresenter = new LoginPresenter(this, this);
            loginPresenter.loginWithGoogle(idToken);
//            updateUI(account);
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("LOGIN", "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onSuccessLogin(String jwt) {
        if (jwt != null) {
            decodeJWT(jwt);
            SharedPreferenceUtils.saveJwtToken(this, jwt);
        }
    }

    @Override
    public void onFailLogin() {

    }

    public void decodeJWT(String token) {
        JWT jwt = new JWT(token);
        String subject=  jwt.getSubject();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Account user = gson.fromJson(subject, Account.class);
        SharedPreferenceUtils.saveInfoUser(this, user);
    }

}
