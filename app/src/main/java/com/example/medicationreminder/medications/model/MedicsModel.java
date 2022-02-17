package com.example.medicationreminder.medications.model;

import java.util.List;

public class MedicsModel {
    private String title;
    private List<ItemModel> list;

    public MedicsModel(String title, List<ItemModel> list) {
        this.title = title;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ItemModel> getList() {
        return list;
    }

    public void setList(List<ItemModel> list) {
        this.list = list;
    }
}
