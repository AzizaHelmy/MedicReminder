package com.example.medicationreminder.model;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "medications")
public class Medication implements Serializable {
    private String drugAdder;
    @PrimaryKey
    @NonNull
    private  String medicine_Name;

    private boolean isRemindered;
    private String medicineType;
    private int frequencyOfRepitionId;
    private int icon;
    private String strength;
    private double dose;
    boolean isDaily;
    private String frequencyOfrepition;
    private int drugDuration;
    private String noOfDays;
    private String stratingDate;
    String duration;
    private List<String> days;
    private String[] drugs;
    private String instructions;
    private String status;
    private  String[] noOfDose;
    private String drugAmount;
    private boolean isRefillReminder;
    private String leftDrug;
    private String RefilTime;

    public Medication(@NonNull String medicine_Name) {
        this.medicine_Name = medicine_Name;
    }

    public Medication(String medicine_Name, String medicineType, int icon, int strength, String mesaure, int drugAmount) {
    }

        //this.drugAdder = drugAdder;
    public Medication(String drugAdder, String medicine_Name, String medicineType, int icon, String strength,  String drugAmount) {
        this.drugAdder = drugAdder;
        this.medicine_Name = medicine_Name;
        this.medicineType = medicineType;
        this.icon = icon;
        this.strength = strength;
        this.drugAmount = drugAmount;
    }

    public Medication() {
    }

    public Medication(String drugAdder, @NonNull String medicine_Name,  ArrayList<String> days) {
        this.drugAdder = drugAdder;
        this.medicine_Name = medicine_Name;

        this.days = days;
    }
      public Medication(String medcine_Name,String medicineType,ArrayList<String>days,int iconId,String drugAmount,boolean isReminderd,String strength,String frequencyOfRepition,String startingDate,String refilTime,String leftDrug,String instructions,String noOfDays){
        this.medicine_Name=medcine_Name;
        this.medicineType=medicineType;
        this.days=days;
        this.icon=iconId;
        this.drugAmount=drugAmount;
        this.isRemindered=isReminderd;
        this.strength=strength;
        this.frequencyOfrepition=frequencyOfRepition;
        this.stratingDate=startingDate;
        this.RefilTime=refilTime;
        this.leftDrug=leftDrug;
        this.instructions=instructions;
        this.noOfDays=noOfDays;
      }
    public Medication(String drugAdder, String medicine_Name, boolean isRemindered, String medicineType, int icon, String strength,double dose, int drugDuration, ArrayList<String> days,String[] drugs, String instructions, String status, String drugAmount, boolean isRefillReminder, String leftDrug, String refilTime) {
        this.drugAdder = drugAdder;
        this.medicine_Name = medicine_Name;
        this.isRemindered = isRemindered;
        this.medicineType = medicineType;
        this.icon = icon;
        this.strength = strength;
        this.dose = dose;
        this.drugDuration = drugDuration;
        this.days = days;
        this.drugs = drugs;
        this.instructions = instructions;
        this.status = status;
        this.drugAmount = drugAmount;
        this.isRefillReminder = isRefillReminder;
        this.leftDrug = leftDrug;
        RefilTime = refilTime;
    }
    public Medication(String medicine_Name,String startingDate,String leftDrug,String drugAmount){
        this.medicine_Name=medicine_Name;
        this.stratingDate=startingDate;
        this.leftDrug=leftDrug;
        this.drugAmount=drugAmount;
    }

    public Medication(String medicine_Name, int icon, String[]drugs, String drugAmount) {
        this.medicine_Name = medicine_Name;
        this.icon = icon;
        this.drugs = drugs;
        this.drugAmount = drugAmount;
    }
public Medication(String medicine_Name,String strength,String duration,String drugAmount,String leftDrug){
        this.medicine_Name=medicine_Name;
        this.strength=strength;
        this.duration=duration;
        this.drugAmount=drugAmount;
        this.leftDrug=leftDrug;
}
    public String getDrugAdder() {
        return drugAdder;
    }

    public void setDrugAdder(String drugAdder) {
        this.drugAdder = drugAdder;
    }

    @NonNull
    public String getMedicine_Name() {
        return medicine_Name;
    }

    public void setMedicine_Name(@NonNull String medicine_Name) {
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

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

   public void setFrequencyOfrepition(String frequencyOfrepition){
        this.frequencyOfrepition=frequencyOfrepition;
   }
   public String getFrequencyOfrepition(){return frequencyOfrepition;}
    public double getDose() {
        return dose;
    }

    public void setDose(double dose) {
        this.dose = dose;
    }

    public boolean isDaily() {
        return isDaily;
    }

    public void setDaily(boolean daily) {
        isDaily = daily;
    }

    public int getDrugDuration() {
        return drugDuration;
    }

    public void setDrugDuration(int drugDuration) {
        this.drugDuration = drugDuration;
    }

    public String getStratingDate() {
        return stratingDate;
    }

    public void setStratingDate(String stratingDate) {
        this.stratingDate = stratingDate;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public String[] getDrugs() {
        return drugs;
    }

    public void setDrugs(String[] drugs) {
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

    public String getDrugAmount() {
        return drugAmount;
    }

    public void setDrugAmount(String drugAmount) {
        this.drugAmount = drugAmount;
    }

    public boolean isRefillReminder() {
        return isRefillReminder;
    }

    public void setRefillReminder(boolean refillReminder) {
        isRefillReminder = refillReminder;
    }

    public String getLeftDrug() {
        return leftDrug;
    }

    public void setLeftDrug(String leftDrug) {
        this.leftDrug = leftDrug;
    }

    public String getRefilTime() {
        return RefilTime;
    }

    public void setRefilTime(String refilTime) {
        RefilTime = refilTime;
    }
    public void setNoOfDays(String NoOfDays){
        noOfDays=NoOfDays;

    }
    public String getNoOfDays(){
        return noOfDays;
    }
    public void setFrequencyOfRepitionId(int frequencyOfRepitionId){
        this.frequencyOfRepitionId=frequencyOfRepitionId;
    }
    public int getFrequencyOfRepitionId(){
        return frequencyOfRepitionId;
    }
    public void setNoOfDose(String[] noOfDose){
        this.noOfDose=noOfDose;
    }
    public String[] getNoOfDose(){
        return  noOfDose;
    }
    public void setDuration(String duration){
        this.duration=duration;
    }
    public String getDuration(){
        return  duration;
    }

}
