package com.example.medicationreminder.displayMedics.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.medicationreminder.model.Medication;

import java.util.List;

public interface DisplayMedicationPresenterInterface {
   // public void displayDrug(Medication medication);
public void deleteMedic(Medication medication);
   LiveData<List<Medication>> displayDrug(LifecycleOwner owner);
}
