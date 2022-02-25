package com.example.medicationreminder.db;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.medicationreminder.model.Medication;

import java.util.ArrayList;
import java.util.List;

public interface LocalSource {
    public void insertDrug(Medication medication);
    public LiveData<List<Medication >>selectAllDrugs(String day);
    public LiveData<List<Medication >>selectAllDrugs1();

    public List<Medication>selectAllDrugs(String day);
    LiveData<List<Medication>> getAllMedics();
}
