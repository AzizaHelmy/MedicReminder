package com.example.medicationreminder.Network;

import android.app.Activity;

import com.example.medicationreminder.model.User;

public interface FirebaseConnectionInterface {
    public void registerNewUser(User user, Activity activity,FirebaseConnectionDelegated firebaseConnectionDelegated);
    boolean   isUserSignIn();
    public  void signIn(User user,FirebaseConnectionDelegated firebaseConnectionDelegated);

}
