package com.example.medicationreminder.login.presenter;

import android.app.Activity;

import com.example.medicationreminder.Network.FirebaseConnectionDelegated;
import com.example.medicationreminder.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

public interface LoginPresenterInterface {
    public void login(User user, Activity activity);
    boolean   isUserSignIn();
    public void signWithGoogle(Activity activity);
    public void firebaseAuthWithGoogle(Activity activity, Task<GoogleSignInAccount> task) ;

}
