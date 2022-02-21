package com.example.medicationreminder.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
@Entity(tableName = "patient")
public class Patient {
    @PrimaryKey
    //@ForeignKey()
    @NonNull
    private String userName;
    private ArrayList<HealthTaker> healthTakers;
    @Ignore
    private List<Drug> drugs;

    public Patient() {
    }

    public Patient(@NonNull String userName, ArrayList<HealthTaker> healthTakers, List<Drug> drugs) {
        this.userName = userName;
        this.healthTakers = healthTakers;
        this.drugs = drugs;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public ArrayList<HealthTaker> getHealthTakers() {
        return healthTakers;
    }

    public void setHealthTakers(ArrayList<HealthTaker> healthTakers) {
        this.healthTakers = healthTakers;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }
}
