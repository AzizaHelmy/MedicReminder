package com.example.medicationreminder.healthTakers.view;

import android.widget.ImageView;

import com.example.medicationreminder.model.Request;


public interface TakerOnClick {
    public void onOkClicked(Request taker, ImageView view);
    public void onCancelClicked(Request taker, ImageView view);
}
