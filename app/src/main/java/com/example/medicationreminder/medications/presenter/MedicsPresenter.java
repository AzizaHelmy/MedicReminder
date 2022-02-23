package com.example.medicationreminder.medications.presenter;

import androidx.lifecycle.LifecycleOwner;

import com.example.medicationreminder.model.Medication;

public interface MedicsPresenter {
    void getMedics(LifecycleOwner owner);
    void suspendReminder(Medication medication);
    void insert(Medication medication);
}
