package com.example.medicationreminder.addmedication.view;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.R;

import com.example.medicationreminder.addmedication.DialougClass;
import com.example.medicationreminder.addmedication.StrengthDialog;
import com.example.medicationreminder.addmedication.StrengthDialog;
import com.example.medicationreminder.addmedication.presenter.AddMedicationPresenter;
import com.example.medicationreminder.addmedication.presenter.AddMedicationPresenterInterface;
import com.example.medicationreminder.addmedication.RefillTimeDialoug;
import com.example.medicationreminder.databinding.FragmentAddMedicationBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AddMedicationFragment extends Fragment implements OnDialogClickListener, AddMedicationViewInterface {
    FragmentAddMedicationBinding binding;
    int iconId;
    int timerHour, timerMinute;
    String frequencyRepition;
    ArrayList<String> days;
  //  String [] doses;
  //  String []  times=new String[3];
    Medication medication;
    String dailyOrCertain;
    String duration;
    String noOfDays;


    int frequncyid;
    AddMedicationPresenterInterface addMedicationPresenterInterface;

    public AddMedicationFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        days = new ArrayList<>();
        addMedicationPresenterInterface = new AddMedicationPresenter(getActivity(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())), this);
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
        binding.oneTimeaday.setVisibility(View.GONE);
        binding.twoTimeaday.setVisibility(View.GONE);
        binding.threeTimeaday.setVisibility(View.GONE);
        //====================================================

        binding.selectdays.setVisibility(View.GONE);
        binding.afterreminderSwitch.setVisibility(View.GONE);
        binding.isdaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.selectdays.setVisibility(View.GONE);


            }
        });
        //=================================================================
        binding.sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.sun.isChecked()) {
                    days.add(binding.sun.getText().toString());
                    duration=binding.noOfdaysEdite.getText().toString()+"days"+days;
                    Log.i("salooooooooomaaaa",""+duration);
                    Log.i("saloka", "day : " + binding.sun.getText().toString());
                }
            }
        });
        //============================================
        binding.mon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.mon.isChecked()) {
                    days.add(binding.mon.getText().toString());
                    duration=binding.noOfdaysEdite.getText().toString()+"days"+days;
                    Log.i("salooooooooomaaaa",""+duration);
                    Log.i("saloka", "day : " + binding.mon.getText().toString());
                }
            }
        });
        //==============================================
        binding.tue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.sun.isChecked()) {
                    days.add(binding.tue.getText().toString());
                    Log.i("saloka", "day : " + binding.tue.getText().toString());
                }
            }
        });
        //=============================================
        binding.wes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.wes.isChecked()) {
                    days.add(binding.sun.getText().toString());
                    Log.i("saloka", "day : " + binding.wes.getText().toString());
                }
            }
        });
        //===========================================================
        binding.thu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.sun.isChecked()) {
                    days.add(binding.thu.getText().toString());
                    Log.i("saloka", "day : " + binding.thu.getText().toString());
                    duration=binding.noOfdaysEdite.getText().toString()+"days"+days;
                    Log.i("salooooooooomaaaa",""+duration);
                }
            }
        });
        //===========================================================
        binding.reminderswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.reminderswitch.isChecked()) {
                    binding.afterreminderSwitch.setVisibility(View.VISIBLE);
                } else {
                    binding.afterreminderSwitch.setVisibility(View.GONE);
                }
            }
        });
        //============================================================
        binding.fri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.fri.isChecked()) {
                    days.add(binding.fri.getText().toString());
                    Log.i("saloka", "day : " + binding.fri.getText().toString());
                }
            }
        });
        //==========================================================
        binding.frequencyOfRepition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                frequncyid = adapterView.getSelectedItemPosition();
                Log.i("salma", "" + frequncyid);
                frequencyRepition = adapterView.getSelectedItem().toString();
                Log.i("salmaaa", "" + frequencyRepition);
                if (frequencyRepition == adapterView.getItemAtPosition(1).toString()) {
                    binding.timerTxtone.setText("");
                    binding.afterLayout3.setEnabled(false);
                    binding.timerTxtone.addTextChangedListener(textWatcher1);
                    binding.oneTimeaday.setVisibility(View.VISIBLE);
                    Log.i("spinnerr", "the first item is" + adapterView.getItemAtPosition(1).toString() + frequencyRepition);
                } else if (frequencyRepition == adapterView.getItemAtPosition(2).toString()) {
                    binding.twoTimeaday.setVisibility(View.VISIBLE);

                } else if (frequencyRepition == adapterView.getItemAtPosition(3).toString()) {
                    binding.threeTimeaday.setVisibility(View.VISIBLE);
                } else {
                    //binding.noOftimesLayout.setVisibility(View.GONE);
                    binding.oneTimeaday.setVisibility(View.GONE);
                    binding.twoTimeaday.setVisibility(View.GONE);
                    binding.threeTimeaday.setVisibility(View.GONE);
                }
                Log.i("spinner", "" + frequencyRepition);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //=======================================================
        binding.sat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.sun.isChecked()) {
                    days.add(binding.sat.getText().toString());
                    Log.i("saloka", "day : " + binding.sat.getText().toString());
                }
            }
        });
        //===========================================================

        binding.specificdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.selectdays.setVisibility(View.VISIBLE);
            }
        });

        binding.ifIsReminderdLayout.setVisibility(View.GONE);
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
                addMedicationPresenterInterface.insert(saveData());
                Navigation.findNavController(view).navigate(R.id.action_addMedicationFragment_to_medicationsFragment2);
            }
        });
        binding.doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMedicationPresenterInterface.insert(saveData());
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
        binding.isReminderd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.isReminderd.isChecked()) {
                    binding.ifIsReminderdLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        //=========================================================================
        binding.capsules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iconId = R.drawable.capsules;

            }
        });
        binding.spoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iconId = R.drawable.spoon;

            }
        });
        //========================================================================
        binding.respirator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iconId = R.drawable.register_icon;

            }
        });
        //=======================================================================
        binding.transfusion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iconId = R.drawable.transfusion;

            }
        });
        //=====================================================================
        binding.pill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iconId = binding.pill.getId();

            }
        });
        //=======================================================================
        binding.ointment.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                iconId =  R.drawable.ointment;


              //  Log.e(TAG, "onClick:salma salma "+ binding.ointment.getSourceLayoutResId());

            }
        });
        //=======================================================================
        binding.vaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iconId = R.drawable.vaccine;

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
        //===========================================================================
        binding.doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: ");
                addMedicationPresenterInterface.insert(saveData());
                Navigation.findNavController(view).navigate(R.id.action_addMedicationFragment_to_medicationsFragment2);
                Log.i("tag", "what is your problem");
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
        binding.timerTxtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimerPickerDialoug();
            }
        });
        //======================================================================
        binding.timerTxttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimerPickerDialoug();
            }
        });
        //=======================================================================
        binding.numberTxtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: " + "erroe");
                openNumberPicker();
            }
        });
        //======================================================================
        binding.numberTxttwo.setOnClickListener(new View.OnClickListener() {
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
        binding.selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });
        //========================================================================
        binding.selectRefillTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRefillTimePicker();
            }
        });
        //====================================================================
        binding.PresstoadjustTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStrengthDialoug();
            }
        });

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
                    binding.timerTxtone.setText(simpleDateFormat1.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, 12, 0, false);
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.updateTime(timerHour, timerMinute);
        timePickerDialog.show();
    }

    //========================================================
    public void openRefillTimePicker() {
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
                    binding.selectRefillTime.setText(simpleDateFormat1.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, 12, 0, false);
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.updateTime(timerHour, timerMinute);
        timePickerDialog.show();
    }

    //=====================================================
    public void openNumberPicker() {
        Log.e(TAG, "openNumberPicker: " + "error");
        DialougClass dialougClass = new DialougClass();
        dialougClass.show(getChildFragmentManager(), "NumberDialoug");

    }

    public void openStrengthDialoug() {
        StrengthDialog strengthDialog = new StrengthDialog();
        strengthDialog.show(getChildFragmentManager(), "strengthDialoug");
    }
    //==================================================================
    public void openRefillDialoug() {
        RefillTimeDialoug refillTimeDialoug = new RefillTimeDialoug();
        refillTimeDialoug.show(getChildFragmentManager(), "refillDialog");
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
        binding.numberTxtone.setText(number);
    }

    @Override
    public void showText(String number) {
        Log.e(TAG, "showText: " + number);
        binding.PresstoadjustTxt.setText(number);
    }

    @Override
    public void chooseRefillAmount(String amount) {
        Log.e(TAG, "chooseRefillAmount: ");
        binding.selectAmountRefill.setText(amount);

    }

    @Override
    public void displayNumbreTwo(String number) {
        binding.numberTxttwo.setText(number);
    }

    @Override
    public void insert() {
        addMedicationPresenterInterface.insert(medication);
    }


    //==========================================================
    public Medication saveData() {
        Log.e(TAG, "saveData: ");
        Medication medication = new Medication();
        medication.setStatus("Active");
        medication.setRemindered(true);
        medication.setMedicine_Name(binding.medName.getText().toString());
        Log.i("med", "" + binding.medName.getText().toString());
        Log.i("salasoka", "hdjhwjq");
        medication.setStrength(binding.PresstoadjustTxt.getText().toString());
        Log.i("med", "" + binding.PresstoadjustTxt.getText().toString());
        medication.setDrugAmount(binding.numberTxtone.getText().toString());
        Log.i("med", "" + binding.numberTxtone.getText().toString());
        medication.setStratingDate(binding.selectdate.getText().toString());
        Log.i("med", "" + binding.selectdate.getText().toString());
        medication.setDaily(binding.isdaily.isChecked());
        Log.i("med", "" + binding.isdaily.isChecked());
        medication.setDrugAmount(binding.amountofdrug.getText().toString());
        Log.i("med", "" + binding.amountofdrug.getText().toString());
        medication.setLeftDrug(binding.selectAmountRefill.getText().toString());
        Log.i("med", "" + binding.selectAmountRefill.getText().toString());
      medication.setRemindered(binding.reminderswitch.isChecked());
        medication.setNoOfDays(binding.noOfdaysEdite.getText().toString());
        Log.i("med", "" + binding.noOfdaysEdite.getText().toString());
        medication.setRefilTime(binding.selectRefillTime.getText().toString());
        Log.i("med", binding.selectRefillTime.getText().toString());
        if (binding.beforeeatingRadio.isChecked()) {
            medication.setInstructions(binding.beforeeatingRadio.getText().toString());
            Log.i("med", binding.beforeeatingRadio.getText().toString());
        }
        if (binding.aftereatingRadio.isChecked()) {
            medication.setInstructions(binding.aftereatingRadio.getText().toString());
            Log.i("med", binding.aftereatingRadio.getText().toString());
        }
        if (binding.whileeatingRadio.isChecked()) {
            medication.setInstructions(binding.whileeatingRadio.getText().toString());
            Log.i("med", binding.whileeatingRadio.getText().toString());
        }
        Log.e(TAG, "saveData: " + medication.getMedicine_Name());


        medication.setRefillReminder(binding.isReminderd.isChecked());
        Log.i("med", "" + binding.isReminderd.isChecked());
        medication.setDays(days);
        Log.i("days", "" + days);
        medication.setIcon(iconId);
        Log.i("icon", "iconId:" + iconId);
        medication.setFrequencyOfrepition(frequencyRepition);
        return medication;
    }
}