package com.example.medicationreminder.healthTakers.patientMedics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.databinding.MedicItemRvBinding;
import com.example.medicationreminder.databinding.MedicPatientBinding;
import com.example.medicationreminder.medications.view.MedicsOnClick;
import com.example.medicationreminder.medications.view.adapter.MedicsAdapter;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;

import java.util.List;


public class PatientMedicsAdapter extends RecyclerView.Adapter<PatientMedicsAdapter.PatientMedicsViewHolder> {
    Context context;
    List<Medication> patientMedics;
    MedicsOnClick onClick;
    MedicPatientBinding binding;
    Repository repository;//Illegal
    public PatientMedicsAdapter(Context context, List<Medication> medics, MedicsOnClick onClick,Repository repository) {
        this.context = context;
        this.patientMedics = medics;
        this.onClick = onClick;
        this.repository = repository;
    }
    public void setList(List<Medication> medics) {
        this.patientMedics = medics;
    }
    @NonNull
    @Override
    public PatientMedicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MedicPatientBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new PatientMedicsViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull PatientMedicsViewHolder holder, int position) {
        Medication model = patientMedics.get(position);
        binding.tvMedicNamePatient.setText(model.getMedicine_Name());
        binding.tvRefillPatient.setText(model.getLeftDrug() + "");
        binding.tvStrngthPatient.setText(model.getStrength() + "");
    }

    @Override
    public int getItemCount() {
        return patientMedics==null? 0:patientMedics.size();
    }

    //===============================================
    public class PatientMedicsViewHolder extends RecyclerView.ViewHolder {

        public PatientMedicsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MedicPatientBinding.bind(itemView);
        }
    }
}
