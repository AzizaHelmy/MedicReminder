package com.example.medicationreminder.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.medicationreminder.model.Drug;

import java.util.List;

public class ConcereteLocalSource implements LocalSource {
    DrugDao drugDao;
    private static ConcereteLocalSource concreteLocalSource = null;

    private ConcereteLocalSource(Context context) {
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
    }

    public static ConcereteLocalSource getInstance(Context context) {
        if (concreteLocalSource == null) {
            concreteLocalSource = new ConcereteLocalSource(context);
        }
        return concreteLocalSource;
    }

    @Override
    public void insertDrug(Drug drug) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                drugDao.insertDrug(drug);
            }
        }).start();
    }

    @Override
    public LiveData<List<Drug>> getActiveDrugs(String status) {
        return null;
    }
}
