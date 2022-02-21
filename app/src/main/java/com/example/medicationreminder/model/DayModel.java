package com.example.medicationreminder.model;

import java.util.List;

public class DayModel {

    private List<Medication> Lists;

    public DayModel(List<Medication> buildItemList) {
        this.Lists=buildItemList;
    }

    public List<Medication> getLists() {
        return Lists;
    }

    public void setLists(List<Medication> lists) {
        Lists = lists;
    }
}
