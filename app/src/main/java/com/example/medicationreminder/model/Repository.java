package com.example.medicationreminder.model;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.medicationreminder.Network.FirebaseConnectionDelegated;
import com.example.medicationreminder.Network.FirebaseConnectionInterface;

public class Repository  implements RepositoryInterface{

    Context context;
    FirebaseConnectionInterface firebaseConnection;
    private static Repository repository=null;

    private Repository(Context context, FirebaseConnectionInterface firebaseConnection) {
        this.context = context;
        this.firebaseConnection = firebaseConnection;
    }
    public static Repository getRepository(Context context, FirebaseConnectionInterface firebaseConnection){
        if (repository==null){
            repository=new Repository(context,firebaseConnection);
        }
        return repository;
    }

    @Override
    public void registerNewUser(User user, Activity activity , FirebaseConnectionDelegated firebaseConnectionDelegated) {
        Log.e(TAG, "registerNewUser:repository ");
        firebaseConnection.registerNewUser(user,activity ,firebaseConnectionDelegated);
    }

//    @Override
//    public void updateUser(FirebaseConnectionDelegated firebaseConnectionDelegated) {
//        firebaseConnection.UpdateUser(firebaseConnectionDelegated);
//    }
}
