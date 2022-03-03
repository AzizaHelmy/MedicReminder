package com.example.medicationreminder.displayMedics.presenter;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.medicationreminder.displayMedics.view.DisplayMedicationViewInterface;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.RepositoryInterface;

import java.util.List;

public class DisplayMedicationPresenter implements DisplayMedicationPresenterInterface{
    RepositoryInterface repo;
    DisplayMedicationViewInterface displayMedicationViewInterface;
    public DisplayMedicationPresenter(FragmentActivity activity, RepositoryInterface repo, DisplayMedicationViewInterface displayMedicationViewInterface){
        this.repo=repo;
        this.displayMedicationViewInterface=displayMedicationViewInterface;
    }


    @Override
    public void deleteMedic(Medication medication) {
        repo.deleteMedic(medication);
    }

    @Override
    public LiveData<List<Medication>> displayDrug(LifecycleOwner owner) {
        repo.displayDrug().observe(owner, new Observer<List<Medication>>() {
            @Override
            public void onChanged(List<Medication> medications) {
                displayMedicationViewInterface.displayDrug();
            }
        });
        return null;
    }
}
