package com.example.medicationreminder.addmedication.view;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
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
import com.example.medicationreminder.workmanger.DailyWorker;
import com.example.medicationreminder.workmanger.ReminderWorkerDrugs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class AddMedicationFragment extends Fragment implements OnDialogClickListener, AddMedicationViewInterface {
    FragmentAddMedicationBinding binding;
    int iconId;
    int timerHour, timerMinute;
    String frequencyRepition;
    ArrayList<String> days;
    Medication medication;
    AddMedicationPresenterInterface addMedicationPresenterInterface;
   String []drugs;
   long[]longDrugs;
    int count=-1;
    public AddMedicationFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        days = new ArrayList<>();


         drugs=new String[3];
         longDrugs=new  long[4];
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
                frequencyRepition = adapterView.getSelectedItem().toString();
                if (frequencyRepition == adapterView.getItemAtPosition(1).toString()) {
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
                callWorkManger(saveData().getMedicine_Name(),getContext());
                Navigation.findNavController(view).navigate(R.id.action_addMedicationFragment_to_medicationsFragment2);
            }
        });
        binding.doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick:sssssssssss ");
                addMedicationPresenterInterface.insert(saveData());
                callWorkManger(saveData().getMedicine_Name(),getContext());

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
                iconId = binding.capsules.getId();

            }
        });
        binding.spoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iconId = binding.spoon.getId();

            }
        });
        //========================================================================
        binding.respirator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iconId = binding.respirator.getId();

            }
        });
        //=======================================================================
        binding.transfusion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iconId = binding.transfusion.getId();

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
            @Override
            public void onClick(View view) {
                iconId = binding.ointment.getId();

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
                Log.e(TAG, "onClick: dododododo");
                addMedicationPresenterInterface.insert(saveData());
                callWorkManger(saveData().getMedicine_Name(),getContext());

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
                Log.e(TAG, "onClick: sdfgj");
                openTimerPickerDialoug(binding.timerTxtone);
                drugs[0]=binding.timerTxtone.getText().toString();
                Log.e(TAG, "onClick: "+drugs[0]);
            }
        });
        //======================================================================
        binding.timerTxttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimerPickerDialoug( binding.timerTxttwo);

                drugs[1]=binding.timerTxtone.getText().toString();

            }
        });
        binding.timerTxtthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimerPickerDialoug(binding.timerTxtthree);

                drugs[2]=binding.timerTxtone.getText().toString();

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

    private void callWorkManger(String medName, Context context) {
        Log.e(TAG, "callWorkManger: "+longDrugs.length);
        Log.e(TAG, "callWorkManger: ");
        WorkRequest createRequest =
                new PeriodicWorkRequest.Builder(DailyWorker.class,
                        24, TimeUnit.HOURS)
                        .setInputData(
                                new Data.Builder()
                                        .putLongArray("drugTimes",longDrugs)
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
    public void openTimerPickerDialoug(TextView text) {
           count++;
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {


                Calendar calendar = Calendar.getInstance();
                calendar.set(hourOfDay,minute,0);
                long startTime = calendar.getTimeInMillis();

                Log.e(TAG, "onTimeSet: nww"+startTime);

                longDrugs[count]=startTime;
                /////////
                timerHour = hourOfDay;
                timerMinute = minute;
                String time = timerHour + ":" + timerMinute;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                try {
                    Date date = simpleDateFormat.parse(time);
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm:aa");
                    text.setText(simpleDateFormat1.format(date));

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

        Medication medication = new Medication();

        medication.setMedicine_Name(binding.medName.getText().toString());
        medication.setStrength(binding.PresstoadjustTxt.getText().toString());
        medication.setDrugAmount(binding.numberTxtone.getText().toString());
        medication.setStratingDate(binding.selectdate.getText().toString());
        medication.setDaily(binding.isdaily.isChecked());
        medication.setDrugAmount(binding.amountofdrug.getText().toString());
        medication.setLeftDrug(binding.selectAmountRefill.getText().toString());
           medication.setDrugs(drugs);
        medication.setNoOfDays(binding.noOfdaysEdite.getText().toString());
        medication.setRefilTime(binding.selectRefillTime.getText().toString());
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


        medication.setRefillReminder(binding.isReminderd.isChecked());
        medication.setDays(days);
        medication.setIcon(iconId);
        medication.setFrequencyOfrepition(frequencyRepition);
        return medication;
    }
}