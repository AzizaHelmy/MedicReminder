package com.example.medicationreminder.Network;

import static android.content.ContentValues.TAG;

import static com.example.medicationreminder.register.view.RegisterActivity.SHARD_NAME;
import static com.example.medicationreminder.register.view.RegisterActivity.USER_NAME;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Context;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.example.medicationreminder.LoadingDialoge;
import com.example.medicationreminder.R;
import com.example.medicationreminder.healthTakers.addTaker.presenter.AddTakerPresenter;
import com.example.medicationreminder.model.Request;
import com.example.medicationreminder.model.User;
import com.example.medicationreminder.register.view.RegisterActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseConnection implements FirebaseConnectionInterface {

    public static final int RC_SIGN_IN = 1;
    private FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    String senderName;
    String senderEmail;
    String senderImg;
    private static FirebaseConnection getInstance = null;
    User newUser;
    ProgressDialog progressDialog;
    static String USER_REF = "user";
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser currentUser;
    private GoogleSignInClient googleSingInClient;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    FirebaseConnectionDelegated connectionDelegated;
    boolean flag = false;

    @SuppressLint("RestrictedApi")
    public FirebaseConnection() {
        mAuth = FirebaseAuth.getInstance();

        preferences = getApplicationContext().getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static FirebaseConnection getFirebaseConnection() {
        if (getInstance == null) {
            getInstance = new FirebaseConnection();
        }
        return getInstance;
    }

    @Override
    public void registerNewUser(User user, Activity activity, FirebaseConnectionDelegated firebaseConnectionDelegated) {


        mAuth.createUserWithEmailAndPassword(user.getUserEmail(), user.getPassword())
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user.setUserId(task.getResult().getUser().getUid());
                            addUser(user, firebaseConnectionDelegated);
                            currentUser = mAuth.getCurrentUser();
                            getUser(mAuth.getUid());
                            firebaseConnectionDelegated.onCompleteResultSuccess(currentUser);
                        } else {
                            firebaseConnectionDelegated.onFailureResult(task.getException().getLocalizedMessage());
                        }
                    }
                });
    }

    @Override
    public FirebaseUser isUserSignIn() {

        if (currentUser != null) {
            return currentUser;
        }

        return null;
    }

    public void addUser(User user, FirebaseConnectionDelegated firebaseConnectionDelegated) {

        UserDao.addUser(user, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    sendEmailValidation();

                } else {
                    firebaseConnectionDelegated.onFailureResult(task.getException().getMessage());
                }
            }
        });

    }


    @Override
    public void signIn(User user, Activity activity, FirebaseConnectionDelegated firebaseConnectionDelegated) {

        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(user.getUserEmail(), user.getPassword())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        currentUser = authResult.getUser();
                        getUser(mAuth.getUid());

                        firebaseConnectionDelegated.onCompleteResultSuccess(currentUser);

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                firebaseConnectionDelegated.onFailureResult(e.getLocalizedMessage());
                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());


            }
        });
    }

    private void sendEmailValidation() {
        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {


                } else {


                }
            }
        });
    }


    @Override
    public void signWithGoogle(Activity activity) {
        Log.e(TAG, "signWithGoogle: connectio");
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.web_client))
                .requestEmail()
                .build();
        googleSingInClient = GoogleSignIn.getClient(activity, gso);
        Intent intent = googleSingInClient.getSignInIntent();
        activity.startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    public void firebaseAuthWithGoogle(Activity activity, Task<GoogleSignInAccount> task, FirebaseConnectionDelegated firebaseConnectionDelegated) {

        try {
            mAuth = FirebaseAuth.getInstance();
            GoogleSignInAccount account = task.getResult(ApiException.class);
            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                currentUser = mAuth.getCurrentUser();
                                editor.putString(USER_NAME, currentUser.getDisplayName());
                                editor.putString(RegisterActivity.EMAIL, currentUser.getEmail());
//                               getUser(mAuth.getUid());
                                firebaseConnectionDelegated.onCompleteResultSuccess(currentUser);
                                //  googleSingInClient.signOut();


                            } else {
                                Log.e(TAG, "onComplete: else");
                                firebaseConnectionDelegated.onFailureResult(task.getException().getLocalizedMessage());
                            }
                        }
                    });
        } catch (ApiException e) {
            Log.e(TAG, "firebaseAuthWithGoogle: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

    }

    @Override
    public boolean restPassword(String emil, FirebaseConnectionDelegated firebaseConnectionDelegated) {
        final int[] isSuccess = {0};
        mAuth = FirebaseAuth.getInstance();
        mAuth.sendPasswordResetEmail(emil).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        })
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        isSuccess[0] = 1;

                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                firebaseConnectionDelegated.onFailureResult(e.getMessage());

            }
        });
        if (isSuccess[0] == 1) {
            return true;
        }

        return false;
    }

    //=================================================
    @Override
    public boolean cheackUser(String userEmail) {

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("user");
        myRef.orderByChild("userEmail").equalTo(userEmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    flag = true;

                }
                connectionDelegated.onSuccess(flag);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return flag;
    }

    //=====================================================
    @Override
    public void addRequest(Request request) {
        FirebaseDatabase.getInstance().getReference("Request").push().setValue(request);
    }

    //========================================================
    @Override
    public void setMyDelegate(FirebaseConnectionDelegated myDelegate) {
        connectionDelegated = myDelegate;
    }

    //==============================================================
    @Override
    public void singOut() {

        mAuth.signOut();
    }

    public void getUser(String id) {
        Log.e(TAG, "getUser: ");
        UserDao.getUser(id, new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    User currentUser = task.getResult().getValue(User.class);
                    editor.putString(USER_NAME, currentUser.getUserName());
                    Log.e(TAG, "onComplete: " + currentUser.getUserName());
                    editor.putString(RegisterActivity.EMAIL, currentUser.getUserEmail());
                    editor.commit();
                    editor.apply();

                } else {
                }
            }

        });


    }

}
