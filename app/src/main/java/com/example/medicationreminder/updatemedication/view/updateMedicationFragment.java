package com.example.medicationreminder.updatemedication.view;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.R;
import com.example.medicationreminder.addmedication.presenter.AddMedicationPresenter;
import com.example.medicationreminder.addmedication.presenter.AddMedicationPresenterInterface;
import com.example.medicationreminder.databinding.FragmentAddMedicationBinding;
import com.example.medicationreminder.databinding.FragmentUpdateMedicationBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;
import com.example.medicationreminder.updatemedication.presenter.UpdateMedicationPresenterInterface;
import com.example.medicationreminder.updatemedication.presenter.UpdateMedicationpresenter;

public class updateMedicationFragment extends Fragment implements UpdateMedicationViewInterface {
    UpdateMedicationPresenterInterface updateMedicationPresenterInterface;
Medication medication;
    FragmentUpdateMedicationBinding binding;
    public updateMedicationFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateMedicationPresenterInterface =new UpdateMedicationpresenter(getActivity(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())),this);
medication=new Medication();
setData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_medication, container, false);
    }

    @Override
    public void update() {
        updateMedicationPresenterInterface.update(medication);
    }
    public void setData(){
        Medication medication = new Medication("salma","salmoma","sat","33");
         binding.medName.setText(medication.getMedicine_Name().toString());
        binding.PresstoadjustTxt.setText(medication.getStrength().toString());
        binding.numberTxtone.setText(medication.getDrugAmount().toString());
        binding.selectdate.setText(medication.getStratingDate().toString());
       binding.isdaily.setChecked(medication.isDaily());
       binding.amountofdrug.setText(medication.getDrugAmount().toString());
       binding.selectAmountRefill.setText(medication.getLeftDrug().toString());
       binding.noOfdaysEdite.setText(medication.getNoOfDays().toString());
       binding.selectRefillTime.setText(medication.getRefilTime().toString());

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
binding.isReminderd.setChecked(medication.isRemindered());
binding.frequencyOfRepition.setSelection(medication.getFrequencyOfRepitionId());

    }
}