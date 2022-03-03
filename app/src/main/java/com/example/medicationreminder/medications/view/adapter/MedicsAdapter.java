package com.example.medicationreminder.medications.view.adapter;

import static com.example.medicationreminder.medications.view.MedicationsFragment.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medicationreminder.R;
import com.example.medicationreminder.databinding.MedicItemRvBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.medications.presenter.MedicationPresenter;
import com.example.medicationreminder.medications.presenter.MedicsPresenter;
import com.example.medicationreminder.medications.view.MedicsOnClick;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;

import java.util.List;

public class MedicsAdapter extends RecyclerView.Adapter<MedicsAdapter.MedicsTypeViewHolder> {
    Context context;
    List<Medication> medics;
    MedicsOnClick onClick;
    MedicItemRvBinding binding;
    MedicsPresenter medicationPresenter;
    Repository repository;//Illegal
    ConcereteLocalSource localSource;
    public MedicsAdapter(Context context, List<Medication> medics, MedicsOnClick onClick,MedicsPresenter medicationPresenter) {
        this.context = context;
        this.medics = medics;
        this.onClick = onClick;
        this.medicationPresenter=medicationPresenter;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MedicsTypeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Medication model = medics.get(position);


        Log.e(TAG, "onBindViewHolder:dddddddddddddd "+medicationPresenter.isReminder(model.getMedicine_Name()));

//        if(model.getStatus().equals("Active")){
//            binding.imgAlarm.setImageResource(R.drawable.reminder);
//        }else{
//            binding.imgAlarm.setImageResource(R.drawable.alarm);
//
//        }
        if (medicationPresenter.isReminder(model.getMedicine_Name())) {
            Log.e(TAG, "onBindViewHolder:zzzzzzzzzz "+medicationPresenter.isReminder(model.getMedicine_Name()));
            binding.imgAlarm.setImageResource(R.drawable.reme);
        } else {
            binding.imgAlarm.setImageResource(R.drawable.alarm);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.ItemOnClick(model,position);
            }
        });
        binding.tvMedicName.setText(model.getMedicine_Name());
        binding.imgMedic.setImageResource(model.getIcon());
        //binding.tvAdder.setText(model.getDrugAdder());
        binding.tvRefill.setText(model.getLeftDrug());
        binding.tvStrngth.setText(model.getStrength());
        if(model.getLeftDrug() != null){
            binding.tvRefill.setText(model.getLeftDrug()+" left");
        }
//        Glide.with(context).load(model.getIcon())
//                .placeholder(R.drawable.medicine)
//                .into(binding.imgMedic);

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