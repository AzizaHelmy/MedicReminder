package com.example.medicationreminder.db;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.medicationreminder.model.Medication;

import java.util.List;

public class ConcereteLocalSource implements LocalSource {
    DrugDao drugDao;
    private static ConcereteLocalSource concreteLocalSource = null;
    LiveData<List<Medication >>allDrugsForHome;

    private LiveData<List<Medication>> storedMedics;
    private ConcereteLocalSource(Context context) {
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        drugDao = db.drugDao();
        storedMedics = drugDao.getAllMedics();
    }

    public static ConcereteLocalSource getInstance(Context context) {
        if (concreteLocalSource == null) {
            concreteLocalSource = new ConcereteLocalSource(context);
        }
        return concreteLocalSource;
    }

    @Override
    public void insertDrug(Medication medication) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                drugDao.insertDrug(medication);
            }
        }).start();
    }


    public LiveData<List<Medication>> selectAllDrugs1() {
       return  drugDao.selectAllDrugsForHome();
    }


    @Override
    public LiveData<List<Medication >>selectAllDrugs(String day) {

        return drugDao.selectAllDrugsForHome1(day);
    }

    @Override
    public LiveData<List<Medication>> getAllMedics() {
        return storedMedics;
    }
}