package com.example.medicationreminder.Network;

import com.google.firebase.auth.FirebaseUser;

public interface FirebaseConnectionDelegated {


    public void onCompleteResultSuccess(FirebaseUser user);
    public void onFailureResult(String errorMessage);
}
