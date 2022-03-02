package com.example.medicationreminder.model;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.medicationreminder.Network.FirebaseConnectionDelegated;
import com.example.medicationreminder.Network.FirebaseConnectionInterface;
import com.example.medicationreminder.db.LocalSource;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class Repository  implements RepositoryInterface{

    Context context;
    FirebaseConnectionInterface firebaseConnection;
    LocalSource localSource;
    private static Repository repository = null;
    private DrugDao drugDao;

    public Repository(Context context, FirebaseConnectionInterface firebaseConnection, LocalSource localSource) {
        this.context = context;
        this.firebaseConnection = firebaseConnection;
        this.localSource = localSource;
    }

    public static Repository getRepository(Context context, FirebaseConnectionInterface firebaseConnection, LocalSource localSource){
        if (repository==null){
            repository=new Repository(context,firebaseConnection,localSource);
        }
        return repository;
    }

    @Override
    public void registerNewUser(User user, Activity activity , FirebaseConnectionDelegated firebaseConnectionDelegated) {
        Log.e(TAG, "registerNewUser:repository ");
        firebaseConnection.registerNewUser(user,activity ,firebaseConnectionDelegated);
    }

    @Override
    public void signIn(User user, Activity activity, FirebaseConnectionDelegated firebaseConnectionDelegated) {
        firebaseConnection.signIn(user,activity,firebaseConnectionDelegated);
    }

    @Override
    public boolean isUserSignIn() {
        return firebaseConnection.isUserSignIn();
    }

    @Override
    public void signWithGoogle(Activity activity) {
        Log.e(TAG, "signWithGoogle:+repo ");
        firebaseConnection.signWithGoogle(activity);
    }

    @Override
    public void firebaseAuthWithGoogle(Activity activity, Task<GoogleSignInAccount> task, FirebaseConnectionDelegated firebaseConnectionDelegated) {
        Log.e(TAG, "firebaseAuthWithGoogle:auth repo ");
             firebaseConnection.firebaseAuthWithGoogle(activity,task,firebaseConnectionDelegated);
    }

    @Override
    public boolean restPassword(String emil,FirebaseConnectionDelegated firebaseConnectionDelegated) {
      return    firebaseConnection.restPassword(emil,firebaseConnectionDelegated);
    }

    @Override
    public void insertMed(Medication medication) {
            localSource.insertDrug(medication);
    }

    @Override
    public LiveData<List<Medication>> displayDrug() {
        return (LiveData<List<Medication>>) localSource.dispalyedDrug();
    }

    @Override
    public void updateDrug(Medication medication) {
        localSource.editeDrug(medication);
    }


    @Override
    public LiveData<List<Medication>>selectAllDrugsForHome(String day , LifecycleOwner owner) {
      return   localSource.selectAllDrugs(day);
    }

    @Override
    public LiveData<List<Medication>> selectAllDrugsForHome1(LifecycleOwner owner) {
        return  localSource.selectAllDrugs1();
    }

    @Override
    public List<Medication> selectAllDrugsForHome(String day) {
        return null;
    }

    //=============================for Medics Screen==============================
    @Override
    public LiveData<List<Medication>> getMedics(LifecycleOwner owner) {
        return localSource.getAllMedics();
    }

    //=========================================
    @Override
    public void addRequest(Request request) {
       // getInfo();
//        Request request = new Request(senderName, reciverEmail, senderEmail, senderImg, medics);      //  Request healthTake = new Request(senderName, reciverEmail, senderEmail, senderImg, medications);
//        FirebaseDatabase.getInstance().getReference("Request").push().setValue(request);
        // Toast.makeText(, "Invitation Sent Successfully", Toast.LENGTH_SHORT).show();

        firebaseConnection.addRequest(request);
    }

    //===================================================================
    @Override
    public boolean cheackUser(String userEmail) {
        return firebaseConnection.cheackUser(userEmail);
    }

    @Override
    public void setMyDelegate(FirebaseConnectionDelegated myDelegate) {
        firebaseConnection.setMyDelegate(myDelegate);
    }


    //==============================================================
    @Override
    public boolean isReminder(String medicName) {
      return   localSource.isReminder(medicName);
    }

    @Override
    public void deleteMedic(Medication medication) {
        localSource.deleteDrug(medication);
    }

//    @Override
//    public boolean isReminder(String medicName) {
//        Reminder reminder =new Reminder();
//        reminder.setName(medicName);
//        Thread th=new Thread(reminder);
//        th.start();
//        try {
//            th.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return  reminder.isFlag();
//    }



}
