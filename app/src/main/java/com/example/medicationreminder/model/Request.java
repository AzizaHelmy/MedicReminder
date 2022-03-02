package com.example.medicationreminder.model;

import java.util.List;

public class Request {
    private String senderName;
    private String reciverEmail;
    private String senderEmail;
    private String senderImg;
    private List<Medication> medication;

    public String getSenderImg() {
        return senderImg;
    }

    public void setSenderImg(String senderImg) {
        this.senderImg = senderImg;
    }

    public Request(String senderName, String reciverEmail, String senderEmail, String senderImg, List<Medication> medication) {
        this.senderName = senderName;
        this.reciverEmail = reciverEmail;
        this.senderEmail = senderEmail;
        this.senderImg = senderImg;
        this.medication = medication;
    }


    public List<Medication> getMedication() {
        return medication;
    }

    public void setMedication(List<Medication> medication) {
        this.medication = medication;
    }

    public Request() {
    }

    public Request(String senderName, String reciverEmail, String senderEmail, List<Medication> medication) {
        this.senderName = senderName;
        this.reciverEmail = reciverEmail;
        this.senderEmail = senderEmail;
        this.medication = medication;
    }

    public Request(String senderName, String senderEmail, String reciverEmail) {
        this.senderName = senderName;
        this.reciverEmail = reciverEmail;
        this.senderEmail = senderEmail;
    }

    public Request(String senderName, int img) {
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReciverEmail() {
        return reciverEmail;
    }

    public void setReciverEmail(String reciverEmail) {
        this.reciverEmail = reciverEmail;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }
}
