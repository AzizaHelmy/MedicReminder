package com.example.medicationreminder.displayMedics.presenter;

import androidx.lifecycle.LiveData;

import com.example.medicationreminder.model.Medication;

import java.util.List;

public interface DisplayMedicationPresenterInterface {
   // public void displayDrug(Medication medication);
   LiveData<List<Medication>> displayDrugs();
}
