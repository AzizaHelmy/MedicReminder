package com.example.medicationreminder.register.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicationreminder.MainActivity;
import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.databinding.ActivityRegisterAcivityBinding;
import com.example.medicationreminder.models.Repository;
import com.example.medicationreminder.models.RepositoryInterface;
import com.example.medicationreminder.models.User;
import com.example.medicationreminder.register.representer.RegisterPresentationInterFace;
import com.example.medicationreminder.register.representer.RegisterPresenter;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements RegisterInterFace{
    public static final String SHARD_NAME="shardName";
    public static final String USER_NAME="userName";
    public static final String EMAIL="email";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private ActivityRegisterAcivityBinding binding;
    RepositoryInterface repository;
    RegisterPresentationInterFace registerPresentationInterFace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterAcivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        registerPresentationInterFace=new RegisterPresenter(this, Repository.getRepository(this, FirebaseConnection.getFirebaseConnection()),this);
        setContentView(view);
        clearEditText();

    }



    public Boolean validDataRegister() {

        Boolean isValid = true;
        if (isValidUsername() == false) {
            isValid = false;

        }

        if (validateEmail() == false) {
            isValid = false;

        }
        if (isValidPassword() == false) {
            isValid = false;


        }
        if (isValidConfirmPassword() == false) {
            isValid = false;

        }

        return isValid;

    }

    public boolean isValidUsername() {
        String regex = "^[a-zA-Z0-9]\\w{5,10}+([_ -]?[a-zA-Z0-9])*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(binding.userName.getEditText().getText());

        if (binding.userName.getEditText().getText().toString().isEmpty()) {
            binding.userName.setError("uer name cant be empty");
            return false;
        } else if (m.matches() == false) {
            binding.userName.setError("user name  must contain 5 to 10 letter");


            return false;

        } else {
            binding.userName.setError("");

            return true;
        }
    }


    private boolean validateEmail() {
        if (binding.email.getEditText().getText().toString().isEmpty()) {
            binding.email.setError("Required *");


            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.email.getEditText().getText().toString()).matches()) {
            binding.email.setError("email must be like  example@gmail.com");


            return false;
        } else {
            binding.email.setError("");

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

    public boolean isValidConfirmPassword() {


        if (binding.confirmPassword.getEditText().getText().toString().isEmpty()) {
            binding.confirmPassword.setError("Required*");

            return false;
        } else if (!binding.confirmPassword.getEditText().getText().toString().equals(binding.password.getEditText().getText().toString())) {
            binding.confirmPassword.setError("don't match password");

            return false;

        } else {
            binding.confirmPassword.setError("");

            return true;
        }

    }


    @Override
    public void updateUser(FirebaseUser currentUser) {
        binding.progressBar.setVisibility(View.GONE);

        saveCurrentUser();
        Intent intent=new Intent(this, MainActivity.class);
             startActivity(intent);
             finish();

    }

    @Override
    public void registerFailed(String errorMessage) {
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

    public void onRegisterClick(View view) {

              if (validDataRegister()){
                  binding.progressBar.setVisibility(View.VISIBLE);

                  User newUser=new User();
                  newUser.setUserName(binding.userName.getEditText().getText().toString());
                  newUser.setUserEmail(binding.email.getEditText().getText().toString());
                  newUser.setPassword(binding.password.getEditText().getText().toString());

                  registerPresentationInterFace.RegisterUser(newUser, RegisterActivity.this);


              }

    }

    public void saveCurrentUser( ) {

  sharedPreferences=getSharedPreferences(SHARD_NAME,this.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString(USER_NAME,binding.userName.getEditText().getText().toString());
        editor.putString(EMAIL,binding.email.getEditText().getText().toString());
        editor.commit();


    }
    void clearEditText(){
        binding.userName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                   // binding.userName.setError("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.userName.setError("");
            }
        });
        binding.email.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // binding.userName.setError("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.userName.setError("");
            }
        });
        binding.password.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // binding.userName.setError("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.userName.setError("");
            }
        });
        binding.confirmPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // binding.userName.setError("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.userName.setError("");
            }
        });


    }


}
