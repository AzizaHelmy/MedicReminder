package com.example.medicationreminder.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "medications")
public class Medication {
    private String drugAdder;
    @PrimaryKey
    @NonNull
    private  String medicine_Name;
    private boolean isRemindered;
    private String medicineType;
    private int icon;
    private int strength;
    private String mesaure;
    private double dose;
    boolean isDaily;
    private int drugDuration;
    private List<String> days;
    private List<Drug> drugs;
    private String instructions;
    private String status;//Active or InActive
    private int drugAmount;
    private boolean isRefillReminder;
    private int leftDrug;
    private String RefilTime;

    public Medication(String medicine_Name, String medicineType, int icon, int strength, String mesaure, int drugAmount) {
        //this.drugAdder = drugAdder;
        this.medicine_Name = medicine_Name;
        this.medicineType = medicineType;
        this.icon = icon;
        this.strength = strength;
        this.mesaure = mesaure;
        this.drugAmount = drugAmount;
    }

    public Medication() {
    }

    public Medication(String drugAdder, @NonNull String medicine_Name,  ArrayList<String> days) {
        this.drugAdder = drugAdder;
        this.medicine_Name = medicine_Name;

        this.days = days;
    }

    public Medication(String drugAdder, String medicine_Name, boolean isRemindered, String medicineType, int icon, int strength, String mesaure, double dose, int drugDuration, ArrayList<String> days, ArrayList<Drug> dates, String instructions, String status, int drugAmount, boolean isRefillReminder, int leftDrug, String refilTime) {
        this.drugAdder = drugAdder;
        this.medicine_Name = medicine_Name;
        this.isRemindered = isRemindered;
        this.medicineType = medicineType;
        this.icon = icon;
        this.strength = strength;
        this.mesaure = mesaure;
        this.dose = dose;
        this.drugDuration = drugDuration;
        this.days = days;
        this.drugs = dates;
        this.instructions = instructions;
        this.status = status;
        this.drugAmount = drugAmount;
        this.isRefillReminder = isRefillReminder;
        this.leftDrug = leftDrug;
        RefilTime = refilTime;
    }

    public Medication(String medicine_Name, int icon, ArrayList<Drug> dates, int drugAmount) {
        this.medicine_Name = medicine_Name;
        this.icon = icon;
        this.drugs = dates;
        this.drugAmount = drugAmount;
    }

    public boolean isDaily() {
        return isDaily;
    }

    public void setDaily(boolean daily) {
        isDaily = daily;
    }

    public String getDrugAdder() {
        return drugAdder;
    }

    public void setDrugAdder(String drugAdder) {
        this.drugAdder = drugAdder;
    }

    public String getMedicine_Name() {
        return medicine_Name;
    }

    public void setMedicine_Name(String medicine_Name) {
        this.medicine_Name = medicine_Name;
    }

    public boolean isRemindered() {
        return isRemindered;
    }

    public void setRemindered(boolean remindered) {
        isRemindered = remindered;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getMesaure() {
        return mesaure;
    }

    public void setMesaure(String mesaure) {
        this.mesaure = mesaure;
    }

    public double getDose() {
        return dose;
    }

    public void setDose(double dose) {
        this.dose = dose;
    }

    public int getDrugDuration() {
        return drugDuration;
    }

    public void setDrugDuration(int drugDuration) {
        this.drugDuration = drugDuration;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDrugAmount() {
        return drugAmount;
    }

    public void setDrugAmount(int drugAmount) {
        this.drugAmount = drugAmount;
    }

    public boolean isRefillReminder() {
        return isRefillReminder;
    }

    public void setRefillReminder(boolean refillReminder) {
        isRefillReminder = refillReminder;
    }

    public int getLeftDrug() {
        return leftDrug;
    }

    public void setLeftDrug(int leftDrug) {
        this.leftDrug = leftDrug;
    }

    public String getRefilTime() {
        return RefilTime;
    }

    public void setRefilTime(String refilTime) {
        RefilTime = refilTime;
    }
}
