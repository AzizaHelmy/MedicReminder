package com.example.medicationreminder.updatemedication.view;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;

import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.R;

import com.example.medicationreminder.addmedication.DialougClass;
import com.example.medicationreminder.addmedication.RefillTimeDialoug;
import com.example.medicationreminder.addmedication.StrengthDialog;
import com.example.medicationreminder.addmedication.view.OnDialogClickListener;
import com.example.medicationreminder.databinding.FragmentDisplayMedicineBinding;
import com.example.medicationreminder.databinding.FragmentUpdateMedicationBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;
import com.example.medicationreminder.updatemedication.presenter.UpdateMedicationPresenterInterface;
import com.example.medicationreminder.updatemedication.presenter.UpdateMedicationpresenter;

public class UpdateMedicationFragment extends Fragment implements UpdateMedicationViewInterface , OnDialogClickListener {
    String frequencyRepition;
    int frequncyid;
    UpdateMedicationPresenterInterface updateMedicationPresenterInterface;
    Medication medication;
    FragmentUpdateMedicationBinding binding;
    public UpdateMedicationFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdateMedicationBinding.inflate(getLayoutInflater(), container, false);
        updateMedicationPresenterInterface =new UpdateMedicationpresenter(getActivity(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())),this);
        View view = binding.getRoot();

        setData();
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

                    binding.oneTimeaday.setVisibility(View.VISIBLE);

                    binding.twoTimeaday.setVisibility(View.VISIBLE);
                    binding.threeTimeaday.setVisibility(View.GONE);

                } else if (frequencyRepition == adapterView.getItemAtPosition(3).toString()) {
                    binding.timerTxtthree.setText("");
                    binding.oneTimeaday.setVisibility(View.VISIBLE);
                    binding.twoTimeaday.setVisibility(View.VISIBLE);
                    binding.threeTimeaday.setVisibility(View.VISIBLE);
                } else {

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
        return view;

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
            // binding.numberTxtone.setText(medication.getNoOfDose()[0]);
//            binding.selectdate.setText(medication.getStratingDate());
//            binding.isdaily.setChecked(medication.isDaily());
            binding.amountofdrug.setText(medication.getDrugAmount());
            binding.selectAmountRefill.setText(medication.getLeftDrug());
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

    }

    @Override
    public void showText(String number) {

    }

    @Override
    public void chooseRefillAmount(String amount) {

    }

    @Override
    public void displayNumbreTwo(String number) {

    }
}