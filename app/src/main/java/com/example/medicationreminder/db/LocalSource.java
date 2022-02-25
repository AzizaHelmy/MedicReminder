package com.example.medicationreminder.db;

import androidx.lifecycle.LiveData;

import com.example.medicationreminder.model.Medication;

import java.util.ArrayList;
import java.util.List;

public interface LocalSource {
    public void insertDrug(Medication medication);
    public void deleteDrug(Medication medication);
   // public void displayDrug(Medication medication);
   LiveData< List<Medication>>dispalyedDrug();
    public void editeDrug(Medication medication);
    public List<Medication>selectAllDrugs(String day);
    public LiveData<List<Medication >>selectAllDrugs(String day);
    public LiveData<List<Medication >>selectAllDrugs1();

    LiveData<List<Medication>> getAllMedics();

}
