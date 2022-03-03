package com.example.medicationreminder.updatemedication.presenter;

import androidx.fragment.app.FragmentActivity;

import com.example.medicationreminder.addmedication.view.AddMedicationViewInterface;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.RepositoryInterface;
import com.example.medicationreminder.updatemedication.view.UpdateMedicationViewInterface;

public class UpdateMedicationpresenter implements UpdateMedicationPresenterInterface{
    RepositoryInterface repo;
    UpdateMedicationViewInterface updateMedicationViewInterface;
    public UpdateMedicationpresenter(FragmentActivity activity, RepositoryInterface repo, UpdateMedicationViewInterface updateMedicationViewInterface){
        this.repo=repo;
        this.updateMedicationViewInterface=updateMedicationViewInterface;
    }
    @Override
    public void update(Medication medication) {
        repo.updateDrug(medication);
    }
}