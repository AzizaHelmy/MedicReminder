package com.example.medicationreminder.medications.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.R;
import com.example.medicationreminder.databinding.MedicItemRvBinding;
import com.example.medicationreminder.medications.view.MedicsOnClick;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;

import java.util.List;

public class MedicsAdapter extends RecyclerView.Adapter<MedicsAdapter.MedicsTypeViewHolder> {
    Context context;
    List<Medication> medics;
    MedicsOnClick onClick;
    MedicItemRvBinding binding;
    Repository repository;//Illegal
    public MedicsAdapter(Context context, List<Medication> medics, MedicsOnClick onClick,Repository repository) {
        this.context = context;
        this.medics = medics;
        this.onClick = onClick;
        this.repository = repository;
    }
    public void setList(List<Medication> medics) {
        this.medics = medics;
    }
    @NonNull
    @Override
    public MedicsTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MedicItemRvBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MedicsTypeViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MedicsTypeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Medication model = medics.get(position);
        if (repository.isReminder(model.getMedicine_Name())) {
          binding.imgAlarm.setImageResource(R.drawable.ic_baseline_notifications_active_24);
        } else {
           binding.imgAlarm.setImageResource(R.drawable.ic_baseline_notifications_off_24);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.ItemOnClick(model);
            }
        });
      //  binding.tvMedicName.setText(model.getMedicine_Name());
        //binding.tvAdder.setText(model.getDrugAdder());
        binding.tvRefill.setText(model.getLeftDrug() + "");
       //binding.
        binding.tvStrngth.setText(model.getStrength() + "");
        //binding.imgMedic.setImageResource(model.getIcon());
        binding.imgAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.alarmOnClick(model,binding.imgAlarm);
            }
        });
    }

    @Override
    public int getItemCount() {

        return medics==null? 0:medics.size();
    }

    //===============================================
    public class MedicsTypeViewHolder extends RecyclerView.ViewHolder {


        public MedicsTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MedicItemRvBinding.bind(itemView);
        }
    }
}
