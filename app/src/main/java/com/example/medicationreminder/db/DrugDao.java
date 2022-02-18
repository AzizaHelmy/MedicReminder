package com.example.medicationreminder.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.medicationreminder.model.Drug;

import java.util.List;

@Dao
public interface DrugDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDrug(Drug drug);

//    @Query("SELECT EXISTS(SELECT medicName,icon,strength,drugAdder,mesaure,drugAmount from medications WHERE status=:status)")
//    LiveData<List<Drug>> getActiveDrugs(String status);

}
