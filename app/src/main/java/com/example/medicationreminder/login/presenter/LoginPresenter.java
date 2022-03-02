package com.example.medicationreminder.login.presenter;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.medicationreminder.Network.FirebaseConnectionDelegated;
import com.example.medicationreminder.login.view.LoginViewInterface;
import com.example.medicationreminder.model.Repository;
import com.example.medicationreminder.model.RepositoryInterface;
import com.example.medicationreminder.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter implements LoginPresenterInterface,FirebaseConnectionDelegated{

    Context context;
    RepositoryInterface repositoryInterface;
   LoginViewInterface loginViewInterface;

    public LoginPresenter(Context context, RepositoryInterface repositoryInterface, LoginViewInterface loginViewInterface) {
        this.context = context;
        this.repositoryInterface = repositoryInterface;
        this.loginViewInterface = loginViewInterface;
    }

    @Override
    public void login(User user, Activity activity) {
         repositoryInterface.signIn(user,activity,this);
    }

    @Override
    public void onCompleteResultSuccess(FirebaseUser user) {

             loginViewInterface.successLogin(user);


    }

    @Override
    public void onFailureResult(String errorMessage) {
        loginViewInterface.loginFailed(errorMessage);

    }

    @Override
    public void onSuccess(Boolean result) {

    }

    @Override
    public boolean isUserSignIn() {
        return  repositoryInterface.isUserSignIn();
    }

    @Override
    public void signWithGoogle(Activity activity) {
        Log.e(TAG, "signWithGoogle: presenter");
        repositoryInterface.signWithGoogle(activity);
    }

    @Override
    public void firebaseAuthWithGoogle(Activity activity, Task<GoogleSignInAccount> task) {
           repositoryInterface.firebaseAuthWithGoogle(activity,task,this);
    }



}
