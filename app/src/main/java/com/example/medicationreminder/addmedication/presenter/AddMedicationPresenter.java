package com.example.medicationreminder.addmedication.presenter;

import androidx.fragment.app.FragmentActivity;

import com.example.medicationreminder.addmedication.view.AddMedicationViewInterface;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.RepositoryInterface;

public class AddMedicationPresenter implements AddMedicationPresenterInterface{
    RepositoryInterface repo;
    AddMedicationViewInterface addMedicationViewInterface;
    public AddMedicationPresenter(FragmentActivity activity, RepositoryInterface repo, AddMedicationViewInterface addMedicationViewInterface){
        this.repo=repo;
        this.addMedicationViewInterface=addMedicationViewInterface;
    }
    @Override
    public void insert(Medication medication) {
      repo.insertMed(medication);
    }
}
