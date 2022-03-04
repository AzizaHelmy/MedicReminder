package com.example.medicationreminder.healthTakers.addTaker.presenter;

import static com.example.medicationreminder.register.view.RegisterActivity.SHARD_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.medicationreminder.Network.FirebaseConnectionDelegated;
import com.example.medicationreminder.healthTakers.addTaker.view.AddTakerInterface;
import com.example.medicationreminder.healthTakers.request.view.HealthTakersInterface;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.RepositoryInterface;
import com.example.medicationreminder.model.Request;
import com.example.medicationreminder.register.view.RegisterActivity;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddTakerPresenter implements AddTakerPresenterInterface, FirebaseConnectionDelegated {
    Context context;
    RepositoryInterface repositoryInterface;
    AddTakerInterface takerInterface;
    SharedPreferences sharedPreferences;
    String senderName;
    String senderEmail;
    String senderImg;

    List<Medication> medics = new ArrayList<>();

    public AddTakerPresenter(Context context, RepositoryInterface repositoryInterface, AddTakerInterface takerInterface) {
        this.context = context;
        this.repositoryInterface = repositoryInterface;
        this.takerInterface = takerInterface;
    }

    @Override
    public boolean checkUser(String userEmail) {
        repositoryInterface.setMyDelegate(this);
        return repositoryInterface.cheackUser(userEmail);
    }

    //=======================================================
    @Override
    public Object getMedicList(LifecycleOwner owner) {
        repositoryInterface.getMedics(owner).observe(owner, new Observer<List<Medication>>() {
            @Override
            public void onChanged(List<Medication> medications) {
                takerInterface.getMedics(medications);
                medics = medications;
            }
        });
        return null;
    }

    //====================================================
    @Override
    public void addRequest(String reciverEmail) {
        getInfo();
        //speak with repo==> i'm Failed!!
        // repositoryInterface.addRequest(reciverEmail);
        Request request = new Request(senderName, reciverEmail, senderEmail, senderImg, medics);      //  Request healthTake = new Request(senderName, reciverEmail, senderEmail, senderImg, medications);
        FirebaseDatabase.getInstance().getReference("Request").push().setValue(request);
        Toast.makeText(context, "Invitation Sent Successfully", Toast.LENGTH_SHORT).show();
    }

    //==============================================================
    private void getInfo() {
        sharedPreferences = context.getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE);
        senderName = sharedPreferences.getString(RegisterActivity.USER_NAME, "anoynmous");
        senderEmail = sharedPreferences.getString(RegisterActivity.EMAIL, "anoynmous@gmail.com");
        senderImg = "https://firebasestorage.googleapis.com/v0/b/medical-reminder-c0a4c.appspot.com/o/mateo-avila-chinchilla-x_8oJhYU31k-unsplash.jpg?alt=media&token=9f6c259e-aace-42ab-8549-f36505939740";
    }

    //==============================================================
    @Override
    public void onCompleteResultSuccess(FirebaseUser user) {

    }

    @Override
    public void onFailureResult(String errorMessage) {

    }

    @Override
    public void onSuccess(Boolean result) {
        takerInterface.onSuccess(result);
    }


//===================================================================
    //presenter will speack with network Delegate
//    @Override
//    public boolean onSuccess(String userEmail) {
//        return false;
//    }
//
//    @Override
//    public boolean onFailure(String userEmail) {
//        return false;
//    }
    //================================================================
}
