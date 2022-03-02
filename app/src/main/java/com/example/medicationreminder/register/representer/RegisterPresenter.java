package com.example.medicationreminder.register.representer;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.medicationreminder.Network.FirebaseConnectionDelegated;
import com.example.medicationreminder.model.RepositoryInterface;
import com.example.medicationreminder.model.User;
import com.example.medicationreminder.register.view.RegisterInterFace;
import com.google.firebase.auth.FirebaseUser;

public class RegisterPresenter implements RegisterPresentationInterFace, FirebaseConnectionDelegated {
  Context context;
  RepositoryInterface repositoryInterface;
  RegisterInterFace registerInterFace;

  public RegisterPresenter(Context context, RepositoryInterface repositoryInterface, RegisterInterFace registerInterFace) {
    this.context = context;
    this.repositoryInterface = repositoryInterface;
    this.registerInterFace = registerInterFace;
  }

  @Override
  public void RegisterUser(User user, Activity activity) {
    Log.e(TAG, "RegisterUser:presenter ");
    repositoryInterface.registerNewUser(user,activity,this);
  }

  @Override
    public void onCompleteResultSuccess(FirebaseUser user) {
                registerInterFace.updateUser(user);
    }

    @Override
    public void onFailureResult(String errorMessage) {
              registerInterFace.registerFailed(errorMessage);
    }

  @Override
  public void onSuccess(Boolean result) {

  }


}
