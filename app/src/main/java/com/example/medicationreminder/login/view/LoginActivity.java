package com.example.medicationreminder.login.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.example.medicationreminder.MainActivity;
import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.databinding.ActivityLoginBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.login.restPassword.r.RestPassword;
import com.example.medicationreminder.login.presenter.LoginPresenter;
import com.example.medicationreminder.login.presenter.LoginPresenterInterface;
import com.example.medicationreminder.model.Repository;
import com.example.medicationreminder.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements LoginViewInterface{
       ActivityLoginBinding binding;
    LoginPresenterInterface loginPresenterInterface;
    FirebaseUser currentUser=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        loginPresenterInterface=new LoginPresenter(this, Repository.getRepository(this, FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(this)),this);
    clearEditText();


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: ");
        if (requestCode == FirebaseConnection.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            Log.d("DOAA",""+task.getResult().getEmail());
           loginPresenterInterface. firebaseAuthWithGoogle(this,task);
           successLogin(null);
        }}

    @Override
    protected void onStart() {
        super.onStart();

        if (loginPresenterInterface.isUserSignIn()){
            Log.e(TAG, "onStart: if");
            Intent intent=new Intent(this, MainActivity.class);

            startActivity(intent);
            finish();
        }


    }

    @Override
    public void loginFailed(String errorMessage) {
        Log.e(TAG, "loginFailed: "+errorMessage.toString());
        AlertDialog alertDialog = new AlertDialog.Builder(this)

                .setIcon(android.R.drawable.ic_dialog_alert)

                .setTitle("Medical Reminder")

                .setMessage(errorMessage)

                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                }).show();
    }

    @Override
    public void successLogin(FirebaseUser user) {
        binding.progressBar.setVisibility(View.GONE);
         currentUser=user;
        Intent intent=new Intent(this, MainActivity.class);
        intent.putExtra("currentUser",user);
        startActivity(intent);
        finish();

       // Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
    }

    private boolean validateEmail() {
        if (binding.loginEmail.getEditText().getText().toString().isEmpty()) {
            binding.loginEmail.setError("Required *");


            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.loginEmail.getEditText().getText().toString()).matches()) {
            binding.loginEmail.setError("email must be like  example@gmail.com");


            return false;
        } else {
            binding.loginEmail.setError("");

            return true;
        }
    }

    public boolean isValidPassword() {

//        final String PASSWORD_PATTERN = "String = \"(?=.*[0-9a-zA-Z]).{8,}\"";
//        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
//        Matcher matcher = pattern.matcher(binding.password.getEditText().getText().toString());
//
//        if (binding.password.getEditText().getText().toString().isEmpty()) {
//            binding.password.setError("Required*");
//
//
//            return false;
//        } else if (matcher.matches() == false) {
//            binding.password.setError("password too week");
//
//
//            return false;

//        }
//        else {
//            binding.email.setError("");

        return true;
        //    }


    }
    public Boolean validLoginData() {

        Boolean isValid = true;

        if (validateEmail() == false) {
            isValid = false;

        }
        if (isValidPassword() == false) {
            isValid = false;


        }


        return isValid;

    }
    public void onLoginClick(View view) {
       if(validLoginData()) {
           User newUser = new User();
           newUser.setUserEmail(binding.loginEmail.getEditText().getText().toString());
           newUser.setPassword(binding.loginPassword.getEditText().getText().toString());

           Log.e(TAG, "onLoginClick: "+binding.loginPassword.getEditText().getText().toString());
           loginPresenterInterface.login(newUser, this);
       }
    }

    public void onForgetPasswordClick(View view) {


         Intent intent=new Intent(this, RestPassword.class);
         startActivity(intent);




        }


    public void loginWithGoogle(View view) {
        Log.e(TAG, "loginWithGoogle: +click");
        loginPresenterInterface.signWithGoogle(this);
    }

    public void loginWithFacebook(View view) {


    }
    void clearEditText(){

        binding.loginEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // binding.userName.setError("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.loginEmail.setError("");
            }
        });
        binding.loginPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // binding.userName.setError("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.loginPassword.setError("");
            }
        });



    }
}