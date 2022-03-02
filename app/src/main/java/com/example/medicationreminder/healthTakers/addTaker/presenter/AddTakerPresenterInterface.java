package com.example.medicationreminder.healthTakers.addTaker.presenter;

import androidx.lifecycle.LifecycleOwner;

public interface AddTakerPresenterInterface {
    boolean checkUser(String userEmail);

    Object getMedicList(LifecycleOwner owner);

    void addRequest(String reciverEmail);
}
