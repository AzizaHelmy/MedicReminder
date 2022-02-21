package com.example.medicationreminder.model;

import java.util.Calendar;

public class Drug {
    private String numberOfDose;
    private Calendar time;


    public Drug(String numberOfDose, String clock) {
        this.numberOfDose = numberOfDose;

    }

    public Drug() {
    }

    public String getNumberOfDose() {
        return numberOfDose;
    }

    public void setNumberOfDose(String numberOfDose) {
        this.numberOfDose = numberOfDose;
    }

}
