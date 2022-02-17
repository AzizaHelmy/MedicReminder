package com.example.medicationreminder.medications.model;

public class ItemModel {

    private int medicImg;
    private String medicName;
    private String medicStrength;
    private String medicLeft;
    private String medicAdder;

    public ItemModel(int medicImg, String medicName, String medicStrength, String medicLeft, String medicAdder) {
        this.medicImg = medicImg;
        this.medicName = medicName;
        this.medicStrength = medicStrength;
        this.medicLeft = medicLeft;
        this.medicAdder = medicAdder;
    }

    public int getMedicImg() {
        return medicImg;
    }

    public void setMedicImg(int medicImg) {
        this.medicImg = medicImg;
    }

    public String getMedicName() {
        return medicName;
    }

    public void setMedicName(String medicName) {
        this.medicName = medicName;
    }

    public String getMedicStrength() {
        return medicStrength;
    }

    public void setMedicStrength(String medicStrength) {
        this.medicStrength = medicStrength;
    }

    public String getMedicLeft() {
        return medicLeft;
    }

    public void setMedicLeft(String medicLeft) {
        this.medicLeft = medicLeft;
    }

    public String getMedicAdder() {
        return medicAdder;
    }

    public void setMedicAdder(String medicAdder) {
        this.medicAdder = medicAdder;
    }
}
