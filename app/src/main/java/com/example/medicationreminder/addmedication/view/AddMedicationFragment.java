package com.example.medicationreminder.addmedication.view;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.R;

import com.example.medicationreminder.workmanger.RefillReminderWorker;
import com.example.medicationreminder.addmedication.DialougClass;
import com.example.medicationreminder.addmedication.StrengthDialog;
import com.example.medicationreminder.addmedication.presenter.AddMedicationPresenter;
import com.example.medicationreminder.addmedication.presenter.AddMedicationPresenterInterface;
import com.example.medicationreminder.addmedication.RefillTimeDialoug;

import com.example.medicationreminder.databinding.FragmentAddMedicationBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;
import com.example.medicationreminder.workmanger.DailyWorker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class AddMedicationFragment extends Fragment implements OnDialogClickListener, AddMedicationViewInterface {
    FragmentAddMedicationBinding binding;
    int iconId;
    int timerHour, timerMinute;
    String frequencyRepition;
    ArrayList<String> days;
    String[] doses;
    Medication medication;
    String dailyOrCertain;
    String duration;
    String noOfDays;
    DatePicker workerDatePicker;
    int frequncyid;
    AddMedicationPresenterInterface addMedicationPresenterInterface;
    String[] drugs;
    long[] longDrugs;
    int count = -1;
    SharedPreferences sharedPreferences;

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
        //============================================
        binding.afterLayout3.setEnabled(false);
        TextWatcher textWatcher3 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.timerTxtone.getText().length() > 0 && binding.timerTxttwo.getText().length() > 0 && binding.timerTxtthree.getText().length() > 0) {
                    binding.afterLayout3.setEnabled(true);
                } else if (binding.timerTxtone.getText().length() == 0 || binding.timerTxttwo.getText().length() == 0 || binding.timerTxtthree.getText().length() == 0)
                    binding.afterLayout3.setEnabled(false);
                Toast.makeText(getContext(), "fill the time", Toast.LENGTH_SHORT).show();
            }


        };
        //==============================================
        //binding.afterLayout3.setEnabled(false);
        TextWatcher textWatcher2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.timerTxtone.getText().length() > 0 && binding.timerTxttwo.getText().length() > 0) {
                    binding.afterLayout3.setEnabled(true);
                } else if (binding.timerTxtone.getText().length() == 0 || binding.timerTxttwo.getText().length() == 0)
                    binding.afterLayout3.setEnabled(false);
                Toast.makeText(getContext(), "fill the time", Toast.LENGTH_SHORT).show();
            }


        };
        //===========================================
        //binding.afterLayout3.setEnabled(false);
        TextWatcher textWatcher1 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.timerTxtone.getText().length() > 0) {
                    binding.afterLayout3.setEnabled(true);
                } else
                    binding.afterLayout3.setEnabled(false);
                Toast.makeText(getContext(), "fill the time", Toast.LENGTH_SHORT).show();
            }


        };
 //================================================
        binding.medName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.medName.getText().length() > 0) {
                    binding.afterLayout1.setEnabled(true);
                } else
                    binding.afterLayout1.setEnabled(false);
            }
        });
        //===========================================
        if (binding.medName.getText().toString().trim().isEmpty()) {
            binding.afterLayout1.setEnabled(false);
        } else {
            Log.e(TAG, "fully ");
            Toast.makeText(getContext(), "fully", Toast.LENGTH_SHORT).show();
            binding.afterLayout1.setEnabled(true);
        }
        //====================================================

        binding.selectdays.setVisibility(View.GONE);
        binding.afterreminderSwitch.setVisibility(View.GONE);
        binding.isdaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.selectdays.setVisibility(View.GONE);
                duration = binding.noOfdaysEdite.getText().toString() + "days" + " " + binding.isdaily.getText();
                Log.i("duration", "" + duration);
                dailyOrCertain = binding.isdaily.getText().toString();
                Log.i("dailee", "" + binding.isdaily.getText().toString());


            }
        });
        //=================================================================
        binding.sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.sun.isChecked()) {
                    days.add(binding.sun.getText().toString());
                    duration = binding.noOfdaysEdite.getText().toString() + "days" + days;
                    Log.i("salooooooooomaaaa", "" + duration);
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
                    duration = binding.noOfdaysEdite.getText().toString() + "days" + days;
                    Log.i("salooooooooomaaaa", "" + duration);
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
                    duration = binding.noOfdaysEdite.getText().toString() + "days" + days;
                    Log.i("salooooooooomaaaa", "" + duration);
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
                    duration = binding.noOfdaysEdite.getText().toString() + "days" + days;
                    Log.i("salooooooooomaaaa", "" + duration);
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
                    duration = binding.noOfdaysEdite.getText().toString() + "days" + days;
                    Log.i("salooooooooomaaaa", "" + duration);
                }
            }
        });
        //===========================================================
//        binding.reminderswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (binding.reminderswitch.isChecked()) {
//                    binding.afterreminderSwitch.setVisibility(View.VISIBLE);
//                } else {
//                    binding.afterreminderSwitch.setVisibility(View.GONE);
//                }
//            }
//        });
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
                    drugs = new String[1];
                    longDrugs = new long[1];
                    binding.timerTxtone.setText("");
                    binding.afterLayout3.setEnabled(false);
                    binding.timerTxtone.addTextChangedListener(textWatcher1);
                    binding.oneTimeaday.setVisibility(View.VISIBLE);
                    binding.twoTimeaday.setVisibility(View.GONE);
                    binding.threeTimeaday.setVisibility(View.GONE);
                    Log.i("spinnerr", "the first item is" + adapterView.getItemAtPosition(1).toString() + frequencyRepition);
                } else if (frequencyRepition == adapterView.getItemAtPosition(2).toString()) {
                    drugs = new String[2];
                    longDrugs = new long[2];

                    binding.timerTxttwo.setText("");
                    binding.afterLayout3.setEnabled(false);
                    binding.timerTxttwo.addTextChangedListener(textWatcher2);
                    binding.oneTimeaday.setVisibility(View.VISIBLE);

                    binding.twoTimeaday.setVisibility(View.VISIBLE);
                    binding.threeTimeaday.setVisibility(View.GONE);

                } else if (frequencyRepition == adapterView.getItemAtPosition(3).toString()) {
                    drugs = new String[3];
                    longDrugs = new long[3];

                    binding.timerTxtthree.setText("");
                    binding.afterLayout3.setEnabled(false);
                    binding.timerTxtthree.addTextChangedListener(textWatcher3);
                    binding.oneTimeaday.setVisibility(View.VISIBLE);
                    binding.twoTimeaday.setVisibility(View.VISIBLE);
                    binding.threeTimeaday.setVisibility(View.VISIBLE);
                } else {

                    binding.afterLayout3.setEnabled(false);
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
        //===================================================
        noOfDays = binding.noOfdaysEdite.getText().toString();
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

                Log.i("duration", "" + duration);
                dailyOrCertain = binding.specificdays.getText().toString();
                Log.i("dailee", "" + binding.specificdays.getText().toString());
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
                callWorkManger(saveData().getMedicine_Name(), getContext());
                Navigation.findNavController(view).navigate(R.id.action_addMedicationFragment_to_medicationsFragment2);
            }
        });
//        binding.doneBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addMedicationPresenterInterface.insert(saveData());
//                Navigation.findNavController(view).navigate(R.id.action_addMedicationFragment_to_medicationsFragment2);
//            }
//        });

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
                binding.afterreminderSwitch.setVisibility(View.VISIBLE);


            }

        });

        //=========================================================================
        binding.isRefillReminderd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.isRefillReminderd.isChecked()) {
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
                iconId = R.drawable.respirator;


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
                iconId = R.drawable.pill;
            }
        });
        //=======================================================================
        binding.ointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iconId = R.drawable.ointment;

            }
        });
        //=======================================================================
        binding.vaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iconId = binding.vaccine.getId();

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

                addMedicationPresenterInterface.insert(saveData());
                callWorkManger(saveData().getMedicine_Name(), getContext());
                setUpWorkerForRefill(saveData().getMedicine_Name(), getContext());
                setUpWorkerForRefill(saveData().getMedicine_Name(), getContext());
                Navigation.findNavController(view).navigate(R.id.action_addMedicationFragment_to_medicationsFragment2);

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
                openTimerPickerDialoug(binding.timerTxtone);
            }
        });
        //======================================================================

        //==================================================================
        binding.timerTxttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openTimerPickerDialoug(binding.timerTxttwo);


            }
        });
        binding.timerTxtthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimerPickerDialoug(binding.timerTxtthree);

//                drugs[2]=binding.timerTxtone.getText().toString();

            }
        });
        //=======================================================================
        binding.numberTxtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: " + "erroe");
                openNumberPicker();
                // doses[0]=binding.numberTxtone.getText().toString();
            }
        });
        //======================================================================
        binding.numberTxttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNumberPicker();
                //  doses[1]=binding.numberTxttwo.getText().toString();
            }
        });
        //======================================================================
        binding.numberTxtthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNumberPicker();
                // doses[2]=binding.numberTxtthree.getText().toString();
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

    //=================================================================
    private void setUpWorkerForRefill(String medName, Context context) {
        @SuppressLint("IdleBatteryChargingConstraints")
        Constraints constraints = new Constraints.Builder()
                //.setRequiresDeviceIdle(true)
                .setRequiresCharging(true)
                .build();
        PeriodicWorkRequest refillReminderRequest = new PeriodicWorkRequest
                .Builder(RefillReminderWorker.class, 1, TimeUnit.DAYS)
                //.setConstraints(constraints)
                // .setInitialDelay(diff,TimeUnit.MILLISECONDS )
                .build();
        // }
        WorkManager.getInstance(getContext()).enqueue(refillReminderRequest);

    }

    //=============================================================
    private void callWorkManger(String medName, Context context) {

        WorkRequest createRequest =
                new PeriodicWorkRequest.Builder(DailyWorker.class,
                        24, TimeUnit.HOURS)
                        .setInputData(
                                new Data.Builder()
                                        .putLongArray("drugTimes", longDrugs)
                                        .putString("medName", medName)
                                        // .putInt("dose", medication.getMedDetails().get(0).getDose())
                                        // .putString("medFood",medName)
                                        .build()
                        )
                        .addTag(medName)
                        .build();

        WorkManager.getInstance(context).enqueue(createRequest);


    }

    //======================================================================
    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    //=======================================================================
    public void openTimerPickerDialoug(TextView timerTxt) {

        count++;
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(workerDatePicker.getYear(), workerDatePicker.getMonth(), workerDatePicker.getDayOfMonth(),
                        timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
                long startTime = calendar.getTimeInMillis();


                longDrugs[count] = startTime;

                timerHour = hourOfDay;
                timerMinute = minute;
                String time = timerHour + ":" + timerMinute;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                try {
                    Date date = simpleDateFormat.parse(time);
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm:aa");
                    timerTxt.setText(simpleDateFormat1.format(date));
                    drugs[count] = timerTxt.getText().toString();

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


                workerDatePicker = datePicker;
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
        Medication medication = new Medication();
        medication.setStatus("Active");
        medication.setRemindered(true);
        medication.setMedicine_Name(binding.medName.getText().toString());
        medication.setStrength(binding.PresstoadjustTxt.getText().toString());
        medication.setNoOfDose(doses);
        medication.setDrugAmount(binding.numberTxtone.getText().toString());
        medication.setStratingDate(binding.selectdate.getText().toString());
        medication.setDaily(binding.isdaily.isChecked());
        medication.setDuration(duration);
        medication.setDrugAmount(binding.amountofdrug.getText().toString());
        medication.setLeftDrug(binding.selectAmountRefill.getText().toString());
        medication.setFrequencyOfRepitionId(frequncyid);
        medication.setNoOfDays(binding.noOfdaysEdite.getText().toString());
        medication.setRefilTime(binding.selectRefillTime.getText().toString());
        if (binding.beforeeatingRadio.isChecked()) {
            medication.setInstructions(binding.beforeeatingRadio.getText().toString());
        }
        if (binding.aftereatingRadio.isChecked()) {
            medication.setInstructions(binding.aftereatingRadio.getText().toString());
        }
        if (binding.whileeatingRadio.isChecked()) {
            medication.setInstructions(binding.whileeatingRadio.getText().toString());
        }

        medication.setDrugs(drugs);

        medication.setRefillReminder(binding.isRefillReminderd.isChecked());
        medication.setDays(days);
        medication.setIcon(iconId);
        medication.setFrequencyOfrepition(frequencyRepition);
        return medication;
    }

    public void insertFirebase() {
        sharedPreferences = getActivity().getSharedPreferences("sharedName", Context.MODE_PRIVATE);
    }

    private static long parseDate(String text)
            throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a",
                Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.parse(text).getTime();
    }
//    public void insertFirebase() {
//        sharedPreferences = getActivity().getSharedPreferences("sharedName", Context.MODE_PRIVATE);
//
//    }
}