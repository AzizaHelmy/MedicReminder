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
    private java.lang.String userName;
    private ArrayList<HealthTaker> healthTakers;
    @Ignore
    private List<String> strings;

    public Patient() {
    }

    public Patient(@NonNull java.lang.String userName, ArrayList<HealthTaker> healthTakers, List<String> strings) {
        this.userName = userName;
        this.healthTakers = healthTakers;
        this.strings = strings;
    }

    @NonNull
    public java.lang.String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull java.lang.String userName) {
        this.userName = userName;
    }

    public ArrayList<HealthTaker> getHealthTakers() {
        return healthTakers;
    }

    public void setHealthTakers(ArrayList<HealthTaker> healthTakers) {
        this.healthTakers = healthTakers;
    }

    public List<String> getDrugs() {
        return strings;
    }

    public void setDrugs(List<String> strings) {
        this.strings = strings;
    }
}
