package com.example.medicationreminder.healthTakers.request.presenter;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.medicationreminder.healthTakers.request.view.HealthTakersInterface;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.RepositoryInterface;

import java.util.List;

public class RequestPresenter implements RequestPresenterInterface {
    Context context;
    RepositoryInterface repositoryInterface;
    HealthTakersInterface healthTakersInterface;

    public RequestPresenter(Context context, RepositoryInterface repositoryInterface, HealthTakersInterface healthTakersInterface) {
        this.context = context;
        this.repositoryInterface = repositoryInterface;
        this.healthTakersInterface = healthTakersInterface;
    }

    @Override
    public Object getAllMedics(LifecycleOwner owner) {
        repositoryInterface.getMedics(owner).observe(owner, new Observer<List<Medication>>() {
            @Override
            public void onChanged(List<Medication> medications) {
                healthTakersInterface.getMedics(medications);
            }
        });
        return null;
    }
}
