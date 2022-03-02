package com.example.medicationreminder.medications.view;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.R;


import java.util.ArrayList;
import java.util.List;

import com.example.medicationreminder.databinding.FragmentMedicationsBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.medications.presenter.MedicationPresenter;
import com.example.medicationreminder.medications.presenter.MedicsPresenter;
import com.example.medicationreminder.medications.view.adapter.MedicsAdapter;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;

public class MedicationsFragment extends Fragment implements MedicsOnClick, MedicsInterface {

    FragmentMedicationsBinding binding;
    MedicsAdapter adapterMedics;
    List<Medication> list;
    MedicsPresenter medicsPresenter;
    public static final String TAG = "TAG";

    public MedicationsFragment() {
        // Required empty public constructor
    }

    //========================================================
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMedicationsBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();

        medicsPresenter = new MedicationPresenter(getContext(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())), this);
        medicsPresenter.getMedics(getViewLifecycleOwner());
        setUpRecyclerView();
        return view;
    }

    //============================================================
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_medicationsFragment_to_addMedicationFragment);

            }
        });
    }

    //==========================================================
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

    //==================================================================
    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // layoutManager.setReverseLayout(true);
        binding.rvMedics.setLayoutManager(layoutManager);
        Log.e(TAG, "showMedics: " + list);
        adapterMedics = new MedicsAdapter(getContext(), list, this, medicsPresenter);
        binding.rvMedics.setAdapter(adapterMedics);
    }

    //========================================================================
    @Override
    public void ItemOnClick(Medication model) {

Medication medication=new Medication();

        Navigation.findNavController(getView()).navigate(R.id.action_medicationsFragment_to_displayMedicineFragment);
        adapterMedics.notifyDataSetChanged();
    }

    @Override
    public void alarmOnClick(Medication medic, ImageView view) {
        medicsPresenter.suspendReminder(medic);

    }

    //=========================================================================
    @Override
    public void showMedics(List<Medication> medications) {

        adapterMedics.setList(medications);
        cheackMedic(medications);
        adapterMedics.notifyDataSetChanged();
    }

    //=======================================================================
    private void cheackMedic(List<Medication> medications) {
        if (medications.isEmpty()) {
            binding.rvMedics.setVisibility(View.GONE);
            binding.emptyIv.setVisibility(View.VISIBLE);
        } else {
            binding.emptyIv.setVisibility(View.GONE);
            binding.rvMedics.setVisibility(View.VISIBLE);
        }
    }
}

