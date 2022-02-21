package com.example.medicationreminder.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    String userId;
    String UserName;
    String password;
    String userEmail;
    private List<HealthTaker> healthTakers;
    private List<Medication> medications;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String userId, String userName, String userEmail) {
        this.userId = userId;
        UserName = userName;
        this.userEmail = userEmail;
    }

    public User() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}



