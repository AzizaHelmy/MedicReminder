package com.example.medicationreminder.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.medicationreminder.model.Medication;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DrugDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   void insertDrug (Medication medication);

    @Query("SELECT * FROM MEDICATIONS where isDaily=1 or days LIKE '%' || :day || '%'")
    List<Medication >selectAllDrugsForHome(String day);

    @Query("SELECT * From MEDICATIONS")
    LiveData<List<Medication>> getAllMedics();

    @Query("SELECT EXISTS (SELECT 1 FROM MEDICATIONS WHERE medicine_Name=:medicine_Name)")
    boolean isReminder(String medicine_Name);
}