package com.example.medicationreminder.home.view;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.medicationreminder.model.Medication;

import java.util.ArrayList;
import java.util.List;

public interface HomeViewInterface {

   public LiveData<List<Medication >> selectAllDrugsForHome(String day, LifecycleOwner owner);
   public LiveData<List<Medication >> selectAllDrugsForHome1( LifecycleOwner owner);

}
