package com.example.medicationreminder.updatemedication.view;

import static android.content.ContentValues.TAG;

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

import com.example.medicationreminder.addmedication.DialougClass;
import com.example.medicationreminder.addmedication.RefillTimeDialoug;
import com.example.medicationreminder.addmedication.StrengthDialog;
import com.example.medicationreminder.addmedication.presenter.AddMedicationPresenter;
import com.example.medicationreminder.addmedication.view.OnDialogClickListener;
import com.example.medicationreminder.databinding.FragmentAddMedicationBinding;
import com.example.medicationreminder.databinding.FragmentDisplayMedicineBinding;
import com.example.medicationreminder.databinding.FragmentUpdateMedicationBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;
import com.example.medicationreminder.updatemedication.presenter.UpdateMedicationPresenterInterface;
import com.example.medicationreminder.updatemedication.presenter.UpdateMedicationpresenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UpdateMedicationFragment extends Fragment implements UpdateMedicationViewInterface , OnDialogClickListener {
    int iconId;
    int timerHour, timerMinute;
    String frequencyRepition;
    ArrayList<String> days;
    String [] doses=new String[3];

    Medication medication;
    String dailyOrCertain;
    String duration;
    String noOfDays;


    int frequncyid;
    UpdateMedicationPresenterInterface updateMedicationPresenterInterface;

    FragmentUpdateMedicationBinding binding;
    public UpdateMedicationFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        days = new ArrayList<>();
        //=========================================================================

        //================================================================================
        updateMedicationPresenterInterface = new UpdateMedicationpresenter(getActivity(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())), this);
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
        binding = FragmentUpdateMedicationBinding.inflate(getLayoutInflater(), container, false);
setData();
        //============================================

        TextWatcher textWatcher3=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.timerTxtone.getText().length() > 0&&binding.timerTxttwo.getText().length() > 0&&binding.timerTxtthree.getText().length() > 0) {


                } else if(binding.timerTxtone.getText().length() == 0||binding.timerTxttwo.getText().length() == 0||binding.timerTxtthree.getText().length()== 0);


            }


        };
        //=============================================
        binding.doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
                Navigation.findNavController(view).navigate(R.id.action_updateMedicationFragment_to_medicationsFragment);
            }
        });
        //==============================================
        //binding.afterLayout3.setEnabled(false);
        TextWatcher textWatcher2=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.timerTxtone.getText().length() > 0&&binding.timerTxttwo.getText().length() > 0) {




                } else if(binding.timerTxtone.getText().length() == 0||binding.timerTxttwo.getText().length() == 0);


            }


        };
        //===========================================
        //binding.afterLayout3.setEnabled(false);


        //================================================

        //========================================================================

        //========================================================================


        //================================================


        //===========================================



        binding.isdaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.selectdays.setVisibility(View.GONE);
                duration=binding.noOfdaysEdite.getText().toString()+"days"+" "+binding.isdaily.getText();
                Log.i("duration",""+duration);
                dailyOrCertain=binding.isdaily.getText().toString();
                Log.i("dailee",""+binding.isdaily.getText().toString());


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
                    duration=binding.noOfdaysEdite.getText().toString()+"days"+days;
                    Log.i("salooooooooomaaaa",""+duration);
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
                    duration=binding.noOfdaysEdite.getText().toString()+"days"+days;
                    Log.i("salooooooooomaaaa",""+duration);
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
                    binding.timerTxtone.setText("");

                    binding.oneTimeaday.setVisibility(View.VISIBLE);
                    binding.twoTimeaday.setVisibility(View.GONE);
                    binding.threeTimeaday.setVisibility(View.GONE);
                    Log.i("spinnerr", "the first item is" + adapterView.getItemAtPosition(1).toString() + frequencyRepition);
                } else if (frequencyRepition == adapterView.getItemAtPosition(2).toString()) {


                    binding.timerTxttwo.setText("");

                    binding.timerTxttwo.addTextChangedListener(textWatcher2);
                    binding.oneTimeaday.setVisibility(View.VISIBLE);

                    binding.twoTimeaday.setVisibility(View.VISIBLE);
                    binding.threeTimeaday.setVisibility(View.GONE);

                } else if (frequencyRepition == adapterView.getItemAtPosition(3).toString()) {
                    binding.timerTxtthree.setText("");

                    binding.timerTxtthree.addTextChangedListener(textWatcher3);
                    binding.oneTimeaday.setVisibility(View.VISIBLE);
                    binding.twoTimeaday.setVisibility(View.VISIBLE);
                    binding.threeTimeaday.setVisibility(View.VISIBLE);
                } else {


                    binding.oneTimeaday.setVisibility(View.VISIBLE);
                    binding.twoTimeaday.setVisibility(View.VISIBLE);
                    binding.threeTimeaday.setVisibility(View.VISIBLE);
                }
                Log.i("spinner", "" + frequencyRepition);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //===================================================
        noOfDays=binding.noOfdaysEdite.getText().toString();
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

                Log.i("duration",""+duration);
                dailyOrCertain=binding.specificdays.getText().toString();
                Log.i("dailee",""+binding.specificdays.getText().toString());
            }
        });





        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //==========================================================================


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

        //============================================================================

        //=============================================================================
        binding.timerTxtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimerPickerDialoug(binding.timerTxtone);
            }
        });
        //======================================================================
        binding.timerTxttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimerPickerDialoug(binding.timerTxttwo);
            }
        });
        //==================================================================
        binding.timerTxtthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimerPickerDialoug(binding.timerTxtthree);
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
                doses[0]=binding.numberTxtone.getText().toString();
                doses[1]=binding.numberTxttwo.getText().toString();
                doses[2]=binding.numberTxtthree.getText().toString();
                Log.i("dose",""+doses[0]);
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
    public void openTimerPickerDialoug(TextView timerTxt) {
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
                    timerTxt.setText(simpleDateFormat1.format(date));
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
        binding.numberTxttwo.setText(number);
        binding.numberTxtthree.setText(number);
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

    }

    @Override
    public void update() {
        updateMedicationPresenterInterface.update(medication);
    }
    public void setData() {
        Bundle args = getArguments();
        if (args != null) {
            medication = (Medication) args.getSerializable("edite");

            binding.medName.setText(medication.getMedicine_Name());
            binding.PresstoadjustTxt.setText(medication.getStrength());
//            binding.numberTxtone.setText(medication.getNoOfDose()[0]);
//            binding.numberTxttwo.setText(medication.getNoOfDose()[1]);
//            binding.numberTxttwo.setText(medication.getNoOfDose()[2]);
            binding.selectdate.setText(medication.getStratingDate());
            binding.isdaily.setChecked(medication.isDaily());
            binding.amountofdrug.setText(medication.getDrugAmount()+" have");
            binding.selectAmountRefill.setText(medication.getLeftDrug()+"left");
//            binding.noOfdaysEdite.setText(medication.getNoOfDays());
//            binding.selectRefillTime.setText(medication.getRefilTime());

//        if (binding.beforeeatingRadio.isChecked()) {
//            medication.setInstructions(binding.beforeeatingRadio.getText().toString());
//            Log.i("med", binding.beforeeatingRadio.getText().toString());
//        }
//        if (binding.aftereatingRadio.isChecked()) {
//            medication.setInstructions(binding.aftereatingRadio.getText().toString());
//            Log.i("med", binding.aftereatingRadio.getText().toString());
//        }
//        if (binding.whileeatingRadio.isChecked()) {
//            medication.setInstructions(binding.whileeatingRadio.getText().toString());
//            Log.i("med", binding.whileeatingRadio.getText().toString());
//        }
//        Log.e(TAG, "saveData: " + medication.getMedicine_Name());
//            binding.isReminderd.setChecked(medication.isRemindered());
//            binding.frequencyOfRepition.setSelection(medication.getFrequencyOfRepitionId());

        }
    }
}