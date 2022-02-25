package com.example.medicationreminder.home.view.model;

import com.example.medicationreminder.model.Medication;

import java.util.ArrayList;

public class HoursModel {
        String title;
        ArrayList<Medication> medicationList;

        public HoursModel(String title, ArrayList<Medication> medicationList) {
            this.title = title;
            this.medicationList = medicationList;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ArrayList<Medication> getMedicationList() {
            return medicationList;
        }

        public void setMedicationList(ArrayList<Medication> medicationList) {
            this.medicationList = medicationList;
        }
    }


