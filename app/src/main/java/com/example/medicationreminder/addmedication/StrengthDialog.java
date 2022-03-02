package com.example.medicationreminder.addmedication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.medicationreminder.R;
import com.example.medicationreminder.addmedication.view.OnDialogClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StrengthDialog extends AppCompatDialogFragment  {
    FloatingActionButton increaseBtn;
    FloatingActionButton decreaseBtn;
    TextView counter;
    OnDialogClickListener listener;
    Spinner usage_spinner;
    int count;
    String selctedusage;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog .Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        View view =layoutInflater.inflate(R.layout.strengthdialoug,null);
        usage_spinner=view.findViewById(R.id.strengthMeasure);
        usage_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selctedusage =adapterView.getSelectedItem().toString();
                Log.i("salma",""+selctedusage);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        builder.setView(view).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String times=counter.getText().toString();

                String output=times+" "+selctedusage;
                Log.i("salma","output"+output);
                listener=(OnDialogClickListener) getParentFragment();
                listener.showText(output);
            }
        });
        increaseBtn=view.findViewById(R.id.increaseBtnstrength);
        decreaseBtn=view.findViewById(R.id.decreaseBtnStrength);
        counter=view.findViewById(R.id.counterStrength);
        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                counter.setText(count+"");
            }
        });
        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>0){

                count--;
                counter.setText(count+"");}}

        });
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }


}
