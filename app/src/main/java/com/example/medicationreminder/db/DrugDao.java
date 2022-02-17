package com.example.medicationreminder.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.medicationreminder.model.Drug;

@Dao
public interface DrugDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   void insertDrug (Drug drug);
}
