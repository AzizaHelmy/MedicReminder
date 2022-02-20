package com.example.medicationreminder.login.restPassword.view;

import com.google.firebase.auth.FirebaseUser;

public interface RestPasswordViewInterface {
    public void loginFailed(String errorMessage);
    public void successLogin(FirebaseUser user);
}
