package com.example.medicationreminder.db;

import android.content.Context;

public class ConcereteLocalSource implements com.example.myapplication.LocalSource {
    com.example.myapplication.DrugDao drugDao;
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
}
