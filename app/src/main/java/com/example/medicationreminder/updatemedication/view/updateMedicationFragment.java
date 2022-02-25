package com.example.medicationreminder.updatemedication.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.R;
import com.example.medicationreminder.addmedication.presenter.AddMedicationPresenter;
import com.example.medicationreminder.addmedication.presenter.AddMedicationPresenterInterface;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;
import com.example.medicationreminder.updatemedication.presenter.UpdateMedicationPresenterInterface;
import com.example.medicationreminder.updatemedication.presenter.UpdateMedicationpresenter;

public class updateMedicationFragment extends Fragment implements UpdateMedicationViewInterface {
    UpdateMedicationPresenterInterface updateMedicationPresenterInterface;
Medication medication;

    public updateMedicationFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateMedicationPresenterInterface =new UpdateMedicationpresenter(getActivity(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())),this);
medication=new Medication();
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
}