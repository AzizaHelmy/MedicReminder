package com.example.medicationreminder.displayMedics.view;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.R;
import com.example.medicationreminder.addmedication.presenter.AddMedicationPresenter;
import com.example.medicationreminder.addmedication.presenter.AddMedicationPresenterInterface;
import com.example.medicationreminder.databinding.FragmentAddMedicationBinding;
import com.example.medicationreminder.databinding.FragmentDisplayMedicineBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.displayMedics.presenter.DisplayMedicationPresenter;
import com.example.medicationreminder.displayMedics.presenter.DisplayMedicationPresenterInterface;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;

import java.util.List;


public class displayMedicineFragment extends Fragment implements DisplayMedicationViewInterface {
    DisplayMedicationPresenterInterface displayMedicationPresenterInterface;
Medication medication;
FragmentDisplayMedicineBinding binding;
    public displayMedicineFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//       displayMedicationPresenterInterface =new DisplayMedicationPresenter(getActivity(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())),this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDisplayMedicineBinding.inflate(getLayoutInflater(), container, false);
        displayMedicationPresenterInterface =new DisplayMedicationPresenter(getActivity(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())),this);

        View view = binding.getRoot();
        displayData();
        return view;


    }

    @Override
    public LiveData<List<Medication>> displayDrug() {
        return displayMedicationPresenterInterface.displayDrug(displayMedicineFragment.this);
    }

  public void displayData(){

     Medication medication=new Medication("sb","19/2/22","55","33");
      Log.e(TAG, "displayData:asdfghjka "+medication.getMedicine_Name());
     binding.medicineTxt.setText(medication.getMedicine_Name());
      Log.i("display",""+medication.getMedicine_Name());
      binding.duration.setText(medication.getDuration());
      Log.i("display",""+medication.getDuration());
      binding.timedate.setText(medication.getStratingDate());
      Log.i("display",""+medication.getStratingDate());
     binding.refilltime.setText(medication.getRefilTime());
      Log.i("display","salma"+medication.getStratingDate());
      binding.medicineIcon.setId(medication.getIcon());
      binding.drugAmount.setText(medication.getDrugAmount());
      binding.leftdrug.setText(medication.getLeftDrug());

  }
}