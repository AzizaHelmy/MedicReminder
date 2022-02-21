package com.example.medicationreminder.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.medicationreminder.model.Medication;

import java.util.ArrayList;
import java.util.List;

public class ConcereteLocalSource implements LocalSource {
    DrugDao drugDao;
    private static ConcereteLocalSource concreteLocalSource = null;
    List<Medication>allDrugsForHome;
    private ConcereteLocalSource(Context context) {
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        drugDao = db.drugDao();
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
//    @Override
//    public void (Medication medication) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                drugDao.insertDrug(medication);
//            }
//        }).start();
//    }

    @Override
    public List<Medication>selectAllDrugs(String day) {

        new Thread(new Runnable() {
            @Override
            public void run() {
        allDrugsForHome=drugDao.selectAllDrugsForHome(day);
            }
        }).start();
 return  allDrugsForHome;
    }
}