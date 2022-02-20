package com.example.medicationreminder.addmedication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.medicationreminder.R;

public class refillTimeDialoug extends AppCompatDialogFragment {
    EditText chooseRefill;
   DialougrefillLisener lisener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        View view =layoutInflater.inflate(R.layout.refilldialouug,null);
        chooseRefill=view.findViewById(R.id.chooseRefillAmount);
        builder.setView(view).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String amount=chooseRefill.getText().toString();
                lisener.chooseRefillAmount(amount);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        lisener= (DialougrefillLisener) context;
    }

    public interface DialougrefillLisener {
        public void chooseRefillAmount(String amount);
    }
}
