package com.example.medicationreminder.db;

import static com.example.medicationreminder.medications.view.MedicationsFragment.TAG;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.medicationreminder.model.Medication;

import java.util.List;

public class ConcereteLocalSource implements LocalSource {
    DrugDao drugDao;
    private static ConcereteLocalSource concreteLocalSource = null;
    LiveData<List<Medication >>allDrugsForHome;

    private LiveData<List<Medication>> storedMedics;
    LiveData<List<Medication>>displayedDrug;
    private ConcereteLocalSource(Context context) {
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        drugDao = db.drugDao();
        //storedMedics = drugDao.getAllMedics();
        displayedDrug=drugDao.displayDrug();
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

    @Override
    public void deleteDrug(Medication medication) {

    }

    @Override
    public LiveData<List<Medication>> dispalyedDrug() {

        return displayedDrug;
    }


    @Override
    public void editeDrug(Medication medication) {
     drugDao.updateDrug(medication);
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
        return drugDao.getAllMedics();
    }

    @Override
    public void addRequest(String reciverEmail) {

    }

    @Override
    public boolean isReminder(String medicine_Name) {
       return getFav(medicine_Name);
    }
//=======================================================

    public boolean getFav(String medicine_Name) {

        Reminder reminder =new Reminder();
        reminder.setName(medicine_Name);
        Log.e(TAG, "getFav: after"+reminder.isFlag());

        Thread th=new Thread(reminder);
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "getFav:befor "+reminder.isFlag());
        return  reminder.isFlag();
    }

    public class Reminder implements Runnable {
        private boolean flag=false;

        private String medicName;

        @Override
        public void run() {
            flag =drugDao.isReminder(medicName) ;
        }
        public void setName(String name) {
            this.medicName = name;
        }
        public boolean isFlag() {
            return flag;
        }


    }


}