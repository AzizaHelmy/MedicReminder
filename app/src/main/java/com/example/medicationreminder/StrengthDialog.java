package com.example.medicationreminder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.medicationreminder.R;

public class StrengthDialog extends AppCompatDialogFragment {
    Button increaseStrength,decreaseStrength;
    TextView counterStrength;
    int countStrength ;
    StrengthDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        View view =layoutInflater.inflate(R.layout.strengthdialoug,null);
        builder.setView(view).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String times=counterStrength.getText().toString();
                listener.showText(times);
            }
        });
        increaseStrength=view.findViewById(R.id.increaseBtn);
        decreaseStrength=view.findViewById(R.id.decreaseBtn);
        counterStrength=view.findViewById(R.id.counter);
        increaseStrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countStrength++;
                counterStrength.setText(countStrength+"");
            }
        });
        decreaseStrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countStrength--;
                counterStrength.setText(countStrength+"");
            }
        });
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
       listener= (StrengthDialogListener) context;
    }

    public  interface StrengthDialogListener{
        public void showText(String number);
    }
    }

