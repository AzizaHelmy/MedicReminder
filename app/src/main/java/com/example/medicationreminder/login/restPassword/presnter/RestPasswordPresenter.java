package com.example.medicationreminder.login.restPassword.presnter;

import android.content.Context;

import com.example.medicationreminder.Network.FirebaseConnectionDelegated;

import com.example.medicationreminder.login.restPassword.r.RestPasswordViewInterface;
import com.example.medicationreminder.model.RepositoryInterface;
import com.google.firebase.auth.FirebaseUser;

public class RestPasswordPresenter implements RestPasswordPresenterInterface , FirebaseConnectionDelegated {
    Context context;
    RepositoryInterface repositoryInterface;
    RestPasswordViewInterface restPasswordViewInterface;

    public RestPasswordPresenter(Context context, RepositoryInterface repositoryInterface, RestPasswordViewInterface restPasswordViewInterface) {
        this.context = context;
        this.repositoryInterface = repositoryInterface;
        this.restPasswordViewInterface = restPasswordViewInterface;
    }

    @Override
    public boolean restPassword(String emil) {
        return repositoryInterface.restPassword(emil ,this);
    }

    @Override
    public void onCompleteResultSuccess(FirebaseUser user) {
             restPasswordViewInterface.successLogin(user);
    }

    @Override
    public void onFailureResult(String errorMessage) {
        restPasswordViewInterface.loginFailed(errorMessage);

    }

    @Override
    public void onSuccess(Boolean result) {

    }
}
