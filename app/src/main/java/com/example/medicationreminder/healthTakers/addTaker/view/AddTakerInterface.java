package com.example.medicationreminder.healthTakers.addTaker.view;

import com.example.medicationreminder.model.Medication;

import java.util.List;

public interface AddTakerInterface {
    void getMedics(List<Medication> medics);

    void onSuccess(boolean result);
}
