package com.example.medicationreminder.displayMedics.presenter;

import androidx.lifecycle.LiveData;

import com.example.medicationreminder.model.Medication;

import java.util.List;

public class DisplayMedicationPresenter implements DisplayMedicationPresenterInterface{

    @Override
    public LiveData<List<Medication>> displayDrugs() {
        return null;
    }
}
