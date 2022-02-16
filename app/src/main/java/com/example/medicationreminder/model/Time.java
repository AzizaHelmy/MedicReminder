package com.example.medicationreminder.model;

import java.util.ArrayList;

public class Time {
    private String numberOfTimes;
    private ArrayList<String> time;
    private String startingDate;

    public Time(String numberOfTimes, String clock) {
        this.numberOfTimes = numberOfTimes;
      //  this.clock = clock;
    }

    public Time() {
    }

    public String getNumberOfTimes() {
        return numberOfTimes;
    }

    public void setNumberOfTimes(String numberOfTimes) {
        this.numberOfTimes = numberOfTimes;
    }
//
//    public String getClock() {
//        return clock;
//    }
//
//    public void setClock(String clock) {
//        this.clock = clock;
   // }
}
