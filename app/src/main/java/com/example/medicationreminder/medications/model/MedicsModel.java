package com.example.medicationreminder.medications.model;

import com.example.medicationreminder.model.Drug;

import java.util.List;

public class MedicsModel {
    private String title;
    private List<Drug> list;

    public MedicsModel(String title, List<Drug> list) {
        this.title = title;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Drug> getList() {
        return list;
    }

    public void setList(List<Drug> list) {
        this.list = list;
    }
}
