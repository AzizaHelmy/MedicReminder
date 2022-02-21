package com.example.medicationreminder.healthTakers.model;

public class HealthTaker {
    private String takerName;
    private int takerImg;

    public HealthTaker(String takerName, int takerImg) {
        this.takerName = takerName;
        this.takerImg = takerImg;
    }

    public HealthTaker() {
    }

    public String getTakerName() {
        return takerName;
    }

    public void setTakerName(String takerName) {
        this.takerName = takerName;
    }

    public int getTakerImg() {
        return takerImg;
    }

    public void setTakerImg(int takerImg) {
        this.takerImg = takerImg;
    }
}
