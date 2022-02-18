package com.example.medicationreminder.register.view;

import com.google.firebase.auth.FirebaseUser;

public interface RegisterInterFace {
    public  void updateUser(FirebaseUser currentUser);
    public  void registerFailed(String errorMessage);

}
