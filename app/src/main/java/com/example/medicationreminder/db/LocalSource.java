package com.example.medicationreminder.db;

import androidx.lifecycle.LiveData;

import com.example.medicationreminder.model.Drug;

import java.util.List;

public interface LocalSource {
    public void insertDrug(Drug drug);

    LiveData<List<Drug>> getActiveDrugs(String status);
}
