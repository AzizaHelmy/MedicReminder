package com.example.medicationreminder.Network;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public interface FirebaseConnectionDelegated {


    public void onCompleteResultSuccess(FirebaseUser user);
    public void onFailureResult(String errorMessage);

    public void onSuccess(Boolean result);

   // public boolean onFailure(String userEmail);

}
