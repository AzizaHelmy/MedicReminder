package com.example.medicationreminder.db;

import com.example.medicationreminder.model.Medication;

public interface LocalSource {
    public void insertDrug(Medication medication);
}
