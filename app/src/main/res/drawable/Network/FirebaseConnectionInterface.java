package com.example.medicationreminder.Network;

import android.app.Activity;

import com.example.medicationreminder.models.User;

public interface FirebaseConnectionInterface {
    public void registerNewUser(User user, Activity activity,FirebaseConnectionDelegated firebaseConnectionDelegated);
    boolean   isUserSignIn();
    public  void getUser(String usrId);
//    public void UpdateUser(FirebaseConnectionDelegated firebaseConnectionDelegated);
}
