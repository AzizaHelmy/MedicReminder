package com.example.medicationreminder.Network;

import android.app.Activity;

import com.example.medicationreminder.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

public interface FirebaseConnectionInterface {
    public void registerNewUser(User user, Activity activity,FirebaseConnectionDelegated firebaseConnectionDelegated);
    boolean   isUserSignIn();
    public  void signIn(User user,Activity activity,FirebaseConnectionDelegated firebaseConnectionDelegated);
    public void signWithGoogle(Activity activity);
    public void firebaseAuthWithGoogle(Activity activity, Task<GoogleSignInAccount> task, FirebaseConnectionDelegated firebaseConnectionDelegated) ;
    public boolean restPassword(String emil,FirebaseConnectionDelegated firebaseConnectionDelegated) ;

    }
