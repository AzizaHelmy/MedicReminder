package com.example.medicationreminder.medications.presenter;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.medicationreminder.medications.view.MedicsInterface;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.RepositoryInterface;

import java.util.List;

public class MedicationPresenter implements MedicsPresenter {
    Context context;
    RepositoryInterface repositoryInterface;
    MedicsInterface medicsInterface;

    public MedicationPresenter(Context context, RepositoryInterface repositoryInterface, MedicsInterface medicsInterface) {
        this.context = context;
        this.repositoryInterface = repositoryInterface;
        this.medicsInterface = medicsInterface;
    }

    @Override
    public void getMedics(LifecycleOwner owner) {
        repositoryInterface.getMedics(owner).observe(owner, new Observer<List<Medication>>() {
            @Override
            public void onChanged(List<Medication> medications) {
                medicsInterface.showMedics(medications);
            }
        });
    }

    @Override
    public void suspendReminder(Medication medication) {
        //  repositoryInterface.
    }

    @Override
    public void insert(Medication medication) {
        repositoryInterface.insertMed(medication);
    }
}
