package com.example.medicationreminder.medications.model;

import java.util.List;

public class MedicsModel {
    private java.lang.String title;
    private List<String> list;

    public MedicsModel(java.lang.String title, List<String> list) {
        this.title = title;
        this.list = list;
    }

    public java.lang.String getTitle() {
        return title;
    }

    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
