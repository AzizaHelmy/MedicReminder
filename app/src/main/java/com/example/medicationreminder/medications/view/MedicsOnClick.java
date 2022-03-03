package com.example.medicationreminder.medications.view;
import android.widget.ImageView;
import com.example.medicationreminder.model.Medication;

public interface MedicsOnClick {
    public void ItemOnClick(Medication medic,int position);
    public  void alarmOnClick(Medication medic, ImageView view);

}
