package com.example.medicationreminder.home.view;

import androidx.lifecycle.LiveData;

import com.example.medicationreminder.model.Medication;

import java.util.ArrayList;
import java.util.List;

public interface HomeViewInterface {

   public  void insert();
   public List<Medication> selectAllDrugsForHome(String day);

}
