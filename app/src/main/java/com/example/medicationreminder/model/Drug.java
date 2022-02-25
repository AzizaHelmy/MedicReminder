package com.example.medicationreminder.model;

import android.text.format.Time;

import java.util.Calendar;

public class Drug {

    private String time;

    public Drug(String  time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
