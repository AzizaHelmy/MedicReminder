package com.example.medicationreminder.model;

import android.app.Activity;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.medicationreminder.Network.FirebaseConnectionDelegated;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

import java.util.List;

public interface RepositoryInterface {
    public void registerNewUser(User user, Activity activity, FirebaseConnectionDelegated firebaseConnectionDelegated);

    public void signIn(User user, Activity activity, FirebaseConnectionDelegated firebaseConnectionDelegated);

    boolean isUserSignIn();

    public void signWithGoogle(Activity activity);
    public void firebaseAuthWithGoogle(Activity activity, Task<GoogleSignInAccount> task, FirebaseConnectionDelegated firebaseConnectionDelegated) ;
    public boolean restPassword(String emil,FirebaseConnectionDelegated firebaseConnectionDelegated) ;
    public  void insertMed(Medication medication);
    public LiveData<List<Medication>> selectAllDrugsForHome(String day, LifecycleOwner owner);
    public LiveData<List<Medication>> selectAllDrugsForHome1(LifecycleOwner owner);
    LiveData<List<Medication>> displayDrug();
    public  void updateDrug(Medication medication);
    //public void displayMed(Medication medication);

    public List<Medication> selectAllDrugsForHome(String day);
    //========================For Medics===========================
    public LiveData<List<Medication>> getMedics(LifecycleOwner owner);
    public boolean isReminder(String medicName);
public  void deleteMedic(Medication medication);
    //============================For Invitation=================================
    void addRequest(Request reciverEmail);
    boolean cheackUser(String userEmail);
    public void setMyDelegate(FirebaseConnectionDelegated myDelegate);

}
