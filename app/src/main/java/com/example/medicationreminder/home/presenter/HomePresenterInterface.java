package com.example.medicationreminder.home.presenter;

import androidx.lifecycle.LiveData;

import com.example.medicationreminder.model.Medication;

import java.util.ArrayList;
import java.util.List;

public interface HomePresenterInterface {
    public  void insertMed(Medication medication);
    public List<Medication>selectAllDrugsForHome(String day);

}
