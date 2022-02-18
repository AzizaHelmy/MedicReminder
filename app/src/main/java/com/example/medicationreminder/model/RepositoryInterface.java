package com.example.medicationreminder.model;

import android.app.Activity;

import com.example.medicationreminder.Network.FirebaseConnectionDelegated;

public interface RepositoryInterface {
    public void registerNewUser(com.example.medicationreminder.model.User user, Activity activity , FirebaseConnectionDelegated firebaseConnectionDelegated);

//    public  void updateUser(FirebaseConnectionDelegated firebaseConnectionDelegated);

}
