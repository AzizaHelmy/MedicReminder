package com.example.medicationreminder.displayMedics.view;

import androidx.lifecycle.LiveData;

import com.example.medicationreminder.model.Medication;

import java.util.List;

public interface DisplayMedicationViewInterface {
    LiveData<List<Medication>> displayDrug();
}
