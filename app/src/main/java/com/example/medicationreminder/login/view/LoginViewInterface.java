package com.example.medicationreminder.login.view;

import androidx.lifecycle.LifecycleOwner;

import com.google.firebase.auth.FirebaseUser;

public interface LoginViewInterface {


    public void loginFailed(String errorMessage);
    public void successLogin(FirebaseUser user);

}
