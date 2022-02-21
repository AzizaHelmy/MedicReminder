package com.example.medicationreminder.addmedication.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.medicationreminder.R;

import com.example.medicationreminder.addmedication.DialougClass;
import com.example.medicationreminder.addmedication.StrengthDialog;
import com.example.medicationreminder.addmedication.refillTimeDialoug;
import com.example.medicationreminder.databinding.FragmentAddMedicationBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddMedicationFragment extends Fragment implements refillTimeDialoug.DialougrefillLisener, DialougClass.DialogClassListener, StrengthDialog.StrengthDialogListener {
    FragmentAddMedicationBinding binding;
    int timerHour, timerMinute;

    public AddMedicationFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback pressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(getView()).navigate(R.id.homeFragment2);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, pressedCallback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddMedicationBinding.inflate(getLayoutInflater(), container, false);
        binding.afterLayout3.setVisibility(View.GONE);
        binding.afterLayout4.setVisibility(View.GONE);
        binding.moreopitions.setVisibility(View.GONE);
        binding.remindertimesLayout.setVisibility(View.GONE);
        binding.schedulingLayout.setVisibility(View.GONE);
        binding.medicinesymbolLayout.setVisibility(View.GONE);
        binding.strengthLayout.setVisibility(View.GONE);
        binding.instructionsLayout.setVisibility(View.GONE);
        binding.refillingLayout.setVisibility(View.GONE);
        binding.doneBtn.setVisibility(View.GONE);
        binding.afterLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_addMedicationFragment_to_medicationsFragment2);
            }
        });
        binding.doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_addMedicationFragment_to_medicationsFragment2);
            }
        });

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //==========================================================================
        binding.afterLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.afterLayout3.setVisibility(View.VISIBLE);
                binding.remindertimesLayout.setVisibility(View.VISIBLE);
                binding.schedulingLayout.setVisibility(View.VISIBLE);
                binding.afterLayout1.setVisibility(View.GONE);
                binding.doneBtn.setVisibility(View.GONE);

            }
        });
        //=========================================================================
        binding.afterLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.afterLayout3.setVisibility(View.GONE);
                binding.afterLayout4.setVisibility(View.VISIBLE);
                binding.moreopitions.setVisibility(View.VISIBLE);
                binding.remindertimesLayout.setVisibility(View.VISIBLE);
                binding.schedulingLayout.setVisibility(View.VISIBLE);
                binding.medicinesymbolLayout.setVisibility(View.VISIBLE);
                binding.doneBtn.setVisibility(View.GONE);
            }
        });
        //============================================================================
        binding.moreopitions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.afterLayout3.setVisibility(View.GONE);
                binding.afterLayout4.setVisibility(View.GONE);
                binding.moreopitions.setVisibility(View.GONE);
                binding.remindertimesLayout.setVisibility(View.VISIBLE);
                binding.schedulingLayout.setVisibility(View.VISIBLE);
                binding.medicinesymbolLayout.setVisibility(View.VISIBLE);
                binding.strengthLayout.setVisibility(View.VISIBLE);
                binding.instructionsLayout.setVisibility(View.VISIBLE);
                binding.refillingLayout.setVisibility(View.VISIBLE);
                binding.doneBtn.setVisibility(View.VISIBLE);
            }
        });
        //=============================================================================
        binding.timerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimerPickerDialoug();
            }
        });
        //=======================================================================
        binding.numberTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNumberPicker();
            }
        });
        //======================================================================
        binding.selectAmountRefill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRefillDialoug();
            }
        });
        //========================================================================
        binding.schedualingTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });
        //========================================================================
        binding.PresstoadjustTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStrengthDialoug();
            }
        });
        // Navigation.findNavController(view).navigate(R.id.action_addMedicationFragment_to_medicationsFragment2);
    }

    //======================================================================
    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    //=======================================================================
    public void openTimerPickerDialoug() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                timerHour = hourOfDay;
                timerMinute = minute;
                String time = timerHour + ":" + timerMinute;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                try {
                    Date date = simpleDateFormat.parse(time);
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm:aa");
                    binding.timerTxt.setText(simpleDateFormat1.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, 12, 0, false);
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.updateTime(timerHour, timerMinute);
        timePickerDialog.show();
    }

    public void openNumberPicker() {
        DialougClass dialougClass = new DialougClass();
        dialougClass.show(getActivity().getSupportFragmentManager(), "NumberDialoug");

    }

    public void openStrengthDialoug() {
        StrengthDialog strengthDialog = new StrengthDialog();
        strengthDialog.show(getActivity().getSupportFragmentManager(), "strengthDialoug");
    }

    //==================================================================
    public void openRefillDialoug() {
        refillTimeDialoug refillTimeDialoug = new refillTimeDialoug();
        refillTimeDialoug.show(getActivity().getSupportFragmentManager(), "refillDialog");
    }

    public void openDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                binding.selectdate.setText(date);
            }
        }, '1', 2, 5);
        datePickerDialog.show();
    }

    @Override
    public void displayText(String number) {
        binding.numberTxt.setText(number);
    }

    @Override
    public void showText(String number) {
        binding.PresstoadjustTxt.setText(number);
    }

    @Override
    public void chooseRefillAmount(String amount) {
        binding.selectAmountRefill.setText(amount);

    }
}