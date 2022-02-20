package com.example.medicationreminder.db;

import android.content.Context;

import com.example.medicationreminder.model.Medication;

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
    public void insertDrug(Medication medication) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                drugDao.insertDrug(medication);
            }
        }).start();
    }
}
