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

    @Query("SELECT * FROM MEDICATIONS  where isDaily=1 or days LIKE '%'|| :day || '%'")
    LiveData<List<Medication >> selectAllDrugsForHome1(String day);
    @Query("SELECT * FROM MEDICATIONS  where isDaily=1 ")
    LiveData<List<Medication >> selectAllDrugsForHome();

}