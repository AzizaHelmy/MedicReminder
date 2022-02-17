package com.example.medicationreminder.db;

import com.example.medicationreminder.model.Drug;

public interface LocalSource {
    public void insertDrug(Drug drug);
}
