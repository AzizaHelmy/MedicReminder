package com.example.medicationreminder.Network;

import static com.example.medicationreminder.medications.view.MedicationsFragment.TAG;

import android.util.Log;

import com.example.medicationreminder.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
   private static FirebaseDatabase database ;

    private static DatabaseReference myRef;

    public UserDao() {

    }



    public static void addUser(User user, OnCompleteListener<Void> onCompleteListener) {
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("user");
         myRef .child(user.getUserId()).setValue(user).addOnCompleteListener(onCompleteListener);
    }

    public static void getUser(String usrId, OnCompleteListener<DataSnapshot> onCompleteListener) {
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("user");
        myRef.child(usrId).get().addOnCompleteListener(onCompleteListener);

    }

}
