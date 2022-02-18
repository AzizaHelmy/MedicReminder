package com.example.medicationreminder.Network;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.medicationreminder.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConnection implements FirebaseConnectionInterface {

    private FirebaseAuth mAuth;

    private static FirebaseConnection getInstance = null;
    User newUser;
    static String USER_REF = "user";
    FirebaseDatabase database ;
    DatabaseReference myRef;
    FirebaseUser currentUser;



    public static FirebaseConnection getFirebaseConnection() {
        if (getInstance == null) {
            getInstance = new FirebaseConnection();
        }
        return getInstance;
    }

    @Override
    public void registerNewUser(User user, Activity activity,FirebaseConnectionDelegated firebaseConnectionDelegated) {

        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(user.getUserEmail(),user.getPassword())
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user.setUserId(task.getResult().getUser().getUid());
                               addUser(user);

                                currentUser= mAuth.getCurrentUser();
                                firebaseConnectionDelegated.onCompleteResultSuccess(currentUser);
                        } else {
                            Log.e(TAG, "onComplete: "+task.getException().getLocalizedMessage());
                          firebaseConnectionDelegated.onFailureResult(task.getException().getLocalizedMessage());


                        }
                    }
                });
    }


//    @Override
//    public void UpdateUser(FirebaseConnectionDelegated firebaseConnectionDelegated) {
//        Log.e(TAG, "UpdateUser: in nretoe");
//        firebaseConnectionDelegated.onCompleteResultSuccess(currentUser);
//    }

    @Override
    public boolean isUserSignIn() {


        return false;
    }



    public void addUser(User user) {
        Log.e(TAG, "addUser: ");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(USER_REF);
        myRef.child(user.getUserId()).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                      if (error==null){
                            sendEmailValidation();

                      }
                      else {


                      }
            }


        });
    }




    @Override
    public void getUser(String usrId) {



    }


    private  void sendEmailValidation(){
        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Log.e("nnnnn", "email verhication");

                } else {
                    Log.e("nnnnn", "cant" + task.getException().getMessage());

                }
            }
        });
    }
}
