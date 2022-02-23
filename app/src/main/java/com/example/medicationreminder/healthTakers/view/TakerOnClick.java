package com.example.medicationreminder.healthTakers.view;

import android.widget.ImageView;

import com.example.medicationreminder.model.HealthTaker;


public interface TakerOnClick {
    public void onOkClicked(HealthTaker taker, ImageView view);
    public void onCancelClicked(HealthTaker taker, ImageView view);
}
